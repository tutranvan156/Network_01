package com.vantu.loginUDP;

import java.io.IOException;
import java.net.*;

public class Server {
    private final int port;
    private InetAddress clientIP;
    private int clientPort;

    public Server(int port) {
        this.port = port;
    }

    private void execute() {
        try {
            DatagramSocket server = new DatagramSocket(port);
            int id;
            String pass;
            do {
                //Nhan du lieu tu client
                String []arrTemp = new String[2];
                String[] arrFirst = receiveData(server).split("_");
                String[] arrSecond = receiveData(server).split("_");

                arrTemp[Integer.parseInt(arrFirst[1]) - 1] = arrFirst[0];
                arrTemp[Integer.parseInt(arrSecond[1]) - 1] = arrSecond[0];


            } while (true);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    private String receiveData(DatagramSocket server) {
        try {
            byte[] temp = new byte[1024];
            DatagramPacket receivedPacket = new DatagramPacket(temp, temp.length);
            server.receive(receivedPacket);
            clientIP = receivedPacket.getAddress();
            clientPort = receivedPacket.getPort();
            return new String(receivedPacket.getData()).trim();
        } catch (IOException e) {
            e.printStackTrace();
            return "FAIL";
        }
    }

}
