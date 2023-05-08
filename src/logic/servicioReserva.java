/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;
import java.beans.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author ADMIN1
 */
public class servicioReserva {

    private Conexion conexion;
    PreparedStatement statement = null;
    int id;
    public int getId(String inicio, String fin, int total, String pago) throws SQLException, ClassNotFoundException {
        conexion = new Conexion();
        String insert = "INSERT INTO reservas VALUES(?,?,?,?,?)"; 
              statement  = conexion.getConnection().prepareStatement(insert,statement.RETURN_GENERATED_KEYS);
              statement.setInt(1,0);
              statement.setString(2, inicio);
               statement.setString(3, fin);
                statement.setInt(4, total);
                 statement.setString(5, pago);
        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("No se pudo guardar");
        }

        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            id = generatedKeys.getInt(1);
        }
        return id;
        //Falta pasa el id en la siguiente ventana para continuar con el registro
        //Tambien falta llenar las 2 tablas y permitir la edicion y el borrado de los datos
        //Falta habilitar la busqueda en la tabla....
    }
    public int update(String id,String inicio, String fin, String total, String pago) throws SQLException, ClassNotFoundException {
     conexion = new Conexion();
        String insert = "UPDATE reservas SET fecha_entrada = ?,fecha_salida = ?,valor = ?,forma_de_pago = ?"
                + " WHERE id = ?";
        int respuesta = 1;
        try {
            statement = conexion.getConnection().prepareStatement(insert);
            statement.setString(1, inicio);
            statement.setString(2, fin);
            statement.setString(3, total);
            statement.setString(4, pago);
            statement.setString(5, id);
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
        String insert = "delete from reservas where id = ?";
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
