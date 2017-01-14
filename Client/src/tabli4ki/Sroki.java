package tabli4ki;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


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
        "Партия","Срок годности"
    };
    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
  
    public Sroki(ResultSet rs) {
         ResultSet=new ArrayList<String[]>();  

        try{
            while(rs.next()){
            String[] row = { rs.getString("partia"),rs.getString("srok")};
            ResultSet.add(row);
            }   
        }
        catch(Exception e){
            System.out.println("Сроки не рулят ((");
        }
    }
    
    public Sroki(ArrayList<String[]> sets){
        ResultSet = sets;
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
    
    public String getColumnName(int param) {
       return colName[param];
    }
    
}