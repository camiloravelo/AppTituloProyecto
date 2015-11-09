package capaLogicaIntro;

import AsignaturaIntro.Topico;
import DAOIntro.DAOException;
import DAOIntro.Retorno;
import OntologiaIntro.Consultas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean (name="accederOaAprendidoIntro")
@SessionScoped
public class AccederOaAprendido implements Serializable {

  private boolean mostrarTabla = false;
    private Estudiante alumno;
    private ArrayList<Topico> ruta;
    private ArrayList<learningObject> objetos;
    private String correo;
    private int topicoSeleccionado;
    private learningObject objetoUnit = new learningObject();
    private Estilo estilo;
    private boolean selector;
    private String mensajeVacio = "<h4>Aun no apruebas ningún tópico </h4>";

    public String getMensajeVacio() {
        return mensajeVacio;
    }

    public void setMensajeVacio(String mensajeVacio) {
        this.mensajeVacio = mensajeVacio;
    }

    public boolean isSelector() {
        if (ruta.isEmpty()) {
            selector = false;
            return false;
        } else {
            selector = true;
            return true;
        }
    }

    public void setSelector(boolean selector) {
        this.selector = selector;
    }

    public learningObject getObjetoUnit() {
        return objetoUnit;
    }

    public void setObjetoUnit(learningObject objetoUnit) {
        this.objetoUnit = objetoUnit;
    }

    public Estilo getEstilo() {
        return estilo;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }

    public AccederOaAprendido() {
        mostrarTabla = false;
        selector = true;
        estilo = new Estilo();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map params = externalContext.getRequestParameterMap();
        correo = (String) params.get("username");
        ruta = new ArrayList<Topico>();

        try {
            alumno = Retorno.getEstudiante(correo);
            ruta = Retorno.getTopicosAprobados(alumno.getCorreo());
            System.out.println("tenemos en ruta todo lo q le falta");

            for (int i = 0; i < ruta.size(); i++) {
                System.out.println(ruta.get(i).toString());
            }

        } catch (DAOException ex) {
            System.out.println("ERRRORR : " + ex);
        }
        
      try {
          estilo=Retorno.getEstiloAprendizaje(correo);
          //ahora estilo tienes los estilos del estudiante
      } catch (DAOException ex) {
            System.out.println("error llamando al metodo getEstiloAprendizaje()");
      }

    }

    public String devolverEtiqueta(int dimension, int dominio) {
        switch (dimension) {
            case 1:
                if (estilo.getDimension1() == dominio) {
                    return "label label-info";
                } else {
                    return "label label-default";
                }

            case 2:
                if (estilo.getDimension2() == dominio) {
                    return "label label-info";
                } else {
                    return "label label-default";
                }
            case 3:
                if (estilo.getDimension3() == dominio) {
                    return "label label-info";
                } else {
                    return "label label-default";
                }

            case 4:
                if (estilo.getDimension4() == dominio) {
                    return "label label-info";
                } else {
                    return "label label-default";
                }
        }
        return "label label-default";
    }

    public int getTopicoSeleccionado() {
        return topicoSeleccionado;
    }

    public void setTopicoSeleccionado(int topicoSeleccionado) {
        this.topicoSeleccionado = topicoSeleccionado;
    }

    public boolean isMostrarTabla() {
        if (selector) {
            return mostrarTabla;
        } else {
            return false;
        }
    }

    public void setMostrarTabla(boolean mostrarTabla) {
        this.mostrarTabla = mostrarTabla;
    }

    public Estudiante getAlumno() {
        return alumno;
    }

    public void setAlumno(Estudiante alumno) {
        this.alumno = alumno;
    }

    public ArrayList<Topico> getRuta() {
        ruta = new ArrayList<Topico>();
        try {
            alumno = Retorno.getEstudiante(correo);
            ruta = Retorno.getTopicosAprobados(alumno.getCorreo());
            System.out.println("tenemos en ruta todo lo q le falta");
            for (int i = 0; i < ruta.size(); i++) {
                System.out.println("topicos: "+ruta.get(i).toString());
            }
        } catch (DAOException ex) {
            System.out.println("ERRRORR : " + ex);
        }
        return ruta;
    }

    public void setRuta(ArrayList<Topico> ruta) {
        this.ruta = ruta;
    }

    public ArrayList<learningObject> getObjetos() {
        return objetos;
    }

    public void setObjetos(ArrayList<learningObject> objetos) {
        this.objetos = objetos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void mostrarResultados() {
        System.out.println("el topico seleccionado es: " + topicoSeleccionado);
        int largoN = -1;
        for (int i = 0; i < ruta.size(); i++) {
            if (topicoSeleccionado == ruta.get(i).getIdTopico()) {
                largoN = i;
                break;
            }
        }
        largoN++;
        System.out.println("el largo de n es: " + largoN);
        Integer n[] = new Integer[largoN];
        for (int i = 0; i < n.length; i++) {
            n[i] = ruta.get(i).getIdTopico();
        }
        /* 
         n[0] = 1;
         n[1] = 5;
         n[2] = 6;
         n[3] = 2;
         n[4] = 3;
         */
        String b = " FILTER (";
        for (int i = 0; i < n.length; i++) {
            b = b + " ?idtopico=" + n[i];
            if (i == n.length - 1) {

            } else {
                b = b + " ||";
            }
        }
        b = b + " )";
        System.out.println("b vale : " + b);
        Consultas con = new Consultas();
        ArrayList<learningObject> auxObjetos = new ArrayList<learningObject>();
        auxObjetos = con.getOA(b);
        //calculamos el ranking
        for (int i = 0; i < auxObjetos.size(); i++) {
            System.out.println("OA q enviamos al ranking"+auxObjetos.get(i).toString());
            auxObjetos.get(i).calcularRanking(estilo);
            try {
                Retorno.guardarRecurso(auxObjetos.get(i));

            } catch (DAOException ex) {
                System.out.println("Error al guardar el  objeto");
            }
        }
        try {
            objetos = Retorno.leerRecurso();
            Retorno.borrarRecursos();
        } catch (DAOException ex) {
            Logger.getLogger(AccederOa.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("MOSTRANDO LAS RUTAS");
        for(int i=0; i<objetos.size();i++){
            System.out.println(objetos.get(i).getRuta());
        }
        
        mostrarTabla = true;
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
            contex.getExternalContext().redirect("/AppTitulo2/faces/Estudiante/ViewRecurso.xhtml?idRecurso=" + objetoUnit.getId());
        } catch (Exception e) {
            System.out.println("Error direccionando");
        }

    }
    
    
    
}
