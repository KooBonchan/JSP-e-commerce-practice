package com.ecommerce.controller.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.ecommerce.dao.ReviewDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/buy-cart")
public class BuyCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Map<Integer, Integer> cart = new HashMap<>();
		session.setAttribute("cart", cart);
        response.setContentType("application/json");
	}
	
}
