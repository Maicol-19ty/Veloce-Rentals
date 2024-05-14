package cue.edu.co.velocerentals.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String userLogged = (String) request.getSession().getAttribute("userLogged");

        if (userLogged == null && !request.getRequestURI().endsWith("login.jsp") && !request.getRequestURI().endsWith("login")) {
            response.sendRedirect("login.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }
}
