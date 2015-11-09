package capaLogicaIntro;

import DAOIntro.DAOException;
import DAOIntro.Retorno;
import java.io.IOException;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

//@Named(value = "alumnado")
@ManagedBean (name="alumnadoIntro")
@ViewScoped
public class Alumnado {

   
    public ArrayList<Estudiante> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(ArrayList<Estudiante> alumnos) {
        this.alumnos = alumnos;
    }

   
    

    private ArrayList<Estudiante> alumnos;

    public ArrayList<Estudiante> getFiltroAlumnos() {
        return filtroAlumnos;
    }

    public void setFiltroAlumnos(ArrayList<Estudiante> filtroAlumnos) {
        this.filtroAlumnos = filtroAlumnos;
    }
    private ArrayList<Estudiante> filtroAlumnos;

    public ArrayList<EstudianteF21> getAlumnof21() {
        return alumnof21;
    }

    public void setAlumnof21(ArrayList<EstudianteF21> alumnof21) {
        this.alumnof21 = alumnof21;
    }

    public ArrayList<EstudianteF22> getAlumnof22() {
        return alumnof22;
    }

    public void setAlumnof22(ArrayList<EstudianteF22> alumnof22) {
        this.alumnof22 = alumnof22;
    }



    public ArrayList<EstudianteF11> getAlumnof11() {
        return alumnof11;
    }

    public void setAlumnof11(ArrayList<EstudianteF11> alumnof11) {
        this.alumnof11 = alumnof11;
    }

    public ArrayList<EstudianteF12> getAlumnof12() {
        return alumnof12;
    }

    public void setAlumnof12(ArrayList<EstudianteF12> alumnof12) {
        this.alumnof12 = alumnof12;
    }

    public ArrayList<EstudianteF31> getAlumnof31() {
        return alumnof31;
    }

    public void setAlumnof31(ArrayList<EstudianteF31> alumnof31) {
        this.alumnof31 = alumnof31;
    }

    public ArrayList<EstudianteF32> getAlumnof32() {
        return alumnof32;
    }

    public void setAlumnof32(ArrayList<EstudianteF32> alumnof32) {
        this.alumnof32 = alumnof32;
    }

    public ArrayList<EstudianteF41> getAlumnof41() {
        return alumnof41;
    }

    public void setAlumnof41(ArrayList<EstudianteF41> alumnof41) {
        this.alumnof41 = alumnof41;
    }

    public ArrayList<EstudianteF42> getAlumnof42() {
        return alumnof42;
    }

    public void setAlumnof42(ArrayList<EstudianteF42> alumnof42) {
        this.alumnof42 = alumnof42;
    }
    private ArrayList<EstudianteF11> alumnof11;
    private ArrayList<EstudianteF12> alumnof12;
    private ArrayList<EstudianteF21> alumnof21;
    private ArrayList<EstudianteF22> alumnof22;
    private ArrayList<EstudianteF31> alumnof31;
    private ArrayList<EstudianteF32> alumnof32;
    private ArrayList<EstudianteF41> alumnof41;
    private ArrayList<EstudianteF42> alumnof42;

    private ArrayList<EstudianteEstilo> alumnosEstilo;

    public ArrayList<EstudianteEstilo> getAlumnosEstilo() {
        return alumnosEstilo;
    }

    public void setAlumnosEstilo(ArrayList<EstudianteEstilo> alumnosEstilo) {
        this.alumnosEstilo = alumnosEstilo;
    }


    public Alumnado() {
        alumnos = new ArrayList<Estudiante>();
        filtroAlumnos=new ArrayList<Estudiante>();
        alumnosEstilo = new ArrayList<EstudianteEstilo>();
        alumnof11 = new ArrayList<EstudianteF11>();
        alumnof12 = new ArrayList<EstudianteF12>();
        alumnof21 = new ArrayList<EstudianteF21>();
        alumnof22 = new ArrayList<EstudianteF22>();
        alumnof31 = new ArrayList<EstudianteF31>();
        alumnof32 = new ArrayList<EstudianteF32>();
        alumnof41 = new ArrayList<EstudianteF41>();
        alumnof42 = new ArrayList<EstudianteF42>();
        try {
            alumnos = Retorno.getAllEstudiantes();
            filtroAlumnos=Retorno.getAllEstudiantes();
            alumnosEstilo = Retorno.getAllEstudianteEstilo();
            alumnof11= Retorno.getAllEstudiantesFs11();
            alumnof12= Retorno.getAllEstudiantesFs12();
            alumnof21 = Retorno.getAllEstudiantesFs21();
            alumnof22 = Retorno.getAllEstudiantesFs22();
            alumnof31 = Retorno.getAllEstudiantesFs31();
            alumnof32 = Retorno.getAllEstudiantesFs32();
            alumnof41 = Retorno.getAllEstudiantesFs41();
            alumnof42 = Retorno.getAllEstudiantesFs42();
          

        } catch (DAOException ex) {
            System.out.println("ERROR: " + ex);
        }
    }

    public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("/AppTituloProyecto/faces/screenProfesor.xhtml");
        } catch (IOException ex) {
            System.out.println("puata madre");
        }
        context.addMessage(null, new FacesMessage("Successful", "Your message: "));
    }

    public void eliminar(String correo) {

        try {
            Retorno.removeEstudiante(correo);
        } catch (DAOException ex) {
            System.out.println("error llamando al metodo remover estudiante " + ex);
        }

        for (int i = 0; i < alumnos.size(); i++) {
            if (correo.equals(alumnos.get(i).getCorreo())) {
                alumnos.remove(i);
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Estudiante Eliminado", "Estudiante eliminado correctamente"));
    }

    public void eliminarTodo() {

        try {
            Retorno.removeAllEstudiantes();
        } catch (DAOException ex) {
            System.out.println("error llamando al metodo remover estudiante " + ex);
        }

        alumnos.clear();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Todos los estudiantes eliminados", "Se han eliminado toda la promoción de estudiantes"));
    }

    public void verPerfil(String correo) {
        try {
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect("/AppTituloProyecto/faces/ProfesorIntro/viewEstudiante.xhtml?username=" + correo);
        } catch (Exception e) {
            System.out.println("Error direccionando");
        }

    }

}
