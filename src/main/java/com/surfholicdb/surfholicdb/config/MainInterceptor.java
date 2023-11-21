package com.surfholicdb.surfholicdb.config;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MainInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        System.out.println("[interceptor] requestURI : " + requestURI);
        
        return true;
    }
}
