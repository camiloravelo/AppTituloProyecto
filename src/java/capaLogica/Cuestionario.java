package capaLogica;

import DAO.DAOException;
import DAO.Retorno;
import java.io.Serializable;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name = "cuestionario")
@SessionScoped
public class Cuestionario implements Serializable {

    private Integer[] respuestas;
    private Estilo estilo;
    private String correo;
    private String mensaje;
    private boolean mostrarMensaje;

    public Cuestionario() {
        System.out.println("entramos al contructor");
        respuestas = new Integer[44];
        mostrarMensaje = false;

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map params = externalContext.getRequestParameterMap();
        //Integer categorySelected = new Integer((String) params.get("username" ));
        correo = (String) params.get("username");

        for (int i = 0; i < respuestas.length; i++) {
            respuestas[i] = 0;
        }

    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isMostrarMensaje() {
        return mostrarMensaje;
    }

    public void setMostrarMensaje(boolean mostrarMensaje) {
        this.mostrarMensaje = mostrarMensaje;
    }

    public Integer[] getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(Integer[] respuestas) {
        this.respuestas = respuestas;
    }

    public void procesarDatos() {
        if (this.validarCampos()) {
            int suma1;
            suma1 = respuestas[0] + respuestas[4] + respuestas[8] + respuestas[12] + respuestas[16] + respuestas[20] + respuestas[24]
                    + respuestas[28] + respuestas[32] + respuestas[36] + respuestas[40];

            int suma2;
            suma2 = respuestas[1] + respuestas[5] + respuestas[9] + respuestas[13] + respuestas[17] + respuestas[21] + respuestas[25]
                    + respuestas[29] + respuestas[33] + respuestas[37] + respuestas[41];

            int suma3;
            suma3 = respuestas[2] + respuestas[6] + respuestas[10] + respuestas[14] + respuestas[18] + respuestas[22] + respuestas[26]
                    + respuestas[30] + respuestas[34] + respuestas[38] + respuestas[42];

            int suma4;
            suma4 = respuestas[3] + respuestas[7] + respuestas[11] + respuestas[15] + respuestas[19] + respuestas[23] + respuestas[27]
                    + respuestas[31] + respuestas[35] + respuestas[39] + respuestas[43];

            int d1, d2, d3, d4;

            if (suma1 > 0) {
                d1 = Estilo.REFLEXIVO;
            } else {
                d1 = Estilo.ACTIVO;

            }

            if (suma2 > 0) {
                d2 = Estilo.INTUITIVO;
            } else {
                d2 = Estilo.SENSITIVO;
            }

            if (suma3 > 0) {
                d3 = Estilo.VERBAL;

            } else {
                d3 = Estilo.VISUAL;

            }

            if (suma4 > 0) {
                d4 = Estilo.GLOBAL;

            } else {
                d4 = Estilo.SECUENCIAL;

            }
            estilo = new Estilo(d1, d2, d3, d4);

            System.out.println("HASTA AQUI VAMOS BIEN");

            // Retorno.setEstiloAprendizaje(dimension1, dimension1,dimension1, dimension1, dimension1);
            try {
                Retorno.setEstiloAprendizaje(this.correo, d1, d2, d3, d4);
            } catch (DAOException ex) {
                System.out.println("Error guardando los datos");
            }

            try {
                FacesContext contex = FacesContext.getCurrentInstance();
                contex.getExternalContext().redirect("/AppTituloProyecto/faces/conocimientoPrevio.xhtml?username=" + correo);
            } catch (Exception e) {
                System.out.println("Error direccionando");
            }

            System.out.println("PROCESO FINALIZADO");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campos Vacios", "Debe contestar todas las preguntas."));

            System.out.println("seteamos el mensaje en true");
        }

    }

    private boolean validarCampos() {
        for (int i = 0; i < this.respuestas.length; i++) {
            if (respuestas[i] == null) {
                System.out.println("falta el dato " + i);
                return false;
            }
        }
        return true;
    }

    public String avansar1() {
        for (int i = 0; i < 11; i++) {
            System.out.println(respuestas[i]);

            if (respuestas[i] == 0) {
                System.out.println("HAY CAMPOS INCOMPLETOS");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campos Vacios", "Debe contestar todas las preguntas."));
                return "";
            }
        }
        mostrarMensaje = false;
        System.out.println("AVANSAMOS SIN INCONVENIENTES");
        return "Cuestionario2.xhtml";
    }

    public String avansar2() {
        for (int i = 11; i < 22; i++) {
            System.out.println(i);
            if (respuestas[i] == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campos Vacios", "Debe contestar todas las preguntas."));

                return "Cuestionario2.xhtml";
            }
        }
        mostrarMensaje = false;
        return "Cuestionario3.xhtml";
    }

    public String avansar3() {
        for (int i = 22; i < 33; i++) {
            if (respuestas[i] == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campos Vacios", "Debe contestar todas las preguntas."));
                return "Cuestionario3.xhtml";
            }
        }
        mostrarMensaje = false;
        return "Cuestionario4.xhtml";
    }

}
