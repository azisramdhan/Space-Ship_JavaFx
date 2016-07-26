/*******************************************************************
* File Name     : DB.java
* Programmer    : Azis Ramdhan Darojat
* Date          : 31/05/2016
* Email         : azisramdhan92@gmail.com
* Website/Blog  : ramdhansan.blogspot.com
* Deskripsi     : Use to Connect Program to Database
*
********************************************************************/

//package model/class yang digunakan untuk mengakses database 
package model;

//import library yang dibutuhkan program
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

//class DB 
public class DB{

    /* Deklarasi atribut global */
    private String ConAddress = "jdbc:mysql://localhost:3306/fightthechallenge?user=root&password=";
    private Statement stmt = null;
    private ResultSet rs = null;
    private Connection conn = null;


    /***************
     * Konstruktor
     ***************/
    public DB() throws Exception, SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            conn = DriverManager.getConnection(ConAddress);
            conn.setTransactionIsolation(conn.TRANSACTION_READ_UNCOMMITTED);
        }catch(SQLException e){
            throw e;
        }
    }


    /************************************************
     * This method is use to execute "select" query 
     ************************************************/
    public void createQuery(String Query)throws Exception,SQLException{
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(Query);
            if(stmt.execute(Query)){
                rs=stmt.executeQuery(Query);
            }
        }catch(SQLException es){
            throw es;
        }
    }

    /***********************************************************
     * This method is use to execute "update or insert" query 
     ***********************************************************/
    public void createUpdate(String Query)throws Exception,SQLException{
        try{
            stmt = conn.createStatement();
            int hasil = stmt.executeUpdate(Query);
        }catch(SQLException es){
            throw es;
        }
    }

    /**********************************************************************************
     * Method ini digunakan untuk mengembalikan nilai yang telah diambil dari database
     **********************************************************************************/
    public ResultSet getResult() throws Exception{
        ResultSet Temp = null;
        try{
            return rs;
        }catch(Exception ex){
            return Temp;
        }
    }

     /**********************************************************************************
     * Method ini digunakan untuk menghentikan pengambilan data dari database
     **********************************************************************************/ 
    public void closeResult()throws SQLException, Exception{
        if(rs != null){
            try{
                rs.close();
            }catch (SQLException sqlEx) {
                rs=null;
                throw sqlEx;
            }
        }
        if(stmt != null){
            try{
                stmt.close();
            }catch(SQLException sqlEx){
                stmt = null;
                throw sqlEx;
            }
        }
    }

     /**********************************************************************************
     * Method ini digunakan untuk menghentikan koneksi program dengan database
     **********************************************************************************/   
    public void closeConnection()throws SQLException,Exception{
        if(conn != null){
            try{
                conn.close();
            }catch(SQLException sqlEx){
                conn = null;
            }
        }
    }

}