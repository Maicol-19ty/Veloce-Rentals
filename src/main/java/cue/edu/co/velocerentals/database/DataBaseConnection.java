package cue.edu.co.velocerentals.database;

import cue.edu.co.velocerentals.exceptions.ServiceJdbcException;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {

    private static final BasicDataSource dataSource = new BasicDataSource();

    static {
        try (InputStream input = DataBaseConnection.class.getClassLoader().getResourceAsStream("database.properties")) {
            Properties prop = new Properties();
            // Checking if the configuration file is present.
            if (input == null) {
                // If the file is not found, throwing an exception.
                throw new RuntimeException("Unable to find database.properties");
            }
            // Loading the properties from the file into a Properties object.
            prop.load(input);

            // Configuring DataSource properties with values from the configuration file.
            dataSource.setUrl(prop.getProperty("db.url"));
            dataSource.setUsername(prop.getProperty("db.user"));
            dataSource.setPassword(prop.getProperty("db.password"));
            dataSource.setDriverClassName(prop.getProperty("db.driver"));

            // Setting pool size properties.
            dataSource.setInitialSize(Integer.parseInt(prop.getProperty("pool.initialSize", "5")));
            dataSource.setMaxTotal(Integer.parseInt(prop.getProperty("pool.maxTotal", "20")));
            dataSource.setMaxIdle(Integer.parseInt(prop.getProperty("pool.maxIdle", "10")));
            dataSource.setMinIdle(Integer.parseInt(prop.getProperty("pool.minIdle", "2")));
            dataSource.setMaxWaitMillis(Long.parseLong(prop.getProperty("pool.maxWaitMillis", "10000")));
        } catch (Exception ex) {
            // If there is an error loading properties or configuring the DataSource, throwing an exception.
            throw new RuntimeException("Error loading database properties", ex);
        }
    }

    // Static method to obtain a connection from the database.
    public static Connection getInstance() throws SQLException, ServiceJdbcException {
        return dataSource.getConnection();
    }

    // Static method to close the connection pool.
    public static void closePool() throws SQLException {
        dataSource.close();
    }
}
