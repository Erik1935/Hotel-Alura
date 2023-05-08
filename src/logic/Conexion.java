/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author ADMIN1
 */
public class Conexion {
    Connection conn = null;
       public Connection getConnection() throws SQLException, ClassNotFoundException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/hotel";
            String user = "root";
            String password = "";
            conn = DriverManager.getConnection(url, user, password);
             return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                    conn.close();
                }
            return null;
        } 
   
}
}
