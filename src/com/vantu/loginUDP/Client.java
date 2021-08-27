package com.vantu.loginUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Client {
    private final InetAddress host;
    private final int port;

    public Client(InetAddress host, int port) {
        this.host = host;
        this.port = port;
    }

    private void execute() {
        try {
            DatagramSocket client = new DatagramSocket();
            Scanner scanner = new Scanner(System.in);
            int id;
            String pass;
            do {
                System.out.println("Nhap vao id ");
                id = Integer.parseInt(scanner.nextLine());
                System.out.println("Nhap vao password ");
                pass = scanner.nextLine();
                DatagramPacket id_DP = createPacket(id, 1);
                DatagramPacket pass_DP = createPacket(pass, 2);
                client.send(id_DP);
                client.send(pass_DP);
            } while (true);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private DatagramPacket createPacket(int data, int index) {
        String str = data + "_" + index;
        byte[] arrData = str.getBytes();
        return new DatagramPacket(arrData, arrData.length, host, port);
    }

    private DatagramPacket createPacket(String data, int index) {
        String str = data + "_" + index;
        byte[] arrData = str.getBytes();
        return new DatagramPacket(arrData, arrData.length, host, port);
    }

}
