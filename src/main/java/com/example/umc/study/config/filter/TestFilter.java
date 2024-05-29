package com.example.umc.study.config.filter;

import jakarta.servlet.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.sun.net.httpserver.Filter.Chain;
import java.io.IOException;

public class TestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            System.out.println(authentication.getName());
        }
        chain.doFilter(request, response);
    }
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
