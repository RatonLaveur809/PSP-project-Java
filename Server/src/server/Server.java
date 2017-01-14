package server;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
/**
 * Created by Admin
 */
public class Server {
    static int ConnectedClients = 0;
    static Frame frame;
    
    public static void main(String[] args) throws IOException, SQLException{
        frame = new Frame();
        frame.setVisible(true);
        ServerSocket listenSocket = new ServerSocket(4000);
        frame.putMessage("Сервер запущен");
        System.out.println("Сервер запущен");
        while(true){
            Socket clientAccepted = listenSocket.accept();
            ConnectedClients++;
            frame.putMessage("Клиентов: " + ConnectedClients);
            new Multi(clientAccepted);
        }
    }
}
