/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Asignatura.EvaluacionUnit;
import Asignatura.Topico;
import static DAO.Conexion.pst;
import static DAO.Conexion.rs;
//import static DAO.Conexion.MySQL;
//import static DAO.Conexion.conec;
//import static DAO.Conexion.pst;
//import static DAO.Conexion.rs;
import capaLogica.Estilo;
import capaLogica.Estilo2;
import capaLogica.Estudiante;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CRAVELO
 */
public class MySQLProfesorDAO extends Conexion implements InterfaceProfesor {

    public void addProfesor(String nombre, String correo, String pass1, String pass2, int asignatura) throws DAOException {
        //hay q escribir en las tablas Usuario, Estudiante
        String insert1 = "insert into Usuario values ('" + correo + "','" + pass1 + "',2)";
        String insert2 = "insert into Profesor values ('" + correo + "','" + nombre + "',1,'" + asignatura + "')";

        try {
//            iniciarConexion(MySQL, "localhost");
//            pst = conec.prepareStatement(insert1);
//            pst.execute();
//            pst = conec.prepareStatement(insert2);
//            pst.execute(); 
            Connection con = dbConexion.getConnection();
            pst = con.prepareStatement(insert1);
            pst.execute();
            pst = con.prepareStatement(insert2);
            pst.execute();

        } catch (SQLException ex) {
            Logger.getLogger(MySQLProfesorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(" aqui ponemos el codigo para guardar el profesro");
    }

    public ArrayList<Estilo> getEstilos() throws DAOException {

        ArrayList<Estilo> EstiloCurso = new ArrayList<Estilo>();
        String select = "select fs1,fs2,fs3,fs4 from Estudiante where fs1<> -1 and fs2<> -1 and fs3<> -1 and fs1<> -1";
        try {
//            iniciarConexion(MySQL, "localhost");
//            pst = conec.prepareStatement(select);
//            rs = pst.executeQuery();
              Connection con = dbConexion.getConnection();
            pst = con.prepareStatement(select);
            rs = pst.executeQuery();
            System.out.println(select);
            while (rs.next()) {

                int fs1 = rs.getInt("fs1");
                int fs2 = rs.getInt("fs2");
                int fs3 = rs.getInt("fs3");
                int fs4 = rs.getInt("fs4");
                Estilo nuevoEstilo = new Estilo(fs1, fs2, fs3, fs4);
                // nuevoEstilo.setIsConocido(true);
                EstiloCurso.add(nuevoEstilo);
            }
        } catch (SQLException ex) {
            System.out.println("camilo : " + ex);
        }
        return EstiloCurso;
    }

}
