package com.vantu.ChatSingle;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author: TranVanTu 23/08/2021 6:31 CH
 * @contact: tutranvan156@gmail.com
 **/
public class WriteThread extends Thread{
    private final Socket socket;
    private final String name;

    public WriteThread(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
    }


    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String sms = sc.nextLine();
                if (sms.equals("exit")) {
                    sc.close();
                    dos.close();
                    socket.close();
                    break;
                }
                dos.writeUTF(name + ": " + sms);
                dos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
