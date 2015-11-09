package capaLogicaIntro;

import DAOIntro.DAOException;
import DAOIntro.Retorno;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name = "evaluacionCrearIntro")
@SessionScoped

public class evaluacionCrear implements Serializable {

    private int topico;
    private ArrayList<pregunta> preguntas;
    private String info;
    private pregunta preguntaNueva = new pregunta();
    private pregunta preguntaEdit = new pregunta();
    private String autor;

    public pregunta getPreguntaNueva() {
        return preguntaNueva;
    }

    public void setPreguntaNueva(pregunta preguntaNueva) {
        this.preguntaNueva = preguntaNueva;
    }

    public int getTopico() {
        return topico;
    }

    public void setTopico(int topico) {
        this.topico = topico;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public evaluacionCrear() {
        System.out.println("Estamos en el constructor de evaluacionCrear.java");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map params = externalContext.getRequestParameterMap();
        //Integer categorySelected = new Integer((String) params.get("username" ));
        String t = (String) params.get("idTopico");

        topico = Integer.parseInt(t);
        try {
            System.out.println("pasando por el constructor de las preguntas");
            preguntas = Retorno.getPreguntasByTopic(topico);
        } catch (DAOException ex) {
            Logger.getLogger(evaluacionCrear.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public void irFormulario() {
        try {
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect("/AppTitulo2/faces/Profesor/crearEvaluacion.xhtml?idTopico=" + topico + "");
        } catch (Exception e) {
            System.out.println("Me voy al carajo, no funciona esta redireccion");
        }
    }

    public void procesar() {
        preguntas = new ArrayList<pregunta>();
        System.out.println("el topico es : " + topico);
        info = "select * from preguntas where topic=" + topico;
        try {
            preguntas = Retorno.getPreguntasByTopic(topico);
        } catch (DAOException ex) {
            Logger.getLogger(evaluacionCrear.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect("/AppTitulo2/faces/Profesor/viewPreguntas.xhtml?idTopico=" + topico + "");
        } catch (Exception e) {
            System.out.println("Me voy al carajo, no funciona esta redireccion");
        }
    }

    public void guardar() {
        if (!preguntaNueva.enunciado.equals("") && !preguntaNueva.alt1.equals("") && !preguntaNueva.alt2.equals("") && !preguntaNueva.alt3.equals("") && !preguntaNueva.alt4.equals("")) {
            pregunta p = new pregunta(1, preguntaNueva.enunciado, preguntaNueva.alt1, preguntaNueva.alt2, preguntaNueva.alt3, preguntaNueva.alt4, preguntaNueva.correcto, preguntaNueva.solucion);
            p.idTopico = this.topico;
            System.out.println("el enuncuiado es: " + this.preguntaNueva.enunciado);
            System.out.println(p.toString());

            try {
                Retorno.recordPregunta(p);
                this.preguntas.add(p);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Estasdfasdf", "Estudiante eliminado correctamente"));

            } catch (DAOException ex) {
                System.out.println("Error llamando a recordPregunta() " + ex);
            }

            try {
                FacesContext contex = FacesContext.getCurrentInstance();
                contex.getExternalContext().redirect("/AppTitulo2/faces/Profesor/viewPreguntas.xhtml?idTopico=" + this.topico);

            } catch (Exception e) {
                System.out.println("Me voy al carajo, no funciona esta redireccion");
            }
        } else {
            System.out.println("Hay campos incompletos");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Hay campos incompletos"));

        }

    }

    public void actualizarById() {
        if (!preguntaEdit.enunciado.equals("") && !preguntaEdit.alt1.equals("") && !preguntaEdit.alt2.equals("") && !preguntaEdit.alt3.equals("") && !preguntaEdit.alt4.equals("")) {
            try {
                Retorno.updatePregunta(this.preguntaEdit);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Estasdfasdf", "Estudiante eliminado correctamente"));

            } catch (DAOException ex) {
                System.out.println("Error llamando a updatePregunta() " + ex);
            }

            try {
                FacesContext contex = FacesContext.getCurrentInstance();
                contex.getExternalContext().redirect("/AppTitulo2/faces/Profesor/viewPreguntas.xhtml?idTopico=" + this.topico);

            } catch (Exception e) {
                System.out.println("Me voy al carajo, no funciona esta redireccion");
            }
        } else {
            System.out.println("Hay campos incompletos");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Hay campos incompletos"));

        }

    }

    public void eliminar(int id) {
        try {
            Retorno.removePregunta(id);
        } catch (DAOException ex) {
            System.out.println("error llamando al metodo borrar pregunta " + ex);
        }
        for (int i = 0; i < preguntas.size(); i++) {
            if (id == preguntas.get(i).getId()) {
                preguntas.remove(i);
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pregunta Eliminada", "Pregunta eliminada correctamente"));
    }

    public void editar(int id) {
        try {
            this.setEditPreguntaById(id);
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect("/AppTitulo2/faces/Profesor/editarEvaluacion.xhtml?");

        } catch (Exception e) {
            System.out.println("Me voy al carajo, no funciona esta redireccion");
        }

    }

    public pregunta getPreguntaEdit() {
        return preguntaEdit;
    }

    public void setPreguntaEdit(pregunta preguntaEdit) {
        this.preguntaEdit = preguntaEdit;
    }

    private void setEditPreguntaById(int id) {
        int large = this.preguntas.size();
        for (int i = 0; i < large; i++) {
            if (id == preguntas.get(i).id) {

                this.preguntaEdit = preguntas.get(i);
            }

        }

    }
}
