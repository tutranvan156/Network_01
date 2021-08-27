package com.vantu.ChatRoomTCP.Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author: TranVanTu 24/08/2021 2:31 CH
 * @desc:
 **/
public class ReadClient extends Thread{

    private final Socket client;

    public ReadClient(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(client.getInputStream());
            while (true) {
                String sms = dis.readUTF();
                System.out.println(sms);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
