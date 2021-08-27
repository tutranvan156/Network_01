package com.vantu.ChatRoomTCP.Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author: TranVanTu 24/08/2021 2:22 CH
 * @desc:
 **/
public class Client {
    private final InetAddress host;
    private final int port;

    public Client(InetAddress host, int port) {
        this.host = host;
        this.port = port;
    }

    public void execute() {
        try {
            Socket client = new Socket(host, port);

            ReadClient readClient = new ReadClient(client);
            readClient.start();
            WriteClient writeClient = new WriteClient(client);
            writeClient.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws UnknownHostException {
        Client client = new Client(InetAddress.getLocalHost(), 5000);
        client.execute();
    }
}
