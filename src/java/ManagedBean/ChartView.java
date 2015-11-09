/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import DAO.DAOException;
import DAO.Retorno;
import capaLogica.Estilo;
import capaLogica.EstudianteF11;
import capaLogica.EstudianteF12;
import capaLogica.EstudianteF21;
import capaLogica.EstudianteF22;
import capaLogica.EstudianteF31;
import capaLogica.EstudianteF32;
import capaLogica.EstudianteF41;
import capaLogica.EstudianteF42;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.PieChartModel;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.primefaces.model.chart.PieChartModel;


/**
 *
 * @author CRAVELO
 */
@ManagedBean
public class ChartView implements Serializable {

    private ArrayList<Estilo> Estilo;
    private ArrayList<EstudianteF11> f11;
    private ArrayList<EstudianteF12> f12;
    private ArrayList<EstudianteF21> f21;
    private ArrayList<EstudianteF22> f22;
    private ArrayList<EstudianteF31> f31;
    private ArrayList<EstudianteF32> f32;
    private ArrayList<EstudianteF41> f41;
    private ArrayList<EstudianteF42> f42;

    private PieChartModel pieModel1;
    private PieChartModel pieModel2;
    private PieChartModel pieModel3;
    private PieChartModel pieModel4;
    private MeterGaugeChartModel meterGaugeModel1;
    private MeterGaugeChartModel meterGaugeModel2;

    int dim1valor1 = 0;
    int dim1valor2 = 0;
    int dim2valor1 = 0;
    int dim2valor2 = 0;
    int dim3valor1 = 0;
    int dim3valor2 = 0;
    int dim4valor1 = 0;
    int dim4valor2 = 0;
    int total1 = 0;
    int total2 = 0;
    int total3 = 0;
    int total4 = 0;

    @PostConstruct
    public void init() {
        createPieModels(); 
        createMeterGaugeModels();
    } 
    
    public MeterGaugeChartModel getMeterGaugeModel1() {
        return meterGaugeModel1;
    }
     
    public MeterGaugeChartModel getMeterGaugeModel2() {
        return meterGaugeModel2;
    }
 
    private MeterGaugeChartModel initMeterGaugeModel() {
        List<Number> intervals = new ArrayList<Number>(){{
            add(1);
            add(11);
            add(16); 
            add(22);
        }};
         
        return new MeterGaugeChartModel(2, intervals);
    }
 
