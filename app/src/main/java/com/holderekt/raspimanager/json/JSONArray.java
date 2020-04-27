package com.holderekt.raspimanager.json;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class JSONArray extends JSONKeyItem {
    private List<JSONItem> items = new LinkedList<>();

    public JSONArray(JSONString key){
        super(key);
    }

    public JSONArray(String key){
        super(key);
    }

    public JSONArray() throws JSONWrongSyntaxException {
        throw new JSONWrongSyntaxException("JSON Array must have a valid key");
    }

    public void addItem(JSONItem item){
        this.items.add(item);
    }

    @Override
    public String render() {
        String renderValue = key.render() + ":[";
        Iterator<JSONItem> iter = items.iterator();
        while(iter.hasNext()){
            renderValue += iter.next().render();
            if(iter.hasNext())
                renderValue += ",";
        }

        return renderValue + "]";
    }
}
