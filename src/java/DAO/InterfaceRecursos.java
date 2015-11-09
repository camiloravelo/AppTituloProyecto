package DAO;

import capaLogica.learningObject;
import java.util.ArrayList;


public interface InterfaceRecursos {
    
    
    public void guardarRecurso(learningObject recurso) throws DAOException;
    public ArrayList<learningObject> leerRecurso(int idTopicoSeleccionado) throws DAOException;
    public ArrayList<learningObject> leerRecursoOaAprendido(int idTopicoSeleccionado) throws DAOException;
    public void borrarRecursos() throws DAOException;
    
    
    public ArrayList<learningObject> leerNombreTopico() throws DAOException;

    

    
    
}
