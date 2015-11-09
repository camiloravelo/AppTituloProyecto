package DAO;

//import static DAO.Conexion.MySQL;
//import static DAO.Conexion.conec;
//import static DAO.Conexion.pst;
import static DAO.Conexion.pst;
import capaLogica.learningObject;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLRecursosDAO extends Conexion implements InterfaceRecursos {

    @Override
    public void guardarRecurso(learningObject recurso) throws DAOException {
        String insert = "insert into Recurso values ('" + recurso.getId() + "'," + recurso.getIdTopico() + "," + recurso.getRanking() + ","
                + " " + recurso.getEstilo1() + ", " + recurso.getEstilo2() + " , " + recurso.getEstilo3() + ", " + recurso.getEstilo4() + ", "
                + " '" + recurso.getTitulo() + "', '" + recurso.getAutor() + "','" + recurso.getTipoRecurso() + "','" + recurso.getNameTopico() + "','"
                + recurso.getRuta() + "','" + recurso.getDescripcion() + "')";

        System.out.println("Descripcion recurso" + recurso.getDescripcion());
        System.out.println("Insert:" + insert);
        try {
//            iniciarConexion(MySQL, "localhost");
//            pst = conec.prepareStatement(insert);
//            pst.execute(); 

            Connection con = dbConexion.getConnection();
            pst = con.prepareStatement(insert);
            pst.execute();
        } catch (SQLException ex) {
            System.out.println("Error al guardar la pregunta " + ex);
        }
        System.out.println(" Pregunta guardada");

    }

    // Aquie se da vuelta la tabla para mostrar los resultados
    @Override
    public ArrayList<learningObject> leerRecurso(int idTopicoSeleccionado) throws DAOException {
        ArrayList<learningObject> o = new ArrayList<learningObject>();
        for (int i = idTopicoSeleccionado; i > 2; i--) {

            String query = "select * from Recurso where ('" + idTopicoSeleccionado + "') = idTopico ;";
            System.out.println("Leer recursos:" + query);
            try {
//                iniciarConexion(MySQL, "localhost");
//                pst = conec.prepareStatement(query);
//                rs = pst.executeQuery(); 

                Connection con = dbConexion.getConnection();
                pst = con.prepareStatement(query);
                rs = pst.executeQuery();
                while (rs.next()) {
                    learningObject nuevo = new learningObject();

                    String id = rs.getString("id");
                    int idTopico = rs.getInt("idTopico");
                    int rankink = rs.getInt("ranking");
                    int estilo1 = rs.getInt("estilo1");
                    int estilo2 = rs.getInt("estilo2");
                    int estilo3 = rs.getInt("estilo3");
                    int estilo4 = rs.getInt("estilo4");

                    String titulo = rs.getString("titulo");
                    String autor = rs.getString("autor");
                    String tipo = rs.getString("tipo");
                    String nombreTopico = rs.getString("nombreTopico");
                    String ruta = rs.getString("ruta");
                    String descripcion = rs.getString("descripcion");

                    nuevo.setId(id);
                    nuevo.setIdTopico(idTopico);
                    nuevo.setRanking(rankink);
                    nuevo.setEstilo1(estilo1);
                    nuevo.setEstilo2(estilo2);
                    nuevo.setEstilo3(estilo3);
                    nuevo.setEstilo4(estilo4);

                    nuevo.setTitulo(titulo);
                    nuevo.setAutor(autor);
                    nuevo.setTipoRecurso(tipo);
                    nuevo.setNameTopico(nombreTopico);
                    nuevo.setRuta(ruta);
                    nuevo.setDescripcion(descripcion);
                    nuevo.EstablecerEstilo();

                    System.out.println("Objeto descripcion Mysql:" + nuevo.getDescripcion());
                    System.out.println("Objeto nuevo:" + nuevo);
                    o.add(nuevo);
                }
            } catch (Exception e) {
                throw new DAOException("Error en la Fuente de Datos de los oas");
            } finally {
                cerrarConexion();
            }
            idTopicoSeleccionado--;
        }
        System.out.println("Objeto o: " + o);
        return o;
    }

    @Override
    public ArrayList<learningObject> leerRecursoOaAprendido(int idTopicoSeleccionado) throws DAOException {
        ArrayList<learningObject> o = new ArrayList<learningObject>();
        System.out.println("idTopico OA" + idTopicoSeleccionado);
        for (int i = idTopicoSeleccionado; i > 0; i--) {

            String query = "select * from Recurso where ('" + idTopicoSeleccionado + "') = idTopico ;";
            System.out.println("Leer recursos OA :" + query);
            try {
//                iniciarConexion(MySQL, "localhost");
//                pst = conec.prepareStatement(query);
//                rs = pst.executeQuery(); 

                Connection con = dbConexion.getConnection();
                pst = con.prepareStatement(query);
                rs = pst.executeQuery();
                while (rs.next()) {
                    learningObject nuevo = new learningObject();

                    String id = rs.getString("id");
                    int idTopico = rs.getInt("idTopico");
                    int rankink = rs.getInt("ranking");
                    int estilo1 = rs.getInt("estilo1");
                    int estilo2 = rs.getInt("estilo2");
                    int estilo3 = rs.getInt("estilo3");
                    int estilo4 = rs.getInt("estilo4");

                    String titulo = rs.getString("titulo");
                    String autor = rs.getString("autor");
                    String tipo = rs.getString("tipo");
                    String nombreTopico = rs.getString("nombreTopico");
                    String ruta = rs.getString("ruta");
                    //String descripcion = rs.getString("descripcion");

                    nuevo.setId(id);
                    nuevo.setIdTopico(idTopico);
                    nuevo.setRanking(rankink);
                    nuevo.setEstilo1(estilo1);
                    nuevo.setEstilo2(estilo2);
                    nuevo.setEstilo3(estilo3);
                    nuevo.setEstilo4(estilo4);

                    nuevo.setTitulo(titulo);
                    nuevo.setAutor(autor);
                    nuevo.setTipoRecurso(tipo);
                    nuevo.setNameTopico(nombreTopico);
                    nuevo.setRuta(ruta);
                    //nuevo.setDescripcion(descripcion);
                    nuevo.EstablecerEstilo();

                    System.out.println("Objeto derecho Mysql:" + nuevo.getDerechos());
                    System.out.println("Objeto nuevo:" + nuevo);
                    o.add(nuevo);
                }
            } catch (Exception e) {
                throw new DAOException("Error en la Fuente de Datos de los oas");
            } finally {
                cerrarConexion();
            }
            idTopicoSeleccionado--;
        }

        System.out.println("Objeto o: " + o);
        return o;
    }

    @Override
    public ArrayList<learningObject> leerNombreTopico() throws DAOException {
        ArrayList<learningObject> o = new ArrayList<learningObject>();
        String query = "SELECT * FROM recurso GROUP BY idTopico order by idTopico desc;";
        System.out.println(query);
        try {
//            iniciarConexion(MySQL, "localhost");
//            pst = conec.prepareStatement(query);
//            rs = pst.executeQuery(); 
            
                Connection con = dbConexion.getConnection();
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                learningObject nuevo = new learningObject();

                String id = rs.getString("id");
                int idTopico = rs.getInt("idTopico");
                /* 
                 int rankink = rs.getInt("ranking");
                 int estilo1 = rs.getInt("estilo1");
                 int estilo2 = rs.getInt("estilo2");
                 int estilo3 = rs.getInt("estilo3");
                 int estilo4 = rs.getInt("estilo4");

                 String titulo = rs.getString("titulo");
                 String autor = rs.getString("autor");
                 String tipo = rs.getString("tipo");
                 */
                String nombreTopico = rs.getString("nombreTopico");
                String ruta = rs.getString("ruta");

                nuevo.setId(id);
                nuevo.setIdTopico(idTopico);
                nuevo.setRuta(ruta);
                /*  
                 nuevo.setRanking(rankink);
                 nuevo.setEstilo1(estilo1);
                 nuevo.setEstilo2(estilo2);
                 nuevo.setEstilo3(estilo3);
                 nuevo.setEstilo4(estilo4);

                 

                 nuevo.setTitulo(titulo);
                 nuevo.setAutor(autor);
                 nuevo.setTipoRecurso(tipo);
                 */
                nuevo.setNameTopico(nombreTopico);
                nuevo.EstablecerEstilo();
                o.add(nuevo);
            }
        } catch (Exception e) {
            throw new DAOException("Error en la Fuente de Datos de los oas");
        } finally {
            cerrarConexion();
        }

        return o;
    }

    @Override
    public void borrarRecursos() throws DAOException {
        String delete = "delete from Recurso";
        System.out.println(delete);
        try {
//            iniciarConexion(MySQL, "localhost");
//            pst = conec.prepareStatement(delete);
//            pst.execute(); 
             Connection con = dbConexion.getConnection();
            pst = con.prepareStatement(delete);
           pst.execute();
        } catch (SQLException ex) {
            System.out.println("Error borrando los oas " + ex);
        }
    }

}
