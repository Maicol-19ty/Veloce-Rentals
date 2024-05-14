package cue.edu.co.velocerentals.servlets;

import cue.edu.co.velocerentals.models.Users;
import cue.edu.co.velocerentals.service.UsersService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// Servlet for updating user profiles and retrieving user details.
@WebServlet("/updateProfile")
public class UpdateProfile extends HttpServlet {

    @Inject
    private UsersService usersService;

    // Handles POST requests to update user profiles.
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieves parameters for updating user profile.
        String username = req.getParameter("username");
        String fullName = req.getParameter("full_name");
        String email = req.getParameter("email");

        // Redirects to profile page if profile is updated successfully; otherwise, displays error.
        if (usersService.updateUserProfile(username, fullName, email)) {
            resp.sendRedirect("profile.jsp");
        } else {
            req.setAttribute("error", "Failed to update profile");
            req.getRequestDispatcher("profile.jsp").forward(req, resp);
        }
    }

    // Handles GET requests to retrieve user details for profile page.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("userLogged");

        // Redirects to login page if user is not logged in.
        if (username == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        // Retrieves user details and forwards to profile page; displays error if user not found.
        Users user = usersService.userDetails(username);
        if (user == null) {
            req.setAttribute("error", "User not found");
        } else {
            req.setAttribute("user", user);
        }
        req.getRequestDispatcher("profile.jsp").forward(req, resp);
    }

}
