/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabli4ki;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import server.What;

/**
 *
 * @author Admin
 */
public class Users extends AbstractTableModel{

    private static final String url = "jdbc:mysql://localhost:3306/med";
    private static final String user = "root";
    private static final String password = "root";
    private  ArrayList<String[]> ResultSet;
    private int colNum = 2;
    private int rowNum;
    private String[] colName={
        "login","password"
    };
    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
 
    public static void getUsers() throws IOException  {
        String query = "SELECT * FROM users";
 
        try {
            // opening database connection to MySQL server
            con = (Connection) DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = (Statement) con.createStatement();
            // executing SELECT query
            rs = stmt.executeQuery(query);
            
            Users   model = new Users  (rs);
            ArrayList<String[]> message = model.getResultSets();
            What.getOut().writeObject(message);
            
            con.close();
            stmt.close();
            rs.close();
 
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public static void addUsers() throws IOException, SQLException  {
        String query ;
        try {
            String[] addUsers;
            addUsers = (String[])What.getIn().readObject();
            String login = addUsers[0];
            String pass = addUsers[1];
            query = "INSERT INTO users(login,password)" + " VALUES('"+login+"','"+pass+"')"; 
            con = (Connection) DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = (Statement) con.createStatement();
            // executing SELECT query
            stmt.execute(query);
            con.close();
            stmt.close();
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void editUsers() throws IOException, SQLException  {
        String query ;
        try {
            String[] editUsers;
            editUsers = (String[])What.getIn().readObject();
            String login = editUsers[1];
            String pass = editUsers[2];
  
            query= "UPDATE users " +"SET login = '"+login+"', password = '"+ pass +"' WHERE login = "+login;
            con = (Connection) DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = (Statement) con.createStatement();
            // executing SELECT query
            stmt.executeUpdate(query);
            con.close();
            stmt.close();
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void deleteUsers() throws SQLException, IOException {
        String query ;
        try {
            String login = (String)What.getIn().readObject();
           
            query= "DELETE FROM users " +
                   "WHERE login = "+login;
            con = (Connection) DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = (Statement) con.createStatement();
            // executing SELECT query
            stmt.executeUpdate(query);
            con.close();
            stmt.close();
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        } //To change body of generated methods, choose Tools | Templates. //To change body of generated methods, choose Tools | Templates.
    }

    public Users (ResultSet rs) {
         ResultSet=new ArrayList<String[]>();  

        try{
            while(rs.next()){
            String[] row = { rs.getString("login"),rs.getString("password") };
            ResultSet.add(row);
            }   
        }
        catch(Exception e){
            System.out.println("Юзеры не рулят");
        }
    }
    
    public Users (ArrayList<String[]> ss){
        ResultSet = ss;
    }
    
    public ArrayList<String[]> getResultSets() {
      
        return ResultSet;
    }
    @Override
    public int getRowCount() {
         return ResultSet.size();
    }

    @Override
    public int getColumnCount() {
         return colNum;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         String[] row = ResultSet.get(rowIndex);
       return row[columnIndex];
    }
    
    @Override
    public String getColumnName(int param) {
       return colName[param];
    }
    
}
