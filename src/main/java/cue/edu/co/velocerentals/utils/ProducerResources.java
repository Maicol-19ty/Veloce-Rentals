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

@ApplicationScoped
public class ProducerResources {

    @Inject
    private Logger log;

    @Resource(name = "jdbc/veloce_rentals")
    private DataSource dataSource;

    @Produces
    @RequestScoped
    @MySqlConn
    private Connection beanConnection() throws NamingException, SQLException {
        return dataSource.getConnection();
    }

    @Produces
    private Logger beanLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    public void close(@Disposes @MySqlConn Connection connection) throws SQLException {
        connection.close();
        log.info("Connection closed");
    }
}
