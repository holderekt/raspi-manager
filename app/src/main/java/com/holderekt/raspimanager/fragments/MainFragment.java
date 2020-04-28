package com.holderekt.raspimanager.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.holderekt.raspimanager.connection.ConnectionData;
import com.holderekt.raspimanager.R;

import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MainFragment extends Fragment {
    private View thisfragment;
    Map<String, EditText> inputs = new HashMap<>();
    EditText command;
    Base64.Encoder encoder = Base64.getEncoder();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        thisfragment = inflater.inflate(R.layout.main_fragment, container, false);

        inputs.put("ip", (EditText) thisfragment.findViewById(R.id.ip));
        inputs.put("port", (EditText) thisfragment.findViewById(R.id.port));
        inputs.put("password", (EditText) thisfragment.findViewById(R.id.password));
        inputs.put("name", (EditText) thisfragment.findViewById(R.id.username));
        command = thisfragment.findViewById(R.id.command);

        return thisfragment;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.shutdown).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try {
                    sendCommand(loadConnection(), "shutdown");
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        });


        view.findViewById(R.id.reboot).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try {
                    sendCommand(loadConnection(), "reboot");
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        });

        view.findViewById(R.id.send_command).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try {
                    sendCommand(loadConnection(), command.getText().toString().replaceAll("\"", "\'"));
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean isValidInput(){
        for(String key : inputs.keySet()){
            if(inputs.get(key).getText().toString().equals("")){
                return false;
            }
        }
        return true;
    }

    private ConnectionData loadConnection() throws UnknownHostException {
        if(isValidInput()){
            ConnectionData data = new ConnectionData();
            data.IP = InetAddress.getByName(inputs.get("ip").getText().toString());
            data.PORT = Integer.parseInt(inputs.get("port").getText().toString());
            data.name = inputs.get("name").getText().toString();
            data.password = inputs.get("password").getText().toString();
            return data;
        }
        Toast myToast = Toast.makeText(getActivity(), "Please insert all data", Toast.LENGTH_LONG);
        myToast.show();
        return null;
    }

    private void sendCommand(ConnectionData data, String  value){
        SocketConnection connection = null;
        if(data != null){
            data.value = value;

            connection = new SocketConnection(data);
            connection.run();
        }
    }

    public class SocketConnection implements Runnable {
        Socket sock;
        ConnectionData data;
        LinkedList<String> responses = new LinkedList<>();

        public SocketConnection(ConnectionData data){
            this.data = data;
        }

        @Override
        public void run() {
            try {
                if(data.IP.isReachable(10)){
                    sock = new Socket(data.IP, data.PORT);
                    System.out.println(data.toJsonString());
                    DataOutputStream dout =new DataOutputStream(sock.getOutputStream());
                    dout.write(encoder.encode(data.toJsonString().getBytes()));
                    dout.flush();
                    dout.close();
                    sock.close();

                    Toast myToast = Toast.makeText(getActivity(), "Message Sent", Toast.LENGTH_LONG);
                    myToast.show();
                }else{
                    throw new NoRouteToHostException();
                }
            } catch (NoRouteToHostException a){
                Toast myToast = Toast.makeText(getActivity(), "Host not reachable", Toast.LENGTH_LONG);
                myToast.show();
            }
            catch (Exception e) {
                e.printStackTrace();
                Toast myToast = Toast.makeText(getActivity(), "Could not connect to Raspberry Pi", Toast.LENGTH_LONG);
                myToast.show();
            }
        }
    }




}
