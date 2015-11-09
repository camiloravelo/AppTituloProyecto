package DAO;

public abstract class DAOFactory {

    public static final int MYSQL = 1;
    public static final int POSTGRESQL = 2;

    public abstract InterfaceEstudiante getEstudianteDAO();
    public abstract InterfaceProfesor getProfesorDAO();
    public abstract InterfaceEvaluaciones getEvaluacionesDAO();
    public abstract InterfaceRecursos getRecursosDAO();

    public static DAOFactory getDAOFactory(
            int whichFactory) {

        switch (whichFactory) {
            case MYSQL:
                return new MySQLFactory();

            default:
                return null;
        }
    }
}
