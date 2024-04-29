package cue.edu.co.velocerentals.utils;

import cue.edu.co.velocerentals.database.DataBaseConnection;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/*")
public class ConnectionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {

        try (Connection conn = DataBaseConnection.getConnection()) {

            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }

            try {
                req.setAttribute("conn", conn);
                chain.doFilter(req, res);
                conn.commit();
            } catch (SQLException | ServiceJdbcException e) {
                conn.rollback();
                ((HttpServletResponse) res).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en la base de datos" + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

}
