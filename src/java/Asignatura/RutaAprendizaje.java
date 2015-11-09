package Asignatura;

import DAO.DAOException;
import DAO.Retorno;
import capaLogica.Estudiante;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;

//@Named(value = "viewAsignatura")
//@Dependent
@ManagedBean (name="viewAsignatura")
@NoneScoped

public class RutaAprendizaje {

    private ArrayList<Topico> tAll = new ArrayList<Topico>();  // todos
    private ArrayList<Topico> tApr = new ArrayList<Topico>(); //aprovados
    private ArrayList<Topico> tPen = new ArrayList<Topico>(); //pendientes

    private String correo;
    private Estudiante estudiante;

    public RutaAprendizaje() {
        System.out.println("ESTAMOS EN EL CONSTRUCTOS");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map params = externalContext.getRequestParameterMap();
        correo = (String) params.get("username");

        EstructuraAsignatura funda = new EstructuraAsignatura();
        try {
            estudiante = Retorno.getEstudiante(correo);
            tApr = Retorno.getTopicosAprobados(correo);
            tPen = Retorno.getTopicosPendientes(correo);
        } catch (DAOException ex) {
            System.out.println("Error llamando a los metodos de la ruta " + ex);
        }

        System.out.println("YA TENEMOS LOS DATOS");
        // unimos ambos algoritmos;
        for (int i = 0; i < tApr.size(); i++) {
            tAll.add(tApr.get(i));
        }

        for (int i = 0; i < tPen.size(); i++) {
            tAll.add(tPen.get(i));
        }

        //los ordenamos con burbuja
        int i, j;

        int aux1;
        String aux2;
        int aux3;
        boolean aux4;
        for (i = 0; i < tAll.size() - 1; i++) {
            for (j = 0; j < tAll.size() - i - 1; j++) {
                if (tAll.get(j + 1).getIdTopico() < tAll.get(j).getIdTopico()) {
                    aux1 = tAll.get(j + 1).getIdTopico();
                    aux2 = tAll.get(j + 1).getNombre();
                    aux3 = tAll.get(j + 1).getIdUnidad();
                    aux4 = tAll.get(j + 1).isIsConocido();

                    tAll.get(j + 1).setIdTopico(tAll.get(j).getIdTopico());
                    tAll.get(j + 1).setNombre(tAll.get(j).getNombre());
                    tAll.get(j + 1).setIdUnidad(tAll.get(j).getIdUnidad());
                    tAll.get(j + 1).setIsConocido(tAll.get(j).isIsConocido());

                    tAll.get(j).setIdTopico(aux1);
                    tAll.get(j).setNombre(aux2);
                    tAll.get(j).setIdUnidad(aux3);
                    tAll.get(j).setIsConocido(aux4);

                }
            }
        }

        i = 0;
        for (i = 0; i < tAll.size(); i++) {
           // System.out.println(tAll.get(i).toString());

        }

    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public ArrayList<Topico> getunidad1() {
        ArrayList<Topico> t = new ArrayList<Topico>();
        for (int i = 0; i < tAll.size(); i++) {
            if (tAll.get(i).getIdUnidad() == 1) {
                t.add(tAll.get(i));
            }

        }
        return t;
    }

    public ArrayList<Topico> getunidad2() {
        ArrayList<Topico> t = new ArrayList<Topico>();
        for (int i = 0; i < tAll.size(); i++) {
            if (tAll.get(i).getIdUnidad() == 2) {
                t.add(tAll.get(i));
            }

        }
        return t;
    }

    public ArrayList<Topico> getunidad3() {
        ArrayList<Topico> t = new ArrayList<Topico>();
        for (int i = 0; i < tAll.size(); i++) {
            if (tAll.get(i).getIdUnidad() == 3) {
                t.add(tAll.get(i));
            }

        }
        return t;
    }

    public void procesarDatos() {

    }
    
    public String Etiqueta(boolean conocido){
    String pendiente="<span class=\"label label-default\">Pendiente</span>";
    String aprobado ="<span class=\"label label-success\">Aprobado</span>";
    
    if(conocido)return aprobado;
    else return pendiente;
    }
}
