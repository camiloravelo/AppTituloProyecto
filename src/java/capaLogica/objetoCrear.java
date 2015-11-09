package capaLogica;

import Ontologia.Inserciones;
import Ontologia.ManejaConfig;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "objetoCrear")
@ViewScoped
public class objetoCrear implements Serializable {
    private static final long serialVersionUID = -5386388760162003705L;

    learningObject oa = new learningObject();
    private UploadedFile file;

    public objetoCrear() {
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public learningObject getOa() {
        return oa;
    }

    public void setOa(learningObject oa) {
        this.oa = oa;
    }

    public void procesar() {

        if (!oa.getTitulo().equals("") && !oa.getDescripcion().equals("") && !oa.getAutor().equals("") && !oa.getFecha().equals("") && !oa.getVersion().equals("") && !oa.getDerechos().equals("")) {
            System.out.println("el titulo es : " + oa.getTitulo());

            Date date = new Date();
            System.out.println(date.getTime());
            String id = String.valueOf(date.getTime());
            //String destination = "/Users/CRAVELO/Desktop/DIUV/";

            String destination = ManejaConfig.objetos;

            String ruta = destination + id + "_" + file.getFileName();
            String nombre = id + "_" + file.getFileName();
            System.out.println("La ruta es: " + ruta);
            System.out.println("el nombre es: " + file.getFileName());
            try {
                this.copyFile(ruta, file.getInputstream());
            } catch (IOException ex) {
                System.out.println("Error con el archivo " + ex);

            }

            String insert1 = "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> PREFIX tesis:<http://informatica.uv.cl/tesis#> INSERT DATA {<http://informatica.uv.cl/tesisDatos#oa_" + id + "> rdf:type tesis:OA .}";

            String insert2 = ""
                    + "PREFIX t:<http://informatica.uv.cl/tesis#>"
                    + "INSERT DATA {"
                    + "<http://informatica.uv.cl/tesisDatos#oa_" + id + ">"
                    + " t:oaAutor '" + oa.getAutor() + "';"
                    + " t:oaDerechos '" + oa.getDerechos() + "';"
                    + " t:oaDescripcion '" + oa.getDescripcion() + "';"
                    + " t:oaEstilo1 " + oa.getEstilo1() + ";"
                    + " t:oaEstilo2 " + oa.getEstilo2() + ";"
                    + " t:oaEstilo3 " + oa.getEstilo3() + ";"
                    + " t:oaEstilo4 " + oa.getEstilo4() + ";"
                    + " t:oaFecha '" + oa.getFecha() + "';"
                    + " t:oaFormato '" + oa.getFormato() + "';"
                    + " t:oaId " + id + ";"
                    + " t:oaRuta '" + nombre + "';"
                    + " t:oaTipoRecurso '" + oa.getTipoRecurso() + "';"
                    + " t:oaTitulo '" + oa.getTitulo() + "';"
                    + " t:oaVersion '" + oa.getVersion() + "';"
                    + " t:topicoTieneOA <http://informatica.uv.cl/tesisDatos#Topicos_" + oa.getIdTopico() + ">."
                    + "}";

            System.out.println("SENTENCIA FINAL");
            System.out.println(insert1);
            System.out.println(insert2);

            try {
                System.out.println("LLAMANDO AL METODO INSERTAR");
                Inserciones i = new Inserciones();
                System.out.println("CREAMOS LA CLASE");
                i.insertar(insert1, insert2);
                System.out.println("INSERCION REALIZADA CON EXITO");
            } catch (Exception ex) {
                System.out.println("ERROR: " + ex);
            }
            try {
                FacesContext contex = FacesContext.getCurrentInstance();
                contex.addMessage(null, new FacesMessage("Muy Bien", "Recurso creado Correctamente"));
                contex.getExternalContext().redirect("/AppTituloProyecto/faces/Profesor/gestionarRecursos.xhtml");

            } catch (Exception e) {
                System.out.println("Error direccionando " + e);
            }

        } else {

            System.out.println("Hay campos incompletos");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Hay campos incompletos"));
        }

    }

    public void copyFile(String fileName, InputStream in) {
        try {
            OutputStream out = new FileOutputStream(new File(fileName));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
            System.out.println("Nuevo archivo creado!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
