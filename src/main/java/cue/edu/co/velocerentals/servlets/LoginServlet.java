package cue.edu.co.velocerentals.servlets;

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            String storeHash = usersService.findUserAndHashPassword(username);
            if (storeHash != null && HashingUtil.checkPassword(password, storeHash)) {

                Cookie cookie = new Cookie("userLogged", username);
                cookie.setMaxAge(60 * 60 * 24 * 30);
                cookie.setHttpOnly(true);
                cookie.setPath("/");
                resp.addCookie(cookie);

                req.getSession().setAttribute("userLogged", username);
                resp.sendRedirect("main.jsp");

            } else {
                req.setAttribute("error", "User or password incorrect");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("error", "Login failed:" + e.getMessage());
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
