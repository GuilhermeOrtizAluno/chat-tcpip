package Servidor.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/Sistema", "root", "");
        }
        catch (SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }

}
