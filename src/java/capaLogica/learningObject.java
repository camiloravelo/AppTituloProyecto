
package capaLogica;

import java.io.Serializable;

public class learningObject implements Serializable{
    private static final long serialVersionUID = -5722928233596063446L;

    private int ranking;

    private String id; //el id es la fecha de creacion del oa
    private String titulo;
    private String descripcion;
    private String autor;
    private String fecha;
    private String version;
    private String formato;
    private String tipoRecurso;
    private int estilo1;
    private int estilo2;
    private int estilo3;
    private int estilo4;

    private Estilo estilo;

    private String derechos;
    private int idTopico;
    private String nameTopico;
    
    
    private String ruta; // es el directorio+fecha+nombre

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public Estilo getEstilo() {
        return estilo;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }

    public void EstablecerEstilo() {
        estilo = new Estilo(estilo1, estilo2, estilo3, estilo4);

    }

    public void calcularRanking(Estilo estiloAlumno) {
        ranking = 0;
        if (estilo.getDimension1() == estiloAlumno.getDimension1()) {
            ranking++;
        }
        if (estilo.getDimension2() == estiloAlumno.getDimension2()) {
            ranking++;
        }

        if (estilo.getDimension3() == estiloAlumno.getDimension3()) {
            ranking++;
        }

        if (estilo.getDimension4() == estiloAlumno.getDimension4()) {
            ranking++;
        }

    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }



    public String getNameTopico() {
        return nameTopico;
    }

    public void setNameTopico(String nameTopico) {
        this.nameTopico = nameTopico;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getEstilo1() {
        return estilo1;
    }

    public void setEstilo1(int estilo1) {
        this.estilo1 = estilo1;
    }

    public int getEstilo2() {
        return estilo2;
    }

    public void setEstilo2(int estilo2) {
        this.estilo2 = estilo2;
    }

    public int getEstilo3() {
        return estilo3;
    }

    public void setEstilo3(int estilo3) {
        this.estilo3 = estilo3;
    }

    public int getEstilo4() {
        return estilo4;
    }

    public void setEstilo4(int estilo4) {
        this.estilo4 = estilo4;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public String getDerechos() {
        return derechos;
    }

    public void setDerechos(String derechos) {
        this.derechos = derechos;
    }

    public int getIdTopico() {
        return idTopico;
    }

    public void setIdTopico(int idTopico) {
        this.idTopico = idTopico;
    }

    public learningObject() {

    }





    @Override
    public String toString() {
        return "learningObject{" + "titulo=" + titulo + ", idTopico=" + idTopico + ", nameTopico=" + nameTopico + '}';
    }

}
