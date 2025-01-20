package github.com.rev.musicbrainz.webservice.impl;

import github.com.rev.musicbrainz.webservice.DefaultWebServiceWs2;
import github.com.rev.musicbrainz.webservice.WebServiceException;
import github.com.rev.musicbrainz.wsxml.MbXMLException;
import github.com.rev.musicbrainz.wsxml.element.Metadata;
import org.apache.hc.client5.http.HttpRequestRetryStrategy;
import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.CredentialsProvider;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.NoHttpResponseException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicClassicHttpRequest;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.net.Host;
import org.apache.hc.core5.util.TimeValue;

import javax.net.ssl.SSLHandshakeException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A simple http client using Apache Commons HttpClient.
 * 
 */
public class HttpClientWebServiceWs2 extends DefaultWebServiceWs2 
{
    /**
    * A logger
    */
    static Logger log = Logger.getLogger(HttpClientWebServiceWs2.class.getName());

    /**
     * A {@link HttpClient} instance
     */
    private HttpClient httpClient;
	
    /**
     * Default constructor creates a httpClient with default properties. 
     */
    public HttpClientWebServiceWs2() 
    {
            this.httpClient = HttpClientBuilder.create()
                    .setRetryStrategy(getRetryStrategy())
                    .setDefaultCredentialsProvider(getCredentialsProvider())
                    .setUserAgent(USERAGENT)
                    .setConnectionManager(getConnectionManager())
                    .build();
    }

    /**
     * Use this constructor to inject a configured {@link HttpClient}.
     * 
     * @param httpClient A configured {@link HttpClient}.
     */
    public HttpClientWebServiceWs2(HttpClient httpClient)
    {
            this.httpClient = httpClient;
    }

    private CredentialsProvider getCredentialsProvider() {
        if (getUsername() == null || getUsername().isEmpty()) {
            return null;
        }

        BasicCredentialsProvider provider = new BasicCredentialsProvider();
        AuthScope authScope = new AuthScope(null, getHost(), -1, null, null);


        UsernamePasswordCredentials creds =
                new UsernamePasswordCredentials(getUsername(), getPassword().toCharArray());
        provider.setCredentials(authScope, creds);
        return provider;
    }

    private HttpClientConnectionManager getConnectionManager() {
        return PoolingHttpClientConnectionManagerBuilder.create().setDefaultConnectionConfig(getConnectionConfig()).build();
    }

    private ConnectionConfig getConnectionConfig() {
        ConnectionConfig.Builder cfg = ConnectionConfig.custom();
        cfg.setConnectTimeout(60000, TimeUnit.MILLISECONDS);
        cfg.setSocketTimeout(60000, TimeUnit.MILLISECONDS);
        return cfg.build();
    }


    private void setConnectionParam(){
        // TODO - Do we need to port this over as well?
//        connectionParams.setParameter("http.protocol.content-charset", "UTF-8");
    }

