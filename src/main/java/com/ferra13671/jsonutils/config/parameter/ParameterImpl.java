package com.ferra13671.jsonutils.config.parameter;

import com.ferra13671.jsonutils.model.JElement;
import com.ferra13671.jsonutils.model.JType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public abstract class ParameterImpl<T> implements Parameter {
    @NonNull
    private final JType<T> type;

    @Override
    public JElement save() {
        return this.type.toJsonFunction().apply(toElement());
    }

    @Override
    public void load(JElement element) {
        element.consumeIfExtends(this.type, this::fromElement);
    }

    protected abstract T toElement();

    protected abstract void fromElement(T element);
}
