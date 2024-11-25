package com.ecommerce.controller.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/product/add-to-cart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder stringBuilder = new StringBuilder();
		try(BufferedReader reader = request.getReader()){
			String line;
			while((line = reader.readLine()) != null)
				stringBuilder.append(line);
		}
		
		try {
			JSONObject jsonObject = new JSONObject(stringBuilder.toString());
			int prodId = jsonObject.getInt("prodId");
			int count = jsonObject.getInt("count");
			
			
			HttpSession session = request.getSession(false);
			Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
	        if (cart == null) {
	            cart = new HashMap<>();
	            session.setAttribute("cart", cart);
	        }
	        cart.put(prodId, cart.getOrDefault(prodId, 0) + count);
	        response.setContentType("application/json");
	        response.getWriter().write("{ \"success\": \"true\"}");
	        return;
		} catch(Exception ignored) {}
		response.setContentType("application/json");
        response.getWriter().write("{ \"success\": \"false\"}");
	}
	
}
