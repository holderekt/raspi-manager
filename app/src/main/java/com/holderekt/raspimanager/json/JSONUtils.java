package com.holderekt.raspimanager.json;

public class JSONUtils {
    public static JSONItem getJsonItem(Object obj) throws JSONWrongTypeException {
        if(obj instanceof String){
            return new JSONString((String) obj);
        }else if(obj instanceof Double || obj instanceof Integer || obj instanceof Boolean ){
            return new JSONValue(obj);
        }else{
            throw new JSONWrongTypeException("Type is not supported");
        }
    }
}
