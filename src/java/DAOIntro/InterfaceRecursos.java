package DAOIntro;

import capaLogicaIntro.learningObject;
import java.util.ArrayList;


public interface InterfaceRecursos {
    
    
    public void guardarRecurso(learningObject recurso) throws DAOException;
    public ArrayList<learningObject> leerRecurso() throws DAOException;
    public void borrarRecursos() throws DAOException;
    
    
}
