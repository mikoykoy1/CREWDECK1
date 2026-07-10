/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.*;
/**
 *
 * @author BE214
 */
public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/lab9";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    
    
    public static Connection GetConnection ()throws SQLException{
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
