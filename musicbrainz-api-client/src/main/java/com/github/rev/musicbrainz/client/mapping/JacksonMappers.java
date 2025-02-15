package com.github.rev.musicbrainz.client.mapping;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.rev.musicbrainz.client.mapping.serdes.MbSerdesModule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public final class JacksonMappers {

    private JacksonMappers() {
    }

    /**
     * @return A JsonMapper instance used for parsing MusicBrainz entities in JSON format.
     */
    public static JsonMapper jsonMapper() {
        JsonMapper mapper = JsonMapper.builder()
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                .build();

        configureMapper(mapper);

        mapper.setPropertyNamingStrategy(new JsonNameStrategy());

        return mapper;
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

        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE);

        JacksonXmlModule jacksonXmlModule = new JacksonXmlModule();
        jacksonXmlModule.setDefaultUseWrapper(false);
        mapper.registerModule(jacksonXmlModule);

        return mapper;
    }

    private static void configureMapper(final ObjectMapper mapper) {
        mapper.setDateFormat(createDateFormat());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
//        mapper.configure(StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION, true);
        mapper.registerModule(new MbSerdesModule());
    }

    private static DateFormat createDateFormat() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        return df;
    }

    public static final class JsonNameStrategy extends PropertyNamingStrategies.KebabCaseStrategy {

        private static final Map<String, String> PLURAL_NAMES_MAP = getPluralNamesMap();

        @Override
        public String translate(final String propertyName) {
            return super.translate(PLURAL_NAMES_MAP.getOrDefault(propertyName, propertyName));
        }

        private static Map<String, String> getPluralNamesMap() {
            Map<String, String> pluralNamesMap = new HashMap<>();
            pluralNamesMap.put("annotationList", "annotations");
            pluralNamesMap.put("areaList", "areas");
            pluralNamesMap.put("artistList", "artists");
            pluralNamesMap.put("stubList", "stubs");
            pluralNamesMap.put("eventList", "events");
            pluralNamesMap.put("genreList", "genres");
            pluralNamesMap.put("instrumentList", "instruments");
            pluralNamesMap.put("labelList", "labels");
            pluralNamesMap.put("placeList", "places");
            pluralNamesMap.put("recordingList", "recordings");
            pluralNamesMap.put("releaseList", "releases");
            pluralNamesMap.put("release-groupList", "releaseGroups");
            pluralNamesMap.put("seriesList", "series");
            pluralNamesMap.put("tagList", "tags");
            pluralNamesMap.put("workList", "works");
            pluralNamesMap.put("urlList", "urls");
            pluralNamesMap.put("aliasList", "aliases");
            return pluralNamesMap;
        }
    }
}
