package capaLogicaIntro;

import OntologiaIntro.Consultas;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

@ManagedBean (name="vistaRecursoIntro")
@ViewScoped

public class VistaRecurso {

  learningObject  objeto; 
  String id;
  Estilo estilo;

    public learningObject getObjeto() {
        return objeto;
    }

    public void setObjeto(learningObject objeto) {
        this.objeto = objeto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
  
  
    public VistaRecurso() {
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map params = externalContext.getRequestParameterMap();
        //Integer categorySelected = new Integer((String) params.get("username" ));
        String recurso = (String) params.get("idRecurso");

        System.out.println("el id es: "+recurso);
        id=recurso;
        
        objeto=new learningObject();
        Consultas con= new Consultas();
        objeto=con.getOAUnit(id);
        System.out.println("objeto rescatado con exito");
        estilo=new Estilo(objeto.getEstilo1(),objeto.getEstilo2(),objeto.getEstilo3(),objeto.getEstilo4());
        
    }

    public Estilo getEstilo() {
        return estilo;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }
    
    
    
}
