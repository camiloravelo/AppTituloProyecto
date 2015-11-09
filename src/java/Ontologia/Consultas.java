package Ontologia;

import capaLogica.learningObject;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.tdb.TDBFactory;
import java.util.ArrayList;
import java.util.List;

public class Consultas {

    private Dataset dataset;
    private Query query;
    private QueryExecution qexec;
    private ResultSet results;
    private QuerySolution soln;
    private Literal literal;

    public Consultas() {
        String directory = ManejaConfig.ontologia;

       //
        
        //String directory = "/Users/CRAVELO/Desktop/Tesis/tesisRobby/Ontologia/tesis/";
        //String directory = "/Users/Badomar/Desktop/Proyecto Ravelo/Ontologia/tesis";
        //String directory = "C:\\Reposotirios\\Moyano\\Ontologia\\tesis";
        try {
            dataset = TDBFactory.createDataset(directory);
        } catch (Exception e) {
            System.out.println("IMPOSIBLE CARGAR ONTOLOGIA");
        }
        dataset.begin(ReadWrite.READ);
    }

    public ArrayList<learningObject> getAllLearningObjects() {
        ArrayList<learningObject> objetos = new ArrayList<learningObject>();
        String a = "PREFIX t:<http://informatica.uv.cl/tesis#>"
                + " SELECT ?topico ?autor ?id ?ruta ?recurso ?titulo "
                + " WHERE {"
                + " ?subject t:topicoTieneOA ?subjectTopico."
                + " ?subject t:oaAutor ?autor."
                + " ?subject t:oaId ?id."
                + " ?subject t:oaRuta ?ruta."
                + " ?subject t:oaTipoRecurso ?recurso."
                + " ?subject t:oaTitulo ?titulo."
                + " ?subjectTopico t:topicoName ?topico."
                + " ?subjectTopico t:topicoId ?idtopico."
                + " }"
                + " ORDER BY (?idtopico) ";

        String a2 = "PREFIX t:<http://informatica.uv.cl/tesis#>"
                + " SELECT ?autor ?descripcion ?fecha ?version ?derechos ?formato ?e1 ?e2 ?e3 ?e4 ?id ?ruta ?recurso ?titulo ?topico"
                + " WHERE {"
                + " ?subject t:topicoTieneOA ?subjectTopico."
                + " ?subject t:oaAutor ?autor."
                + " ?subject t:oaDescripcion ?descripcion."
                + " ?subject t:oaFecha ?fecha."
                + " ?subject t:oaVersion ?version."
                + " ?subject t:oaDerechos ?derechos."
                + " ?subject t:oaFormato ?formato."
                + " ?subject t:oaEstilo1 ?e1."
                + " ?subject t:oaEstilo2 ?e2."
                + " ?subject t:oaEstilo3 ?e3."
                + " ?subject t:oaEstilo4 ?e4."
                + " ?subject t:oaId ?id."
                + " ?subject t:oaRuta ?ruta."
                + " ?subject t:oaTipoRecurso ?recurso."
                + " ?subject t:oaTitulo ?titulo."
                + " ?subjectTopico t:topicoName ?topico."
                + " ?subjectTopico t:topicoId ?idtopico." //es el unico q no incluyo en el arreglo
                + " }"
                + " ORDER BY (?idtopico) ";

        query = QueryFactory.create(a2);
        qexec = QueryExecutionFactory.create(query, dataset);
        results = qexec.execSelect();

        List<String> lista = new ArrayList<String>();
        lista = results.getResultVars();
        System.out.println("Lista getAllLearningObjects:"+lista);

        while (results.hasNext()) {
            soln = results.nextSolution();

            literal = soln.getLiteral("autor");   // Get a result variable - must be a literal
            String autor = literal.getString();
            ////////////////////////////////////////////////

            literal = soln.getLiteral("descripcion");   // Get a result variable - must be a literal
            String descripcion = literal.getString();

            literal = soln.getLiteral("fecha");   // Get a result variable - must be a literal
            String fecha = literal.getString();

            literal = soln.getLiteral("version");   // Get a result variable - must be a literal
            String version = literal.getString();

            literal = soln.getLiteral("derechos");   // Get a result variable - must be a literal
            String derechos = literal.getString();

            literal = soln.getLiteral("formato");   // Get a result variable - must be a literal
            String formato = literal.getString();

            literal = soln.getLiteral("e1");   // Get a result variable - must be a literal
            String e1 = literal.getString();

            literal = soln.getLiteral("e2");   // Get a result variable - must be a literal
            String e2 = literal.getString();

            literal = soln.getLiteral("e3");   // Get a result variable - must be a literal
            String e3 = literal.getString();

            literal = soln.getLiteral("e4");   // Get a result variable - must be a literal
            String e4 = literal.getString();

            /////////////////////////////////////////////////////////
            literal = soln.getLiteral("id");   // Get a result variable - must be a literal
            String id = literal.getString();

            literal = soln.getLiteral("ruta");   // Get a result variable - must be a literal
            String ruta = literal.getString();

            literal = soln.getLiteral("recurso");   // Get a result variable - must be a literal
            String recurso = literal.getString();

            literal = soln.getLiteral("titulo");   // Get a result variable - must be a literal
            String titulo = literal.getString();

            literal = soln.getLiteral("topico");   // Get a result variable - must be a literal
            String topico = literal.getString();
            System.out.println(topico);

            //====================================================
            learningObject nuevoObjeto = new learningObject();
            System.out.println("objecto creado pero vacio");

            System.out.println("seteando al autor");
            nuevoObjeto.setAutor(autor);
            System.out.println("set realizado :)");

            nuevoObjeto.setDescripcion(descripcion);
            nuevoObjeto.setFecha(fecha);
            nuevoObjeto.setVersion(version);
            nuevoObjeto.setDerechos(derechos);

            nuevoObjeto.setFormato(formato);

            int estilo1 = Integer.parseInt(e1);
            nuevoObjeto.setEstilo1(estilo1);

            int estilo2 = Integer.parseInt(e2);
            nuevoObjeto.setEstilo2(estilo2);

            int estilo3 = Integer.parseInt(e3);
            nuevoObjeto.setEstilo3(estilo3);

            int estilo4 = Integer.parseInt(e4);
            nuevoObjeto.setEstilo4(estilo4);

            nuevoObjeto.setId(id);
            nuevoObjeto.setTipoRecurso(recurso);
            nuevoObjeto.setTitulo(titulo);
            nuevoObjeto.setNameTopico(topico);
            nuevoObjeto.setRuta(ruta);
            System.out.println(nuevoObjeto.toString());
            objetos.add(nuevoObjeto);
            System.out.println("objeto añadido al arregelo");

        }
        return objetos;
    }

