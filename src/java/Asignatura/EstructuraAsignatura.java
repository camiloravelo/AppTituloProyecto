package Asignatura;

import capaLogica.Estilo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//import mysql.Conector;

public class EstructuraAsignatura {

    ArrayList<Topico> topicos = new ArrayList<Topico>();

    public EstructuraAsignatura() {
       

    }
    
    

    public ArrayList<String> getPrimerNivel() {
        ArrayList<String> a = new ArrayList<String>();
        a.add("Unidad 1");
        a.add("Unidad 2");
        a.add("Unidad 3");
        return a;

    }

  /*  public ArrayList<String> getSegundoNivel(String unidad) {
        //String id=getId(nombre);
        System.out.println("EL TAMAÑO DE LOS TOPICOS ES: " + topicos.size());
        ArrayList<String> a = new ArrayList<String>();
        Conector conector = new Conector();

        ResultSet consulta = null;
        ResultSet rs = null;

        //==================================================
        String select = "select Unidades.name, Contenidos.name from Contenidos, Unidades where Unidades.name='" + unidad + "' and Unidades.id = Contenidos.idUnidad order by Contenidos.id;";

        try {

            Statement st = conector.conexion.createStatement();
            rs = st.executeQuery(select);
            while (rs.next()) {

                String b = rs.getString("Contenidos.name");
                a.add(b);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return a;

    }*/

    public Topico getTopic(int id) {
        for (int i = 0; i < this.topicos.size(); i++) {
            System.out.println("EL TOPICO ES: " + topicos.get(i).getNombre());

            if (topicos.get(i).getIdTopico() == id) {
                return topicos.get(i);
            }

        }
        return null;
    }

}
