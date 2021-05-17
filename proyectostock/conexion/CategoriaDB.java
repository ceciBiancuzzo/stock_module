/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectostock.conexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import proyectostock.modelo.Categoria;
import proyectostock.modelo.Producto;

/**
 *
 * @author Mati10
 */
public class CategoriaDB {

    public ObservableList<Categoria> buscarTodos() throws ClassNotFoundException {
//        String query = "SELECT * FROM Productos";

        String query = "select * from Categoria WHERE BAJA=0 ";

        JdbcHelper jdbc = new JdbcHelper();
        ResultSet rs = jdbc.realizarConsulta(query);

        ObservableList<Categoria> categorias = FXCollections.observableArrayList();

        try {
            while (rs.next()) {
                Integer id = rs.getInt(1);
                String nombre = rs.getString(2);
                
                Categoria c = new Categoria(id, nombre);

                categorias.add(c);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar categorias: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("resultset: " + categorias.toString());
        return categorias;
    }

    public boolean insertarCategoria(Categoria c) {

        System.out.println("en insertar categoria " + c.toString() + "\n ");
        String query = "insert into categoria values ("
                + "'" + c.getNombre() + "'," + 0
                + ")";

        JdbcHelper jdbc = new JdbcHelper();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }

    public boolean editarCategoria(Categoria c) {
        String query = "UPDATE Categoria SET DESCRIPCION WHERE ID_CATEGORIA= " + c.getIdCategoria();

        JdbcHelper jdbc = new JdbcHelper();
        boolean exito = jdbc.ejecutarQuery(query);

        return exito;
    }

    //hecho ceci
    public boolean eliminarCategoria(Categoria c) {
        System.out.println("Categoria traida: " + c.getIdCategoria());
        String query = "UPDATE Categoria  SET BAJA= 1 WHERE ID_CATEGORIA= " + c.getIdCategoria();
        JdbcHelper jdbc = new JdbcHelper();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }

//
//    public Producto buscarProductoPorId(int idProducto) {
//
//        try {
//            String query = "SELECT * FROM producto where ID_PRODUCTOS =  " + idProducto;
//            JdbcHelper jdbc = new JdbcHelper();
//            ResultSet rs = jdbc.realizarConsulta(query);
//
//            while (rs.next()) {
//                if (idProducto == rs.getInt("ID_PRODUCTOS")) {
//                    System.out.println("Validación de id correcta desde la bd");
//
////                    Date fecha = (Date) rs.getDate("fecha");
////                    Time hora = rs.getTime("hora");
////                    String comentario = rs.getString("comentario");
////                    Integer idCliente = rs.getInt("idCliente");
////                    Integer idVehiculo = rs.getInt("idVehiculo");
////                    boolean realizado = rs.getBoolean("realizado");
////                    Turno turno = new Turno(idTurno, fecha, hora, comentario, realizado);
//                    System.out.println("Turno db");
//
//                    System.out.println("Conexion cerrada");
//                    rs.close();
//
//                    return null;
//                }
//
//            }
//        } catch (SQLException ex) {
//            ex.getMessage();
//            System.out.println("Error al procesar con la bd");
//        }
//        return null;
//
//    }
//
//    public boolean eliminarTurno(int idTurno) {
//
//        JdbcHelper jdbc = new JdbcHelper();
//
//        String query = "UPDATE turno SET baja=1 where idTurno = "
//                + idTurno;
//
//        boolean exito = jdbc.ejecutarQuery(query);
//
//        return exito;
//
//    }
}
