package contenidoPrevio;

import Asignatura.Topico;

public class Resultados {
    Topico topico;
    boolean aprobado;

    public Resultados(Topico topico, boolean aprobado) {
        this.topico = topico;
        this.aprobado = aprobado;
    }

    
     public String Etiqueta(boolean conocido){
    String pendiente="<span class=\"label label-danger\">Reprobado</span>";
    String aprobado ="<span class=\"label label-success\">Aprobado</span>";
    
    if(conocido)return aprobado;
    else return pendiente;
    }
    
    
    @Override
    public String toString() {
        return "Resultados{" + "topico=" + topico + ", aprobado=" + aprobado + '}';
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }
}
