/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectostock.conexion;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import proyectostock.modelo.Categoria;
import proyectostock.modelo.Producto;

/**
 *
 * @author Mati10
 */
public class ProductoDB {

//    public ObservableList<Producto> buscarProductos() throws SQLException {
//
//        String query = "";
//
//        JdbcHelper jdbc = new JdbcHelper();
//        ResultSet rs = null;
//        rs = jdbc.realizarConsulta(query);
//
//        ObservableList<Producto> productos = FXCollections.observableArrayList();
//
//        while (rs.next()) {
//
////            Integer idTurno = rs.getInt("idTurno");
//            Date fecha = (Date) rs.getDate("fecha");
////            Time hora = rs.getTime("hora");
////            String comentario = rs.getString("comentario");
//
////            Turno turn = new Turno(fecha, hora, comentario,
////                    new Vehiculo(patente, marca),
////                    new Cliente(apellido, nombre)
////            );
////            for (Turno t : turnos) {
////                System.out.println("Turno:" + t.toString());
////            }
////            turnos.add(turn);
//            System.out.println("Producto db");
//
//        }
//        return productos;
//
//    }

    public ObservableList<Producto> buscarTodos() throws ClassNotFoundException {
//        String query = "SELECT * FROM Productos";

        String query = "select "
                + "P.ID_PRODUCTOS, "
                + "P.CODIGO_PRODUCTO, "
                + "P.NOMBRE,"
                + "P.DESCRIPCION, "
                + "P.CAPACIDAD,"
                + "P.STOCK,"
                + "P.MINIMO_STOCK,"
                + "P.PRECIO_COMPRA,"
                + "P.PRECIO_VENTA, "
                + "P.PRECIO_MAYORISTA, "
                + "P.ESTADO, "
                + "P.ES_MAYOR,"
                + "P.PROVEEDOR, "
                + "P.FECHA_INGRESO, "
                + "c.ID_CATEGORIA,"
                + "c.DESCRIPCION "
                + "from Productos P  "
                + "INNER JOIN Categoria c "
                + "ON (P.ID_CATEGORIA = C.ID_CATEGORIA ) WHERE P.ESTADO=0;";

        JdbcHelper jdbc = new JdbcHelper();
        ResultSet rs = jdbc.realizarConsulta(query);

        ObservableList<Producto> productos = FXCollections.observableArrayList();

        try {
            while (rs.next()) {
//                
                Integer idProducto = rs.getInt(1);
                String codProducto = rs.getString(2);
                String nombre = rs.getString(3);
                String descripcion = rs.getString(4);
                String capacidad = rs.getString(5);
                Integer stock = rs.getInt(6);
                Integer minStock = rs.getInt(7);
                Float precioC = rs.getFloat(8);
                Float precioV = rs.getFloat(9);
                Float precioM = rs.getFloat(9);
                boolean esMayor = rs.getBoolean(11);
                boolean estado = rs.getBoolean(12);
                String proveedor = rs.getString(13);
                Date fechaIn = rs.getDate(14);
                Integer idCat = rs.getInt(15);
                String categoria = rs.getString(16);

                Producto p = new Producto(idProducto, codProducto, nombre, descripcion,
                        capacidad, stock, minStock, precioC, precioV, precioM, esMayor, estado, proveedor,
                        new Categoria(idCat, categoria), fechaIn
                );

                productos.add(p);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar productos: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("resultset: " + productos.toString());
        return productos;
    }

    public boolean insertarProducto(Producto p) {
//insert into Productos values('103','vaso','plastico chico','x50',30,10,19.5,16.6,14.7,1,1,'nose',1,getdate())

        System.out.println("en insertar pruducto " + p.toString() + "\n ");
        String query = "insert into Productos values ("
                + "'" + p.getCodigoProducto() + "','"
                + p.getNombre() + "','"
                + p.getDescripcion() + "','"
                + p.getCapacidad() + "','"
                + p.getStock() + "','"
                + p.getMinimoStock() + "','"
                + p.getPrecioCompra() + "','"
                + p.getPrecioVenta() + "','"
                + p.getPrecioMayorista() + "','"
                + p.getEsMayor() + "','"
                + p.getEstado() + "','"
                + p.getProveedor() + "','"
                + p.getCategoria().getIdCategoria() + "',"
                + "getdate()" + ")";

        //  'plastico mediano','x50',30,90,19.5,16.6,14.7,1,1,'matias' ,1)
        JdbcHelper jdbc = new JdbcHelper();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }

    public boolean actualizarProducto(Producto p) {

        JdbcHelper jdbc = new JdbcHelper();
        System.out.println("p: " + p.toString());

        String query = "UPDATE Productos SET  CODIGO_PRODUCTO='" + p.getCodigoProducto() + "',"
                + "NOMBRE='" + p.getNombre() + "',"
                + "DESCRIPCION='" + p.getDescripcion() + "',"
                + "CAPACIDAD='" + p.getCapacidad() + "',"
                + "STOCK='" + p.getStock() + "',"
                + "MINIMO_STOCK='" + p.getMinimoStock() + "',"
                + "PRECIO_COMPRA='" + p.getPrecioCompra() + "',  "
                + "PRECIO_VENTA='" + p.getPrecioVenta() + "' , "
                + "PRECIO_MAYORISTA='" + p.getPrecioMayorista() + "' , "
                //              + "ES_MAYOR=" + p.getEsMayor() + " , "
                //              + "ESTADO=" + p.getEstado() + " , "
                + "ES_MAYOR=" + 1 + " , "
                + "ESTADO=" + 0 + " , "
                + "PROVEEDOR='" + p.getProveedor() + "' , "
                + "ID_CATEGORIA='" + p.getCategoria().getIdCategoria() + "'  "
                + " where CODIGO_PRODUCTO = '" + p.getCodigoProducto() + "';";

        jdbc.ejecutarQuery(query);

        return true;
    }

//    public boolean actualizarProducto(Producto p) {
//        
//        System.out.println("Prod a editar:" + p.toString());
//        
//        
//        
//        
//        
//        String query = "UPDATE Productos SET  CODIGO_PRODUCTO='" + p.getCodigoProducto() + "',"
//                + "NOMBRE='" + p.getNombre() + "',"
//                + "DESCRIPCION='" + p.getDescripcion() + "',"
//                + "CAPACIDAD='" + p.getCapacidad() + "',"
//                + "STOCK='" + p.getStock() + "',"
//                + "MINIMO_STOCK='" + p.getMinimoStock() + "',"
//                + "PRECIO_COMPRA='" + p.getPrecioCompra() + "'  "
//                + "PRECIO_VENTA='" + p.getPrecioVenta() + "'  "
//                + "PRECIO_MAYORISTA='" + p.getPrecioMayorista() + "'  "
//                + "ES_MAYOR=" + p.getEsMayor() + "'  "
//                + "ESTADO=" + p.getEstado() + "  "
//                + "PROVEEDOR='" + p.getProveedor() + "'  "
//                + "ID_CATEGORIA='" + p.getCategoria().getIdCategoria() + "'  "
//                + " where ID_PRODUCTOS = " + p.getIdProducto();
//         JdbcHelper jdbc = new JdbcHelper();
//          jdbc.ejecutarQuery(query);
//       
//        return true;
//    }
//    public boolean editarTurno(Turno t) {
//
////        JdbcHelper jdbc = new JdbcHelper();
////
////        Integer id = t.getIdTurno();
////        Date fecha = t.getFecha();
////        Time hora = t.getHora();
////        String comentario = t.getComentario();
////        Integer idCliente = t.getIdCliente();
////
////        String query = "UPDATE turno SET "
////                + "fecha='" + fecha + "'"
////                + ",hora='" + hora + "'"
////                + ",comentario='" + comentario + "'"
////                + ",idCliente='" + idCliente + "'"
////                + "WHERE idTurno=" + id
////                + ";";
////
////        boolean exito = jdbc.ejecutarQuery(query);
////
//        return exito;
//
//    }
    public Producto buscarProductoPorId(int idProducto) {

        try {
            String query = "SELECT * FROM producto where ID_PRODUCTOS =  " + idProducto;
            JdbcHelper jdbc = new JdbcHelper();
            ResultSet rs = jdbc.realizarConsulta(query);

            while (rs.next()) {
                if (idProducto == rs.getInt("ID_PRODUCTOS")) {
                    System.out.println("Validación de id correcta desde la bd");

//                    Date fecha = (Date) rs.getDate("fecha");
//                    Time hora = rs.getTime("hora");
//                    String comentario = rs.getString("comentario");
//                    Integer idCliente = rs.getInt("idCliente");
//                    Integer idVehiculo = rs.getInt("idVehiculo");
//                    boolean realizado = rs.getBoolean("realizado");
//                    Turno turno = new Turno(idTurno, fecha, hora, comentario, realizado);
                    System.out.println("Turno db");

                    System.out.println("Conexion cerrada");
                    rs.close();

                    return null;
                }

            }
        } catch (SQLException ex) {
            ex.getMessage();
            System.out.println("Error al procesar con la bd");
        }
        return null;

    }

    public boolean eliminarProducto(int idProducto) {

        JdbcHelper jdbc = new JdbcHelper();

        String query = "UPDATE Productos SET ESTADO=1 where ID_PRODUCTOS = "
                + idProducto;

        boolean exito = jdbc.ejecutarQuery(query);

        return exito;

    }

    public boolean actualizarStock(Producto p, int cant) {

        JdbcHelper jdbc = new JdbcHelper();
        System.out.println("p: " + p.toString());

        String query = "Exec DESCONTAR_STOCK " + "'"+p.getCodigoProducto()+ "'" + "," + cant;

        jdbc.ejecutarQuery(query);

        return true;
    }

    
    
     public boolean restablecerStock(Producto p, int cant) {

        JdbcHelper jdbc = new JdbcHelper();
        System.out.println("p: " + p.toString());

        String query = "Exec REESTABLECER_STOCK " + "'"+p.getCodigoProducto()+ "'" + "," + cant;

        jdbc.ejecutarQuery(query);

        return true;
    }
}
