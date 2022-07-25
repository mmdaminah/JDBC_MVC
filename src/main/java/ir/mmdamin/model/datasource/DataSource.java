package ir.mmdamin.model.datasource;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DataSource {
    public static final String dataBaseUrl = "jdbc:postgresql://localhost:5432/JDBC";
    public static final String username = "postgres";
    public static final String password = "13761376";

    public Connection getConnection(){
        try{
            var connection = DriverManager.getConnection(dataBaseUrl,username,password);
            System.out.println("Successfully Connected to the DB;");
            return connection;
        }
        catch (SQLException e){
            throw new RuntimeException("Failed to connect to the Database;");
        }
    }
}
