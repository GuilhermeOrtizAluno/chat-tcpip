package Servidor.DB;

import java.sql.Connection;

public class BaseDB {
    protected static Connection getConnection() {
        return DB.getConnection();
    }
}
