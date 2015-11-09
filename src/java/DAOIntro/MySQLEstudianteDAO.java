package DAOIntro;

import AsignaturaIntro.EvaluacionUnit;
import AsignaturaIntro.Topico;
import static DAOIntro.ConexionIntro.MySQL;
import static DAOIntro.ConexionIntro.conec;
import static DAOIntro.ConexionIntro.pst;
import static DAOIntro.Conexion.rs;
import static DAOIntro.ConexionIntro.MySQL;
import static DAOIntro.ConexionIntro.conec;
import static DAOIntro.ConexionIntro.pst;
import static DAOIntro.ConexionIntro.rs;
import capaLogicaIntro.EstudianteEstilo;
import capaLogicaIntro.EstudianteF11;
import capaLogicaIntro.EstudianteF12;
import capaLogicaIntro.EstudianteF21;
import capaLogicaIntro.EstudianteF22;
import capaLogicaIntro.EstudianteF31;
import capaLogicaIntro.EstudianteF32;
import capaLogicaIntro.EstudianteF41;
import capaLogicaIntro.EstudianteF42;
import capaLogicaIntro.Estilo;
import capaLogicaIntro.Estudiante;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLEstudianteDAO extends ConexionIntro implements InterfaceEstudiante {

    public ArrayList<Estudiante> getAllEstudiante() throws DAOException {
        ArrayList<Estudiante> LP = new ArrayList<Estudiante>();
        Estudiante p;
        String query = "Select * from Estudiante";
        try {
            iniciarConexionIntro(MySQL, "localhost");
            pst = conec.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                String a = rs.getString(1);
                String b = rs.getString(2);
                int i = rs.getInt(3);
                double j = 100 * i / 333;
                p = new Estudiante(a, b, 1, 1, 1, 1, 1);
                LP.add(p);
            }
        } catch (Exception e) {
            
            throw new DAOException("Error en la Fuente de Datos de los estudiantes ");
           
        } finally {
            cerrarConexionIntro();
        }
        return LP;
    }

    public ArrayList<EvaluacionUnit> getHistorial(String correo) throws DAOException {
        ArrayList<EvaluacionUnit> LP = new ArrayList<EvaluacionUnit>();
        String query = "select Topic.name , preguntas , buenas from Historial, Topic where Historial.topic=Topic.id and idEstudiante='" + correo + "';";
        try {
            iniciarConexionIntro(MySQL, "localhost");
            pst = conec.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                String topic = rs.getString("name");
                int preguntas = rs.getInt("preguntas");
                int buenas = rs.getInt("buenas");

                EvaluacionUnit ev = new EvaluacionUnit(topic, preguntas, buenas);

                LP.add(ev);
            }
        } catch (Exception e) {
            throw new DAOException("Error en la Fuente de Datos");
        } finally {
            cerrarConexionIntro();
        }
        return LP;
    }

    public void addEstudiante(String nombre, String correo, String pass1, String pass2) throws DAOException {
        //hay q escribir en las tablas Usuario, Estudiante
        String insert1 = "insert into Usuario values ('" + correo + "','" + pass1 + "',1)";
        String insert2 = "insert into Estudiante values ('" + correo + "','" + nombre + "',-1,-1,-1,-1,-1,0)";

        try {
            iniciarConexionIntro(MySQL, "localhost");
            pst = conec.prepareStatement(insert1);
            pst.execute();
            pst = conec.prepareStatement(insert2);
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLEstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(" aqui ponemos el codigo para guardar el estudiante");
    }

    public void setEstiloAprendizaje(String correo, int d1, int d2, int d3, int d4) throws DAOException {

        System.out.println("Guardando el estilo de aprendizaje");
        //hay q escribir en las tablas Usuario, Estudiante
        String insert = "update Estudiante set fs1 = " + d1 + " , fs2=" + d2 + " , fs3=" + d3 + ", fs4=" + d4 + " where correo ='" + correo + "';";

        System.out.println(insert);
        try {
            iniciarConexionIntro(MySQL, "localhost");
            pst = conec.prepareStatement(insert);
            pst.execute();

        } catch (SQLException ex) {
            System.out.println("Erro al setear el estilo de aprendizaje: " + ex);

        }

    }

    public Estudiante getEstudiante(String correo) throws DAOException {
        Estudiante nuevo = null;
        String query = "Select  * from Estudiante where correo='" + correo + "'";
        System.out.println("STRINGGGGGGG : " + query);
        try {
            iniciarConexionIntro(MySQL, "localhost");
            pst = conec.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {

                String a = rs.getString(1);
                String b = rs.getString(2);
                int c = rs.getInt(3);
                int d = rs.getInt(4);
                int e = rs.getInt(5);
                int f = rs.getInt(6);
                int g = rs.getInt(7);
                nuevo = new Estudiante(a, b, c, d, e, f, g);

            }
        } catch (Exception e) {
            throw new DAOException("Error en la Fuente de Datos " + e);
        } finally {
            cerrarConexionIntro();
        }
        return nuevo;
    }

    public ArrayList<Topico> getAllTopic() throws DAOException {
        ArrayList<Topico> topicos = new ArrayList<Topico>();
        String select = "select * from TopicView;";
        try {
            iniciarConexionIntro(MySQL, "localhost");
            pst = conec.prepareStatement(select);
            rs = pst.executeQuery();
            while (rs.next()) {
                int idUnidad = rs.getInt("idUnidad");

                int id = rs.getInt("id");
                String nombre = rs.getString("topico");
                Topico nuevoTopico = new Topico(id, idUnidad, nombre);
                topicos.add(nuevoTopico);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return topicos;
    }

    public ArrayList<Topico> getTopicosAprobados(String correoEstudiante) throws DAOException {

        ArrayList<Topico> topicos = new ArrayList<Topico>();
        String select = "select id, idUnidad, topico from TopicView, AvanceEstudiante "
                + "where TopicView.id = AvanceEstudiante.idTopico and idEstudiante = '" + correoEstudiante + "';";
        try {
            iniciarConexionIntro(MySQL, "localhost");
            pst = conec.prepareStatement(select);
            rs = pst.executeQuery();
            System.out.println(select);
            while (rs.next()) {

                int id = rs.getInt("id");
                int idUnidad = rs.getInt("idUnidad");

                String nombre = rs.getString("topico");
                Topico nuevoTopico = new Topico(id, idUnidad, nombre);
                nuevoTopico.setIsConocido(true);
                topicos.add(nuevoTopico);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return topicos;
    }

    public ArrayList<Topico> getTopicosPendientes(String correoEstudiante) throws DAOException {

        ArrayList<Topico> topicos = new ArrayList<Topico>();
        String select = "select * from TopicView where id "
                + "NOT IN (select idTopico from AvanceEstudiante where idEstudiante='" + correoEstudiante + "') order by id;";
        try {
            iniciarConexionIntro(MySQL, "localhost");
            pst = conec.prepareStatement(select);
            rs = pst.executeQuery();
            while (rs.next()) {
                int idUnidad = rs.getInt("idUnidad");

                int id = rs.getInt("id");

                String nombre = rs.getString("topico");
                Topico nuevoTopico = new Topico(id, idUnidad, nombre);
                nuevoTopico.setIsConocido(false);
                topicos.add(nuevoTopico);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return topicos;
    }

    @Override
    public void removeEstudiante(String correo) throws DAOException {
        String delete1 = "delete from AvanceEstudiante where idEstudiante='" + correo + "' ;";

        String delete2 = "delete from Estudiante where correo='" + correo + "';";
        String delete3 = "delete from Usuario where correo='" + correo + "';";
        // tambien debemos eliminar su estado de avance

        try {
            iniciarConexionIntro(MySQL, "localhost");
            pst = conec.prepareStatement(delete1);
            pst.execute();
            pst = conec.prepareStatement(delete2);
            pst.execute();
            pst = conec.prepareStatement(delete3);
            pst.execute();
        } catch (SQLException ex) {
            System.out.println("Erro al borrar un estudiante: " + ex);
        }
    }

    @Override
    public void avanzarRuta(String correo, int idTopico) throws DAOException {
        String insert = "insert into AvanceEstudiante values ('" + correo + "'," + idTopico + ") ";
        System.out.println(insert);
        try {
            iniciarConexionIntro(MySQL, "localhost");
            pst = conec.prepareStatement(insert);
            pst.execute();

        } catch (SQLException ex) {
            System.out.println("Erro al insertar avance: " + ex);
        }

    }

    @Override
    public boolean existeCorreo(String correo) throws DAOException {
        String select = "select count(correo) from Usuario where correo='" + correo + "'; ";
        try {
            iniciarConexionIntro(MySQL, "localhost");
            pst = conec.prepareStatement(select);
            rs = pst.executeQuery();
            while (rs.next()) {
                int cantidad = rs.getInt(1);

                if (cantidad > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            System.out.println("error comprobando si existe correo : " + ex);
        }
        return false;
    }

    @Override
    public void removeAllEstudiantes() throws DAOException {

        String delete1 = "delete from AvanceEstudiante ;";
        String delete2 = "delete from Estudiante ";
        String delete3 = "delete from Usuario where tipo=1 ;";

        try {
            iniciarConexionIntro(MySQL, "localhost");
            pst = conec.prepareStatement(delete1);
            pst.execute();
            pst = conec.prepareStatement(delete2);
            pst.execute();
            pst = conec.prepareStatement(delete3);
            pst.execute();
        } catch (SQLException ex) {
            System.out.println("Erro al borrar un estudiante: " + ex);
        }
    }


    public Estilo getEstiloAprendizaje(String correo) throws DAOException {

        Estilo estilo = null;
        String select = "select * from Estudiante where correo = '" + correo + "' ;";

        try {
            iniciarConexionIntro(MySQL, "localhost");
            pst = conec.prepareStatement(select);
            rs = pst.executeQuery();
            while (rs.next()) {

                int fs1 = rs.getInt("fs1");
                int fs2 = rs.getInt("fs2");
                int fs3 = rs.getInt("fs3");
                int fs4 = rs.getInt("fs4");
                estilo = new Estilo(fs1, fs2, fs3, fs4);
            }
        } catch (SQLException ex) {
            System.out.println("error extrayendo los estilos: " + ex);
        }
        return estilo;
    } 
    
     
    public ArrayList<EstudianteEstilo> getAllEstudianteEstilo() throws DAOException {
        ArrayList<EstudianteEstilo> fsnuevo = new ArrayList<EstudianteEstilo>();
        EstudianteEstilo p;

        String query = "select correo,nombre, fs1,fs2,fs3,fs4 from Estudiante where fs1<> -1 and fs2 <> -1 and fs3 <> -3 and fs4 <> -1";
        try {
            iniciarConexionIntro(MySQL, "localhost");
            pst = conec.prepareStatement(query);
            rs = pst.executeQuery();
            int fe11 = 0;
            int fe12 = 0;
            int fe21 = 0;
            int fe22 = 0;
            int fe31 = 0;
            int fe32 = 0;
            int fe41 = 0;
            int fe42 = 0;
          

            while (rs.next()) {
                

                String correo = rs.getString(1);

                int fs1 = rs.getInt(3);
                if (fs1 == 1) {
                    fe11 = 1;

                    String nombre1 = rs.getString(2);
                 fsnuevo.add(new EstudianteEstilo(correo, nombre1, " "," ", " ", " ", " ", " ", " "));
                } else {
                    fe12 = 2;
                    String nombre2 = rs.getString(2); 
                    fsnuevo.add(new EstudianteEstilo(correo, "", nombre2," ", " ", " ", " ", " ", " "));
                }

                int fs2 = rs.getInt(4);
                if (fs2 == 1) {
                    fe21 = 1;

                    String nombre3 = rs.getString(2); 
                    fsnuevo.add(new EstudianteEstilo(correo, "", "",nombre3, " ", " ", " ", " ", " "));
     
                } else {
                    fe22 = 2;
                    String nombre4 = rs.getString(2);
                    fsnuevo.add(new EstudianteEstilo(correo, "", "","", nombre4, " ", " ", " ", " "));
                }

                int fs3 = rs.getInt(5); 
                if (fs3 == 1) {
                    fe31 = 1;
                    String nombre5 = rs.getString(2);
                    fsnuevo.add(new EstudianteEstilo(correo, "", "","", " ", nombre5, " ", " ", " "));

                } else {
                    fe32 = 2;

                    String nombre6 = rs.getString(2); 
                    fsnuevo.add(new EstudianteEstilo(correo, "", "","", " ", " ", nombre6, " ", " "));
                }

                int fs4 = rs.getInt(6);
                if (fs4 == 1) {
                    fe41 = 1;
                    String nombre7 = rs.getString(2); 
                    fsnuevo.add(new EstudianteEstilo(correo, "", "","", " ", " ", " ", nombre7, " "));

                } else {
                    fe42 = 2;
                    String nombre8 = rs.getString(2); 
                    fsnuevo.add(new EstudianteEstilo(correo, "", "","", " ", " ", " ", " ", nombre8));

                }

               

            }
        } catch (Exception e) {
            throw new DAOException("Error en la Fuente de Datos");
        } finally {
            try {
                cerrarConexionIntro();
            } catch (DAOException ex) {
                Logger.getLogger(MySQLEstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fsnuevo;
    }

    public ArrayList<EstudianteF11> getAllEstudiantefs11() throws DAOException {
        ArrayList<EstudianteF11> fs11 = new ArrayList<EstudianteF11>();
        String query = "select correo, nombre from Estudiante where fs1=1";
        EstudianteF11 p; 
        try {
            iniciarConexionIntro(MySQL, "localhost");
            pst = conec.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
              String a= rs.getString("correo");
              String b= rs.getString("nombre"); 
               p=new EstudianteF11(b,a);
                fs11.add(p);
               
            }
        } catch (Exception e) {
            throw new DAOException("Error en la Fuente de Datos");
        } finally {
            try {
                cerrarConexionIntro();
            } catch (DAOException ex) {
                Logger.getLogger(MySQLEstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fs11;
    }

    public ArrayList<EstudianteF12> getAllEstudiantefs12() throws DAOException {
        ArrayList<EstudianteF12> fs12 = new ArrayList<EstudianteF12>();
        String query = "select correo, nombre from Estudiante where fs1=2";
        EstudianteF12 p;
        try {
            iniciarConexionIntro(MySQL, "localhost");
            pst = conec.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                String a= rs.getString("correo"); 
                String b= rs.getString("nombre");
                 p=new EstudianteF12(b,a,12);
                fs12.add(p);
            }
        } catch (Exception e) {
            throw new DAOException("Error en la Fuente de Datos");
        } finally {
            try {
                cerrarConexionIntro();
            } catch (DAOException ex) {
                Logger.getLogger(MySQLEstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fs12;
    }

    public ArrayList<EstudianteF21> getAllEstudiantefs21() throws DAOException {
        ArrayList<EstudianteF21> fs21 = new ArrayList<EstudianteF21>();
        EstudianteF21 p;
        String query = "select correo, nombre from Estudiante where fs2=1";
        try {
            iniciarConexionIntro(MySQL, "localhost");
            pst = conec.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                String a = rs.getString("correo");
                String b = rs.getString("nombre");
                p = new EstudianteF21(b, a);
                fs21.add(p);
                      
            }
        } catch (Exception e) {
            throw new DAOException("Error en la Fuente de Datos");
        } finally {
            try {
                cerrarConexionIntro();
            } catch (DAOException ex) {
                Logger.getLogger(MySQLEstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fs21;
    }

    public ArrayList<EstudianteF22> getAllEstudiantefs22() throws DAOException {
        ArrayList<EstudianteF22> fs22 = new ArrayList<EstudianteF22>();
        EstudianteF22 p;
        String query = "select correo, nombre from Estudiante where fs2=2";
        try {
            iniciarConexionIntro(MySQL, "localhost");
            pst = conec.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) { 
                String correo = rs.getString("correo");
                String nombre = rs.getString("nombre");
                

                p = new EstudianteF22(nombre,correo);
                fs22.add(p); 
                
                System.out.println("dasdasdasd"+nombre);
            }
        } catch (Exception e) {
            throw new DAOException("Error en la Fuente de Datos");
        } finally {
            try {
                cerrarConexionIntro();
            } catch (DAOException ex) {
                Logger.getLogger(MySQLEstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fs22;
    }

    public ArrayList<EstudianteF31> getAllEstudiantefs31() throws DAOException {
        ArrayList<EstudianteF31> fs31 = new ArrayList<EstudianteF31>();
        EstudianteF31 p;
        String query = "select correo, nombre from Estudiante where fs3=1";
        try {
            iniciarConexionIntro(MySQL, "localhost");
            pst = conec.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                String b = rs.getString("nombre");
                String a = rs.getString("correo");

                p = new EstudianteF31(b,a);
                fs31.add(p);
            }
        } catch (Exception e) {
            throw new DAOException("Error en la Fuente de Datos");
        } finally {
            try {
                cerrarConexionIntro();
            } catch (DAOException ex) {
                Logger.getLogger(MySQLEstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fs31;
    }

    public ArrayList<EstudianteF32> getAllEstudiantefs32() throws DAOException {
        ArrayList<EstudianteF32> fs32 = new ArrayList<EstudianteF32>();
        EstudianteF32 p;
        String query = "select correo, nombre from Estudiante where fs3=2";
        try {
            iniciarConexionIntro(MySQL, "localhost");
            pst = conec.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                String b = rs.getString("nombre");
                String a = rs.getString("correo");

                p = new EstudianteF32(b,a);
                fs32.add(p);
            }
        } catch (Exception e) {
            throw new DAOException("Error en la Fuente de Datos");
        } finally {
            try {
                cerrarConexionIntro();
            } catch (DAOException ex) {
                Logger.getLogger(MySQLEstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fs32;
    }

    public ArrayList<EstudianteF41> getAllEstudiantefs41() throws DAOException {
        ArrayList<EstudianteF41> fs41 = new ArrayList<EstudianteF41>();
        EstudianteF41 p;
        String query = "select correo, nombre from Estudiante where fs4=1";
        try {
            iniciarConexionIntro(MySQL, "localhost");
            pst = conec.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                String b = rs.getString("nombre");
                String a = rs.getString("correo");
                p = new EstudianteF41(b,a);
                fs41.add(p);
            }
        } catch (Exception e) {
            throw new DAOException("Error en la Fuente de Datos");
        } finally {
            try {
                cerrarConexionIntro();
            } catch (DAOException ex) {
                Logger.getLogger(MySQLEstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fs41;
    }

    public ArrayList<EstudianteF42> getAllEstudiantefs42() throws DAOException {
        ArrayList<EstudianteF42> fs42 = new ArrayList<EstudianteF42>();
        EstudianteF42 p;
        String query = "select correo, nombre from Estudiante where fs4=2";
        try {
            iniciarConexionIntro(MySQL, "localhost");
            pst = conec.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                String b = rs.getString("nombre");
                String a = rs.getString("correo");

                p=new EstudianteF42(b,a);
                fs42.add(p);
            }
        } catch (Exception e) {
            throw new DAOException("Error en la Fuente de Datos");
        } finally {
            try {
                cerrarConexionIntro();
            } catch (DAOException ex) {
                Logger.getLogger(MySQLEstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fs42;
    }

    @Override
    public int getPromedio() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 





}
