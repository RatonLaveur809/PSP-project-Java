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
public class Sroki extends AbstractTableModel{
     // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/med";
    private static final String user = "root";
    private static final String password = "root";
    private  ArrayList<String[]> ResultSet;
    private int colNum = 2;
    private int rowNum;
    private String[] colName={
        "partia","srok"};
    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    public static void addSrok() throws IOException, SQLException  {
        String query ;
        try {
            String[] addSrok;
            addSrok= (String[])What.getIn().readObject();
            String partia = addSrok[0];
            String srok = addSrok[1];
            query = "INSERT INTO sroki(partia,srok)" + " VALUES("+partia+",'"+srok+"')"; 
            con = (Connection) DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = (Statement) con.createStatement();
            // executing SELECT query
            stmt.execute(query);
            con.close();
            stmt.close();
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Sroki.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void editSrok() throws IOException, SQLException  {
        String query ;
        try {
            String[] editSrok;
            editSrok= (String[])What.getIn().readObject();
            String partia = editSrok[0];
            String srok = editSrok[1];
           
            query= "UPDATE sroki " +"SET partia = "+partia+", srok = '"+ srok+" WHERE partia = "+partia;
            con = (Connection) DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = (Statement) con.createStatement();
            // executing SELECT query
            stmt.executeUpdate(query);
            con.close();
            stmt.close();
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Sroki.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void deleteSrok() throws SQLException, IOException {
        String query ;
        try {
            String id = (String)What.getIn().readObject();
           
            query= "DELETE FROM sroki " +
                   "WHERE id = "+id;
            con = (Connection) DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = (Statement) con.createStatement();
            // executing SELECT query
            stmt.executeUpdate(query);
            con.close();
            stmt.close();
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Sroki.class.getName()).log(Level.SEVERE, null, ex);
        } //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void getSroki() throws IOException  {
        String query = "SELECT * FROM sroki";
 
        try {
            // opening database connection to MySQL server
            con = (Connection) DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = (Statement) con.createStatement();
            // executing SELECT query
            rs = stmt.executeQuery(query);
            
            Sroki  model = new Sroki (rs);
            ArrayList<String[]> message = model.getResultSets();
            What.getOut().writeObject(message);
            
            con.close();
            stmt.close();
            rs.close();
 
        } catch (SQLException ex) {
            Logger.getLogger(Sroki.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public Sroki(ResultSet rs) {
         ResultSet=new ArrayList<String[]>();  

        try{
            while(rs.next()){
            String[] row = { rs.getString("partia"),rs.getString("srok")};
            ResultSet.add(row);
            }   
        }
        catch(Exception e){
            System.out.println("Пичаль очередная ((");
        }
    }
    
    public Sroki(ArrayList<String[]> ss){
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
    
    public static void enterUsers() throws IOException, ClassNotFoundException, SQLException{
        
        String query = "SELECT * FROM users";
        String message="wrong";
        String[] logpas;
        logpas = (String[])What.getIn().readObject();
        String log=logpas[0];
        String pass=logpas[1];
            con = (Connection) DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = (Statement) con.createStatement();
            // executing SELECT query
            rs = stmt.executeQuery(query);
        try {
            while(rs.next()){
                String rowl =  rs.getString("login");
                String rowp =  rs.getString("password");
                if(rowl.equals(log) && rowp.equals(pass)){
                   message="right";
                   if(rowl.equals("admin")&&rowp.equals("admin"))
                   {
                      message="admin"; 
                   }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        What.getOut().writeObject(message); 
           
}

}
