
package client;
import Forms.For_everybody;
import java.io.*;
import java.net.*;
public class Client extends shablon_tipa  {
    private static Socket socket;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    
    public static void sendMessagetoServer(Object message) throws IOException{
        out.writeObject(message);
    }
    
    public static Object recieveMessageFromServer() throws IOException, ClassNotFoundException{
        return in.readObject();
    }
    
    public static void closeSocket() throws IOException{
        sendMessagetoServer("Close socket");
        in.close();
        out.close();
        socket.close();
    }
    
    public static void main(String[] args) throws IOException{
        socket = new Socket("localhost", 4000);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        shablon_tipa.setSocket(socket);
        shablon_tipa.setOut(out);
        shablon_tipa.setIn(in);
        For_everybody.main(null);
        
    }
}

