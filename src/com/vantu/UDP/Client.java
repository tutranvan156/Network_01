package com.vantu.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * @author: TranVanTu 30/08/2021 1:54 CH
 * @desc:
 **/
public class Client {
    private final InetAddress address;
    private final int port;

    public Client(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public void execute() throws IOException {
        DatagramSocket client = new DatagramSocket();

        Scanner sc = new Scanner(System.in);
        String n = sc.nextLine();

        byte[] buf;
        buf = n.trim().getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
        client.send(packet);

        //Show result
        byte[] result = new byte[256];
        packet = new DatagramPacket(result, result.length);
        client.receive(packet);
        String out = new String(packet.getData(), 0, packet.getLength());
        String[] a = out.split(" ");

        StringBuilder rs = new StringBuilder();
        for (String i : a) {
            rs.append(i).append(" ");
        }
        System.out.println(rs.toString().trim());
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client(InetAddress.getLocalHost(), 5000);
        client.execute();
    }
}
