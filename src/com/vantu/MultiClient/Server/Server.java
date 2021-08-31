package com.vantu.MultiClient.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author: TranVanTu 30/08/2021 10:51 CH
 * @desc:
 **/
public class Server {

    public final int PORT = 5000;

    public Server() {

    }

    public void execute() throws IOException {
        //list of "clients"
        ArrayList<Socket> clients = new ArrayList<>();
        //HashMap this is know that with each Socket we have a specific name of client
        HashMap<Socket, String> clientNameLists = new HashMap<>();
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("[SERVER] is started ....");
        while (true) {
            Socket client = server.accept();
            clients.add(client);
            ThreadServer threadServer = new ThreadServer(client, clients, clientNameLists);
            threadServer.start();
        }
    }
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.execute();
    }

}
