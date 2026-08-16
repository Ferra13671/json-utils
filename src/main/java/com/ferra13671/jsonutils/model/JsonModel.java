package com.ferra13671.jsonutils.model;

import com.ferra13671.jsonutils.model.reader.HJReader;
import com.ferra13671.jsonutils.model.reader.J5Reader;
import com.ferra13671.jsonutils.model.reader.JDefaultReader;
import com.ferra13671.jsonutils.model.reader.JReader;
import com.ferra13671.jsonutils.model.writer.HJWriter;
import com.ferra13671.jsonutils.model.writer.J5Writer;
import com.ferra13671.jsonutils.model.writer.JDefaultWriter;
import com.ferra13671.jsonutils.model.writer.JWriter;
import lombok.experimental.UtilityClass;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class JsonModel {
    private final List<JReader> readers = initReaders();
    private final List<JWriter> writers = initWriters();

    public JElement readNullable(Reader reader) {
        JElement element = null;

        for (JReader jReader : readers) {
            try {
                element = jReader.read(reader);
            } catch (Exception ignored) {}
        }

        return element;
    }

    public JElement readNullable(String string) {
        try(StringReader reader = new StringReader(string)) {
            return readNullable(reader);
        }
    }

    public JElement read(Reader reader) {
        JElement element = readNullable(reader);

        if (element == null)
            throw new UnsupportedOperationException("No one JReader was initialized, or the element could not be read.");

        return element;
    }

    public JElement read(String string) {
        try(StringReader reader = new StringReader(string)) {
            return read(reader);
        }
    }

    public String toStringNullable(JElement element) {
        String s = null;

        for (JWriter jWriter : writers) {
            try {
                s = jWriter.toString(element);
            } catch (Exception ignored) {}
        }

        return s;
    }

    public String toString(JElement element) {
        String s = toStringNullable(element);

        if (s == null)
            throw new UnsupportedOperationException("No one JWriter was initialized, or the element could not be write to string.");

        return s;
    }

    public List<JReader> initReaders() {
        List<JReader> list = new ArrayList<>();

        try {
            list.addLast(new JDefaultReader());
        } catch (Exception ignored) {}

        try {
            list.addLast(new J5Reader());
        } catch (Exception ignored) {}

        try {
            list.addLast(new HJReader());
        } catch (Exception ignored) {}

        return list;
    }

    public List<JWriter> initWriters() {
        List<JWriter> list = new ArrayList<>();

        try {
            list.addLast(new JDefaultWriter());
        } catch (Exception ignored) {}

        try {
            list.addLast(new J5Writer());
        } catch (Exception ignored) {}

        try {
            list.addLast(new HJWriter());
        } catch (Exception ignored) {}

        return list;
    }
}
