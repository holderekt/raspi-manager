package com.holderekt.raspimanager.json;

public class JSONWrongTypeException extends Exception{
    public JSONWrongTypeException(String errorMessage){
        super(errorMessage);
    }
}