    public learningObject getOAUnit(String identificador) {
        System.out.println("entramos al metodo");
        System.out.println("el id es: " + identificador);
        learningObject nuevoObjeto = new learningObject();

        String filter = "FILTER ( ?id=" + identificador + ")";
        String a = "PREFIX t:<http://informatica.uv.cl/tesis#>"
                + " SELECT ?topico ?autor ?id ?ruta ?recurso ?titulo "
                + " WHERE {"
                + " ?subject t:topicoTieneOA ?subjectTopico."
                + " ?subject t:oaAutor ?autor."
                + " ?subject t:oaId ?id."
                + " ?subject t:oaRuta ?ruta."
                + " ?subject t:oaTipoRecurso ?recurso."
                + " ?subject t:oaTitulo ?titulo."
                + " ?subjectTopico t:topicoName ?topico."
                + " ?subjectTopico t:topicoId ?idtopico."
                + " }"
                + " ORDER BY (?idtopico) ";

        String a2 = "PREFIX t:<http://informatica.uv.cl/tesis#>"
                + " SELECT ?autor ?descripcion ?fecha ?version ?derechos ?formato ?e1 ?e2 ?e3 ?e4 ?id ?ruta ?recurso ?titulo ?topico"
                + " WHERE {"
                + " ?subject t:topicoTieneOA ?subjectTopico."
                + " ?subject t:oaAutor ?autor."
                + " ?subject t:oaDescripcion ?descripcion."
                + " ?subject t:oaFecha ?fecha."
                + " ?subject t:oaVersion ?version."
                + " ?subject t:oaDerechos ?derechos."
                + " ?subject t:oaFormato ?formato."
                + " ?subject t:oaEstilo1 ?e1."
                + " ?subject t:oaEstilo2 ?e2."
                + " ?subject t:oaEstilo3 ?e3."
                + " ?subject t:oaEstilo4 ?e4."
                + " ?subject t:oaId ?id."
                + " ?subject t:oaRuta ?ruta."
                + " ?subject t:oaTipoRecurso ?recurso."
                + " ?subject t:oaTitulo ?titulo."
                + " ?subjectTopico t:topicoName ?topico."
                + " ?subjectTopico t:topicoId ?idtopico. "
                + filter
                + " }"
                + " ORDER BY (?idtopico) ";
// nameTopico es el unico q no incluyo en el arreglo
        System.out.println(a2);

        query = QueryFactory.create(a2);
        qexec = QueryExecutionFactory.create(query, dataset);
        results = qexec.execSelect();

        System.out.println("HASTA AQUI VAMOS BIEN");
        List<String> lista = new ArrayList<String>();
        lista = results.getResultVars();

        while (results.hasNext()) {
            soln = results.nextSolution();

            literal = soln.getLiteral("autor");   // Get a result variable - must be a literal
            String autor = literal.getString();
            ////////////////////////////////////////////////

            literal = soln.getLiteral("descripcion");   // Get a result variable - must be a literal
            String descripcion = literal.getString();

            literal = soln.getLiteral("fecha");   // Get a result variable - must be a literal
            String fecha = literal.getString();

            literal = soln.getLiteral("version");   // Get a result variable - must be a literal
            String version = literal.getString();

            literal = soln.getLiteral("derechos");   // Get a result variable - must be a literal
            String derechos = literal.getString();

            literal = soln.getLiteral("formato");   // Get a result variable - must be a literal
            String formato = literal.getString();

            literal = soln.getLiteral("e1");   // Get a result variable - must be a literal
            String e1 = literal.getString();

            literal = soln.getLiteral("e2");   // Get a result variable - must be a literal
            String e2 = literal.getString();

            literal = soln.getLiteral("e3");   // Get a result variable - must be a literal
            String e3 = literal.getString();

            literal = soln.getLiteral("e4");   // Get a result variable - must be a literal
            String e4 = literal.getString();

            /////////////////////////////////////////////////////////
            literal = soln.getLiteral("id");   // Get a result variable - must be a literal
            String id = literal.getString();

            literal = soln.getLiteral("ruta");   // Get a result variable - must be a literal
            String ruta = literal.getString();

            literal = soln.getLiteral("recurso");   // Get a result variable - must be a literal
            String recurso = literal.getString();

            literal = soln.getLiteral("titulo");   // Get a result variable - must be a literal
            String titulo = literal.getString();

            literal = soln.getLiteral("topico");   // Get a result variable - must be a literal
            String topico = literal.getString();
            System.out.println(topico);

            //====================================================
            System.out.println("objecto creado pero vacio");

            nuevoObjeto.setAutor(autor);
            nuevoObjeto.setDescripcion(descripcion);
            nuevoObjeto.setFecha(fecha);
            nuevoObjeto.setVersion(version);
            nuevoObjeto.setDerechos(derechos);

            nuevoObjeto.setFormato(formato);

            int estilo1 = Integer.parseInt(e1);
            nuevoObjeto.setEstilo1(estilo1);

            int estilo2 = Integer.parseInt(e2);
            nuevoObjeto.setEstilo2(estilo2);

            int estilo3 = Integer.parseInt(e3);
            nuevoObjeto.setEstilo3(estilo3);

            int estilo4 = Integer.parseInt(e4);
            nuevoObjeto.setEstilo4(estilo4);

            nuevoObjeto.setId(id);
            nuevoObjeto.setTipoRecurso(recurso);
            nuevoObjeto.setTitulo(titulo);
            nuevoObjeto.setNameTopico(topico);
            nuevoObjeto.setRuta(ruta);
            System.out.println(nuevoObjeto.toString());
            System.out.println("objeto añadido al arregelo");

        }
        return nuevoObjeto;
    }

