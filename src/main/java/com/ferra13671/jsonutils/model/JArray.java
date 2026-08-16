package com.ferra13671.jsonutils.model;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@AllArgsConstructor
public class JArray extends JElement {
    private final List<JElement> elements;

    public JArray() {
        this(new ArrayList<>());
    }

    public List<JElement> asList() {
        return new ArrayList<>(this.elements);
    }

    public void add(JElement element) {
        this.elements.add(element);
    }

    public <T> void add(JType<T> type, T value) {
        add(type.toJsonFunction().apply(value));
    }

    public void add(int index, JElement element) {
        this.elements.add(index, element);
    }

    public <T> void add(int index, JType<T> type, T value) {
        add(index, type.toJsonFunction().apply(value));
    }

    public void set(int index, JElement element) {
        this.elements.set(index, element);
    }

    public <T> void set(int index, JType<T> type, T value) {
        set(index, type.toJsonFunction().apply(value));
    }

    public void remove(JElement element) {
        this.elements.remove(element);
    }

    public <T> void remove(JType<T> type, T value) {
        remove(type.toJsonFunction().apply(value));
    }

    public void remove(int index) {
        this.elements.remove(index);
    }

    public void addAll(JArray array) {
        this.elements.addAll(array.elements);
    }

    public boolean contains(JElement element) {
        return this.elements.contains(element);
    }

    public <T> boolean contains(JType<T> type, T value) {
        return contains(type.toJsonFunction().apply(value));
    }

    public int size() {
        return this.elements.size();
    }

    public boolean isEmpty() {
        return this.elements.isEmpty();
    }

    public void forEach(Consumer<JElement> consumer) {
        this.elements.forEach(consumer);
    }

    @Override
    public int hashCode() {
        return this.elements.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof JArray array && this.elements.equals(array.elements);
    }
}
