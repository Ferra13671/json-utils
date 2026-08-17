package com.ferra13671.jsonutils.model.writer;

import com.ferra13671.jsonutils.model.JArray;
import com.ferra13671.jsonutils.model.JElement;
import com.ferra13671.jsonutils.model.JObject;
import com.ferra13671.jsonutils.model.JValue;
import org.hjson.JsonArray;
import org.hjson.JsonObject;
import org.hjson.JsonValue;
import org.hjson.Stringify;

public class HJWriter implements JWriter {
    private final Stringify stringify = Stringify.HJSON;

    @Override
    public String write(JElement element) {
        return toHJson(element).toString(this.stringify);
    }

    public JsonValue toHJson(JElement element) {
        if (element instanceof JValue value)
            return toHJson(value);
        else
        if (element instanceof JObject object)
            return toHJson(object);
        else
        if (element instanceof JArray array)
            return toHJson(array);

        return JsonValue.NULL;
    }

    public JsonValue toHJson(JValue value) {
        if (value.isBoolean())
            return JsonValue.valueOf(value.getAsBoolean());

        if (value.isNumber())
            return JsonValue.valueOf(value.getAsNumber().doubleValue());

        return JsonValue.valueOf(value.getAsString());
    }

    public JsonValue toHJson(JObject object) {
        JsonObject hjsonObject = new JsonObject();

        object.forEach((name, element) -> hjsonObject.add(name, toHJson(element)));

        return hjsonObject;
    }

    public JsonValue toHJson(JArray array) {
        JsonArray hjsonArray = new JsonArray();

        array.forEach(element -> hjsonArray.add(toHJson(element)));

        return hjsonArray;
    }
}
