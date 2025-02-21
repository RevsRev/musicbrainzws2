package com.github.rev.musicbrainz.client.mapping.serdes.handler;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdValueInstantiator;
import org.musicbrainz.ns.mmd_2.Relation;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * A (somewhat naive) attempt to resolve a few jackson issues with instantiating certain classes during parsing.
 */
public final class MbValueInstantiator extends StdValueInstantiator {

    /**
     * Types that can be handled by this instantiator.
     */
    public static final Set<Class<?>> HANDLED_TYPES = new HashSet<>();
    static {
        HANDLED_TYPES.add(Relation.AttributeList.Attribute.class);
    }

    /**
     * @param config
     * @param valueType
     */
    public MbValueInstantiator(final DeserializationConfig config,
                               final Class<?> valueType) {
        super(config, valueType);
    }

    /**
     * @param config
     * @param valueType
     */
    public MbValueInstantiator(final DeserializationConfig config,
                               final JavaType valueType) {
        super(config, valueType);
    }

    /**
     * @param src
     */
    protected MbValueInstantiator(final StdValueInstantiator src) {
        super(src);
    }

    @Override
    public String getValueTypeDesc() {
        return "MbValueInstantiator: " + super.getValueTypeDesc();
    }

    @Override
    public boolean canCreateFromObjectWith() {
        if (HANDLED_TYPES.contains(getValueClass())) {
            return true;
        }
        return super.canCreateFromObjectWith();
    }

    @Override
    public boolean canCreateUsingDefault() {
        if (HANDLED_TYPES.contains(getValueClass())) {
            return true;
        }
        return super.canCreateFromObjectWith();
    }

    @Override
    public Object createUsingDefault(final DeserializationContext ctxt) throws IOException {
        try {
            Constructor<?> constructor = getValueClass().getConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException | InvocationTargetException
                 | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object createFromString(final DeserializationContext ctxt,
                                   final String value) throws IOException {
        try {
            Constructor<?> constructor = getValueClass().getConstructor();
            Method setter = getValueClass().getMethod("setContent", String.class);
            Object obj = constructor.newInstance();
            setter.invoke(obj, value);
            return obj;
        } catch (NoSuchMethodException | InvocationTargetException
                 | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
