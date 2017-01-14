/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Users extends AbstractTableModel{

    private static final String url = "jdbc:mysql://localhost:3306/med";
    private static final String user = "root";
    private static final String password = "root";
    private  ArrayList<String[]> ResultSet;
    private int colNum = 2;
    private int rowNum;
    private String[] colName={
        "Логин","Пароль"
    };
    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
   
    public Users (ResultSet rs) {
         ResultSet=new ArrayList<String[]>();  

        try{
            while(rs.next()){
            String[] row = { rs.getString("login"),rs.getString("password") };
            ResultSet.add(row);
            }   
        }
        catch(Exception e){
            System.out.println("Юзеры не рулят ((");
        }
    }
    
    public Users (ArrayList<String[]> sets){
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
    public String getColumnName(int param) {
       return colName[param];
    }
    
}
