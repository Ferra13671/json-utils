package com.ferra13671.jsonutils.model;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@AllArgsConstructor
public class JObject extends JElement {
    private final Map<String, JElement> elements;

    public JObject() {
        this(new HashMap<>());
    }

    public int size() {
        return this.elements.size();
    }

    public Map<String, JElement> asMap() {
        return new HashMap<>(this.elements);
    }

    public JElement getElement(String name) {
        return this.elements.getOrDefault(name, new JElement());
    }

    public <T> T castElement(String name, JType<T> type, T defaultValue) {
        return getElement(name).cast(type, defaultValue);
    }

    public void addElement(String name, JElement element) {
        this.elements.put(name, element);
    }

    public <T> void addElement(String name, JType<T> type, T value) {
        addElement(name, type.toJsonFunction().apply(value));
    }

    public boolean contains(String name) {
        return this.elements.containsKey(name);
    }

    public <T> void consumeIfPresent(String name, JType<T> type, Consumer<T> consumer) {
        getElement(name).consumeIfExtends(type, consumer);
    }

    public void forEach(BiConsumer<String, JElement> consumer) {
        this.elements.forEach(consumer);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof JObject o && this.elements.equals(o.elements);
    }

    @Override
    public int hashCode() {
        return this.elements.hashCode();
    }
}
