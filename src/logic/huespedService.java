/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ADMIN1
 */
public class huespedService {

    private Conexion conexion;
    PreparedStatement statement;

    public int insertar(String nombre, String apellido, String fecha, String nacionalidad, String telefono, int id) throws SQLException, ClassNotFoundException {
        conexion = new Conexion();
        String insert = "INSERT INTO huespedes(id, nombre, apellido,fecha_de_naciemiento,nacionalidad"
                + ",telefono,id_reserva) VALUES(?,?,?,?,?,?,?)";
        int respuesta = 1;
        try {
            statement = conexion.getConnection().prepareStatement(insert);
            statement.setInt(1, 0);
            statement.setString(2, nombre);
            statement.setString(3, apellido);
            statement.setString(4, fecha);
            statement.setString(5, nacionalidad);
            statement.setString(6, telefono);
            statement.setInt(7, id);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            respuesta = 0;
        }
        conexion.getConnection().close();
        return respuesta;
    }
    public int update(String id, String nombre, String apellido, String fecha_nacimiento, String nacionalidadtxt, String telefono, String num_reserva){
        conexion = new Conexion();
        String insert = "UPDATE huespedes SET nombre = ?,apellido = ?,fecha_de_naciemiento = ?,nacionalidad = ?,telefono = ?,"
                + "id_reserva = ? WHERE id = ?";
        int respuesta = 1;
        try {
            statement = conexion.getConnection().prepareStatement(insert);
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setString(3, fecha_nacimiento);
            statement.setString(4, nacionalidadtxt);
            statement.setString(5, telefono);
            statement.setString(6, num_reserva);
            statement.setString(7, id);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            respuesta = 0;
        }
        System.out.println("exito");
        return respuesta;
    }
    public int delete(String id){
      conexion = new Conexion();
        String insert = "delete from huespedes where id = ?";
        int respuesta = 1;
        try {
            statement = conexion.getConnection().prepareStatement(insert);
            statement.setString(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            respuesta = 0;
        }
        return respuesta;
    }

}
