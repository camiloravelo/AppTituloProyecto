package capaLogica;

import Ontologia.Consultas;
import Ontologia.Inserciones;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

@ManagedBean (name="tablaLearningObject")
@ViewScoped
public class TablaLearningObject {

    public ArrayList<learningObject> getObjetos() {
        return objetos;
    }

    public void setObjetos(ArrayList<learningObject> objetos) {
        this.objetos = objetos;
    }

    ArrayList<learningObject> objetos = new ArrayList<learningObject>();
    learningObject objetoUnit = new learningObject();

    public learningObject getObjetoUnit() {
        return objetoUnit;
    }

    public void setObjetoUnit(learningObject objetoUnit) {
        this.objetoUnit = objetoUnit;
    }

    public TablaLearningObject() {
        Consultas con = new Consultas();
        objetos = con.getAllLearningObjects();
    }

    public void quiarUno() {
        objetos.remove(1);
    }

    public void eliminarObjeto(String id) {
        System.out.println("entramos el metodo eliminar id: " + id);
        //primero lo eliminamos de la estrucutura
        for (int i = 0; i < this.objetos.size(); i++) {
            if (id == objetos.get(i).getId()) {
                objetos.remove(i);
                System.out.println("ok removido");
            }           
        }
        //luego lo eliminamos de la ontologia
        Inserciones delete = new Inserciones();
        delete.eliminar(id);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Recurso Eliminado", "Recurso eliminado correctamente"));


    }
    public void verObjeto(String id) {
        for (int i = 0; i < objetos.size(); i++) {
            if (id.equals(objetos.get(i).getId())) {
                System.out.println("encontramos el id correcto");
                System.out.println(" es el numero " + i);

                objetoUnit = objetos.get(i);

            } else {
                System.out.println("no encontrado en  " + i);
            }

        }

        try {
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect("/AppTituloProyecto/faces/Profesor/ViewRecurso.xhtml?idRecurso=" + objetoUnit.getId());
        } catch (Exception e) {
            System.out.println("Error direccionando");
        }

    }

}
