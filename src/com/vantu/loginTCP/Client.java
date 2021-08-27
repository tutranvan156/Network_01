package com.vantu.loginTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
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
            Socket client = new Socket(host, port);
            DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());

            Scanner sc = new Scanner(System.in);
            int id;
            String pass;
            int result;
            do {
                System.out.println("Nhap vao id");
                id = Integer.parseInt(sc.nextLine());
                System.out.println("Nhap vao password");
                pass = sc.nextLine();
                dataOutputStream.writeInt(id);
                dataOutputStream.writeUTF(pass);
                result = dataInputStream.readInt();
                if (result == -1) {
                    System.out.println("Sai password");
                } else if (result == -2) {
                    System.out.println("User khong ton tai");
                } else {
                    System.out.println("So tien cua ban la " + result);
                    break;
                }
            } while (true);
            dataInputStream.close();
            dataOutputStream.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws UnknownHostException {
        Client client = new Client(InetAddress.getLocalHost(), 5000);
        client.execute();

    }
}
