package cue.edu.co.velocerentals.utils;

import cue.edu.co.velocerentals.annotations.MySqlConn;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

// Application-scoped class for producing resources like connections and loggers.
@ApplicationScoped
public class ProducerResources {

    @Inject
    private Logger log;

    @Resource(name = "jdbc/veloce_rentals")
    private DataSource dataSource;

    // Producer method for creating a request-scoped connection.
    @Produces
    @RequestScoped
    @MySqlConn
    private Connection beanConnection() throws NamingException, SQLException {
        return dataSource.getConnection();
    }

    // Producer method for creating loggers.
    @Produces
    private Logger beanLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    // Method to close the connection.
    public void close(@Disposes @MySqlConn Connection connection) throws SQLException {
        connection.close();
        log.info("Connection closed");
    }
}
