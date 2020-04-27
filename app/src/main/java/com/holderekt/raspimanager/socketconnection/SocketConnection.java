package com.holderekt.raspimanager.socketconnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketConnection {
    Socket socket;
    OutputStream out;
    InputStream in;

    public SocketConnection(String ip, int port) throws IOException {
        ip = "127.0.0.1";
        port = 65432;
        out = socket.getOutputStream();
        in = socket.getInputStream();
    }

    public void sendMessage(byte[] message) throws IOException {
        out.write(message);
        out.flush();
        out.close();
    }
}
