package com.example.umc.study.config.filter;

import com.example.umc.study.config.SecurityConfig;
import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

@Slf4j
public class TestFilter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            log.info("Anonymous User");
        } else {
            log.info("User: " + authentication.getName());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
