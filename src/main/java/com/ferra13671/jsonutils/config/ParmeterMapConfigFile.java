package com.ferra13671.jsonutils.config;

import com.ferra13671.jsonutils.config.parameter.Parameter;
import com.ferra13671.jsonutils.model.JElement;
import com.ferra13671.jsonutils.model.JModel;
import com.ferra13671.jsonutils.model.JObject;
import com.ferra13671.jsonutils.model.JType;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Map;

public class ParmeterMapConfigFile extends ConfigFile {
    private final Map<String, Parameter> parameterMap;

    public ParmeterMapConfigFile(Map<String, Parameter> parameterMap, Path defaultPath) {
        super(defaultPath);

        this.parameterMap = parameterMap;
    }

    public ParmeterMapConfigFile(Map<String, Parameter> parameterMap) {
        this.parameterMap = parameterMap;
    }

    @Override
    public void load(InputStream inputStream) throws Exception {
        try(Reader reader = new InputStreamReader(inputStream)) {
            JModel.read(reader).consumeIfExtends(JType.JOBJECT, object ->
                this.parameterMap.forEach((name, parameter) -> parameter.load(object.getElement(name)))
            );
        }
    }

    @Override
    public void save(OutputStream outputStream) throws Exception {
        JObject object = new JObject();

        this.parameterMap.forEach((name, parameter) -> {
            JElement element = parameter.save();

            if (element != null)
                object.addElement(name, element);
        });

        try (OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
            writer.write(JModel.write(object));
        }
    }
}
