/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectostock.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    public Connection conectarSQL() {
        //MI CONEXION A SQL
        Connection con = null;
        String servidor = "jdbc:sqlserver://localhost:1434;databaseName=ProyectoStock;";
        //String servidor = "jdbc:sqlserver://TERM250619:1433;";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            con = DriverManager.getConnection(servidor, "Cecilia", "1234");

            //JOptionPane.showMessageDialog(null, "Conectado a SQL");
            System.out.println("CONECTADO");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());

        }

        return con;
    }

}
