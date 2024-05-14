package cue.edu.co.velocerentals.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// Servlet for user logout functionality.
@WebServlet(name = "logoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    // Method to handle GET requests for user logout.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Invalidate session.
        req.getSession().invalidate();

        // Remove user cookie.
        Cookie cookie = new Cookie("userLogged", "");
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        resp.addCookie(cookie);

        // Redirect user to main page.
        resp.sendRedirect("main.jsp");
    }
}
