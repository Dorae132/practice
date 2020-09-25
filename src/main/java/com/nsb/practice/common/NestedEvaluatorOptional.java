package com.nsb.practice.common;

import java.util.Optional;

/**
 * @desc same as {@link NestedEvaluator}, but return optional
 * @author Dorae
 * @date 2020/9/25 20:24
 **/
@FunctionalInterface
public interface NestedEvaluatorOptional<T, R> {

    Optional<R> apply(T t);

    default <V> NestedEvaluatorOptional<T, V> andThen(NestedEvaluatorOptional<R, V> after) {
        return (T p) -> {
            Optional<R> tmp = apply(p);
            if (!tmp.isPresent()) {
                return Optional.empty();
            }
            return after.apply(tmp.get());
        };
    }
}
