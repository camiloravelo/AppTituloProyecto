/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Asignatura.EvaluacionUnit;
import Asignatura.Topico;
import capaLogica.Estilo;
import capaLogica.Estilo2;
import capaLogica.Estudiante;
import java.util.ArrayList;
/**
 *
 * @author CRAVELO
 */
public interface InterfaceProfesor {
    
    
     public void addProfesor(String nombre, String correo, String pass1, String pass2, int asignatura) throws DAOException;
     public ArrayList<Estilo> getEstilos()throws DAOException;
 

}
