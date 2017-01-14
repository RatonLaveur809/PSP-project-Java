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
public class Info extends AbstractTableModel{
     // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/med";
    private static final String user = "root";
    private static final String password = "root";
    private  ArrayList<String[]> ResultSet;
    private int colNum = 3;
    private int rowNum;
    private String[] colName={
        "id","Наименование","Описание"
    };
    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public Info(ResultSet rs) {
         ResultSet=new ArrayList<String[]>();  

        try{
            while(rs.next()){
            String[] row = { rs.getString("id"),rs.getString("naim"),rs.getString("infa")};
            ResultSet.add(row);
            }   
        }
        catch(Exception e){
            System.out.println("Всё не круто ((");
        }
    }
    
    public Info(ArrayList<String[]> sets){
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
    
    @Override
    public String getColumnName(int par) {
       return colName[par];
    }
}