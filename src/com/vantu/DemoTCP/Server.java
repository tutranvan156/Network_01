package com.vantu.DemoTCP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * @author: TranVanTu 27/08/2021 2:51 CH
 * @desc: this is a code to print all element that just appear one time
 **/
public class Server {
    private final int port;

    public Server(int port) {
        this.port = port;
    }
    public void execute() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("[SERVER] is running .....");
            Socket client = serverSocket.accept();
            System.out.println("[SERVER] is connected.");
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            HashMap<Integer, Boolean> hashMap = new HashMap<>();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder result = new StringBuilder();
            int[] arr = Arrays.stream(in.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

            for (int i : arr) {
                if (hashMap.containsKey(i)) {
                    hashMap.put(i, false);
                } else {
                    hashMap.put(i, true);
                }
            }
            for (Integer key : hashMap.keySet()) {
                if (hashMap.get(key).equals(true)) {
                    result.append(key).append(" ");
                }
            }
            dos.writeUTF(String.valueOf(result).trim());

            //close connect
            dos.close();
            client.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Server server = new Server(5000);
        server.execute();
    }
}
