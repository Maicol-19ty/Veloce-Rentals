package cue.edu.co.velocerentals.servlets;

import cue.edu.co.velocerentals.models.UsersCredentials;
import cue.edu.co.velocerentals.service.UsersService;
import cue.edu.co.velocerentals.utils.HashingUtil;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// Servlet for user login functionality.
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Inject
    private UsersService usersService;

    // Method to handle POST requests for user login.
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            // Retrieve user credentials from the service.
            UsersCredentials credentials = usersService.findUserAndHashPassword(username);

            // Validate user credentials.
            if (credentials != null && HashingUtil.checkPassword(password, credentials.getPassword())) {
                // Create a session cookie for the user.
                Cookie cookie = new Cookie("userLogged", username);
                cookie.setMaxAge(60 * 60 * 24 * 30); // 30 days expiration
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                resp.addCookie(cookie);

                // Set user information in the session.
                req.getSession().setAttribute("userLogged", username);
                req.getSession().setAttribute("user", credentials.getRoleName());

                // Redirect user based on their role.
                if ("Admin".equals(credentials.getRoleName())) {
                    resp.sendRedirect("admin.jsp");
                } else {
                    resp.sendRedirect("main.jsp");
                }

            } else {
                // If credentials are incorrect, forward user back to login page with error message.
                req.setAttribute("error", "User or password incorrect");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            // If an error occurs during login, forward user back to login page with error message.
            req.setAttribute("error", "Login failed:" + e.getMessage());
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
