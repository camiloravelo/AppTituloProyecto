package AsignaturaIntro;

public class Topico {

    private boolean isConocido;
    private int idTopico;
    private int idUnidad;
    private String nombre;

    public boolean isIsConocido() {
        return isConocido;
    }

    public void setIsConocido(boolean isConocido) {
        this.isConocido = isConocido;
    }

    public int getIdTopico() {
        return idTopico;
    }

    public void setIdTopico(int idTopico) {
        this.idTopico = idTopico;
    }

    public int getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(int idUnidad) {
        this.idUnidad = idUnidad;
    }

   

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Topico(int idTopico, int idUnidad, String nombre) {
        this.idTopico = idTopico;
        this.idUnidad = idUnidad;
        this.nombre = nombre;
    }



    @Override
    public String toString() {
        return "Topico{" + "isConocido=" + isConocido + ", idTopico=" + idTopico + ", idUnidad=" + idUnidad + ", nombre=" + nombre + '}';
    }

    

    

}