    private HttpRequestRetryStrategy getRetryStrategy() {
        return new HttpRequestRetryStrategy() {
            @Override
            public boolean retryRequest(HttpRequest request, IOException exception, int execCount,
                                        HttpContext context) {
                if (execCount >= 3) {
                    // Do not retry if over max retry count
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {
                    // Retry if the server dropped connection on us
                    return true;
                }
                if (exception instanceof SSLHandshakeException) {
                    // Do not retry on SSL handshake exception
                    return false;
                }
                boolean idempotent = !(request instanceof HttpUriRequestBase);
                if (idempotent) {
                    // Retry if the request is considered idempotent
                    return true;
                }
                return false;
            }

            @Override
            public boolean retryRequest(HttpResponse response, int execCount,
                                        HttpContext context) {
                return switch (response.getCode()) {
                    case HttpStatus.SC_SERVICE_UNAVAILABLE, HttpStatus.SC_BAD_GATEWAY -> execCount < 5;
                    default -> false;
                };
            }

            @Override
            public TimeValue getRetryInterval(HttpResponse response, int execCount,
                                              HttpContext context) {
                return TimeValue.ofSeconds(1);
            }
        };
    }

    @Override
    protected Metadata doGet(String url) throws WebServiceException, MbXMLException
    {
        HttpGet method = new HttpGet(url);
        Metadata md = executeMethod(method);
        if (md == null)
        {
            String em = "ABORTED: web service returned an error";
            log.severe(em);
            throw new WebServiceException(em);
        }
        return md;
    }

    /* (non-Javadoc)
     * @see github.com.rev.musicbrainz.webservice.DefaultWebServiceWs1#doGet(java.lang.String, java.io.InputStream)
     */
    @Override
    protected Metadata doPost(String url, Metadata md) throws MbXMLException {
        HttpPost method = new HttpPost(url);

        StringEntity httpentity = new StringEntity(getWriter().getXmlString(md), ContentType.APPLICATION_XML);

        method.setEntity(httpentity);
        return executeMethod(method);

    }
    @Override
    protected Metadata doPut(String url) {
        HttpPut method = new HttpPut(url);
        return executeMethod(method);
    }

    @Override
    protected Metadata doDelete(String url) {
        HttpDelete method = new HttpDelete(url);
        return executeMethod(method);
    }

    private Metadata executeMethod(HttpUriRequest method) {
        try {
            BasicClassicHttpRequest request = new BasicClassicHttpRequest(method.getMethod(), method.getUri());
            return httpClient.execute(request, getResponseHandler());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpClientResponseHandler<Metadata> getResponseHandler() {
        return response ->
        {
            switch (response.getCode()) {
                case HttpStatus.SC_OK:
                    InputStream instream = response.getEntity().getContent();
                    try {
                        Metadata mtd = getParser().parse(instream);
                        return mtd;
                    } catch (Exception ignore) {
                    } finally {
                        //Closing the input stream will trigger connection release
                        instream.close();
                    }
                case HttpStatus.SC_SERVICE_UNAVAILABLE: {
                    // Maybe the server is too busy, let's try again.
                    log.warning(buildMessage(response, "Service unavaillable"));
                }
                case HttpStatus.SC_BAD_GATEWAY: {
                    // Maybe the server is too busy, let's try again.
                    log.warning(buildMessage(response, "Bad Gateway"));
                }
                case HttpStatus.SC_NOT_FOUND:
                    log.warning(buildMessage(response, "Not found"));
                case HttpStatus.SC_BAD_REQUEST:
                    log.warning(buildMessage(response, "Bad Request"));
                case HttpStatus.SC_FORBIDDEN:
                    log.warning(buildMessage(response, "Forbidden"));
                case HttpStatus.SC_UNAUTHORIZED:
                    log.warning(buildMessage(response, "Unauthorized"));
                    // This is the actual response code for invalid username o password
                case HttpStatus.SC_INTERNAL_SERVER_ERROR: {
                    log.warning(buildMessage(response, "Internal server error"));
                }
                default: {
                    String em = buildMessage(response, "" + response.getCode());
                    log.severe("Fatal web service error: " + em);
                    throw new RuntimeException(new WebServiceException(em));
                }
            }
        };
    }

    private String buildMessage(ClassicHttpResponse response, String status){
        String msg="";
        InputStream instream;
        int statusCode = response.getCode();
        String reasonPhrase = response.getReasonPhrase();
        
        if (reasonPhrase==null || reasonPhrase.isEmpty())
            reasonPhrase=status;

        msg = "Server response was: "+statusCode+" "+reasonPhrase;

        try {instream = response.getEntity().getContent();
              Metadata mtd;
            try {
                mtd = getParser().parse(instream);
                msg = msg+" MESSAGE: "+mtd.getMessage();
                instream.close();
            } catch (MbXMLException ex) {
                Logger.getLogger(HttpClientWebServiceWs2.class.getName()).log(
                        Level.SEVERE, convertInputStreamToString(instream), ex);
            }
        } 
            catch (IOException ignore) {}
            catch (IllegalStateException ignore) {}
            
        finally {
            try {
                EntityUtils.consume(response.getEntity());} catch (IOException ex) {}
        }
        return msg;
    }
    
    private static long lastHitTime=0;
    private static void wait (int seconds){
        long t1;
        if (lastHitTime >0)
        {
            do{ t1=System.currentTimeMillis();
            } while (t1-lastHitTime<seconds*1000);
        }
    }
    /** 
      * method to convert an InputStream to a string using the BufferedReader.readLine() method 
      * this methods reads the InputStream line by line until the null line is encountered 
      * it appends each line to a StringBuilder object for optimal performance 
      * @param is 
      * @return 
      * @throws IOException 
      */  
     public static String convertInputStreamToString(InputStream inputStream) throws IOException  
     {  
         if (inputStream != null)  
         {  
             StringBuilder stringBuilder = new StringBuilder();  
             String line;  
   
             try {  
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));  
                 while ((line = reader.readLine()) != null)  
                 {  
                    stringBuilder.append(line).append("\n");  
                }  
             }  
             finally  
             {  
                 inputStream.close();  
             }  
   
             return stringBuilder.toString();  
         }  
         else  
         {  
            return null;  
         }  
     }  
}
