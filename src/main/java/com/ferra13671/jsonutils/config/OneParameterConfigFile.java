package com.ferra13671.jsonutils.config;

import com.ferra13671.jsonutils.config.parameter.Parameter;
import com.ferra13671.jsonutils.model.JElement;
import com.ferra13671.jsonutils.model.JModel;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class OneParameterConfigFile extends ConfigFile {
    private final Parameter parameter;

    public OneParameterConfigFile(Parameter parameter, Path defaultPath) {
        super(defaultPath);

        this.parameter = parameter;
    }

    public OneParameterConfigFile(Parameter parameter) {
        this.parameter = parameter;
    }

    @Override
    public void load(InputStream inputStream) throws Exception {
        try(Reader reader = new InputStreamReader(inputStream)) {
            JElement element = JModel.read(reader);

            if (element.isType(this.parameter.getType()))
                this.parameter.load(element);
        }
    }

    @Override
    public void save(OutputStream outputStream) throws Exception {
        JElement element = this.parameter.save();

        if (element != null)
            try (OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
                writer.write(JModel.toString(element));
            }
    }
}