    private void createMeterGaugeModels() {
        meterGaugeModel1 = initMeterGaugeModel();
        meterGaugeModel1.setTitle("Avance del Curso");
        meterGaugeModel1.setGaugeLabel("Tópicos");
        meterGaugeModel1.setLegendPosition("e"); 
        
         
        meterGaugeModel2 = initMeterGaugeModel();
        meterGaugeModel2.setTitle("Custom Options");
        meterGaugeModel2.setSeriesColors("66cc66,93b75f,E7E658,cc6666");
        meterGaugeModel2.setGaugeLabel("tópicos");
        meterGaugeModel2.setGaugeLabelPosition("bottom");
        meterGaugeModel2.setShowTickLabels(false);
        meterGaugeModel2.setLabelHeightAdjust(110);
        meterGaugeModel2.setIntervalOuterRadius(100);
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    public PieChartModel getPieModel3() {
        return pieModel3;
    }

    public PieChartModel getPieModel4() {
        return pieModel4;
    }

    private void createPieModels() {
        createPieModel1();
        createPieModel2();
        createPieModel3();
        createPieModel4();
    }

    private void createPieModel1() {
        pieModel1 = new PieChartModel();
        Estilo = new ArrayList<Estilo>();

//        f11 = new ArrayList<EstudianteF11>();
//        f12 = new ArrayList<EstudianteF12>();
//        f21 = new ArrayList<EstudianteF21>();
//        f22 = new ArrayList<EstudianteF22>();
//        f31 = new ArrayList<EstudianteF31>();
//        f32 = new ArrayList<EstudianteF32>();
//        f41 = new ArrayList<EstudianteF41>();
//        f42 = new ArrayList<EstudianteF42>();
        try {

            f11 = Retorno.getAllEstudiantesFs11();
            f12 = Retorno.getAllEstudiantesFs12();
//            f21 = Retorno.getAllEstudiantesFs21();
//            f22 = Retorno.getAllEstudiantesFs22();
//            f31 = Retorno.getAllEstudiantesFs31();
//            f32 = Retorno.getAllEstudiantesFs32();
//            f41 = Retorno.getAllEstudiantesFs41();
//            f42 = Retorno.getAllEstudiantesFs42();

        } catch (DAOException ex) {
            System.out.println("ERROR: " + ex);
        }
        dim1valor1 = f11.size();
        System.out.println("valor f11" + dim1valor1);
        dim1valor2 = f12.size();
        pieModel1.set("Sensitivo", dim1valor1);
        pieModel1.set("Intuitivo", dim1valor2);
        pieModel1.setShowDataLabels(true);
        pieModel1.setTitle("Percepción");
       // pieModel1.setDataFormat("value");
        pieModel1.setLegendPosition("e");

    } 


    private void createPieModel2() {
        pieModel2 = new PieChartModel();
//
//        f11 = new ArrayList<EstudianteF11>();
//        f12 = new ArrayList<EstudianteF12>();
//        f21 = new ArrayList<EstudianteF21>();
//        f22 = new ArrayList<EstudianteF22>();
//        f31 = new ArrayList<EstudianteF31>();
//        f32 = new ArrayList<EstudianteF32>();
//        f41 = new ArrayList<EstudianteF41>();
//        f42 = new ArrayList<EstudianteF42>();
        try {

//            f11 = Retorno.getAllEstudiantesFs11();
//            f12 = Retorno.getAllEstudiantesFs12();
            f21 = Retorno.getAllEstudiantesFs21();
            f22 = Retorno.getAllEstudiantesFs22();
//            f31 = Retorno.getAllEstudiantesFs31();
//            f32 = Retorno.getAllEstudiantesFs32();
//            f41 = Retorno.getAllEstudiantesFs41();
//            f42 = Retorno.getAllEstudiantesFs42();

        } catch (DAOException ex) {
            System.out.println("ERROR: " + ex);
        }
        dim2valor1 = f21.size();

        dim2valor2 = f22.size();

        pieModel2.set("Visual", dim2valor1);
        pieModel2.set("Verbal", dim2valor2);
        pieModel2.setShowDataLabels(true);
        pieModel2.setTitle("Input");
       // pieModel2.setDataFormat("value");
        pieModel2.setLegendPosition("e");
    }

    private void createPieModel3() {
        pieModel3 = new PieChartModel();

//        f11 = new ArrayList<EstudianteF11>();
//        f12 = new ArrayList<EstudianteF12>();
//        f21 = new ArrayList<EstudianteF21>();
//        f22 = new ArrayList<EstudianteF22>();
//        f31 = new ArrayList<EstudianteF31>();
//        f32 = new ArrayList<EstudianteF32>();
//        f41 = new ArrayList<EstudianteF41>();
//        f42 = new ArrayList<EstudianteF42>();
        try {
//
//            f11 = Retorno.getAllEstudiantesFs11();
//            f12 = Retorno.getAllEstudiantesFs12();
//            f21 = Retorno.getAllEstudiantesFs21();
//            f22 = Retorno.getAllEstudiantesFs22();
            f31 = Retorno.getAllEstudiantesFs31();
            f32 = Retorno.getAllEstudiantesFs32();
//            f41 = Retorno.getAllEstudiantesFs41();
//            f42 = Retorno.getAllEstudiantesFs42();

        } catch (DAOException ex) {
            System.out.println("ERROR: " + ex);
        }
        dim3valor1 = f31.size();

        dim3valor2 = f32.size();

        pieModel3.set("Activo", dim3valor1);
        pieModel3.set("Reflexivo", dim3valor2);
        pieModel3.setShowDataLabels(true);
        pieModel3.setTitle("Procesamiento");
        //pieModel3.setDataFormat("value");
        pieModel3.setLegendPosition("e");
    }

    private void createPieModel4() {
        pieModel4 = new PieChartModel();
//
//        f11 = new ArrayList<EstudianteF11>();
//        f12 = new ArrayList<EstudianteF12>();
//        f21 = new ArrayList<EstudianteF21>();
//        f22 = new ArrayList<EstudianteF22>();
//        f31 = new ArrayList<EstudianteF31>();
//        f32 = new ArrayList<EstudianteF32>();
//        f41 = new ArrayList<EstudianteF41>();
//        f42 = new ArrayList<EstudianteF42>();
        try {

//            f11 = Retorno.getAllEstudiantesFs11();
//            f12 = Retorno.getAllEstudiantesFs12();
//            f21 = Retorno.getAllEstudiantesFs21();
//            f22 = Retorno.getAllEstudiantesFs22();
//            f31 = Retorno.getAllEstudiantesFs31();
//            f32 = Retorno.getAllEstudiantesFs32();
            f41 = Retorno.getAllEstudiantesFs41();
            f42 = Retorno.getAllEstudiantesFs42();

        } catch (DAOException ex) {
            System.out.println("ERROR: " + ex);
        }
        dim4valor1 = f41.size();

        dim4valor2 = f42.size();

        pieModel4.set("Secuencial", dim4valor1);
        pieModel4.set("Global", dim4valor2);
        pieModel4.setShowDataLabels(true);
        pieModel4.setTitle("Entendimiento");
       // pieModel4.setDataFormat("value");
        pieModel4.setLegendPosition("e");
    } 
    
    
    


}
