package com.ecommerce.controller.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart-count")
public class CartCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		int size = 0;
		if(session != null) {
			Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
	        if (cart == null) {
	            cart = new HashMap<>();
	            session.setAttribute("cart", cart);
	        }
	        size = cart.size();
		}
		
        response.setContentType("application/json");
        response.getWriter().write("{ \"count\": \""+size+"\"}");
	}
	
}
