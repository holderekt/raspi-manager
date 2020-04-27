package com.holderekt.raspimanager.json;

public class JSONString extends JSONItem {
    private String item;

    public JSONString(String item) {
        this.item = item;
    }

    @Override
    public String render() {
        return "\""+item+"\"";
    }
}
