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
public class JModel {
    private final List<JReader> readers = initReaders();
    private final List<JWriter> writers = initWriters();

    public JElement readNullable(Reader reader, List<JReader> readers) {
        JElement element = null;

        for (JReader jReader : readers) {
            try {
                System.out.println(jReader.getClass().getName());
                element = jReader.read(reader);
                if (!element.getClass().getName().equals(JElement.class.getName()))
                    return element;
            } catch (Exception ignored) {}
        }

        return element;
    }

    public JElement readNullable(Reader reader, JFormat format) {
        return readNullable(reader, filterReaders(format));
    }

    public JElement readNullable(Reader reader) {
        return readNullable(reader, readers);
    }

    public JElement readNullable(String string, List<JReader> readers) {
        try(StringReader reader = new StringReader(string)) {
            return readNullable(reader, readers);
        }
    }

    public JElement readNullable(String string, JFormat format) {
        return readNullable(string, filterReaders(format));
    }

    public JElement readNullable(String string) {
        return readNullable(string, readers);
    }

    public JElement read(Reader reader, List<JReader> readers) {
        JElement element = readNullable(reader, readers);

        if (element == null)
            throw new UnsupportedOperationException("No one JReader was initialized, or the element could not be read.");

        return element;
    }

    public JElement read(Reader reader, JFormat format) {
        return read(reader, filterReaders(format));
    }

    public JElement read(Reader reader) {
        return read(reader, readers);
    }

    public JElement read(String string, List<JReader> readers) {
        try(StringReader reader = new StringReader(string)) {
            return read(reader, readers);
        }
    }

    public JElement read(String string, JFormat format) {
        return read(string, filterReaders(format));
    }

    public JElement read(String string) {
        return read(string, readers);
    }

    public String writeNullable(JElement element, List<JWriter> writers) {
        String s = null;

        for (JWriter jWriter : writers) {
            try {
                s = jWriter.write(element);
                if (s != null)
                    return s;
            } catch (Exception ignored) {}
        }

        return s;
    }

    public String writeNullable(JElement element, JFormat format) {
        return writeNullable(element, filterWriters(format));
    }

    public String writeNullable(JElement element) {
        return writeNullable(element, writers);
    }

    public String write(JElement element, List<JWriter> writers) {
        String s = writeNullable(element, writers);

        if (s == null)
            throw new UnsupportedOperationException("No one JWriter was initialized, or the element could not be write to string.");

        return s;
    }

    public String write(JElement element, JFormat format) {
        return write(element, filterWriters(format));
    }

    public String write(JElement element) {
        return write(element, writers);
    }

    private List<JReader> filterReaders(JFormat format) {
        return readers.stream().filter(format.isSameReaderFunction::apply).toList();
    }

    private List<JWriter> filterWriters(JFormat format) {
        return writers.stream().filter(format.isSameWriterFunction::apply).toList();
    }

    private List<JReader> initReaders() {
        List<JReader> list = new ArrayList<>();

        try {
            list.add(new HJReader());
        } catch (Throwable ignored) {}

        try {
            list.add(new J5Reader());
        } catch (Throwable ignored) {}

        try {
            list.add(new JDefaultReader());
        } catch (Throwable ignored) {}

        return list;
    }

    private List<JWriter> initWriters() {
        List<JWriter> list = new ArrayList<>();

        try {
            list.add(new HJWriter());
        } catch (Throwable ignored) {}

        try {
            list.add(new J5Writer());
        } catch (Throwable ignored) {}

        try {
            list.add(new JDefaultWriter());
        } catch (Throwable ignored) {}

        return list;
    }
}
