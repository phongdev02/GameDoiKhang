/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class ConnectionFactory {
 
    public static Connection getConnection() {
        Connection c = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection ("jdbc:mysql://localhost:3306/" 
                    + "gamedoikhang?useUnicode=true&characterEncoding=UTF-8", "root", "");
        } catch (ClassNotFoundException e) {
        System.out.println("ClassNotFoundException " + e);
        } catch (SQLException e) {
        System.out.println("SQLException " + e);
        }
        return c;
    }
}
