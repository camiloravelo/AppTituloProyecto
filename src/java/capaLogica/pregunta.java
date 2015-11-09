package capaLogica;


public class pregunta {

    int id;
    String enunciado;
    String solucion;
    String alt1;
    String alt2;
    String alt3;
    String alt4;
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


    public int getIdTopico() {
        return idTopico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdTopico(int idTopico) {
        this.idTopico = idTopico;
    }

    @Override
    public String toString() {
        return "pregunta{" + "enunciado=" + enunciado + ", solucion=" + solucion + ", alt1=" + alt1 + ", alt2=" + alt2 + ", alt3=" + alt3 + ", alt4=" + alt4 + ", respuesta=" + respuesta + ", correcto=" + correcto + ", idTopico=" + idTopico + '}';
    }

    public String getAlt3() {
        return alt3;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
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

    public void setAlt3(String alt3) {
        this.alt3 = alt3;
    }

    public String getAlt4() {
        return alt4;
    }

    public void setAlt4(String alt4) {
        this.alt4 = alt4;
    }

    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
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

    public int getCorrecto() {
        return correcto;
    }

    public void setCorrecto(int correcto) {
        this.correcto = correcto;
    }

    public pregunta(int id, String enunciado, String alt1, String alt2, String alt3, String alt4, int correcto, String solucion,int tipoPregunta) {
        this.id = id;
        this.enunciado = enunciado;
        this.alt1 = alt1;
        this.alt2 = alt2;
        this.alt3 = alt3;
        this.alt4 = alt4;
        this.correcto = correcto;
        this.solucion = solucion;
       this.tipoPregunta=tipoPregunta;
    }

    public pregunta() {
    }

}
