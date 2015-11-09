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
import java.util.ArrayList;

public interface InterfaceEstudiante {

    public ArrayList<Estudiante> getAllEstudiante() throws DAOException;

    public ArrayList<EstudianteEstilo> getAllEstudianteEstilo() throws DAOException;

    public ArrayList<EstudianteF11> getAllEstudiantefs11() throws DAOException;

    public ArrayList<EstudianteF12> getAllEstudiantefs12() throws DAOException;

    public ArrayList<EstudianteF21> getAllEstudiantefs21() throws DAOException;

    public ArrayList<EstudianteF22> getAllEstudiantefs22() throws DAOException;

    public ArrayList<EstudianteF31> getAllEstudiantefs31() throws DAOException;

    public ArrayList<EstudianteF32> getAllEstudiantefs32() throws DAOException;

    public ArrayList<EstudianteF41> getAllEstudiantefs41() throws DAOException;

    public ArrayList<EstudianteF42> getAllEstudiantefs42() throws DAOException;

    public ArrayList<EvaluacionUnit> getHistorial(String correo) throws DAOException;

    public void addEstudiante(String nombre, String correo, String pass1, String pass2) throws DAOException;

    public void setEstiloAprendizaje(String correo, int d1, int d2, int d3, int d4) throws DAOException;

    public Estilo getEstiloAprendizaje(String correo) throws DAOException;

    public Estudiante getEstudiante(String correo) throws DAOException;

    //metodos para la ruta de aprendizaje

    public ArrayList<Topico> getAllTopic() throws DAOException;

    public ArrayList<Topico> getTopicosAprobados(String correoEstudiante) throws DAOException;

    public ArrayList<Topico> getTopicosPendientes(String correoEstudiante) throws DAOException;

    public void avanzarRuta(String correo, int idTopico) throws DAOException;

    public void removeEstudiante(String correo) throws DAOException;

    public void removeAllEstudiantes() throws DAOException;

    public boolean existeCorreo(String correo) throws DAOException; 
    
    public int getPromedio() throws DAOException;
}
