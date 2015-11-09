package profesor;

public class Profesor {

    private String nombre;
    private String correo;
    private int paralelo;
    private int asignatura; 

    public int getParalelo() {
        return paralelo;
    }

    public void setParalelo(int paralelo) {
        this.paralelo = paralelo;
    }

    public int getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(int asignatura) {
        this.asignatura = asignatura;
    }
  

    public Profesor(String nombre, String correo, int paralelo, int asignatura) {
        this.nombre = nombre;
        this.correo = correo;
        this.paralelo=paralelo;
        this.asignatura=asignatura;
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

    @Override
    public String toString() {
        return "Profesor{" + "nombre=" + nombre + ", correo=" + correo + ", asignatura=" + asignatura + '}';
    }
    
    
    
}
