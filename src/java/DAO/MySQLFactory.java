package DAO;

import DAO.DAOFactory;

public class MySQLFactory extends DAOFactory {

    public InterfaceEstudiante getEstudianteDAO() {
        return new MySQLEstudianteDAO();
    }

    public InterfaceProfesor getProfesorDAO() {
        return new MySQLProfesorDAO();
    }

    public InterfaceEvaluaciones getEvaluacionesDAO() {
        return new MySQLEvaluacionesDAO();
    }

    public InterfaceRecursos getRecursosDAO() {
        return new MySQLRecursosDAO();
    }

}
