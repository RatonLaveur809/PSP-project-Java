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
public class Medicaments extends AbstractTableModel{
     // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/med";
    private static final String user = "root";
    private static final String password = "root";

   
    private  ArrayList<String[]> ResultSet;
    private int colNum = 5;
    private int rowNum;
    private String[] colName={
        "id","naim","cost","count","partia"};
    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement st;
    private static ResultSet rs;
   
    
    public static void getMedicaments() throws IOException  {
        String query = "SELECT * FROM medicaments";
 
        try {
            // opening database connection to MySQL server
            con = (Connection) DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            st = (Statement) con.createStatement();
            // executing SELECT query
            rs = st.executeQuery(query);
            
            Medicaments model = new Medicaments (rs);
            ArrayList<String[]> message = model.getResultSets();
            What.getOut().writeObject(message);
            
            con.close();
            st.close();
            rs.close();
 
        } catch (SQLException ex) {
            Logger.getLogger(Medicaments.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
     public static void meds() throws IOException, ClassNotFoundException, SQLException {
            String[] meds;
            String query;
            meds = (String[])What.getIn().readObject();
            String id = meds[0];
            String naim = meds[1];
            String cost = meds[2];
            String count = meds[3];
            String partia = meds[4];
            
            query = "INSERT INTO medicaments(id,naim,cost,count,partia)" + " VALUES("+id+","+naim+",'"+cost+"','"+count+"','"+partia +"')";             
            con = (Connection) DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            st = (Statement) con.createStatement();
            // executing SELECT query
            st.execute(query);            
            con.close();
            st.close();
    }
     
     public static void edit() throws IOException, ClassNotFoundException, SQLException {
      String query_update;
            String id = (String)What.getIn().readObject();
            query_update= "UPDATE medicaments " +"' WHERE id = "+id;            
            con = (Connection) DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            st = (Statement) con.createStatement();
            // executing SELECT query
            st.executeUpdate(query_update);
            con.close();
            st.close();
    }

    public Medicaments(ResultSet rs) {
         ResultSet=new ArrayList<String[]>();  

        try{
            while(rs.next()){
            String[] row = { rs.getString("id"),rs.getString("naim"),rs.getString("cost"),rs.getString("count"),rs.getString("partia")};
            ResultSet.add(row);
            }   
        }
        catch(Exception e){
            System.out.println("Ну вооот ((");
        }
    }
    
    public Medicaments(ArrayList<String[]> ss){
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
    
    /**
     *
     * @throws IOException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void checkmed() throws IOException,  SQLException,   ClassNotFoundException{
        
        String query = "SELECT *FROM medicaments ";
        String message="wrong";
        String id;
        id = (String)What.getIn().readObject();
        String naim = "";
        String cost = "";
        String count = "";
            con = (Connection) DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            st = (Statement) con.createStatement();
            // executing SELECT query
            rs = st.executeQuery(query);
        try {
            while(rs.next()){
                String row =  rs.getString("id");
                if(row.equals(id)){
                    naim = rs.getString("naim");
                    cost = rs.getString("cost");
                    count = rs.getString("count");
                    message="right";
                }
                
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(Medicaments.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(message.equals("right"))
        {
        What.getOut().writeObject(message); 
        String[] array={naim,cost,count};
        What.getOut().writeObject(array); 
        }
        if(message.equals("wrong"))
        {
          What.getOut().writeObject(message);
        }
       
}
    public static void checksrok() throws IOException, ClassNotFoundException, SQLException{
        
        String query = "SELECT * FROM sroki";
        String message="wrong";
        String id;
        String row = null;
        id = (String)What.getIn().readObject();
        String srok = "";
       
            con = (Connection) DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            st = (Statement) con.createStatement();
            // executing SELECT query
            rs = st.executeQuery(query);
        try {
            while(rs.next()){
                String str =  rs.getString("book_id");
                
                if(str.equals(id)){
                    srok = rs.getString("srok");
                    row=rs.getString("id");
                    message="right";
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Medicaments.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(message.equals("right"))
        {
        What.getOut().writeObject(message); 
        String[] array={srok,id};
        What.getOut().writeObject(array); 
        }
        if(message.equals("wrong"))
        {
          What.getOut().writeObject(message);
        }
           
}
   
}