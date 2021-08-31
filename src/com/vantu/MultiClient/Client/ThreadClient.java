package com.vantu.MultiClient.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author: TranVanTu 30/08/2021 10:51 CH
 * @desc:
 **/
public class ThreadClient extends Thread{
    private final Socket client;
    public final BufferedReader in;
    public ThreadClient(Socket client) throws IOException {
        this.client = client;
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    @Override
    public void run() {
        try {
            while (true) {
                String msg = in.readLine();
                System.out.println(msg);
            }
        } catch (IOException e) {
            System.out.println("You left the room");
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
