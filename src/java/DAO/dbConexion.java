/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CRAVELO
 */
public class dbConexion {

     public static Connection conec;
    public static PreparedStatement pst;
    public static ResultSet rs;
    private static Connection con = null;
    //private String DRIVER;
    public static Connection getConnection() throws SQLException {
 if (con == null) {
           
     try {
         String driver = "com.mysql.jdbc.Driver"; //el driver varia segun la DB que usemos
         String url = "jdbc:mysql://localhost:3306/AppTituloProyecto?autoReconnect=true";
         String pwd = "root";
         String usr = "root";
         Class.forName(driver);
         con = DriverManager.getConnection(url, usr, pwd);
         System.out.println("Conectionesfull");
     } catch (ClassNotFoundException ex) {
         Logger.getLogger(dbConexion.class.getName()).log(Level.SEVERE, null, ex);
     }
            }
 
            
        

        return con;
    }

}
