package com.vantu.loginTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Server {
    private final int port;

    public Server(int port) {
        this.port = port;
    }

    private void execute() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            int id;
            String pass;
            int result;
            do {
                id = dataInputStream.readInt();
                pass = dataInputStream.readUTF();
                Connection connection = DBConnection.getConnection();
                String query = "select dbo.FN_login(?, ?) as result";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, id);
                ps.setString(2, pass);
                ResultSet resultSet = ps.executeQuery();
                resultSet.next();
                result = resultSet.getInt("result");
                if (result == -1) {
                    dataOutputStream.writeInt(-1);
                } else if (result == -2) {
                    dataOutputStream.writeInt(-2);
                } else {
                    dataOutputStream.writeInt(result);
                    DBConnection.closeConnection(connection);
                    break;
                }
            } while (true);
            dataInputStream.close();
            dataOutputStream.close();
            socket.close();
            serverSocket.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Server server = new Server(5000);
        server.execute();
    }

}
