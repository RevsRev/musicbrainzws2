package com.github.rev.musicbrainz.client.mapping.serdes.handler;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.ValueInstantiators;

/**
 * A (somewhat naive) attempt to resolve a few jackson issues with instantiating certain classes during parsing.
 */
public final class MbValueInstantiators extends ValueInstantiators.Base {

    @Override
    public ValueInstantiator findValueInstantiator(final DeserializationConfig config,
                                                   final BeanDescription beanDesc,
                                                   final ValueInstantiator defaultInstantiator) {
        if (MbValueInstantiator.HANDLED_TYPES.contains(beanDesc.getBeanClass())) {
            return new MbValueInstantiator(config, beanDesc.getClassInfo().getRawType());
        }
        return super.findValueInstantiator(config, beanDesc, defaultInstantiator);
    }
}
