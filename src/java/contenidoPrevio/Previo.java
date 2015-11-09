package contenidoPrevio;

import Asignatura.Topico;
import DAO.DAOException;
import DAO.Retorno;
import capaLogica.Estudiante;
import capaLogica.pregunta;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean (name="previo")
@SessionScoped

public class Previo implements Serializable {

    private ArrayList<Topico> All = new ArrayList<Topico>();  // todos
    private ArrayList<Topico> Know = new ArrayList<Topico>();  // los conocidos por el estudiante
    private String correo;
    private Estudiante alumno;
    private int actual;
    private ArrayList<pregunta> hist;
    private ArrayList<Resultados> res = new ArrayList<Resultados>();  // resultados de las evaluaciones

    public ArrayList<pregunta> getHist() {
        return hist;
    }

    public void setHist(ArrayList<pregunta> hist) {
        this.hist = hist;
    }

    public Previo() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map params = externalContext.getRequestParameterMap();
        correo = (String) params.get("username");
        try {
            All = Retorno.getAllTopic();
            alumno = Retorno.getEstudiante(correo);

        } catch (DAOException ex) {
            System.out.println("Erro cargando los topicos " + ex);
        }
    }

    public String procesarDatos() {
        System.out.println("ESTOY EN EL METODO procesarDatos()");

        try {
            //actualizamos el campo declarado
            Retorno.conocimientoDeclarado(correo);
        } catch (DAOException ex) {
            System.out.println("Error llamando a conocimiento declarado() ");
        }

        Know = new ArrayList<Topico>();
        for (int i = 0; i < this.All.size(); i++) {
            System.out.println(All.get(i).isIsConocido());
            if (All.get(i).isIsConocido()) {
                Know.add(All.get(i));
            }
        }
        actual = 0;
        if (Know.isEmpty()) {
            return "screenEstudiante.xhtml";
        } else {
            hist = new ArrayList<pregunta>();
            try {
                hist = Retorno.getPreguntasByTopic(Know.get(actual).getIdTopico());
            } catch (DAOException ex) {
                System.out.println("Error al cargar las preguntas");
            }
            return "tomarEvaluacion.xhtml";
        }
    }

    public String nextEvaluacion() {

        /// Revisar este metodo
        System.out.println("entramos al metodo");      //  
        System.out.println("el nombre ahora es: " + Know.get(actual).getNombre());

        //debemos procesar loq respondió
        if (!hist.isEmpty()) {
            int correctas = 0;
            for (int i = 0; i < hist.size(); i++) {
                if (hist.get(i).getCorrecto() == hist.get(i).getRespuesta()) {
                    correctas++;
                }
            }

            int malas = hist.size() - correctas;
            double puntaje = 100 * correctas / hist.size();

            if (puntaje > 74) {
                res.add(new Resultados(Know.get(actual), true));
                //guardamos en la database los cambios
                try {
                    System.out.println("guardando los datos");
                    Retorno.avanzarRuta(correo, Know.get(actual).getIdTopico());
                    System.out.println("datos guardadots");
                } catch (DAOException ex) {
                    System.out.println("error llamando al metodo avanzarRuta() " + ex);
                }
            } else {
                res.add(new Resultados(Know.get(actual), false));
            }
        }

        //fin de procesamiento de la evaluacion
        actual++;
        System.out.println("el contador vale: " + actual);

        if (actual == Know.size()) {
            System.out.println("retornamos la pagina");
            return "resultadoPrevio.xhtml";
        } else {

            try {
                System.out.println("cargamos otras preguntas con el topcio " + Know.get(actual).getIdTopico());
                hist = Retorno.getPreguntasByTopicRandom(Know.get(actual).getIdTopico());
            } catch (DAOException ex) {
                System.out.println("Error al cargar las preguntas");
            }
            return "tomarEvaluacion.xhtml";
        }

    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Estudiante getAlumno() {
        return alumno;
    }

    public void setAlumno(Estudiante alumno) {
        this.alumno = alumno;
    }

    public int getActual() {
        return actual;
    }

    public void setActual(int actual) {
        this.actual = actual;
    }

    public ArrayList<Topico> getAll() {
        return All;
    }

    public void setAll(ArrayList<Topico> All) {
        this.All = All;
    }

    public ArrayList<Topico> getKnow() {
        return Know;
    }

    public void setKnow(ArrayList<Topico> Know) {
        this.Know = Know;
    }

    public ArrayList<Resultados> getRes() {
        return res;
    }

    public void setRes(ArrayList<Resultados> res) {
        this.res = res;
    }

}
