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

@WebServlet("/updateProfile")
public class UpdateProfile extends HttpServlet {

    @Inject
    private UsersService usersService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String fullName = req.getParameter("full_name");
        String email = req.getParameter("email");

        if (usersService.updateUserProfile(username, fullName, email)) {
            resp.sendRedirect("profile.jsp");
        } else {
            req.setAttribute("error", "Failed to update profile");
            req.getRequestDispatcher("profile.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("userLogged");

        if (username == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        Users user = usersService.userDetails(username);
        if (user == null) {
            req.setAttribute("error", "User not found");
        } else {
            req.setAttribute("user", user);
        }
        req.getRequestDispatcher("profile.jsp").forward(req, resp);
    }

}
