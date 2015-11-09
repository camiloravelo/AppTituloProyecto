package DAOIntro;

import AsignaturaIntro.EvaluacionUnit;
import AsignaturaIntro.Topico;
import capaLogicaIntro.Estilo;
import capaLogicaIntro.Estudiante;
import capaLogicaIntro.EstudianteEstilo;
import capaLogicaIntro.EstudianteF11;
import capaLogicaIntro.EstudianteF12;
import capaLogicaIntro.EstudianteF21;
import capaLogicaIntro.EstudianteF22;
import capaLogicaIntro.EstudianteF31;
import capaLogicaIntro.EstudianteF32;
import capaLogicaIntro.EstudianteF41;
import capaLogicaIntro.EstudianteF42;
import capaLogicaIntro.learningObject;
import capaLogicaIntro.pregunta; //cambiar
import java.util.ArrayList;
import profesor.Profesor;

public class Retorno {

    public static ArrayList<Estudiante> getAllEstudiantes() throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().getAllEstudiante();
    }

    public static ArrayList<AsignaturaIntro.EvaluacionUnit> getHistorial(String correo) throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().getHistorial(correo);

    }

    public static void addEstudiante(String nombre, String correo, String pass1, String pass2) throws DAOException {
        DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().addEstudiante(nombre, correo, pass1, pass2);

    }

    public static void setEstiloAprendizaje(String correo, int d1, int d2, int d3, int d4) throws DAOException {
        DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().setEstiloAprendizaje(correo, d1, d2, d3, d4);

    }

    public static Estilo getEstiloAprendizaje(String correo) throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().getEstiloAprendizaje(correo);

    }

      public static ArrayList<EstudianteEstilo> getAllEstudianteEstilo() throws DAOException{
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

    
    public static Estudiante getEstudiante(String correo) throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().getEstudiante(correo);
    }

    public static ArrayList<capaLogicaIntro.pregunta> getPreguntasByTopic(int idTopic) throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEvaluacionesDAO().getPreguntasByTopic(idTopic);

    }

    public static ArrayList<capaLogicaIntro.pregunta> getPreguntasByTopicRandom(int idTopic) throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEvaluacionesDAO().getPreguntasByTopicRandom(idTopic);

    }

    public static void recordPregunta(pregunta p) throws DAOException {
        DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEvaluacionesDAO().recordPregunta(p);

    }

    public static void removePregunta(int idPregunta) throws DAOException {
        DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEvaluacionesDAO().removePregunta(idPregunta);

    }

    public static ArrayList<AsignaturaIntro.Topico> getAllTopic() throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().getAllTopic();

    }

    public static ArrayList<AsignaturaIntro.Topico> getTopicosAprobados(String correoEstudiante) throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().getTopicosAprobados(correoEstudiante);

    }

    public static ArrayList<AsignaturaIntro.Topico> getTopicosPendientes(String correoEstudiante) throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEstudianteDAO().getTopicosPendientes(correoEstudiante);

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

    public static ArrayList<capaLogicaIntro.learningObject> leerRecurso() throws DAOException {
        return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getRecursosDAO().leerRecurso();
    }

    public static void borrarRecursos() throws DAOException {
        DAOFactory.getDAOFactory(DAOFactory.MYSQL).getRecursosDAO().borrarRecursos();
    }

    public static void updatePregunta(pregunta ep) throws DAOException {
        DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEvaluacionesDAO().updatePregunta(ep);
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
