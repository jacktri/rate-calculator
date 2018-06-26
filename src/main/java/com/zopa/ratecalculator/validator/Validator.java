package com.zopa.ratecalculator.validator;

@FunctionalInterface
public interface Validator<T> {
    boolean valid(T input);
}
