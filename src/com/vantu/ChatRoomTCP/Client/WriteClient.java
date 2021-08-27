package com.vantu.ChatRoomTCP.Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author: TranVanTu 24/08/2021 2:31 CH
 * @desc:
 **/
public class WriteClient extends Thread{
    private final Socket client;

    public WriteClient(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        try {
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            String sms = sc.nextLine();
            dos.writeUTF(sms);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
