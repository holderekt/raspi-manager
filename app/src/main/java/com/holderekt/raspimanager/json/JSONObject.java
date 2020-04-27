package com.holderekt.raspimanager.json;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class JSONObject extends JSONKeyItem{
    protected List<JSONKeyItem> items = new LinkedList<>();

    public JSONObject(){
        super();
    }

    public JSONObject(JSONString key) {
        super(key);
    }

    public JSONObject(String key) {
        super(key);
    }


    public void addItem(JSONKeyItem item){
        this.items.add(item);
    }

    @Override
    public String render() {

        String renderValue = this.key != null ? this.key.render() + ":{" : "{";

        Iterator<JSONKeyItem> iter = items.iterator();
        while(iter.hasNext()){
            renderValue += iter.next().render();
            if(iter.hasNext())
                renderValue += ",";
        }

        return renderValue + "}";
    }
}
