package DAOIntro;

import capaLogicaIntro.pregunta; //cambiar
import java.util.ArrayList;
import profesor.Profesor;


public interface InterfaceEvaluaciones {
    public ArrayList<pregunta> getPreguntasByTopic(int topic) throws DAOException;
    public ArrayList<pregunta> getPreguntasByTopicRandom(int topic) throws DAOException;

    public void recordPregunta(pregunta nuevaPregunta) throws DAOException;
    public void removePregunta(int idPregunta) throws DAOException;
    public void conocimientoDeclarado(String correo) throws DAOException;
    public void actualizarPass(String correo, String pass) throws DAOException;
    public Profesor getProfesor(String correo) throws DAOException;
    public void updatePregunta (pregunta ep) throws DAOException;
            
}
