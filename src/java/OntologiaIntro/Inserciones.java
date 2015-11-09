package OntologiaIntro;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.update.GraphStore;
import com.hp.hpl.jena.update.GraphStoreFactory;
import com.hp.hpl.jena.update.UpdateExecutionFactory;
import com.hp.hpl.jena.update.UpdateFactory;
import com.hp.hpl.jena.update.UpdateProcessor;
import com.hp.hpl.jena.update.UpdateRequest;

public class Inserciones {

    Dataset dataset;
    GraphStore graphStore;
    UpdateRequest request;
    UpdateProcessor proc;

    public Inserciones() {
        String directory = ManejaConfig.ontologia;
       // String directory = "/Users/robby/Documents/Ontologia/tesis";
        //String directory = "C:\\Reposotirios\\Moyano\\Ontologia\\tesis";
        try {
            dataset = TDBFactory.createDataset(directory);
        } catch (Exception e) {
            System.out.println("IMPOSIBLE CARGAR ONTOLOGIA");
        }
        dataset.begin(ReadWrite.WRITE);
        System.out.println("CONSTRUIMOS LA CLASE");
    }

    public void insertar(String insert1, String insert2) {
        // String insert1 = "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> PREFIX tesis:<http://informatica.uv.cl/tesis#> INSERT DATA {<http://informatica.uv.cl/tesisDatos#oa_1404962079769> rdf:type tesis:OA .}";
        //String insert2 = "PREFIX t:<http://informatica.uv.cl/tesis#>INSERT DATA {<http://informatica.uv.cl/tesisDatos#oa_1404962079769> t:oaAutor 'Lorena Gonzalez'; t:oaDerechos 'libre uso'; t:oaDescripcion '-'; t:oaEstilo1 1; t:oaEstilo2 2; t:oaEstilo3 1; t:oaEstilo4 1; t:oaFecha '31-1-2013'; t:oaFormato 'doc'; t:oaId 1404962079769; t:oaTipoRecurso 'tutorial'; t:oaTitulo 'ejercicios de arreglos'; t:oaVersion '2.1'; t:topicoTieneOA 13.}";

        System.out.println("ENTRAMOS AL METODO");
        graphStore = GraphStoreFactory.create(dataset);
        request = UpdateFactory.create(insert1);
        proc = UpdateExecutionFactory.create(request, graphStore);
        proc.execute();
        System.out.println("asta aqui vamos bien");
        request = UpdateFactory.create(insert2);
        proc = UpdateExecutionFactory.create(request, graphStore);
        proc.execute();

        dataset.commit();
        dataset.end();
        System.out.println("FELICITACIONES");
    }

    public void eliminar(String id) {
        System.out.println("estamos en el eliminar");
        String insert1 = "DELETE WHERE { <http://informatica.uv.cl/tesisDatos#oa_" + id + "> ?p ?o } ;";
        System.out.println(insert1);
        graphStore = GraphStoreFactory.create(dataset);
        System.out.println("creamos el graph");
        request = UpdateFactory.create(insert1);
        System.out.println("seteamos el request");
        proc = UpdateExecutionFactory.create(request, graphStore);
        System.out.println("seteamos proc");
        proc.execute();
        System.out.println("sentencia ejecutada");

        dataset.commit();
        dataset.end();

        System.out.println("dato eliminado");

    }

}
