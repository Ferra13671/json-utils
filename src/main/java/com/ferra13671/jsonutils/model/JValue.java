package com.ferra13671.jsonutils.model;

import lombok.Getter;

@Getter
public class JValue extends JElement {
    private final Object value;

    public JValue(Boolean value) {
        this.value = value;
    }

    public JValue(Number value) {
        this.value = value;
    }

    public JValue(String value) {
        this.value = value;
    }

    public JValue(Character value) {
        this.value = value;
    }

    public boolean isBoolean() {
        return this.value instanceof Boolean;
    }

    public Boolean getAsBoolean() {
        if (isBoolean())
            return (Boolean) this.value;

        return Boolean.parseBoolean(getAsString());
    }

    public boolean isNumber() {
        return this.value instanceof Number;
    }

    public Number getAsNumber() {
        if (isNumber())
            return (Number) this.value;
        else if (isString()) {
            Number n = parseNumber();

            if (n != null)
                return n;
        }

        throw new AssertionError("Value is not a number: " + this.value.getClass());
    }

    private Number parseNumber() {
        try {
            return Byte.parseByte(getAsString());
        } catch (Exception e) {
            try {
                return Short.parseShort(getAsString());
            } catch (Exception e2) {
                try {
                    return Integer.parseInt(getAsString());
                } catch (Exception e3) {
                    try {
                        return Long.parseLong(getAsString());
                    } catch (Exception e4) {
                        try {
                            return Float.parseFloat(getAsString());
                        } catch (Exception e5) {
                            try {
                                return Double.parseDouble(getAsString());
                            } catch (Exception e6) {
                                return null;
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean isString() {
        return this.value instanceof String;
    }

    public String getAsString() {
        if (this.value instanceof String s) {
            return s;
        } else if (isNumber()) {
            return getAsNumber().toString();
        } else if (isBoolean()) {
            return ((Boolean) this.value).toString();
        }

        throw new AssertionError("Unexpected value type: " + this.value.getClass());
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof JValue v && this.value.equals(v.value);
    }
}
