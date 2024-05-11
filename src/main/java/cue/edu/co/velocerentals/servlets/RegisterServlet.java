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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String fullName = req.getParameter("full_name");

        System.out.println("Verificando usuario: " + username + ", Email: " + email);

        UsersDTo usersDTo = UsersDTo.builder()
                .username(username)
                .password(password)
                .email(email)
                .full_name(fullName)
                .build();

        try {
            if (usersService.checkUser(username, email)) {
                req.setAttribute("error", "User or Email already exists");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            } else {

                usersService.register(usersDTo);

                Cookie cookie = new Cookie("userLogged", username);
                cookie.setMaxAge(60 * 60 * 24 * 30);
                cookie.setHttpOnly(true);
                cookie.setPath("/");
                resp.addCookie(cookie);
                req.getSession().setAttribute("userLogged", username);
                resp.sendRedirect("main.jsp");

            }
        } catch (Exception e) {
            req.setAttribute("error", "Registration failed:" + e.getMessage());
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }
}
