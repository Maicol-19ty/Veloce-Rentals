package cue.edu.co.velocerentals.servlets;

import cue.edu.co.velocerentals.mapping.DTO.UsersDTo;
import cue.edu.co.velocerentals.service.UsersService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "registerServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    @Inject
    private UsersService usersService;

    // Method to handle POST requests (user registration)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Retrieving user registration data from request parameters
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String fullName = req.getParameter("full_name");
        int defaultRoleId = 1; // Assuming a default role ID for newly registered users

        // Creating a UsersDTo object with the registration data
        UsersDTo usersDTo = UsersDTo.builder()
                .username(username)
                .password(password)
                .email(email)
                .full_name(fullName)
                .build();

        try {
            // Checking if the username or email already exists
            if (usersService.checkUser(username, email)) {
                // If the username or email already exists, forwarding to the registration page with an error message
                req.setAttribute("error", "User or Email already exists");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            } else {
                // If the username and email are unique, registering the user
                usersService.register(usersDTo, defaultRoleId);

                // Creating a cookie to store the logged-in user's information
                Cookie cookie = new Cookie("userLogged", username);
                cookie.setMaxAge(60 * 60 * 24 * 30); // Setting cookie expiration time (30 days)
                cookie.setHttpOnly(true); // Making the cookie accessible only via HTTP (not JavaScript)
                cookie.setPath("/"); // Setting cookie path to root
                resp.addCookie(cookie); // Adding cookie to the response

                // Setting user information in the session
                req.getSession().setAttribute("userLogged", username);

                // Redirecting to the main.jsp page
                resp.sendRedirect("main.jsp");
            }
        } catch (Exception e) {
            // Handling any exceptions and forwarding to the registration page with an error message
            req.setAttribute("error", "Registration failed:" + e.getMessage());
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }
}
