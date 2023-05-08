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
public class iniciarSesion {

    private Conexion conexion;

    public String[] getParameters(String usuario) throws SQLException {
        conexion = new Conexion();
        String query = "SELECT usuario, password FROM usuarios WHERE usuario = ?";
        PreparedStatement statement = conexion.getConnection().prepareStatement(query);
        statement.setString(1, usuario);
        ResultSet resultSet = statement.executeQuery();
        String[] datosUser = new String[2];
        while (resultSet.next()) {
            datosUser[0] = resultSet.getString("usuario");
            datosUser[1] = resultSet.getString("password");
            // hacer algo con los valores obtenidos
        }
        return datosUser;
    }
}
