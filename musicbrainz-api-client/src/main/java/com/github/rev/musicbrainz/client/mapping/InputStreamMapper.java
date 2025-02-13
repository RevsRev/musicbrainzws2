package com.github.rev.musicbrainz.client.mapping;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.rev.musicbrainz.client.MbFormat;
import lombok.Getter;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class to handle reading input streams into a POJO.
 * @param <T> The type that the InputStreamMapper maps to.
 */
@Getter
public abstract class InputStreamMapper<T> {

    private final Class<T> clazz;

    private InputStreamMapper(final Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * Method to read the input stream and parse to a new instance of T.
     * @param is The InputStream to be read from.
     * @return A new instance of T
     * @throws IOException whenever the input stream cannot be parsed to a new instance of T.
     */
    public abstract T parse(InputStream is) throws IOException;

    /**
     * Factory method for constructing a new InputStreamMapper for a given MusicBrainz result type and response format.
     * @param clazz The type to map the InputStream to.
     * @param format The expected format of the InputStream being read.
     * @return A new instance of T.
     * @param <T> The type that the InputStreamMapper maps to.
     */
    public static <T> InputStreamMapper<T> factory(final Class<T> clazz, final MbFormat format) {
        switch (format) {
            case XML -> {
                return new XmlInputStreamMapper<>(clazz);
            }
            case JSON -> {
                return new JsonInputStreamMapper<>(clazz);
            }
            default -> {
                return null;
            }
        }
    }

    private static final class XmlInputStreamMapper<T> extends InputStreamMapper<T> {

        private final XMLInputFactory inputFactory = XMLInputFactory.newFactory();
        private final XmlMapper xmlMapper = JacksonMappers.xmlMapper();

        private XmlInputStreamMapper(final Class<T> clazz) {
            super(clazz);
        }

        @Override
        public T parse(final InputStream is) throws IOException {
            try {
                XMLStreamReader xmlStreamReader = inputFactory.createXMLStreamReader(is);
                return xmlMapper.readValue(xmlStreamReader, getClazz());
            } catch (XMLStreamException e) {
                //TODO - Maybe wrap in a custom exception?
                throw new IOException(e);
            }
        }
    }

    private static final class JsonInputStreamMapper<T> extends InputStreamMapper<T> {

        private final JsonMapper jsonMapper = JacksonMappers.jsonMapper();

        private JsonInputStreamMapper(final Class<T> clazz) {
            super(clazz);
        }

        @Override
        public T parse(final InputStream is) throws IOException {
            return jsonMapper.readValue(is, getClazz());
        }
    }
}
