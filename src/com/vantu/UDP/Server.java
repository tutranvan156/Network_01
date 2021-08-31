package com.vantu.UDP;

import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

/**
 * @author: TranVanTu 30/08/2021 1:54 CH
 * @desc:
 **/
public class Server {
    private final int port;

    public Server(int port) {
        this.port = port;
    }

    public void execute() throws IOException {
        DatagramSocket server = new DatagramSocket(port);
        System.out.println("[SERVER] is running ......");
        byte[] buf = new byte[256];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        server.receive(packet);
        String s = new String(packet.getData(),0, packet.getLength());

        String[] arr = s.split("\\s+");

        HashMap<String, Boolean> hashMap = new HashMap<>();
        StringBuilder result = new StringBuilder();

        for (String i : arr) {
            if (hashMap.containsKey(i)) {
                hashMap.put(i, false);
            } else {
                hashMap.put(i, true);
            }
        }
        for (String key : hashMap.keySet()) {
            if (hashMap.get(key).equals(true)) {
                result.append(key).append(" ");
            }
        }
        byte[] send;

        send = result.toString().getBytes();
        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        packet = new DatagramPacket(send, send.length, address, port);
        server.send(packet);
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(5000);
        server.execute();
    }
}
