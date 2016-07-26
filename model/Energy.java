/*******************************************************************
* File Name     : Energy.java
* Programmer    : Azis Ramdhan Darojat
* Date          : 31/05/2016
* Email         : azisramdhan92@gmail.com
* Website/Blog  : ramdhansan.blogspot.com
* Deskripsi     : Use to execute query
*
********************************************************************/

//package model/class yang digunakan untuk mengakses database 
package model;

//import package controller
import controller.GameController;

//class Energy diset public agar bisa diakses oleh class controller
public class Energy extends DB{

    /***************
     * Konstruktor
     ***************/
    public Energy()throws Exception {
        //memanggil konstuktor parent class
        super();
    }

    /*************************************************************************************************
     * This method is use to execute "select" query that get data from database using driver manager
     *************************************************************************************************/
    public void getAllData(){
        try{
            //deklarasi dan inisialisasi atribut query
            String query = "SELECT * FROM PLAYER";
            System.out.println(query);
            //memanggil method createQuery pada class DB
            createQuery(query);
        //jika proses query tidak berjalan    
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

    /*************************************************************************************************
     * This method is use to execute "insert" query that put data from database using driver manager
     *************************************************************************************************/
    public void insertData(String username){
        try{
            //deklarasi dan inisialisasi atribut query
            String query="INSERT INTO PLAYER VALUES('"+username+"',0);";
            System.out.println(query);
            //memanggil method createUpdate pada class DB
            createUpdate(query);
        //jika proses query tidak berjalan
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

    /*************************************************************************************************
     * This method is use to execute "update" query that put data from database using driver manager
     *************************************************************************************************/
    public void updateData(int energy, String username){
        try{
            //deklarasi dan inisialisasi atribut query
            String query="UPDATE PLAYER SET energy = '"+energy+"' WHERE USERNAME = '"+username+"';";
            System.out.println(query);
            //memanggil method createUpdate pada class DB
            createUpdate(query);
        //jika proses query tidak berjalan
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
}
