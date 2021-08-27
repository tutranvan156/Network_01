package com.vantu.ChatSingle;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author: TranVanTu 23/08/2021 6:30 CH
 * @contact: tutranvan156@gmail.com
 **/
public class Client {
    private final InetAddress host;
    private final int port;
    private final String name;

    public Client(InetAddress host, int port, String name) {
        this.host = host;
        this.port = port;
        this.name = name;
    }

    public void execute() {
        try {
            Socket socket = new Socket(host, port);
            WriteThread write = new WriteThread(socket, name);
            write.start();
            ReadThread read = new ReadThread(socket);
            read.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws UnknownHostException {
        Client client = new Client(InetAddress.getLocalHost(), 5000, "Van Tu");
        client.execute();
    }
}
