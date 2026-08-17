package com.ferra13671.jsonutils.model.reader;

import com.ferra13671.jsonutils.model.JArray;
import com.ferra13671.jsonutils.model.JElement;
import com.ferra13671.jsonutils.model.JObject;
import com.ferra13671.jsonutils.model.JValue;
import de.marhali.json5.*;
import de.marhali.json5.stream.Json5Lexer;
import de.marhali.json5.stream.Json5Parser;

import java.io.Reader;

public class J5Reader implements JReader {
    private final Json5Options options = Json5Options.builder()
            .prettyPrinting()
            .quoteSingle()
            .build();

    @Override
    public JElement read(Reader reader) {
        return toJElement(Json5Parser.parse(new Json5Lexer(
                reader,
                this.options
        )));
    }

    public JElement toJElement(Json5Element element) {
        if (element instanceof Json5Primitive primitive)
            return toJElement(primitive);
        else
        if (element instanceof Json5Object object)
            return toJElement(object);
        else
        if (element instanceof Json5Array array)
            return toJElement(array);

        return null;
    }

    public JElement toJElement(Json5Primitive primitive) {
        if (primitive.isBoolean())
            return new JValue(primitive.getAsBoolean());

        if (primitive.isNumber())
            return new JValue(primitive.getAsNumber());

        if (primitive.isString())
            return new JValue(primitive.getAsString());

        return null;
    }

    public JElement toJElement(Json5Object object) {
        JObject jObject = new JObject();

        object.entrySet()   .forEach(entry -> jObject.addElement(entry.getKey(), toJElement(entry.getValue())));

        return jObject;
    }

    public JElement toJElement(Json5Array array) {
        JArray jArray = new JArray();

        array.forEach(element -> jArray.add(toJElement(element)));

        return jArray;
    }
}
