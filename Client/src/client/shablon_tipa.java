package client;
import java.io.*;
import java.net.*;

 public class shablon_tipa{
    private static Socket socket;
    private static ObjectInputStream input;
    private static ObjectOutputStream  output;
    public static void setSocket(Socket sock){
        socket=sock;
    }
    public static void setIn(ObjectInputStream in){
        input=in;
    }
    public static void setOut(ObjectOutputStream out){
        output=out;
    }
    public static ObjectInputStream getIn(){
        return input;
    }
    public static ObjectOutputStream getOut(){
        return output;
    }
}
