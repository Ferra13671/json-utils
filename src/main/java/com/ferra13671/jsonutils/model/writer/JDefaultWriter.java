package com.ferra13671.jsonutils.model.writer;

import com.ferra13671.jsonutils.model.JArray;
import com.ferra13671.jsonutils.model.JElement;
import com.ferra13671.jsonutils.model.JObject;
import com.ferra13671.jsonutils.model.JValue;
import com.google.gson.*;

public class JDefaultWriter implements JWriter {
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
    
    @Override
    public String write(JElement element) {
        return this.gson.toJson(toJson(element));
    }
    
    private JsonElement toJson(JElement element) {
        if (element instanceof JValue value)
            return toJson(value);
        else
        if (element instanceof JObject object)
            return toJson(object);
        else
        if (element instanceof JArray array)
            return toJson(array);

        return JsonNull.INSTANCE;
    }

    private JsonElement toJson(JValue value) {
        if (value.isBoolean())
            return new JsonPrimitive(value.getAsBoolean());

        if (value.isNumber())
            return new JsonPrimitive(value.getAsNumber());

        return new JsonPrimitive(value.getAsString());
    }

    private JsonElement toJson(JObject object) {
        JsonObject jsonObject = new JsonObject();

        object.forEach((name, element) -> jsonObject.add(name, toJson(element)));

        return jsonObject;
    }

    private JsonElement toJson(JArray array) {
        JsonArray jsonArray = new JsonArray();

        array.forEach(element -> jsonArray.add(toJson(element)));

        return jsonArray;
    }
}
