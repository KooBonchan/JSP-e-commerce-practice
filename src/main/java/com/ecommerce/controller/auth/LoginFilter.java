package com.ecommerce.controller.auth;


import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {
		"/bundle-page-with-directory/except-login-signup",
		"/home",
		"/product",
		"/product/*"
	}) //auth protected pages
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            httpResponse.sendRedirect("login.html?error=not_authenticated");
            return;
        }
        String logout = httpRequest.getParameter("logout");
        if(logout != null && logout.equals("true")) {
        	httpResponse.sendRedirect("login.html");
            return;
        }
        chain.doFilter(request, response);
    }
}