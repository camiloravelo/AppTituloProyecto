/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import capaLogica.Alumnado;
import capaLogica.Estudiante;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

/**
 *
 * @author CRAVELO
 */
@ManagedBean(name="dtFilterView")
@ViewScoped
public class FilterView implements Serializable{
    ArrayList<Estudiante> alumnos = new ArrayList<Estudiante>();
     private ArrayList<Estudiante>  Cars;

    public ArrayList<Estudiante> getCars() {
        return Cars;
    }

    public void setCars(ArrayList<Estudiante> Cars) {
        this.Cars = Cars;
    }
    private ArrayList<Estudiante>  filteredCars;
     @ManagedProperty("#{alumnado}")

    private Alumnado alumnado;

    public ArrayList<Estudiante> getFilteredCars() {
        return filteredCars;
    }

    public void setFilteredCars(ArrayList<Estudiante> filteredCars) {
        this.filteredCars = filteredCars;
    }
    
     public ArrayList<Estudiante> getEstudiantes() {
        return alumnos;
    } 
}
