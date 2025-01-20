/**
 * Copyright (C) ${license.git.copyrightYears} Last.fm
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package github.com.rev.fm.last.musicbrainz.coverart.impl;

import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FetchJsonListingResponseHandlerTest {

  @Mock
  private ClassicHttpResponse response;

  @Mock
  private HttpEntity entity;

  private InputStream inputStream;

  private final HttpClientResponseHandler<String> handler = FetchJsonListingResponseHandler.INSTANCE;

  @Before
  public void initialise() throws IllegalStateException, IOException {
    inputStream = new ByteArrayInputStream("JSON".getBytes(StandardCharsets.UTF_8));
    when(entity.getContent()).thenReturn(inputStream);
    when(response.getCode()).thenReturn(HttpStatus.SC_OK);
  }

  @Test
  public void jsonIsReturnedWhenReleaseExists() throws HttpException, IOException {
    when(response.getEntity()).thenReturn(entity);
    String json = handler.handleResponse(response);
    assertThat(json, is("JSON"));
  }

  @Test
  public void nullIsReturnedWhenNoReleaseExists() throws IOException, HttpException {
    when(response.getCode()).thenReturn(HttpStatus.SC_NOT_FOUND);
    when(response.getEntity()).thenReturn(null);
    String json = handler.handleResponse(response);
    assertThat(json, is(nullValue()));
  }

  @Test(expected = IOException.class)
  public void ioExceptionIsThrownWhenRequestCannotBeFulfilled() throws IOException, HttpException {
    when(response.getCode()).thenReturn(HttpStatus.SC_METHOD_NOT_ALLOWED);
    String json = handler.handleResponse(response);
  }
}
