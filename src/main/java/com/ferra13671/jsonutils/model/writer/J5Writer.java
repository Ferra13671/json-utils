package com.ferra13671.jsonutils.model.writer;

import com.ferra13671.jsonutils.model.JArray;
import com.ferra13671.jsonutils.model.JElement;
import com.ferra13671.jsonutils.model.JObject;
import com.ferra13671.jsonutils.model.JValue;
import de.marhali.json5.*;

public class J5Writer implements JWriter {
    private final Json5Options options = Json5Options.builder()
            .prettyPrinting()
            .quoteSingle()
            .build();

    @Override
    public String toString(JElement element) {
        return toJson5(element).toString(this.options);
    }

    private Json5Element toJson5(JElement element) {
        if (element instanceof JValue value)
            return toJson5(value);
        else
        if (element instanceof JObject object)
            return toJson5(object);
        else
        if (element instanceof JArray array)
            return toJson5(array);

        return Json5Null.INSTANCE;
    }

    public Json5Element toJson5(JValue value) {
        if (value.isBoolean())
            return new Json5Boolean(value.getAsBoolean());

        if (value.isNumber())
            return new Json5Number(value.getAsNumber());

        return new Json5String(value.getAsString());
    }

    public Json5Element toJson5(JObject object) {
        Json5Object json5Object = new Json5Object();

        object.forEach((name, element) -> json5Object.add(name, toJson5(element)));

        return json5Object;
    }

    private Json5Element toJson5(JArray array) {
        Json5Array json5Array = new Json5Array();

        array.forEach(element -> json5Array.add(toJson5(element)));

        return json5Array;
    }
}
