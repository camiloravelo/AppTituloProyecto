package capaLogica;

import java.io.Serializable;

public class Estudiante implements Serializable{
    private static final long serialVersionUID = -6709244247867049578L;

    private String correo;
    private String nombre;
    private int indexTopicDeseado;
    private int di1;
    private int di2;
    private int di3;
    private int di4;
    private Estilo estilo;
   
    public String Etiqueta(boolean conocido){
    String pendiente="<span class=\"label label-danger\">Reprobado</span>";
    String aprobado ="<span class=\"label label-success\">Aprobado</span>";
    
    if(conocido)return aprobado;
    else return pendiente;
    }
    @Override
    public String toString() {
        return "Estudiante{" + "correo=" + correo + ", nombre=" + nombre + ", indexTopicDeseado=" + indexTopicDeseado + '}';
    }

    public Estudiante(String correo, String nombre, int indexTopicDeseado, int fs1, int fs2, int fs3, int fs4) {
        this.correo = correo;
        this.nombre = nombre;
        this.indexTopicDeseado = indexTopicDeseado;
        this.di1 = fs1;
        this.di2 = fs2;
        this.di3 = fs3;
        this.di4 = fs4;
        //estilo = new Estilo(fs1, fs2, fs3, fs4);
    } 

    public int getDi1() {
        return di1;
    }

    public void setDi1(int di1) {
        this.di1 = di1;
    }

    public int getDi2() {
        return di2;
    }

    public void setDi2(int di2) {
        this.di2 = di2;
    }

    public int getDi3() {
        return di3;
    }

    public void setDi3(int di3) {
        this.di3 = di3;
    }

    public int getDi4() {
        return di4;
    }

    public void setDi4(int di4) {
        this.di4 = di4;
    }

    public Estilo getEstilo() {
        return estilo;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }

    public String getTopicEvaluar() {
        return null;
    }

    public int getIndexTopicEvaluar() {
        return 1;
    }

    public String getTopicAprendido() {
        return "casas";
    }

    public int getIndexTopicDeseado() {
        return indexTopicDeseado;
    }

    public void setIndexTopicDeseado(int indexTopicDeseado) {
        this.indexTopicDeseado = indexTopicDeseado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
