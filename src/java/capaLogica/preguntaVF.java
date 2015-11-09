/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaLogica;

/**
 *
 * @author CRAVELO
 */
public class preguntaVF {

    int id;
    String enunciado;
    String solucion;
    String alt1;
    String alt2;
    int respuesta = 1;
    int correcto;

    int idTopico;
    int tipoPregunta; 

    public int getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(int tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }
    
    public preguntaVF(int id, String enunciado, String alt1, String alt2, int correcto, String solucion, int tipoPregunta) {
        this.id = id;
        this.enunciado = enunciado;
        this.solucion = solucion;
        this.alt1 = alt1;
        this.alt2 = alt2;
        this.correcto = correcto;
       
        this.tipoPregunta=tipoPregunta; 
    }

    public preguntaVF(){
    
    
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public String getAlt1() {
        return alt1;
    }

    public void setAlt1(String alt1) {
        this.alt1 = alt1;
    }

    public String getAlt2() {
        return alt2;
    }

    public void setAlt2(String alt2) {
        this.alt2 = alt2;
    }

    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }

    public int getCorrecto() {
        return correcto;
    }

    public void setCorrecto(int correcto) {
        this.correcto = correcto;
    }

    public int getIdTopico() {
        return idTopico;
    }

    public void setIdTopico(int idTopico) {
        this.idTopico = idTopico;
    }

    
     @Override
    public String toString() {
        return "pregunta{" + "enunciado=" + enunciado + ", solucion=" + solucion + ", alt1=" + alt1 + ", alt2=" + alt2 + ", respuesta=" + respuesta + ", correcto=" + correcto + ", idTopico=" + idTopico + '}';
    }
    
     public String devolverColor(int alternativa) {
        if (respuesta == this.correcto) {
            if (alternativa == this.respuesta) {
                return "text-success";
            } else {
                return "";
            }

        } else {

            if (alternativa == this.respuesta) {
                return "text-danger";
            } else {
                return "";
            }
        }
    }

    public String devolverEtiquetaColor() {
        if (respuesta == this.correcto) {
            return "label-success";
        } else {
            return "label-danger";
        }

    }

    public String devolverEtiquetaTexto() {
        if (respuesta == this.correcto) {
            return "Correcta";
        } else {
            return "Incorrecta";
        }

    }

    public String Fuente(int alternativa) {

        if (respuesta == this.correcto) {
            if (alternativa == this.respuesta) {
                return "font-weight: bold;BACKGROUND-COLOR: #DFF0D8;";
            } else {
                return "";
            }

        } else {

            if (alternativa == this.respuesta) {
                return "font-weight: bold;BACKGROUND-COLOR: #F2DEDE;";
            }
            if (alternativa == this.correcto) {
                return "font-weight: bold;BACKGROUND-COLOR: #D9EDF7;";
            } else {
                return "";
            }
        }
    }

    public String Buena(int alternativa) {

        if (alternativa == this.correcto) {
            return "font-weight: bold;BACKGROUND-COLOR: #DFF0D8;";
        } else {
            return "";
        }

    }
    
    
    public String Buenacolor(int alternativa) {

        if (alternativa == this.correcto) {
            return "text-success";
        } else {
            return "";
        }

    }

    
    
}
