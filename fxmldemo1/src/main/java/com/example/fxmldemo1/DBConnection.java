package com.example.fxmldemo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
    static String user="root";
    static String pass="";
    static String url="jdbc:mysql://localhost/dbperpustakaan";
    static String driver="com.mysql.cj.jdbc.Driver";
    public static Connection getConn(){
        Connection conn=null;
        try{
            Class.forName(driver);
            conn= DriverManager.getConnection(url,user,pass);
// System.out.println("koneksi berhasil");
        }catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException();
        }
        return conn;
    }
}