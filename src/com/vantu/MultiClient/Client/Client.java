package com.vantu.MultiClient.Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author: TranVanTu 30/08/2021 10:50 CH
 * @desc:
 **/
public class Client {
    private final InetAddress HOST = InetAddress.getLocalHost();
    private final int PORT = 5000;

    public Client() throws UnknownHostException {

    }

    public void execute() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        String name = sc.nextLine();
        String reply;

        Socket client = new Socket(HOST, PORT);
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        ThreadClient threadClient = new ThreadClient(client);
        threadClient.start();
        out.println(name + ": has joined the room");
        while (true) {
            String msg = name + ": ";
            reply = sc.nextLine();
            if (reply.equals("logout")) {
                out.println("logout");
                break;
            }
            out.println(msg + reply);
        }
    }
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.execute();
    }
}