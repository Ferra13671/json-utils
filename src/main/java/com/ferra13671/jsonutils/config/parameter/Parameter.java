package com.ferra13671.jsonutils.config.parameter;

import com.ferra13671.jsonutils.model.JElement;
import com.ferra13671.jsonutils.model.JType;

public interface Parameter {

    JType<?> getType();

    JElement save();

    void load(JElement element);
}