    public ArrayList<learningObject> getOA(String filter) {
        ArrayList<learningObject> objetos = new ArrayList<learningObject>();
        String a = "PREFIX t:<http://informatica.uv.cl/tesis#>"
                + " SELECT ?topico ?autor ?id ?e1 ?e2 ?e3 ?e4 ?ruta ?recurso ?titulo ?idtopico ?descripcion"
                + " WHERE {"
                + " ?subject t:topicoTieneOA ?subjectTopico."
                + " ?subject t:oaAutor ?autor."
                + " ?subject t:oaId ?id."
                + " ?subject t:oaEstilo1 ?e1."
                + " ?subject t:oaEstilo2 ?e2."
                + " ?subject t:oaEstilo3 ?e3."
                + " ?subject t:oaEstilo4 ?e4."
                + " ?subject t:oaRuta ?ruta."
                + " ?subject t:oaTipoRecurso ?recurso."
                + " ?subject t:oaTitulo ?titulo."
                + " ?subjectTopico t:topicoName ?topico."
                + " ?subjectTopico t:topicoId ?idtopico."
                + " ?subject t:oaDescripcion ?descripcion."
                + filter
                + " }"
                + " ORDER BY DESC(?id)"; //Ordena los topicos desc

        System.out.println("Consulta"+a);
        query = QueryFactory.create(a);
        qexec = QueryExecutionFactory.create(query, dataset);
        results = qexec.execSelect();

        //System.out.println("HASTA AQUI VAMOS BIEN");
        List<String> lista = new ArrayList<String>();
        lista = results.getResultVars();
        System.out.println("Lista getOA:"+lista);
        while (results.hasNext()) {
            soln = results.nextSolution();

            literal = soln.getLiteral("topico");   // Get a result variable - must be a literal
            String topico = literal.getString();
            System.out.println("Topico:"+topico);

            literal = soln.getLiteral("autor");   // Get a result variable - must be a literal
            String autor = literal.getString();
            System.out.println("Autor:"+autor);

            literal = soln.getLiteral("id");   // Get a result variable - must be a literal
            String id = literal.getString();
            System.out.println("Id:"+id);

            literal = soln.getLiteral("e1");   // Get a result variable - must be a literal
            String e1 = literal.getString();

            literal = soln.getLiteral("e2");   // Get a result variable - must be a literal
            String e2 = literal.getString();

            literal = soln.getLiteral("e3");   // Get a result variable - must be a literal
            String e3 = literal.getString();

            literal = soln.getLiteral("e4");   // Get a result variable - must be a literal
            String e4 = literal.getString();

            literal = soln.getLiteral("ruta");   // Get a result variable - must be a literal
            String ruta = literal.getString();
            System.out.println("Ruta:"+ruta);

            literal = soln.getLiteral("recurso");   // Get a result variable - must be a literal
            String recurso = literal.getString();

            literal = soln.getLiteral("titulo");   // Get a result variable - must be a literal
            String titulo = literal.getString();

            literal = soln.getLiteral("idtopico");   // Get a result variable - must be a literal
            String idtopico = literal.getString();
            System.out.println("idtopico:"+idtopico);
            
            literal = soln.getLiteral("descripcion");   // Get a result variable - must be a literal
            String descripcion = literal.getString();
            System.out.println("descripcion:"+descripcion);

            //====================================================
            learningObject nuevoObjeto = new learningObject();
            System.out.println("objecto creado pero vacio");

            nuevoObjeto.setAutor(autor);
            System.out.println("Autor Nuevo:"+autor);
            nuevoObjeto.setId(id);
            System.out.println("Id Nuevo:"+id);

            int aux = Integer.parseInt(idtopico);
            nuevoObjeto.setIdTopico(aux);
            
            int estilo1 = Integer.parseInt(e1);
            nuevoObjeto.setEstilo1(estilo1);

            int estilo2 = Integer.parseInt(e2);
            nuevoObjeto.setEstilo2(estilo2);

            int estilo3 = Integer.parseInt(e3);
            nuevoObjeto.setEstilo3(estilo3);

            int estilo4 = Integer.parseInt(e4);
            nuevoObjeto.setEstilo4(estilo4);

            nuevoObjeto.setTipoRecurso(recurso);
            nuevoObjeto.setDescripcion(descripcion);
            System.out.println("Nombre Nuevo Descripcion:"+descripcion);
            nuevoObjeto.setTitulo(titulo);
            nuevoObjeto.setNameTopico(topico);
            System.out.println("Nombre Nuevo Topico:"+topico);
            nuevoObjeto.setRuta(ruta);
            System.out.println("LA RUTA ES : " + ruta);
            nuevoObjeto.EstablecerEstilo();///setea el estilo
            System.out.println(nuevoObjeto.toString());
            objetos.add(nuevoObjeto);

        }
        return objetos;
    }

}
