/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author ADMIN1
 */
public class DatosHotel {
    private Conexion conexion;
    
    public  ResultSet reservas() throws SQLException, ClassNotFoundException{
        conexion = new Conexion();
     String consulta = "SELECT id, fecha_entrada, fecha_salida,valor,forma_de_pago FROM reservas";
            PreparedStatement statement = conexion.getConnection().prepareStatement(consulta);
            return statement.executeQuery();
    }
     public  ResultSet huespedes() throws SQLException, ClassNotFoundException{
        conexion = new Conexion();
     String consulta = "SELECT * FROM huespedes";
            PreparedStatement statement = conexion.getConnection().prepareStatement(consulta);
            return statement.executeQuery();
    }
    public ResultSet busquedaHuespedes(String dato ) throws ClassNotFoundException, SQLException{
        conexion = new Conexion();
    PreparedStatement statement = conexion.getConnection().prepareStatement("SELECT * FROM huespedes WHERE id LIKE ? or nombre LIKE ? or"
            + " apellido LIKE ? OR fecha_de_naciemiento LIKE ? OR nacionalidad LIKE ? OR telefono LIKE ? OR id_reserva LIKE ?");
            statement.setString(1, dato + "%");
            statement.setString(2, dato + "%");
            statement.setString(3, dato + "%");
            statement.setString(4, dato + "%");
            statement.setString(5, dato + "%");
            statement.setString(6, dato + "%");
            statement.setString(7, dato + "%");
            System.out.println("Dentro del busqueda huespedes" );
            return statement.executeQuery();
    } 
     public ResultSet busquedaReservas(String dato) throws ClassNotFoundException, SQLException{
        conexion = new Conexion();
    PreparedStatement statement = conexion.getConnection().prepareStatement("SELECT * FROM reservas WHERE id LIKE ? OR fecha_entrada LIKE ? OR fecha_salida LIKE ? OR valor LIKE ? OR forma_de_pago LIKE ?");
            statement.setString(1, dato + "%");
            statement.setString(2, dato + "%");
            statement.setString(3, dato + "%");
            statement.setString(4, dato + "%");
            statement.setString(5, dato + "%");
            return statement.executeQuery();
    } 
    
}
