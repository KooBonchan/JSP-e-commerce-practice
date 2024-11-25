package com.ecommerce.controller.product;

import java.io.IOException;

import com.ecommerce.dao.ProductDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/product")
public class ProductView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductDAO productDAO;
    {
        productDAO = new ProductDAO();
        // soon injected by..container?;
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		int id;
		try {
			String rawId = request.getParameter("id");
			id = Integer.parseInt(rawId);
		} catch(Exception e) {
			response.sendRedirect("home"); return;
		}
		
		var product = productDAO.readProduct(id);
		if(product != null) {
			request.setAttribute("product", product);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/product/product-view.jsp");
			dispatcher.forward(request, response);
		} else {
			response.getWriter().print("No such Item. redirecting to home");
			response.addHeader("Refresh", "1;url=/home");
		}

	}
}
