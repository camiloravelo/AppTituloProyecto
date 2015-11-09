
package DAOIntro;

import DAOIntro.DAOFactory;



public class MySQLFactory extends DAOFactory {

    public InterfaceEstudiante getEstudianteDAO() {
        return new MySQLEstudianteDAO();
    }

     public InterfaceEvaluaciones getEvaluacionesDAO() {
        return new MySQLEvaluacionesDAO();
    }

    public InterfaceRecursos getRecursosDAO() {
return new MySQLRecursosDAO();
        }


   

  
}
