package com.ferra13671.jsonutils.model.reader;

import com.ferra13671.jsonutils.model.JArray;
import com.ferra13671.jsonutils.model.JElement;
import com.ferra13671.jsonutils.model.JObject;
import com.ferra13671.jsonutils.model.JValue;
import com.google.gson.*;

import java.io.Reader;

public class JDefaultReader implements JReader {

    public JDefaultReader() {
        //check library existing
        new JsonPrimitive(false);
    }

    @Override
    public JElement read(Reader reader) {
        return toJElement(JsonParser.parseReader(reader));
    }

    public JElement toJElement(JsonElement element) {
        if (element instanceof JsonPrimitive primitive)
            return toJElement(primitive);
        else
        if (element instanceof JsonObject object)
            return toJElement(object);
        else
        if (element instanceof JsonArray array)
            return toJElement(array);

        return null;
    }

    public JElement toJElement(JsonPrimitive primitive) {
        if (primitive.isBoolean())
            return new JValue(primitive.getAsBoolean());

        if (primitive.isNumber())
            return new JValue(primitive.getAsNumber());

        if (primitive.isString())
            return new JValue(primitive.getAsString());

        return null;
    }

    public JElement toJElement(JsonObject object) {
        JObject jObject = new JObject();

        object.asMap().forEach((name, element) -> jObject.addElement(name, toJElement(element)));

        return jObject;
    }

    public JElement toJElement(JsonArray array) {
        JArray jArray = new JArray();

        array.forEach(element -> jArray.add(toJElement(element)));

        return jArray;
    }
}
