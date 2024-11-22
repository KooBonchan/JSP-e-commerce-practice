package com.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.dto.ProductVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/home")
public class Home extends HttpServlet {
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
		
		
		int page = 1;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception ignored) {}
		if(page < 1) {
			page = 1;
		}
		
		List<ProductVO> products = null;
		String myProducts = request.getParameter("my");
		boolean defaultFallback = true;
		if(myProducts != null && myProducts.equals("true")) {
			HttpSession session = request.getSession(false);
			if(session != null && (Boolean) session.getAttribute("permission")) {
				products = productDAO.readMyProducts((String) session.getAttribute("id"));
				request.setAttribute("products", products);
				RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}
		products = productDAO.readProductPage(page);
		request.setAttribute("products", products);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
	}
	
}
