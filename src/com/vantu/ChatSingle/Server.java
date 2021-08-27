package com.vantu.ChatSingle;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: TranVanTu 23/08/2021 6:30 CH
 * @contact: tutranvan156@gmail.com
 **/
public class Server {
    private final int port;

    public Server(int port) {
        this.port = port;
    }

    public void execute() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            String NAME = "SERVER";
            ReadThread read = new ReadThread(socket);
            read.start();
            WriteThread write = new WriteThread(socket, NAME);
            write.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(5000);
        server.execute();
    }
}
