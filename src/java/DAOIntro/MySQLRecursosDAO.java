package DAOIntro;

import static DAOIntro.ConexionIntro.MySQL;
import static DAOIntro.ConexionIntro.conec;
import static DAOIntro.ConexionIntro.pst;
import capaLogicaIntro.learningObject;//cambiar
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLRecursosDAO extends ConexionIntro implements InterfaceRecursos {

    @Override
    public void guardarRecurso(learningObject recurso) throws DAOException {
        String insert = "insert into Recurso values ('" + recurso.getId() + "'," + recurso.getIdTopico() + "," + recurso.getRanking() + ","
                + " " + recurso.getEstilo1() + ", " + recurso.getEstilo2() + " , " + recurso.getEstilo3() + ", " + recurso.getEstilo4() + ", "
                + " '" + recurso.getTitulo() + "', '" + recurso.getAutor() + "','" + recurso.getTipoRecurso() + "','"+recurso.getNameTopico()+"','"+recurso.getRuta()+"')";

        System.out.println(insert);
        try {
            iniciarConexionIntro(MySQL, "localhost");
            pst = conec.prepareStatement(insert);
            pst.execute();
        } catch (SQLException ex) {
            System.out.println("Error al guardar la pregunta " + ex);
        }
        System.out.println(" Pregunta guardada");

    }

    @Override
    public ArrayList<learningObject> leerRecurso() throws DAOException {
        ArrayList<learningObject> o = new ArrayList<learningObject>();
        String query = "select * from Recurso order by idTopico, ranking desc; ";
        System.out.println(query);
        try {
            iniciarConexionIntro(MySQL, "localhost");
            pst = conec.prepareStatement(query);
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
                String ruta=rs.getString("ruta");
                
                nuevo.setId(id);
                nuevo.setIdTopico(idTopico);
                nuevo.setRanking(rankink);
                nuevo.setEstilo1(estilo1);
                nuevo.setEstilo2(estilo2);
                nuevo.setEstilo3(estilo3);
                nuevo.setEstilo4(estilo4);
                
                nuevo.setRuta(ruta);
                
                nuevo.setTitulo(titulo);
                nuevo.setAutor(autor);
                nuevo.setTipoRecurso(tipo);
                nuevo.setNameTopico(nombreTopico);
                nuevo.EstablecerEstilo();
                o.add(nuevo);
            }
        } catch (Exception e) {
            throw new DAOException("Error en la Fuente de Datos de los oas");
        } finally {
            cerrarConexionIntro();
        }

        return o;
    }

    @Override
    public void borrarRecursos() throws DAOException {
         String delete="delete from Recurso";
        System.out.println(delete);
        try {
            iniciarConexionIntro(MySQL, "localhost");
            pst = conec.prepareStatement(delete);
            pst.execute();
        } catch (SQLException ex) {
            System.out.println("Error borrando los oas " + ex);
        }
    }

}
