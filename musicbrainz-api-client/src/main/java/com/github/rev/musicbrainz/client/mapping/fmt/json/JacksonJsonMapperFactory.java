package com.github.rev.musicbrainz.client.mapping.fmt.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.rev.musicbrainz.client.mapping.fmt.json.serdes.MbJsonSerdesModule;
import com.github.rev.musicbrainz.client.mapping.fmt.xml.serdes.MbXmlSerdesModule;
import com.github.rev.musicbrainz.client.mapping.fmt.shared.handler.IgnoringProblemHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.TimeZone;

/**
 * Factory class for constructing JSON mappers.
 */
public final class JacksonJsonMapperFactory {

    private static final Set<String> FIELDS_TO_IGNORE_IN_PARSING = Set.of(
            "score",
            "text",
            "track"
    );

    private JacksonJsonMapperFactory() {
    }

    /**
     * @return A JsonMapper instance used for parsing MusicBrainz entities in JSON format.
     */
    public static JsonMapper jsonMapper() {
        JsonMapper mapper = JsonMapper.builder()
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                .build();

        configureMapper(mapper);

        mapper.registerModule(new MbJsonSerdesModule());

        return mapper;
    }

    private static void configureMapper(final ObjectMapper mapper) {
        mapper.setDateFormat(createDateFormat());
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.registerModule(new MbXmlSerdesModule());

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        mapper.addHandler(new IgnoringProblemHandler(FIELDS_TO_IGNORE_IN_PARSING));
    }

    private static DateFormat createDateFormat() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        return df;
    }

}
