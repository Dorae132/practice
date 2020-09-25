package com.nsb.practice.common;

/**
 * @desc Eliminate the NPE problems when get the nested values
 * @author Dorae
 * @version 1.0
 * @date 2020/9/25 19:58
 **/
@FunctionalInterface
public interface NestedEvaluator<T, R> {

    R apply(T t);

    default <V> NestedEvaluator<T, V> andThen(NestedEvaluator<R, V> after) {
        return (T p) -> {
            R tmp = apply(p);
            if (tmp == null) {
                return null;
            }
            return after.apply(tmp);
        };
    }
}
