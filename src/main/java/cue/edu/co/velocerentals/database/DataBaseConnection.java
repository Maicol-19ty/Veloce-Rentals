package cue.edu.co.velocerentals.database;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {

    private static final String url;
    private static final String user;
    private static final String password;

    static {
        try (InputStream input = DataBaseConnection.class.getClassLoader().getResourceAsStream("database.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                throw new RuntimeException("Unable to find database.properties");
            }
            prop.load(input);
            url = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");
        } catch (Exception ex) {
            throw new RuntimeException("Error loading database properties", ex);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
