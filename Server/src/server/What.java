
package server;
import tabli4ki.Medicaments;
import tabli4ki.Info;
import tabli4ki.Users;
import tabli4ki.Sroki;
import java.io.*;
import java.net.*;
import java.sql.SQLException;

public class What {
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
    
    public static void quoi_faire(String quoi) throws IOException, ClassNotFoundException, SQLException
    {
        switch (quoi){
            case "Get sroki":
                Sroki.getSroki();
                break;            
            case "Get users":
                Users.getUsers();
                break;
            case "Get info":
                Info.getInfo();
                break;
            case "Add user":
                Users.addUsers();
                break;
            case "Edit user":
                Users.editUsers();
                break;
            case "Delete user":
                Users.deleteUsers();
                break;
            case "Add srok":
                Sroki.addSrok();
                break;
            case "Edit srok":
                Sroki.editSrok();
                break;
            case "Delete srok":
                Sroki.deleteSrok();
                break;
            case "Add info":
                Info.addInfo();
                break;
            case "Edit info":
                Info.editInfo();
                break;
            case "Delete info":
                Info.deleteInfo();
                break;    
            case "Enter":
                Sroki.enterUsers();
                break;
            case "Check medicament":
                Medicaments.checkmed();
                break;
            case "Check srok":
                Medicaments.checksrok();
                break;
            case "Meds":
                Medicaments.meds();
                break;
            case "Edit medicaments":
                Medicaments.edit();
                break;           
                
        }
    }

   
   
}

