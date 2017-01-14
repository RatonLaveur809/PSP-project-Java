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
public class Info extends AbstractTableModel{
     // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/med";
    private static final String user = "root";
    private static final String password = "root";
    private static final String coding="UTF8";
    private  ArrayList<String[]> ResultSet;
    private int colNum = 3;
    private int rowNum;
    private String[] colName={
        "id","naim","infa"};
    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement st;
    private static ResultSet rs;
    public static void addInfo() throws IOException, SQLException  {
        
        String query ;
        try {
            String[] addInfo;
            addInfo= (String[])What.getIn().readObject();
            
            String id = addInfo[0];
            String naim = addInfo[1];
            String infa = addInfo[2];
            query = "INSERT INTO info(id,naim,infa)" + " VALUES('"+id+"','"+naim+"','"+infa+"')"; 
            con = (Connection) DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            st = (Statement) con.createStatement();
            // executing SELECT query
            st.execute(query);
            con.close();
            st.close();
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Info.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void editInfo() throws IOException, SQLException  {
        String query ;
        try {
            String[] editInfo;
            editInfo= (String[])What.getIn().readObject();
            String id = editInfo[0];
            String naim = editInfo[1];
            String infa = editInfo[2];
           
            query= "UPDATE info " +"SET id = '"+id+"', naim = '"+ naim+"',infa='"+ infa+"' WHERE id = "+id;
            con = (Connection) DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            st = (Statement) con.createStatement();
            // executing SELECT query
            st.executeUpdate(query);
            con.close();
            st.close();
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Info.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void deleteInfo() throws SQLException, IOException {
        String query ;
        try {
            String id = (String)What.getIn().readObject();
           
            query= "DELETE FROM info " +
                   "WHERE id = "+id;
            con = (Connection) DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            st = (Statement) con.createStatement();
            // executing SELECT query
            st.executeUpdate(query);
            con.close();
            st.close();
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Info.class.getName()).log(Level.SEVERE, null, ex);
        } //To change body of generated methods, choose Tools | Templates. //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void getInfo() throws IOException  {
        String query = "SELECT * FROM info";
 
        try {
            // opening database connection to MySQL server
            con = (Connection) DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            st = (Statement) con.createStatement();
            // executing SELECT query
            rs = st.executeQuery(query);
            
            Info  model = new Info (rs);
            ArrayList<String[]> message = model.getResultSets();
            What.getOut().writeObject(message);
            
            con.close();
            st.close();
            rs.close();
 
        } catch (SQLException ex) {
            Logger.getLogger(Info.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public Info(ResultSet rs) {
         ResultSet=new ArrayList<String[]>();  

        try{
            while(rs.next()){
            String[] row = { rs.getString("id"),rs.getString("naim"),rs.getString("infa")};
            ResultSet.add(row);
            }   
        }
        catch(Exception e){
            System.out.println("Вечно что-то не так!");
        }
    }
    
    public Info(ArrayList<String[]> ss){
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