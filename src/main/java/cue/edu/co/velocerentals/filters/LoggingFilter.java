package cue.edu.co.velocerentals.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

@WebFilter("/*")
public class LoggingFilter extends HttpFilter {

    private static final Logger LOGGER = Logger.getLogger(LoggingFilter.class.getName());

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        LOGGER.info("Request URI: " + request.getRequestURI());
        LOGGER.info("Method: " + request.getMethod());

        chain.doFilter(request, response);

        LOGGER.info("Response Status: " + response.getStatus());
    }
}
