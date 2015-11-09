/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlAcceso;

import DAO.DAOException;
import DAO.Retorno;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
/**
 *
 * @author CRAVELO
 */ 


@ManagedBean (name="crearCuentaProfesor")
@SessionScoped
public class CrearCuentaProfesor implements Serializable { 
    
    
    String nombre;
    String correo;
    String pass1;
    String pass2;
    int asignatura; 
    public int  getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(int asignatura) {
        this.asignatura = asignatura;
    }
    
    


    boolean verLink;

    public boolean isVerLink() {
        return verLink;
    }

    public void setVerLink(boolean verLink) {
        this.verLink = verLink;
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

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public CrearCuentaProfesor() {
        
    }

    public void procesarProfesor() {
        if (nombre.equals("") || correo.equals("") || pass1.equals("") || pass2.equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Debe completar todos los campos"));

            this.verLink = false;
        } else {
            boolean existeCorreo = false;
            try {
                existeCorreo = Retorno.existeCorreo(correo);
            } catch (DAOException ex) {
                System.out.println("error llamando al metodo existeCorreo()");
            }

            if (!existeCorreo) {
                boolean valid = ValidatorUtil.validateEmail(correo);
                if (valid) {
                    if (this.pass1.equals(this.pass2)) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bien echo", "La cuenta a sido creada, tu nombre de usuario es: <strong>" + this.correo + "</strong>"));

                        try {
                            //creamos la sentencia para guardar
                            Retorno.addProfesor(nombre, correo, pass1, pass2,asignatura);
                        } catch (DAOException ex) {
                            System.out.println("NO se puede conectar");
                        }

                        this.verLink = true;
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Las contraseñas no son iguales"));

                        this.verLink = false;

                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "El correo no es válido"));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ya existe este correo registrado"));
            }

        }


    }

    
    
    
    
    
    
    
    
    
}
