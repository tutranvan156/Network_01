package com.vantu.MultiClient.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author: TranVanTu 30/08/2021 10:51 CH
 * @desc:
 **/
public class ThreadServer extends Thread {
    private final Socket socket;
    private final ArrayList<Socket> clients;
    private final HashMap<Socket, String> clientNameLists;

    public ThreadServer(Socket socket, ArrayList<Socket> clients, HashMap<Socket, String> clientNameLists) {
        this.socket = socket;
        this.clients = clients;
        this.clientNameLists = clientNameLists;
    }

    @Override
    public void run() {
        try {
            //read the input that contain name and message of client
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //check if client want to exit then exit that client
            while (true) {
                String out = input.readLine();
                if (out.equals("logout")) {
                    throw new SocketException();
                }
                if (!clientNameLists.containsKey(socket)) {
                    String[] smg = out.split(":", 2);
                    clientNameLists.put(socket, smg[0]);
                    System.out.println(smg[0] + smg[1]);
                    showMessageToAllClient(socket, smg[0] + smg[1]);

                } else {
                    System.out.println(out);
                    showMessageToAllClient(socket, out);
                }
            }
        } catch (SocketException e) {
            String handleException = clientNameLists.get(socket) + " has left the room";
            System.out.println(handleException);
            showMessageToAllClient(socket, handleException);
            clients.remove(socket);
            clientNameLists.remove(socket);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showMessageToAllClient(Socket sender, String message) {
        PrintWriter printWriter;
        for (Socket eSocket : clients) {
            try {
                if (eSocket != sender) {
                    printWriter = new PrintWriter(eSocket.getOutputStream(), true);
                    printWriter.println(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
