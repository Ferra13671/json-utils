package com.ferra13671.jsonutils.model;

import java.util.function.Function;

public record JType<T>(Function<JElement, Boolean> equalsFunction, Function<JElement, T> toElementFunction, Function<T, JElement> toJsonFunction) {
}
