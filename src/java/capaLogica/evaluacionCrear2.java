/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaLogica;

import DAO.DAOException;
import DAO.Retorno;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;




@ManagedBean(name = "evaluacionCrear2")
@SessionScoped
/**
 *
 * @author CRAVELO
 */
public class evaluacionCrear2 implements Serializable{
    
    
    private int topico;
    private int tipo;
    private String info;
    private ArrayList<preguntaVF> preguntas;
    private pregunta preguntaNueva = new pregunta();
    private pregunta preguntaEdit = new pregunta();

    public int getTopico() {
        return topico;
    }

    public void setTopico(int topico) {
        this.topico = topico;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public ArrayList<preguntaVF> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<preguntaVF> preguntas) {
        this.preguntas = preguntas;
    }

    public pregunta getPreguntaNueva() {
        return preguntaNueva;
    }

    public void setPreguntaNueva(pregunta preguntaNueva) {
        this.preguntaNueva = preguntaNueva;
    }

    public pregunta getPreguntaEdit() {
        return preguntaEdit;
    }

    public void setPreguntaEdit(pregunta preguntaEdit) {
        this.preguntaEdit = preguntaEdit;
    }

      
}
