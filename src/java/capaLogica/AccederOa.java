package capaLogica;

import Asignatura.Topico;
import DAO.DAOException;
import DAO.Retorno;
import Ontologia.Consultas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name = "accederOa")
@SessionScoped

public class AccederOa implements Serializable {
    private static final long serialVersionUID = 4726395829590710553L;

    private boolean mostrarTabla = false;
    private Estudiante alumno;
    private ArrayList<Topico> ruta;
    private ArrayList<learningObject> objetos;
    private String correo;
    private int topicoSeleccionado;
    private learningObject objetoUnit = new learningObject();
    private Estilo estilo;
    private boolean selector;
    private String mensajeVacio = "<h4>Ya completaste los tópicos de la asignatura :) </h4>";
    private ArrayList<learningObject> objetos1;

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

    public AccederOa() {
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
            ruta = Retorno.getTopicosPendientes(alumno.getCorreo());
            //System.out.println("tenemos en ruta todo lo q le falta");

            for (int i = 0; i < ruta.size(); i++) {
                //System.out.println(ruta.get(i).toString());
            }

        } catch (DAOException ex) {
            //System.out.println("ERRRORR : " + ex);
        }

        try {
            estilo = Retorno.getEstiloAprendizaje(correo);
            //ahora estilo tienes los estilos del estudiante
        } catch (DAOException ex) {
            //System.out.println("error llamando al metodo getEstiloAprendizaje()");
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
                    //String color2=String.valueOf(Color.rgb(255,250,205));
                    return "label label-info";
                } else {
                    return "label label-defaul";
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
            ruta = Retorno.getTopicosPendientes(alumno.getCorreo());
            //System.out.println("tenemos en ruta todo lo q le falta");
            for (int i = 0; i < ruta.size(); i++) {
                //System.out.println(ruta.get(i).toString());
            }
        } catch (DAOException ex) {
            //System.out.println("ERRRORR : " + ex);
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

    public ArrayList<learningObject> getObjetos1() {
        return objetos1;
    }

    public void setObjetos1(ArrayList<learningObject> objetos1) {
        this.objetos1 = objetos1;
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
        int idTopicoSeleccionado = 0;
        for (int i = 0; i < ruta.size(); i++) {
            if (topicoSeleccionado == ruta.get(i).getIdTopico()) {
                largoN = i;
                break;
            }
        }

        largoN++;
        idTopicoSeleccionado = topicoSeleccionado;
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
            //System.out.println("Auxiliar:" + auxObjetos.get(i).toString());
            auxObjetos.get(i).calcularRanking(estilo);
            try {
                Retorno.guardarRecurso(auxObjetos.get(i));

            } catch (DAOException ex) {
                System.out.println("Error al guardar el  objeto");
            }
        }
        try {

            objetos = Retorno.leerNombreTopico();//Aqui va a buscar los objetos ordenados DESC

            objetos1 = Retorno.leerRecurso(idTopicoSeleccionado);//Aqui va a buscar los objetos ordenados DESC

            //System.out.println(objetos.get(1)+"Objeto posicion 1:");
            //System.out.println(objetos1.get(2)+"Objeto posicion 2:");
            
            for (learningObject objeto1 : objetos1) {
                System.out.println("Descripcion Objeto1:" + objeto1.getDescripcion());
                System.out.println("Contenido Objetos1:" + objeto1);
            }
            System.out.println("Objetos"+objetos);
            
            
            Retorno.borrarRecursos();
        } catch (DAOException ex) {
            Logger.getLogger(AccederOa.class.getName()).log(Level.SEVERE, null, ex);
        }

        mostrarTabla = true;
    }

    public void verObjeto(String id) {
        Consultas con = new Consultas();
        System.out.println("id VerObjeto:" + id);
        for (int i = 0; i < objetos1.size(); i++) {
            if (id.equals(objetos1.get(i).getId())) {
                //System.out.println("encontramos el id correcto");
                //System.out.println(" es el numero " + i);
                //System.out.println("Objeto1 derechos:" + objetos1.get(i).getDerechos());
                objetoUnit = objetos1.get(i);
                
                objetoUnit = con.getOAUnit(id);
                System.out.println("Objeto Unit derechos:" + objetoUnit.getDerechos());
                
            } else {
                //System.out.println("no encontrado en  " + i);
            }
        }
    }
}
