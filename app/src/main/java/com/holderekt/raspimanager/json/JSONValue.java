package com.holderekt.raspimanager.json;

public class JSONValue extends JSONItem {
    private Object value;

    public JSONValue(Object item) {
        this.value = item;
    }

    @Override
    public String render() {
        return value.toString();
    }

}
