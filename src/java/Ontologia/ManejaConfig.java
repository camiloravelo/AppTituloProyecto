package Ontologia;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;

public class ManejaConfig {

    public static String path;
    public static String ontologia;
    public static String objetos;

    public static String user;
    public static String pass;
    public static String host;
    public static String database;

    public void getPropValues() {

        System.out.println("[Accediendo] accediendo al archivo de las configuraciones");
        URL r = this.getClass().getResource("/");
        String decoded = null;
        try {
            // path decoded "/C:/Program Files/Tomcat 6.0/webapps/myapp/WEB-INF/classes/"
            decoded = URLDecoder.decode(r.getFile(), "UTF-8");
            System.out.println(decoded);
        } catch (UnsupportedEncodingException ex) {
            System.out.println("error");
        }
        //decode tiene la ruta, hay q borrarle clases

        decoded = decoded.replace("classes/", "");
        path = decoded;
        System.out.println(decoded);

        Properties prop = new Properties();
        String propFileName = decoded + "config.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        try {
            prop.load(new FileInputStream(propFileName));
        } catch (Exception ex) {
            System.out.println("ERROR al cargar las configuraciones");
        }

        // comenzamos a setear los atributos
        ontologia = prop.getProperty("ontologia");
        System.out.println("la ruta para la ontologia es: " + ontologia);

        objetos = prop.getProperty("objetos");
        System.out.println("la ruta para los objetos es: " + objetos);

        user = prop.getProperty("user");
        pass = prop.getProperty("pass");
        System.out.println("la pass vale :"+pass);
        host = prop.getProperty("host");
        database = prop.getProperty("database");

    }
}
