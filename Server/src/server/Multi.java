package server;
import java.net.*;
import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static server.Server.frame;
import static server.Server.ConnectedClients;

public class Multi extends Thread{
    private static Socket socket;
    private static ObjectInputStream input;
    private static ObjectOutputStream output;

    public Multi(Socket clientAccepted) throws IOException, SQLException{
        socket = clientAccepted;
        input = new ObjectInputStream(socket.getInputStream());
        output = new ObjectOutputStream(socket.getOutputStream());
        What.setSocket(socket);
        What.setOut(output);
        What.setIn(input);
        start();
    }
    
    public void closeSocket() throws IOException{
        input.close();
        output.close();
        socket.close();
        Server.ConnectedClients--;
        frame.putMessage("Клиентов: " + Server.ConnectedClients);
    }
    
    
    @Override
    public void run(){
        try{
            while(true){
                String message = (String)input.readObject();
                
                if(message.equals("Close socket")){
                    break;
                }
                else
                {
                    What.quoi_faire(message);
                }
            }
            closeSocket();
        } catch (ClassNotFoundException e) {
        } 
        catch (IOException e) {
        } catch (SQLException ex) {
            Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    