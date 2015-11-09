/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author CRAVELO
 */
@ManagedBean
@RequestScoped
public class ChartBean implements Serializable { 

private CartesianChartModel categoryModel; 

public ChartBean() { 
createCategoryModel(); 
} 

public CartesianChartModel getCategoryModel() { 
return categoryModel; 
} 

private void createCategoryModel() { 
categoryModel = new CartesianChartModel(); 

ChartSeries boys = new ChartSeries(); 
boys.setLabel("Boys"); 

boys.set("2004", 120); 
boys.set("2005", 140); 
boys.set("2006", 160); 
boys.set("2007", 180); 
boys.set("2008", 200); 

ChartSeries girls = new ChartSeries(); 
girls.setLabel("Girls"); 

girls.set("2004", 100); 
girls.set("2005", 100); 
girls.set("2006", 100); 
girls.set("2007", 100); 
girls.set("2008", 100); 

categoryModel.addSeries(boys); 
categoryModel.addSeries(girls); 
} 
}
    

