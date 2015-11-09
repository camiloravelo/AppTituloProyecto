package capaLogica;

import DAO.DAOException;
import DAO.Retorno;
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

@ManagedBean(name = "evaluacionCrear")
@SessionScoped

public class evaluacionCrear implements Serializable {

    private int topico;
    private int tipo; //tipo de pregunta verdadero 1 alternativas 2 v o f 
    private ArrayList<pregunta> preguntas;
    private ArrayList<preguntaVF> preguntasVF;
    private String info;
    private pregunta preguntaNueva = new pregunta();
    private preguntaVF preguntaNuevaVF = new preguntaVF();
    private pregunta preguntaEdit = new pregunta();
    private preguntaVF preguntaEditVF = new preguntaVF();
    private String autor;

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public ArrayList<preguntaVF> getPreguntasVF() {
        return preguntasVF;
    }

    public void setPreguntasVF(ArrayList<preguntaVF> preguntasVF) {
        this.preguntasVF = preguntasVF;
    }

    public preguntaVF getPreguntaNuevaVF() {
        return preguntaNuevaVF;
    }

    public void setPreguntaNuevaVF(preguntaVF preguntaNuevaVF) {
        this.preguntaNuevaVF = preguntaNuevaVF;
    }

    public preguntaVF getPreguntaEditVF() {
        return preguntaEditVF;
    }

    public void setPreguntaEditVF(preguntaVF preguntaEditVF) {
        this.preguntaEditVF = preguntaEditVF;
    }

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

    public void irFormulario(int i) {

        try {
            if (i == 1) {

                FacesContext contex = FacesContext.getCurrentInstance();
                contex.getExternalContext().redirect("/AppTituloProyecto/faces/Profesor/crearEvaluacion.xhtml?idTopico=" + topico + "");

            } else {

                FacesContext contex = FacesContext.getCurrentInstance();
                contex.getExternalContext().redirect("/AppTituloProyecto/faces/Profesor/crearEvaluacionVF.xhtml?idTopico=" + topico + "");

            }
        } catch (Exception e) {
            System.out.println("Me voy al carajo, no funciona esta redireccion");
        }
    }

    public void procesar() {
        preguntas = new ArrayList<pregunta>();
        preguntasVF = new ArrayList<preguntaVF>();
        System.out.println("el topico es : " + topico);
        info = "select * from preguntas where tipo <> '' and topic=" + topico;
        try {
            preguntas = Retorno.getPreguntasByTopic(topico);
            preguntasVF = Retorno.getPreguntasByTopicVF(topico);
        } catch (DAOException ex) {
            Logger.getLogger(evaluacionCrear.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect("/AppTituloProyecto/faces/Profesor/viewPreguntas.xhtml?idTopico=" + topico + "");
        } catch (Exception e) {
            System.out.println("Me voy al carajo, no funciona esta redireccion");
        }
    }

    public void guardar() {
        if (!preguntaNueva.enunciado.equals("") && !preguntaNueva.alt1.equals("") && !preguntaNueva.alt2.equals("") && !preguntaNueva.alt3.equals("") && !preguntaNueva.alt4.equals("")) {
            pregunta p = new pregunta(1, preguntaNueva.enunciado, preguntaNueva.alt1, preguntaNueva.alt2, preguntaNueva.alt3, preguntaNueva.alt4, preguntaNueva.correcto, preguntaNueva.solucion, 1);
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
                contex.getExternalContext().redirect("/AppTituloProyecto/faces/Profesor/viewPreguntas.xhtml?idTopico=" + this.topico);

            } catch (Exception e) {
                System.out.println("Me voy al carajo, no funciona esta redireccion");
            }
        } else {
            System.out.println("Hay campos incompletos");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Hay campos incompletos"));

        }

    }

    public void guardarVF() {
        if (!preguntaNuevaVF.enunciado.equals("") && !preguntaNuevaVF.alt1.equals("") && !preguntaNuevaVF.alt2.equals("")) {

            preguntaVF p = new preguntaVF(1, preguntaNuevaVF.enunciado, preguntaNuevaVF.alt1, preguntaNuevaVF.alt2, preguntaNuevaVF.correcto, preguntaNuevaVF.solucion, 2);

            p.idTopico = this.topico;
            System.out.println("el enuncuiado es: " + this.preguntaNuevaVF.enunciado);
            System.out.println(p.toString());

            try {
                Retorno.recordPreguntaVF(p);

                this.preguntasVF.add(p);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Estasdfasdf", "Estudiante eliminado correctamente"));

            } catch (DAOException ex) {
                System.out.println("Error llamando a recordPregunta() " + ex);
            }

            try {
                FacesContext contex = FacesContext.getCurrentInstance();
                contex.getExternalContext().redirect("/AppTituloProyecto/faces/Profesor/viewPreguntas.xhtml?idTopico=" + this.topico);

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
                contex.getExternalContext().redirect("/AppTituloProyecto/faces/Profesor/viewPreguntas.xhtml?idTopico=" + this.topico);

            } catch (Exception e) {
                System.out.println("Me voy al carajo, no funciona esta redireccion");
            }
        } else {
            System.out.println("Hay campos incompletos");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Hay campos incompletos"));

        }

    }

    public void actualizarByIdVF() {
        if (!preguntaEditVF.enunciado.equals("") && !preguntaEditVF.alt1.equals("") && !preguntaEditVF.alt2.equals("")) {
            try {
                Retorno.updatePreguntaVF(this.preguntaEditVF);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Estasdfasdf", "Estudiante eliminado correctamente"));

            } catch (DAOException ex) {
                System.out.println("Error llamando a updatePregunta() " + ex);
            }

            try {
                FacesContext contex = FacesContext.getCurrentInstance();
                contex.getExternalContext().redirect("/AppTituloProyecto/faces/Profesor/viewPreguntas.xhtml?idTopico=" + this.topico);

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

    public void eliminarVF(int id) {
        try {
            Retorno.removePreguntaVF(id);
        } catch (DAOException ex) {
            System.out.println("error llamando al metodo borrar pregunta " + ex);
        }
        for (int i = 0; i < preguntasVF.size(); i++) {
            if (id == preguntasVF.get(i).getId()) {
                preguntasVF.remove(i);
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pregunta Eliminada", "Pregunta eliminada correctamente"));
    }

    public void editar(int id) {
        try {
            this.setEditPreguntaById(id);
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect("/AppTituloProyecto/faces/Profesor/editarEvaluacion.xhtml?");

        } catch (Exception e) {
            System.out.println("Me voy al carajo, no funciona esta redireccion");
        }

    }

    public void editarVF(int id) {
        try {
            this.setEditPreguntaByIdVF(id);
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect("/AppTituloProyecto/faces/Profesor/editarEvaluacionVF.xhtml?");

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

    private void setEditPreguntaByIdVF(int id) {
        int large = this.preguntasVF.size();
        for (int i = 0; i < large; i++) {
            if (id == preguntasVF.get(i).id) {

                this.preguntaEditVF = preguntasVF.get(i);
            }

        }

    }
}
