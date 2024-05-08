package cue.edu.co.velocerentals.filters;

import cue.edu.co.velocerentals.annotations.MySqlConn;
import cue.edu.co.velocerentals.database.DataBaseConnection;
import cue.edu.co.velocerentals.exceptions.ServiceJdbcException;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConnectionFilter implements Filter {

    @Inject
    @MySqlConn
    private Connection conn;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {

        try {
            Connection connectionRequest = this.conn;

            if (connectionRequest.getAutoCommit()) {
                connectionRequest.setAutoCommit(false);
            }
            try {
                req.setAttribute("conn", connectionRequest);
                chain.doFilter(req, res);
                connectionRequest.commit();
            } catch (ServiceJdbcException e) {
                connectionRequest.rollback();
                ((HttpServletResponse) res).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
