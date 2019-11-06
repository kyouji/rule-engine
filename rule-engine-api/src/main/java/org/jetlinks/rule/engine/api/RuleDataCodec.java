package org.jetlinks.rule.engine.api;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.Optional;

public interface RuleDataCodec<T> {

    Object encode(T data, Feature... features);

    Flux<T> decode(RuleData data, Feature... features);

    interface Feature {
        String getId();

        String getName();

        default boolean has(Feature... features) {
            return Arrays.stream(features)
                    .anyMatch(feature -> feature.getId().equals(this.getId()));
        }

        static <T extends Feature> Optional<T> find(Class<T> type,Feature... features){
            return Arrays.stream(features)
                    .filter(type::isInstance)
                    .map(type::cast)
                    .findFirst();
        }

    }
}
