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
            if (input == null) {
                throw new RuntimeException("Unable to find database.properties");
            }
            prop.load(input);

            dataSource.setUrl(prop.getProperty("db.url"));
            dataSource.setUsername(prop.getProperty("db.user"));
            dataSource.setPassword(prop.getProperty("db.password"));

            dataSource.setInitialSize(Integer.parseInt(prop.getProperty("pool.initialSize", "5")));
            dataSource.setMaxTotal(Integer.parseInt(prop.getProperty("pool.maxTotal", "10")));
            dataSource.setMaxIdle(Integer.parseInt(prop.getProperty("pool.maxIdle", "5")));
            dataSource.setMinIdle(Integer.parseInt(prop.getProperty("pool.minIdle", "1")));
            dataSource.setMaxWaitMillis(Long.parseLong(prop.getProperty("pool.maxWaitMillis", "10000")));
        } catch (Exception ex) {
            throw new RuntimeException("Error loading database properties", ex);
        }
    }

    public static Connection getInstance() throws SQLException, ServiceJdbcException {
        return dataSource.getConnection();
    }

    public static void closePool() throws SQLException {
        dataSource.close();
    }
}
