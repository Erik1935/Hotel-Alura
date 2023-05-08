/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import java.sql.SQLException;
import logic.huespedService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ADMIN1
 */
public class NewEmptyJUnitTest {
    huespedService huesped = new huespedService();
    @Test
    public void NewEmptyJUnitTest() throws SQLException, ClassNotFoundException {
        var id = huesped.insertar("dede", "cce", "2023/04/12", "algo", "9810", 11);
        System.out.println("id = " + id);
    }
    
   

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
