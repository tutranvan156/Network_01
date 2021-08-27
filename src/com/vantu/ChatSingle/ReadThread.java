package com.vantu.ChatSingle;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author: TranVanTu 23/08/2021 6:30 CH
 * @contact: tutranvan156@gmail.com
 **/
public class ReadThread extends Thread{
    private final Socket socket;


    public ReadThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            while (true) {
                String sms = dis.readUTF();
                System.out.println(sms);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
