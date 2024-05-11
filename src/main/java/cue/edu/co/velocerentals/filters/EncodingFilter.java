package cue.edu.co.velocerentals.filters;

import jakarta.servlet.*;

import java.io.IOException;

// Filter for setting character encoding to UTF-8.
public class EncodingFilter implements Filter {

    private static final String ENCODING = "UTF-8";

    // Initialization method.
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    // Method to set character encoding and pass request/response down the filter chain.
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(ENCODING);
        servletResponse.setCharacterEncoding(ENCODING);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    // Destruction method.
    @Override
    public void destroy() {}
}
