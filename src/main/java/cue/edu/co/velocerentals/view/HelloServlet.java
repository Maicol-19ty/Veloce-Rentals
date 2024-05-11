package cue.edu.co.velocerentals.view;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

// Servlet for saying hello.
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    // Initialization method.
    public void init() {
        message = "Hello World! Test";
    }

    // Method to handle GET requests.
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello message
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    // Destruction method.
    public void destroy() {
    }
}
