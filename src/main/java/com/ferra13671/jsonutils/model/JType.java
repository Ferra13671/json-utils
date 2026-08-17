package com.ferra13671.jsonutils.model;

import java.util.function.Function;

public record JType<T>(Function<JElement, Boolean> equalsFunction, Function<JElement, T> toElementFunction, Function<T, JElement> toJsonFunction) {

    public static final JType<JValue> JVALUE = new JType<>(
            element -> element instanceof JValue,
            element -> (JValue) element,
            value -> value
    );

    public static final JType<JObject> JOBJECT = new JType<>(
            element -> element instanceof JObject,
            element -> (JObject) element,
            object -> object
    );

    public static final JType<JArray> JARRAY = new JType<>(
            element -> element instanceof JArray,
            element -> (JArray) element,
            array -> array
    );

    public static final JType<Boolean> BOOLEAN = new JType<>(
            element -> element instanceof JValue value && value.isBoolean(),
            element -> ((JValue) element).getAsBoolean(),
            JValue::new
    );

    public static final JType<Number> NUMBER = new JType<>(
            element -> element instanceof JValue value && value.isNumber(),
            element ->  ((JValue) element).getAsNumber(),
            JValue::new
    );

    public static final JType<String> STRING = new JType<>(
            element -> element instanceof JValue value && value.isString(),
            element -> ((JValue) element).getAsString(),
            JValue::new
    );
}
