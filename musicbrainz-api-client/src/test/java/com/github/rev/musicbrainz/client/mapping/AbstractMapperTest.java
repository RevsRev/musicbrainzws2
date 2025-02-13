package com.github.rev.musicbrainz.client.mapping;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.rev.musicbrainz.client.MbFormat;
import com.github.rev.musicbrainz.client.artist.MbArtistResult;
import com.github.rev.musicbrainz.client.mapping.xml.MbXmlSerdesModule;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.recursive.comparison.ComparisonDifference;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonDifferenceCalculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.function.BiPredicate;

public class AbstractMapperTest {

    private final ObjectMapper jsonMapper = new ObjectMapper(
            JsonFactory.builder().configure(JsonWriteFeature.WRITE_NUMBERS_AS_STRINGS, true).build())
            .setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .setDateFormat(createDateFormat())
            .configure(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED, true)
            .registerModule(new MbXmlSerdesModule());
    private final ObjectMapper xmlMapper = new XmlMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .setDateFormat(createDateFormat())
            .configure(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS, true)
            .registerModule(new MbXmlSerdesModule());

    protected JsonNode loadJson(final String resourcePath, boolean fromXml) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath);
        ObjectMapper mapper = fromXml ? xmlMapper : jsonMapper;
        try {
            return mapper.readTree(is);
        } catch (IOException e) {
            Assertions.fail(e);
            return null;
        }
    }

    @ParameterizedTest
    @MethodSource("getMapperTestParams")
    public <T> void testMappers(final MapperTestParams<T> testParams) throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(testParams.resourceName);
        T actualParsed = testParams.mapper.parse(is);

        JsonNode expected = loadJson(testParams.resourceName, testParams.format == MbFormat.XML);
        JsonNode actualJsonMaybeDifferentTypes = jsonMapper.convertValue(actualParsed, JsonNode.class);
        JsonNode actual = jsonMapper.readTree(jsonMapper.writeValueAsString(actualJsonMaybeDifferentTypes));

        String[] ignoreFields = {
                "score",
                ""
        };

        Assertions.assertThat(actual)
                .usingRecursiveComparison(getRecursiveComparisonConfiguration(ignoreFields))
                .isEqualTo(expected);
    }

    private RecursiveComparisonConfiguration getRecursiveComparisonConfiguration(final String[] ignoreFields) {
        RecursiveComparisonConfiguration config = new RecursiveComparisonConfiguration();
        config.ignoreCollectionOrder(true);
        config.ignoreFields(ignoreFields);
        config.registerEqualsForFields(getChildrenPredicate(ignoreFields), "_children");
        return config;
    }

    private BiPredicate<?, ?> getChildrenPredicate(final String[] fieldNamesToIgnore) {
        return new BiPredicate<>() {
            @Override
            public boolean test(Object o1, Object o2) {
                if (o2 == null) {
                    o2 = o1;
                    o1 = null;
                }

                if (o1 == null) {
                    if (o2 == null) {
                        return true;
                    }
                    if (o2 instanceof Map<?, ?>) {
                        return ((Map<?, ?>) o2).isEmpty();
                    }
                    return false;
                }

                if (o1 instanceof Map<?, ?> && o2 instanceof Map<?, ?>) {
                    return mapsEqual((Map<?, ?>) o1, (Map<?, ?>) o2, fieldNamesToIgnore);
                }
                return false;
            }

            private boolean mapsEqual(final Map<?, ?> actual, final Map<?, ?> expected,
                                      final String[] fieldNamesToIgnore) {
                if (actual.isEmpty() && expected.isEmpty()) {
                    return true;
                }

                Map<?, ?> o1cpy = new HashMap<>(Map.copyOf(actual));
                Map<?, ?> o2cpy = new HashMap<>(Map.copyOf(expected));

                for (String s : fieldNamesToIgnore) {
                    o1cpy.remove(s);
                    o2cpy.remove(s);
                }

                stripNullOrEmptyValues(o1cpy);
                stripNullOrEmptyValues(o2cpy);

                if (!o1cpy.keySet().equals(o2cpy.keySet())) {
                    logDifferences(actual, expected);
                    return false;
                }

                RecursiveComparisonConfiguration configuration =
                        getRecursiveComparisonConfiguration(fieldNamesToIgnore);
                RecursiveComparisonDifferenceCalculator differenceCalculator =
                        new RecursiveComparisonDifferenceCalculator();
                for (Map.Entry<?, ?> entry : o1cpy.entrySet()) {
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    Object otherValue = o2cpy.get(key);

                    List<ComparisonDifference> comparisonDifferences =
                            differenceCalculator.determineDifferences(value, otherValue, configuration);
                    if (!comparisonDifferences.isEmpty()) {
                        if (shouldLogDifference(o1cpy, o2cpy)) {
                            System.out.println("Equality failed due to unequal values in maps: ");
                            System.out.println("Actual:   " + value);
                            System.out.println("Expected: " + otherValue);
                        }
                        return false;
                    }
                }
                return true;
            }

            private static void logDifferences(Map<?, ?> actual, Map<?, ?> expected) {
                if (!shouldLogDifference(actual, expected)) {
                    return;
                }

                System.out.println("************************************");
                System.out.println("Equality failed due to unequal maps");
                System.out.println("Expected: " + Arrays.toString(expected.entrySet().toArray()));
                System.out.println("Actual: " + Arrays.toString(actual.entrySet().toArray()));
                System.out.println();
                System.out.println("Entries in actual that are not in expected:");
                for (Map.Entry<?, ?> entry : actual.entrySet()) {
                    if (!expected.containsKey(entry.getKey())) {
                        System.out.println(entry.getKey() + " -> " + entry.getValue());
                    }
                }
                System.out.println("Entries in expected that are not in actual:");
                for (Map.Entry<?, ?> entry : expected.entrySet()) {
                    if (!actual.containsKey(entry.getKey())) {
                        System.out.println(entry.getKey() + " -> " + entry.getValue());
                    }
                }
                System.out.println();
            }

            private static boolean shouldLogDifference(Map<?, ?> actual, Map<?, ?> expected) {
                Object actualId = actual.get("id");
                Object expectedId = expected.get("id");
                return actualId != null && actualId.equals(expectedId);
            }

            private static void stripNullOrEmptyValues(Map<?, ?> o1cpy) {
                Iterator<? extends Map.Entry<?, ?>> it = o1cpy.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<?, ?> next = it.next();
                    Object value = next.getValue();
                    if (value == null
                            || (value instanceof Collection<?> && ((Collection<?>) value).isEmpty())
                            || (value instanceof Map<?, ?> && ((Map<?, ?>) value).isEmpty())
                            || (value instanceof ContainerNode<?> && ((ContainerNode<?>) value).isEmpty())) {
                        it.remove();
                    }
                }
            }
        };
    }

    private static DateFormat createDateFormat() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        return df;
    }

    public static List<MapperTestParams<?>> getMapperTestParams() {
        List<MapperTestParams<?>> testParams = new ArrayList<>();
        testParams.add(MapperTestParams.factory(MbArtistResult.class, MbFormat.XML, "artist_result.xml"));
        return testParams;
    }


    public static class MapperTestParams<T> {
        private final InputStreamMapper<T> mapper;
        private final String resourceName;
        private final MbFormat format;

        private MapperTestParams(final InputStreamMapper<T> mapper,
                                 final String resourceName, MbFormat format) {
            this.mapper = mapper;
            this.resourceName = resourceName;
            this.format = format;
        }

        private static <T> MapperTestParams<T> factory(final Class<T> clazz,
                                                       final MbFormat format,
                                                       final String resource) {
            InputStreamMapper<T> mapper = InputStreamMapper.factory(clazz, MbFormat.XML);
            return new MapperTestParams<>(mapper, "example_data/" + resource, format);
        }

    }

}
