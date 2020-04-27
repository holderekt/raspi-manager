package com.holderekt.raspimanager.connection;

import com.holderekt.raspimanager.json.JSONObject;
import com.holderekt.raspimanager.json.JSONString;
import com.holderekt.raspimanager.json.JSONTuple;
import com.holderekt.raspimanager.json.JSONWrongSyntaxException;

import java.net.InetAddress;

public class ConnectionData {
    public InetAddress IP;
    public int PORT;
    public String name;
    public String password;
    public String value;

    public String toString(){
        return "IP\t" + IP.getHostAddress() + "\n" +
                "PORT\t" + PORT + "\n" +
                "NANE\t" + name;
    }

    public String toJsonString() {
        JSONObject base = new JSONObject();
        base.addItem(new JSONTuple("username", new JSONString(this.name)));
        base.addItem(new JSONTuple("password", new JSONString(this.password)));
        base.addItem(new JSONTuple("value", new JSONString(this.value)));

        return base.render();
    }
}
