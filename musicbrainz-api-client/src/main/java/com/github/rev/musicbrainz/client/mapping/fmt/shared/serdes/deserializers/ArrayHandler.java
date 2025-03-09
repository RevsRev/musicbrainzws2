package com.github.rev.musicbrainz.client.mapping.fmt.shared.serdes.deserializers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.rev.musicbrainz.client.mapping.fmt.shared.serdes.MbSerdesException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Sometimes MusicBrainz decided to return e.g. a "RelationList" entity as just an array of the nested Relations.
 * This handler deals with that case.
 *
 * @param <T> The class this handler acts on.
 * @param <R> The music brainz list class
 */
public final class ArrayHandler<T, R> extends BadKeyGroupHandler<T> {

    private final String listKeyName;
    private final String targetName;
    private final Method listSetter;
    private final Class<R> listClazz;

    private ArrayHandler(final Class<T> clazz,
                         final String listKeyName,
                         final String targetName,
                         final Method listSetter,
                         final Class<R> listElementClazz) {
        super(clazz, listKeyName);
        this.listKeyName = listKeyName;
        this.targetName = targetName;
        this.listSetter = listSetter;
        this.listClazz = listElementClazz;
    }

    @Override
    protected void acceptImpl(final T t,
                              final JsonNode jsonNode,
                              final ObjectMapper mapper) throws MbSerdesException {
        try {
            JsonNode node = jsonNode.get(listKeyName);

            if (node == null) {
                return;
            }

            if (node instanceof ObjectNode) {
                convertObjectNode(t, (ObjectNode) node, mapper);
                return;
            }

            convertObjectNode(t, new ObjectNode(JsonNodeFactory.instance, Map.of(targetName, node)), mapper);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new MbSerdesException(e);
        }
    }

    private void convertObjectNode(final T t,
                                   final ObjectNode jsonNode,
                                   final ObjectMapper mapper)
            throws IllegalAccessException, InvocationTargetException {
        R listInstance = mapper.convertValue(jsonNode, listClazz);
        listSetter.invoke(t, listInstance);
    }

    /**
     * Factory method.
     * @param clazz The music brains "List" class
     * @param listKeyName The key of the sub-contained list of elements in the enclosing "List" class
     * @param targetName The field name of the nested list elements in the enclosing "List" class
     * @param listGetter Corresponding getter on the POJO.
     * @param listElementClazz The type contained within the list.
     * @return A new ListHandler.
     * @param <T> The MusicBrainz "List" class, e.g. AliasList.
     * @param <R> The type of elements contained within the list class, e.g. "Alias".
     */
    @SuppressWarnings("checkstyle.LineLength")
    public static <T, R> ArrayHandler<T, R> factory(final Class<T> clazz,
                                                    final String listKeyName,
                                                    final String targetName,
                                                    final String listGetter,
                                                    final Class<R> listElementClazz) {
        Method setter = getSetter(clazz, listGetter, listElementClazz);
        return new ArrayHandler<>(clazz, listKeyName, targetName, setter, listElementClazz);
    }

    private static <T, R> Method getSetter(final Class<T> parentClazz,
                                           final String setterName,
                                           final Class<R> listElementClazz) {
        try {
            return parentClazz.getMethod(setterName, listElementClazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
