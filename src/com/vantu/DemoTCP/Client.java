package com.vantu.DemoTCP;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author: TranVanTu 27/08/2021 2:51 CH
 * @desc: this code is get data from server and print in console
 **/
public class Client {

    public final InetAddress host;
    public final int port;

    public Client(InetAddress host, int port) {
        this.host = host;
        this.port = port;
    }
    public void execute() {
        try {
            Socket client = new Socket(host, port);
            DataInputStream dis = new DataInputStream(client.getInputStream());
            System.out.println(dis.readUTF());
            dis.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws UnknownHostException {
        Client client = new Client(InetAddress.getLocalHost(), 5000);
        client.execute();
    }
}
