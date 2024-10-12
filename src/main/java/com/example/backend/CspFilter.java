package com.example.backend;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.*;

import java.io.IOException;

public class CspFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Content-Security-Policy", "default-src *; connect-src *; script-src 'self' 'unsafe-inline'; style-src 'self' 'unsafe-inline';");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
