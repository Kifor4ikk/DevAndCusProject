package configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.SortedMap;

public class Database {

    private static final String jdbcUrl = "jdbc:postgresql://localhost:1489/DevAndCus";
    private static final String username = "postgres";
    private static final String password = "pass";

    public static Statement connectWithDataBase() throws SQLException, ClassNotFoundException {
        Connection connection = DriverManager.getConnection(jdbcUrl,username,password);
        System.out.println("Connected!");
        return connection.createStatement();
    }

}
