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

    // Logger instance for logging request and response details.
    private static final Logger LOGGER = Logger.getLogger(LoggingFilter.class.getName());

    // Intercepts the request to log request URI and method before passing it through the filter chain.
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        LOGGER.info("Request URI: " + request.getRequestURI());
        LOGGER.info("Method: " + request.getMethod());

        // Passes the request through the filter chain.
        chain.doFilter(request, response);

        // Logs the response status code after the request has been processed.
        LOGGER.info("Response Status: " + response.getStatus());
    }
}
