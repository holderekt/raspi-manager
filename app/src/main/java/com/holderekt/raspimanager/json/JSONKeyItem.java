package com.holderekt.raspimanager.json;

public abstract class JSONKeyItem extends JSONItem{
    protected JSONString key;

    public JSONKeyItem(JSONString key){
        this.key = key;
    }

    public JSONKeyItem(){
        this.key = null;
    }

    public JSONKeyItem(String key){
        this.key = new JSONString(key);
    }
}
