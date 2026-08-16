package com.ferra13671.jsonutils.model;

import java.util.function.Consumer;

public class JElement {

    public boolean isType(JType<?> type) {
        return type.equalsFunction().apply(this);
    }

    public <T> T cast(JType<T> type, T defaultValue) {
        if (isType(type))
            return type.toElementFunction().apply(this);

        return defaultValue;
    }

    public <T> void consumeIfExtends(JType<T> type, Consumer<T> consumer) {
        if (isType(type))
            consumer.accept(type.toElementFunction().apply(this));
    }
}
