package DAO;

import Ontologia.ManejaConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Conexion {
    public static Connection conec;
    public static PreparedStatement pst;
    public static ResultSet rs;
    public final static int MySQL = 1;
    public final static int PostgreSQL = 2;
    public final static String DATABASE="AppTituloProyecto";
    
    private String HOST;
    private String ACCOUNT;
    private String PASSWORD;
    private String DRIVER;

    public void iniciarConexion(int tipo, String ip) throws DAOException {
        switch (tipo) {
            case 1:
                HOST = "jdbc:mysql://" + ip + ":3306/";
                ACCOUNT = "root";
                PASSWORD = "root";
                DRIVER = "com.mysql.jdbc.Driver";
                break;
            case 2:
                HOST = "jdbc:postgresql://localhost:5432/";
                ACCOUNT = "rmoyano";
                PASSWORD = "postgres";
                DRIVER = "org.postrgresql.Driver";
                break;
            default:
                break;
        }
        try {
            Class.forName(DRIVER);
            conec = DriverManager.getConnection(HOST + DATABASE, ACCOUNT, PASSWORD);
            System.out.println("Driver OK");
            System.out.println("el pass es: "+ManejaConfig.pass);
        } catch (Exception ex) {
            throw new DAOException("Error en el Inicio de Conexion");
        }

    }

    public void cerrarConexion() throws DAOException {
        try {
            if (conec != null) {
                conec.close();
                conec = null;
            }
            if (pst != null) {
                pst.close();
                pst = null;
            }
            if (rs != null) {
                rs.close();
                rs = null;
            }
        } catch (Exception e) {
            throw new DAOException("Error en el cierre Conexion");
        }
    }
    
}
