package DAO;

//import static DAO.Conexion.MySQL;
//import static DAO.Conexion.conec;
//import static DAO.Conexion.pst; 
import static DAO.Conexion.pst;
import static DAO.Conexion.rs;
import static DAO.dbConexion.conec;
import static DAO.dbConexion.pst;
import static DAO.dbConexion.rs;
import capaLogica.pregunta;
import capaLogica.preguntaVF;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import profesor.Profesor;

public class MySQLEvaluacionesDAO extends Conexion implements InterfaceEvaluaciones {

    public ArrayList<pregunta> getPreguntasByTopic(int idTopic) throws DAOException {
        ArrayList<pregunta> setPreguntas = new ArrayList<pregunta>();
        pregunta p;
        String query = "select * from Pregunta where tipo=1 and idTopic=" + idTopic + ";";
        System.out.println(query);
        try {
//            iniciarConexion(MySQL, "localhost");
//            pst = conec.prepareStatement(query);
//            rs = pst.executeQuery(); 
            Connection con = dbConexion.getConnection();
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String enunciado = rs.getString("enunciado");
                int isCorrecta = rs.getInt("isCorrecta");
                String alt1 = rs.getString("alternativa1");
                String alt2 = rs.getString("alternativa2");
                String alt3 = rs.getString("alternativa3");
                String alt4 = rs.getString("alternativa4");
                String solucion = rs.getString("solucion");
                int tipoPregunta = rs.getInt("tipo");
                p = new pregunta(id, enunciado, alt1, alt2, alt3, alt4, isCorrecta, solucion, tipoPregunta);
                setPreguntas.add(p);
            }
        } catch (Exception e) {
            throw new DAOException("Error en la Fuente de Datos de las preguntas");
        } finally {
            cerrarConexion();
        }
        return setPreguntas;
    }

    public ArrayList<preguntaVF> getPreguntasByTopicVF(int idTopic) throws DAOException {
        ArrayList<preguntaVF> setPreguntas = new ArrayList<preguntaVF>();
        preguntaVF p;
        String query = "select * from Pregunta where tipo=2 and idTopic=" + idTopic + ";";
        System.out.println(query);
        try {
//            iniciarConexion(MySQL, "localhost");
//            pst = conec.prepareStatement(query);
//            rs = pst.executeQuery(); 
            Connection con = dbConexion.getConnection();
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String enunciado = rs.getString("enunciado");
                int isCorrecta = rs.getInt("isCorrecta");
                String alt1 = rs.getString("alternativa1");
                String alt2 = rs.getString("alternativa2");

                String solucion = rs.getString("solucion");
                int tipoPregunta = rs.getInt("tipo");
                p = new preguntaVF(id, enunciado, alt1, alt2, isCorrecta, solucion, tipoPregunta);

                setPreguntas.add(p);
            }
        } catch (Exception e) {
            throw new DAOException("Error en la Fuente de Datos de las preguntas");
        } finally {
            cerrarConexion();
        }
        return setPreguntas;
    }

    public void recordPregunta(pregunta np) throws DAOException {
        String insert = "insert into Pregunta "
                + "(idTopic, enunciado, isCorrecta, alternativa1, alternativa2, alternativa3, alternativa4,solucion,tipo) "
                + "values (" + np.getIdTopico() + ",'" + np.getEnunciado() + "'," + np.getCorrecto() + ",'" + np.getAlt1() + "','" + np.getAlt2() + "','" + np.getAlt3() + "','" + np.getAlt4() + "','" + np.getSolucion() + "','" + 1 + "');";
        System.out.println(insert);
        try {
//            iniciarConexion(MySQL, "localhost");
//            pst = conec.prepareStatement(insert);
//            pst.execute(); 
            Connection con = dbConexion.getConnection();
            pst = con.prepareStatement(insert);
            pst.execute();
        } catch (SQLException ex) {
            System.out.println("Error al guardar la pregunta " + ex);
        }finally {
            cerrarConexion();
        }
        System.out.println(" Pregunta guardada");
    }

    public void recordPreguntaVF(preguntaVF np) throws DAOException {
        String insert = "insert into Pregunta "
                + "(idTopic, enunciado, isCorrecta, alternativa1, alternativa2,solucion,tipo) "
                + "values (" + np.getIdTopico() + ",'" + np.getEnunciado() + "'," + np.getCorrecto() + ",'" + np.getAlt1() + "','" + np.getAlt2() + "','" + np.getSolucion() + "','" + 2 + "');";
        System.out.println(insert);
        try {
//            iniciarConexion(MySQL, "localhost");
//            pst = conec.prepareStatement(insert);
//            pst.execute(); 
            Connection con = dbConexion.getConnection();
            pst = con.prepareStatement(insert);
            pst.execute();
        } catch (SQLException ex) {
            System.out.println("Error al guardar la pregunta " + ex);
        }finally {
            cerrarConexion();
        }
        System.out.println(" Pregunta guardada");
    }

    public void removePregunta(int idPregunta) throws DAOException {
        String delete = "delete from Pregunta where id=" + idPregunta;
        System.out.println(delete);
        try {
//            iniciarConexion(MySQL, "localhost");
//            pst = conec.prepareStatement(delete);
//            pst.execute(); 
            Connection con = dbConexion.getConnection();
            pst = con.prepareStatement(delete);
            pst.execute();
        } catch (SQLException ex) {
            System.out.println("Error al guardar borrar la pregunta " + ex);
        }finally {
            cerrarConexion();
        }
        System.out.println(" Pregunta borrada");

    }

    public void removePreguntaVF(int idPregunta) throws DAOException {
        String delete = "delete from Pregunta where id=" + idPregunta;
        System.out.println(delete);
        try {
//            iniciarConexion(MySQL, "localhost");
//            pst = conec.prepareStatement(delete);
//            pst.execute(); 
            Connection con = dbConexion.getConnection();
            pst = con.prepareStatement(delete);
            pst.execute();
        } catch (SQLException ex) {
            System.out.println("Error al guardar borrar la pregunta " + ex);
        }finally {
            cerrarConexion();
        }
        System.out.println(" Pregunta borrada");

    }

    @Override
    public void conocimientoDeclarado(String correo) throws DAOException {
        String insert = "update Estudiante set declarado =1 where correo='" + correo + "' ;";
        System.out.println(insert);
        try {
//            iniciarConexion(MySQL, "localhost");
//            pst = conec.prepareStatement(insert);
//            pst.execute(); 

            Connection con = dbConexion.getConnection();
            pst = con.prepareStatement(insert);
            pst.execute();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar el campo declarado " + ex);
        }finally {
            cerrarConexion();
        }
    }

    public void actualizarPass(String correo, String pass) throws DAOException {
        String update = "update Usuario set pass='" + pass + "' where correo ='" + correo + "' ;";
        System.out.println(update);
        try {
            iniciarConexion(MySQL, "localhost");
            pst = conec.prepareStatement(update);
            pst.execute();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar la contraseña " + ex);
        }finally {
            cerrarConexion();
        }

    }

    @Override
    public Profesor getProfesor(String correo) throws DAOException {
        Profesor nuevo = null;
        String query = "select  * from Profesor where correo='" + correo + "'";
        System.out.println(query);
        try {
//            iniciarConexion(MySQL, "localhost");
//            pst = conec.prepareStatement(query);
//            rs = pst.executeQuery(); 
            Connection con = dbConexion.getConnection();
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {

                String a = rs.getString("correo");
                String b = rs.getString("nombre");
                int c = rs.getInt("paralelo");
                int d = rs.getInt("asignatura");

                nuevo = new Profesor(b, a, c, d);

            }
        } catch (Exception e) {
            throw new DAOException("Error al obtener al profesor : " + e);
        }finally {
            cerrarConexion();
        }
        return nuevo;

    }

    @Override
    public ArrayList<pregunta> getPreguntasByTopicRandom(int topic) throws DAOException {
        String query = "select * from Pregunta where tipo=1 and idTopic=" + topic + " order by rand() limit 5;";
        ArrayList<pregunta> setPreguntas = new ArrayList<pregunta>();
        pregunta p;
        System.out.println(query);
        try {
//            iniciarConexion(MySQL, "localhost");
//            pst = conec.prepareStatement(query);
//            rs = pst.executeQuery(); 
            Connection con = dbConexion.getConnection();
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String enunciado = rs.getString("enunciado");
                int isCorrecta = rs.getInt("isCorrecta");
                String alt1 = rs.getString("alternativa1");
                String alt2 = rs.getString("alternativa2");
                String alt3 = rs.getString("alternativa3");
                String alt4 = rs.getString("alternativa4");
                String solucion = rs.getString("solucion");
                p = new pregunta(id, enunciado, alt1, alt2, alt3, alt4, isCorrecta, solucion, 1);
                setPreguntas.add(p);
            }
        } catch (Exception e) {
            throw new DAOException("Error en la Fuente de Datos de las preguntas");
        } finally {
            cerrarConexion();
        }
        return setPreguntas;
    }

    @Override
    public ArrayList<preguntaVF> getPreguntasByTopicRandomVF(int topic) throws DAOException {
        String query = "select * from Pregunta where tipo=2 and idTopic=" + topic + " order by rand() limit 5;";
        ArrayList<preguntaVF> setPreguntas = new ArrayList<preguntaVF>();
        preguntaVF p;
        System.out.println(query);
        try {
//            iniciarConexion(MySQL, "localhost");
//            pst = conec.prepareStatement(query);
//            rs = pst.executeQuery(); 
            Connection con = dbConexion.getConnection();
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String enunciado = rs.getString("enunciado");
                int isCorrecta = rs.getInt("isCorrecta");
                String alt1 = rs.getString("alternativa1");
                String alt2 = rs.getString("alternativa2");

                String solucion = rs.getString("solucion");
                p = new preguntaVF(id, enunciado, alt1, alt2, isCorrecta, solucion, 1);
                setPreguntas.add(p);
            }
        } catch (Exception e) {
            throw new DAOException("Error en la Fuente de Datos de las preguntas");
        } finally {
            cerrarConexion();
        }
        return setPreguntas;
    }

    @Override
    public void updatePregunta(pregunta ep) throws DAOException {
        String insert = "update Pregunta set "
                + "enunciado = '" + ep.getEnunciado() + "', isCorrecta = " + ep.getCorrecto() + ", "
                + "alternativa1 = '" + ep.getAlt1() + "', alternativa2 = '" + ep.getAlt2() + "', alternativa3 = '" + ep.getAlt3() + "', alternativa4 = '" + ep.getAlt4() + "',"
                + "solucion = '" + ep.getSolucion() + "' where id=" + ep.getId() + "and tipo=1;";
        System.out.println(insert);
        try {
//            iniciarConexion(MySQL, "localhost");
//            pst = conec.prepareStatement(insert);
//            pst.execute(); 
            Connection con = dbConexion.getConnection();
            pst = con.prepareStatement(insert);
            pst.execute();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar la pregunta " + ex);
        }finally {
            cerrarConexion();
        }
        System.out.println("Pregunta actualizada");
    }

    @Override
    public void updatePreguntaVF(preguntaVF ep) throws DAOException {
        String insert = "update Pregunta set "
                + "enunciado = '" + ep.getEnunciado() + "', isCorrecta = " + ep.getCorrecto() + ", "
                + "alternativa1 = '" + ep.getAlt1() + "', alternativa2 = '" + ep.getAlt2() + "',"
                + "solucion = '" + ep.getSolucion() + "' where id=" + ep.getId() + "and tipo=2;";
        System.out.println(insert);
        try {
//            iniciarConexion(MySQL, "localhost");
//            pst = conec.prepareStatement(insert);
//            pst.execute(); 
              Connection con = dbConexion.getConnection();
              pst = con.prepareStatement(insert);
              pst.execute();
              
        } catch (SQLException ex) {
            System.out.println("Error al actualizar la pregunta " + ex);
        }finally {
            cerrarConexion();
        }
        System.out.println("Pregunta actualizada");
    }

}
