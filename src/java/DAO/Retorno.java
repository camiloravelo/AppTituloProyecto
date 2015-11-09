package DAO;

import Asignatura.EvaluacionUnit;
import Asignatura.Topico;
import capaLogica.Estilo;
import capaLogica.Estudiante;
import capaLogica.EstudianteEstilo;
import capaLogica.EstudianteF11;
import capaLogica.EstudianteF12;
import capaLogica.EstudianteF21;
import capaLogica.EstudianteF22;
import capaLogica.EstudianteF31;
import capaLogica.EstudianteF32;
import capaLogica.EstudianteF41;
import capaLogica.EstudianteF42;
import capaLogica.learningObject;
import capaLogica.pregunta;
import capaLogica.preguntaVF;
import java.util.ArrayList;
import profesor.Profesor;

public class Retorno {

    public static ArrayList<Estudiante> getAllEstudiantes() throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().getAllEstudiante();
    }

    public static ArrayList<EstudianteEstilo> getAllEstudianteEstilo() throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().getAllEstudianteEstilo();

    }

    public static ArrayList<EstudianteF11> getAllEstudiantesFs11() throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().getAllEstudiantefs11();
    }

    public static ArrayList<EstudianteF12> getAllEstudiantesFs12() throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().getAllEstudiantefs12();
    }

    public static ArrayList<EstudianteF21> getAllEstudiantesFs21() throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().getAllEstudiantefs21();
    }

    public static ArrayList<EstudianteF22> getAllEstudiantesFs22() throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().getAllEstudiantefs22();
    }

    public static ArrayList<EstudianteF31> getAllEstudiantesFs31() throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().getAllEstudiantefs31();
    }

    public static ArrayList<EstudianteF32> getAllEstudiantesFs32() throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().getAllEstudiantefs32();
    }

    public static ArrayList<EstudianteF41> getAllEstudiantesFs41() throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().getAllEstudiantefs41();
    }

    public static ArrayList<EstudianteF42> getAllEstudiantesFs42() throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().getAllEstudiantefs42();
    }

    public static ArrayList<EvaluacionUnit> getHistorial(String correo) throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().getHistorial(correo);

    }

    public static void addEstudiante(String nombre, String correo, String pass1, String pass2) throws DAOException {
        DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().addEstudiante(nombre, correo, pass1, pass2);

    }

    public static void addProfesor(String nombre, String correo, String pass1, String pass2, int asignatura) throws DAOException {
        DAOFactory.getDAOFactory(DAOFactory.MYSQL).getProfesorDAO().addProfesor(nombre, correo, pass1, pass2, asignatura);

    }

    public static void setEstiloAprendizaje(String correo, int d1, int d2, int d3, int d4) throws DAOException {
        DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().setEstiloAprendizaje(correo, d1, d2, d3, d4);

    }

    public static Estilo getEstiloAprendizaje(String correo) throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().getEstiloAprendizaje(correo);

    }

    public static Estudiante getEstudiante(String correo) throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().getEstudiante(correo);
    }

    public static ArrayList<pregunta> getPreguntasByTopic(int idTopic) throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEvaluacionesDAO().getPreguntasByTopic(idTopic);

    }

    public static ArrayList<preguntaVF> getPreguntasByTopicVF(int idTopic) throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEvaluacionesDAO().getPreguntasByTopicVF(idTopic);

    }

    public static ArrayList<pregunta> getPreguntasByTopicRandom(int idTopic) throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEvaluacionesDAO().getPreguntasByTopicRandom(idTopic);

    }

    public static ArrayList<preguntaVF> getPreguntasByTopicRandomVF(int idTopic) throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEvaluacionesDAO().getPreguntasByTopicRandomVF(idTopic);

    }

    public static void recordPregunta(pregunta p) throws DAOException {
        DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEvaluacionesDAO().recordPregunta(p);

    }

    public static void removePregunta(int idPregunta) throws DAOException {
        DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEvaluacionesDAO().removePregunta(idPregunta);

    }

    public static void recordPreguntaVF(preguntaVF p) throws DAOException {
        DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEvaluacionesDAO().recordPreguntaVF(p);

    }

    public static void removePreguntaVF(int idPregunta) throws DAOException {
        DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEvaluacionesDAO().removePreguntaVF(idPregunta);

    }

    public static ArrayList<Topico> getAllTopic() throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().getAllTopic();

    }

    public static ArrayList<Topico> getTopicosAprobados(String correoEstudiante) throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().getTopicosAprobados(correoEstudiante);

    }

    public static ArrayList<Topico> getTopicosPendientes(String correoEstudiante) throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().getTopicosPendientes(correoEstudiante);

    }

    public static ArrayList<Estilo> getEstilos() throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getProfesorDAO().getEstilos();

    }

    public static void removeEstudiante(String correo) throws DAOException {
        DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().removeEstudiante(correo);

    }

    public static void removeAllEstudiantes() throws DAOException {
        DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().removeAllEstudiantes();

    }

    public static void avanzarRuta(String correo, int idTopico) throws DAOException {

        DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().avanzarRuta(correo, idTopico);
    }

    public static void conocimientoDeclarado(String correo) throws DAOException {

        DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEvaluacionesDAO().conocimientoDeclarado(correo);
    }

    public static void actualizarPass(String correo, String pass) throws DAOException {
        DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEvaluacionesDAO().actualizarPass(correo, pass);
    }

    public static void guardarRecurso(learningObject recurso) throws DAOException {
        DAOFactory.getDAOFactory(DAOFactory.MYSQL).getRecursosDAO().guardarRecurso(recurso);
    }

    public static ArrayList<learningObject> leerNombreTopico() throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getRecursosDAO().leerNombreTopico();
    }
    
    public static ArrayList<learningObject> leerRecursoOaAprendido(int idTopicoSeleccionado) throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getRecursosDAO().leerRecursoOaAprendido(idTopicoSeleccionado);
    }

    public static ArrayList<learningObject> leerRecurso(int idTopicoSeleccionado) throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getRecursosDAO().leerRecurso(idTopicoSeleccionado);
    }

    public static void borrarRecursos() throws DAOException {
        DAOFactory.getDAOFactory(DAOFactory.MYSQL).getRecursosDAO().borrarRecursos();
    }

    public static void updatePregunta(pregunta ep) throws DAOException {
        DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEvaluacionesDAO().updatePregunta(ep);
    }

    public static void updatePreguntaVF(preguntaVF ep) throws DAOException {
        DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEvaluacionesDAO().updatePreguntaVF(ep);
    }

    public static Profesor getProfesor(String correo) throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEvaluacionesDAO().getProfesor(correo);
    }

    public static boolean existeCorreo(String correo) throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().existeCorreo(correo);
    }

    /*
    
    
    
    
     public static Country getCountry(String code) throws DAOException {
     return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getMondialDAO().getCountry(code);
     }

     public static List<Pokemon> getAllPokemons() throws DAOException {
     return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getPokemonDAO().getAllPokemon();
     }
     */
}
