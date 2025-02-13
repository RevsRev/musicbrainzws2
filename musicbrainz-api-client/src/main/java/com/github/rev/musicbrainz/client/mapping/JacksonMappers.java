package com.github.rev.musicbrainz.client.mapping;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.rev.musicbrainz.client.mapping.serdes.MbSerdesModule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public final class JacksonMappers {

    private JacksonMappers() {
    }

    /**
     * @return A JsonMapper instance used for parsing MusicBrainz entities in JSON format.
     */
    public static JsonMapper jsonMapper() {
        JsonMapper mapper = new JsonMapper();

        configureMapper(mapper);

        return mapper;
    }

    /**
     * @return An XmlMapper instance used for parsing MusicBrainz entities in XML format.
     */
    public static XmlMapper xmlMapper() {
        XmlMapper mapper = XmlMapper.builder()
                .defaultUseWrapper(false)
                .build();
        configureMapper(mapper);

        JacksonXmlModule jacksonXmlModule = new JacksonXmlModule();
        jacksonXmlModule.setDefaultUseWrapper(false);
        mapper.registerModule(jacksonXmlModule);

        return mapper;
    }

    private static void configureMapper(final ObjectMapper mapper) {
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE);
        mapper.setDateFormat(createDateFormat());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        mapper.registerModule(new MbSerdesModule());
    }

    private static DateFormat createDateFormat() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        return df;
    }
}
