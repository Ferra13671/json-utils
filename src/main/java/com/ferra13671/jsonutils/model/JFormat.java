package com.ferra13671.jsonutils.model;

import com.ferra13671.jsonutils.model.reader.HJReader;
import com.ferra13671.jsonutils.model.reader.J5Reader;
import com.ferra13671.jsonutils.model.reader.JDefaultReader;
import com.ferra13671.jsonutils.model.reader.JReader;
import com.ferra13671.jsonutils.model.writer.HJWriter;
import com.ferra13671.jsonutils.model.writer.J5Writer;
import com.ferra13671.jsonutils.model.writer.JDefaultWriter;
import com.ferra13671.jsonutils.model.writer.JWriter;
import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public enum JFormat {
    JSON(
            reader -> reader instanceof JDefaultReader,
            writer -> writer instanceof JDefaultWriter
    ),
    JSON5(
            reader -> reader instanceof J5Reader,
            writer -> writer instanceof J5Writer
    ),
    HJSON(
            reader -> reader instanceof HJReader,
            writer -> writer instanceof HJWriter
    );

    public final Function<JReader, Boolean> isSameReaderFunction;
    public final Function<JWriter, Boolean> isSameWriterFunction;
}
