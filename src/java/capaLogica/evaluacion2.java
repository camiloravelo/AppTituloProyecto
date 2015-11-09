package capaLogica;

import Asignatura.Topico;
import DAO.DAOException;
import DAO.Retorno;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean (name="evaluacion2")
@SessionScoped

public class evaluacion2 implements Serializable {

    private ArrayList<pregunta> hist; // de aqui saco el total
    private ArrayList<preguntaVF> histVF;
    private String info;
    private Estudiante alumno;
    private Topico topico;
    private String correo;
    private int correctas; // numero de correctas , la resta son las malas
    private double puntaje;
    private boolean mensaje;
    private boolean boton;

    public boolean isBoton() {
        return boton;
    }

    public void setBoton(boolean boton) {
        this.boton = boton;
    }
        

    public boolean isMensaje() {
        return mensaje;
    }

    public void setMensaje(boolean mensaje) {
        this.mensaje = mensaje;
    }
    
   
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public ArrayList<pregunta> getHist() {
        return hist;
    }
    
    public ArrayList<preguntaVF> getHistVF(){
        return histVF;
    }

    public void setHist(ArrayList<pregunta> hist) {
        this.hist = hist;
    } 
    public void setHistVF(ArrayList<preguntaVF> histVF) {
        this.histVF = histVF;
    }
    
    
    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public Estudiante getAlumno() {
        return alumno;
    }

    public void setAlumno(Estudiante alumno) {
        this.alumno = alumno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }

    public String getMalas() {
        int aux = (hist.size()+histVF.size()) - correctas;
        return String.valueOf(aux);

    }

    public int getCorrectas() {
        return correctas;
    }

    public int getTotal() {
        return hist.size()+histVF.size();
    }

    public int getPuntaje() {
        return 100 * correctas / (hist.size()+histVF.size());
    }

    public void setCorrectas(int correctas) {
        this.correctas = correctas;
    }

    public String getColor() {
        if (puntaje > 74) {
            return "bs-callout bs-callout-info";
        } else {
            return "bs-callout bs-callout-danger";
        }
    }

    public String getTitleBox() {
        if (puntaje > 74) {
            return "Felicitaciones";
        } else {
            return "Inténtalo nuevamente";
        }
    }

    public String getTextBox() {
        if (puntaje > 74) {
            return "Haz aprobado la evaluación y haz avanzado un tópico";
        } else {
            return "Vuelve a estudiar los contenidos";
        }
    }

    public evaluacion2() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map params = externalContext.getRequestParameterMap();
        correo = (String) params.get("username");

        ArrayList<Topico> t = new ArrayList<Topico>();

        try {
            alumno = Retorno.getEstudiante(correo);
            t = Retorno.getTopicosPendientes(alumno.getCorreo());
            if(t.isEmpty()){
            mensaje=true;
            }
            else {mensaje=false; topico = t.get(0);boton=true;}

        } catch (DAOException ex) {
            System.out.println("ERRRORR : " + ex);
        }
        System.out.println(alumno.toString());

    }

    public String procesar() {

        int c = 0; 
        int cVF=0;
        for (int i = 0; i < hist.size(); i++) {
            if (hist.get(i).correcto == hist.get(i).respuesta) {
                c++;
            }
        } 
        for (int i = 0; i < histVF.size(); i++) {
            if (histVF.get(i).correcto == histVF.get(i).respuesta) {
                cVF++;
            }
        }
        correctas = c+cVF; 
        int total=hist.size() +histVF.size();
        int malas = total - correctas;
        puntaje = 100 * correctas / total;

        if (puntaje > 74) {
            String insert = "insert into AvanceEstudiante values ('" + correo + "'," + topico.getIdTopico() + ")";
            System.out.println(insert);
            System.out.println("actualizamos el topico");
            
            try {
                Retorno.avanzarRuta(correo, topico.getIdTopico());
            } catch (DAOException ex) {
                System.out.println("error llamando al metodo avanzarRuta() "+ex); 
            }

            ArrayList<Topico> t = new ArrayList<Topico>();
            try {
                /*
                Ahora actualizamos el atributa topico, topico=t.get(0)
                es el topico que se debe aprobar
                */
                t = Retorno.getTopicosPendientes(alumno.getCorreo());
                if(t.isEmpty()){
            mensaje=true;
            }
            else {mensaje=false; topico = t.get(0);}
                
                
            } catch (DAOException ex) {
                System.out.println("ERRRORR : " + ex);
            }

        } else {
            System.out.println("el estudiante no ha aprobado la evaluacion");
        }
        return "resultadoEvaluacion";
     // FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("evaluacion2");

    }

    public String cargarPreguntas() {
        hist = new ArrayList<pregunta>();
        histVF=new ArrayList<preguntaVF>();
        try {
            hist = Retorno.getPreguntasByTopicRandom(topico.getIdTopico());
            histVF = Retorno.getPreguntasByTopicRandomVF(topico.getIdTopico());
            if(hist.isEmpty() && histVF.isEmpty()){
                System.out.println("No hay preguntas");
                boton=false;
            }
        } catch (DAOException ex) {
            System.out.println("Error al cargar las preguntas");
        }
        return "rendirEvaluacion";
    }

}
