package com.holderekt.raspimanager.json;

public class JSONTuple extends JSONKeyItem {
    JSONItem item;

    public JSONTuple(String key, JSONValue item) {
        super(key);
        this.item = item;
    }

    public JSONTuple(JSONString key, JSONValue item){
        super(key);
        this.item = item;
    }

    public JSONTuple(String key, JSONString item) {
        super(key);
        this.item = item;
    }

    public JSONTuple(JSONString key, JSONString item){
        super(key);
        this.item = item;
    }

    public String render() {
        return this.key.render() + ":" + this.item.render();
    }
}
