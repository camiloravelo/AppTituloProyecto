package capaLogica;

import DAO.DAOException;
import DAO.Retorno;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean (name="perfilEstudiante")
@NoneScoped

public class PerfilEstudiante {

    private Estudiante alumno;

    private Estilo estilo;

    public void cambiarPass() {
        try {
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect("/AppTituloProyecto/faces/cambiarPass.xhtml");
        } catch (Exception e) {
            System.out.println("Error direccionando " + e);
        }
    }

    public PerfilEstudiante() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map params = externalContext.getRequestParameterMap();
        String correo = (String) params.get("username");

        try {
            alumno = Retorno.getEstudiante(correo);
        } catch (DAOException ex) {
            System.out.println("error cargando el estudiantes " + ex);
        }

        try {
          estilo=Retorno.getEstiloAprendizaje(correo);
          //ahora estilo tienes los estilos del estudiante
      } catch (DAOException ex) {
            System.out.println("error llamando al metodo");
      }

    }

    public Estilo getEstilo() {
        return estilo;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }

    public Estudiante getAlumno() {
        return alumno;
    }

    public void setAlumno(Estudiante alumno) {
        this.alumno = alumno;
    }

}
