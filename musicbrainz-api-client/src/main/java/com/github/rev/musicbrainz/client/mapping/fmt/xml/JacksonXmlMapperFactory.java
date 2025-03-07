package com.github.rev.musicbrainz.client.mapping.fmt.xml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.rev.musicbrainz.client.mapping.fmt.xml.serdes.MbXmlSerdesModule;
import com.github.rev.musicbrainz.client.mapping.fmt.shared.handler.IgnoringProblemHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

public final class JacksonXmlMapperFactory {

    private static final Set<String> FIELDS_TO_IGNORE_IN_PARSING = Set.of(
            "score",
            "text",
            "track"
    );

    private JacksonXmlMapperFactory() {
    }

    /**
     * @return An XmlMapper instance used for parsing MusicBrainz entities in XML format.
     */
    public static XmlMapper xmlMapper() {
        XmlMapper mapper = XmlMapper.builder()
                .defaultUseWrapper(false)
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                .build();
        configureMapper(mapper);

        mapper.setPropertyNamingStrategy(new XmlNameStrategy());

        JacksonXmlModule jacksonXmlModule = new JacksonXmlModule();
        jacksonXmlModule.setDefaultUseWrapper(false);
        mapper.registerModule(jacksonXmlModule);

        // TODO - I want to use this, but a bug mixing @XmlValue with fields named "value"
//        JakartaXmlBindAnnotationModule jakartaXmlModule = new JakartaXmlBindAnnotationModule();
//        mapper.registerModule(jakartaXmlModule);

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

    public static final class XmlNameStrategy extends PropertyNamingStrategies.KebabCaseStrategy {

        private static final Map<String, String> OVERRIDEN_NAMES_MAP = overridenNamesMap();

        @Override
        public String translate(final String propertyName) {
            return super.translate(OVERRIDEN_NAMES_MAP.getOrDefault(propertyName, propertyName));
        }

        private static Map<String, String> overridenNamesMap() {
            Map<String, String> overridenNamesMap = new HashMap<>();
            overridenNamesMap.put("iso31661CodeList", "iso-3166-1-code-list");
            overridenNamesMap.put("iso31661Code", "iso-3166-1-code");
            return overridenNamesMap;
        }
    }

}
