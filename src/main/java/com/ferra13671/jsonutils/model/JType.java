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
}
