package com.ferra13671.jsonutils.model.reader;

import com.ferra13671.jsonutils.model.JArray;
import com.ferra13671.jsonutils.model.JElement;
import com.ferra13671.jsonutils.model.JObject;
import com.ferra13671.jsonutils.model.JValue;
import org.hjson.JsonArray;
import org.hjson.JsonObject;
import org.hjson.JsonValue;

import java.io.Reader;

public class HJReader implements JReader {

    public HJReader() {
        //check library existing
        JsonValue.valueOf(false);
    }

    @Override
    public JElement read(Reader reader) {
        try {
            return toJElement(JsonValue.readHjson(reader));
        } catch (Exception e) {
            return new JElement();
        }
    }

    public JElement toJElement(JsonValue value) {
        if (value instanceof JsonObject object)
            return toJElement(object);
        else
        if (value instanceof JsonArray array)
            return toJElement(array);

        return toJElementInternal(value);
    }

    public JElement toJElementInternal(JsonValue primitive) {
        if (primitive.isBoolean())
            return new JValue(primitive.asBoolean());

        if (primitive.isNumber())
            return new JValue(primitive.asDouble());

        if (primitive.isString())
            return new JValue(primitive.asString());

        return new JElement();
    }

    public JElement toJElement(JsonObject object) {
        JObject jObject = new JObject();

        object.forEach(member -> jObject.addElement(member.getName(), toJElement(member.getValue())));

        return jObject;
    }

    public JElement toJElement(JsonArray array) {
        JArray jArray = new JArray();

        array.forEach(element -> jArray.add(toJElement(element)));

        return jArray;
    }
}
