package profesor;

import DAO.DAOException;
import DAO.Retorno;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;


@ManagedBean (name="perfilProfesor")
@ViewScoped
public class PerfilProfesor {

    Profesor profesor;
    
    public PerfilProfesor() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map params = externalContext.getRequestParameterMap();
        String correo = (String) params.get("username");

        try {
            profesor=Retorno.getProfesor(correo);
        } catch (DAOException ex) {
            System.out.println("error cargando el profesor " + ex);
        } 
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    
    public void cambiarPass() {
        try {
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect("/AppTituloProyecto/faces/cambiarPass.xhtml");
        } catch (Exception e) {
            System.out.println("Me voy al carajo, no funciona esta redireccion " + e);
        }
    }
    
}
