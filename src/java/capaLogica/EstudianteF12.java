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
public class EstudianteF12 {
    
    private String nombre;
    private String correo;
    private int dimension;

    public EstudianteF12(String nombre, String correo, int dimension) {
        this.nombre = nombre;
        this.correo = correo;
        this.dimension = dimension;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
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
