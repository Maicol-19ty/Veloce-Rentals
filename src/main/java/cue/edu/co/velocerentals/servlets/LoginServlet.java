/*
 * This servlet handles user login requests.
 */

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

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Inject
    private UsersService usersService;

    // Method to handle POST requests (user login)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieving username and password parameters from the request
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            // Finding user credentials by username and hashing password
            UsersCredentials credentials = usersService.findUserAndHashPassword(username);

            // Validating user credentials and redirecting to main page if login is successful
            if (credentials != null && HashingUtil.checkPassword(password, credentials.getPassword())) {
                // Creating a cookie to store the logged-in user's information
                Cookie cookie = new Cookie("userLogged", username);
                cookie.setMaxAge(60 * 60 * 24 * 30); // Setting cookie expiration time (30 days)
                cookie.setPath("/"); // Setting cookie path to root
                cookie.setHttpOnly(true); // Making the cookie accessible only via HTTP (not JavaScript)
                resp.addCookie(cookie); // Adding cookie to the response

                // Setting user information in the session
                req.getSession().setAttribute("userLogged", username);
                req.getSession().setAttribute("userRole", credentials.getRoleName());

                // Redirecting to the main.jsp page
                resp.sendRedirect("main.jsp");

            } else {
                // If user credentials are incorrect, forwarding to the login page with an error message
                req.setAttribute("error", "User or password incorrect");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            // Handling any exceptions and forwarding to the login page with an error message
            req.setAttribute("error", "Login failed:" + e.getMessage());
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
