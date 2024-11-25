package com.ecommerce.controller.product;

import java.io.IOException;
import java.util.Arrays;

import javax.print.attribute.PrintServiceAttribute;

import com.ecommerce.controller.util.FilesUtil;
import com.ecommerce.dao.ProductDAO;
import com.ecommerce.dto.ProductVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;


@MultipartConfig(location = "c:\\temp",
	maxFileSize = 5 * 1024 * 1024,
	maxRequestSize = 50 * 1024 * 1024)
@WebServlet("/product/update")
public class UpdateProduct extends HttpServlet {
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/product/product-update.jsp");
			dispatcher.forward(request, response);
		} else {
			response.getWriter().print("No such Item. redirecting to home");
			response.addHeader("Refresh", "1;url='/home'");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		Part filePart = request.getPart("image");
		String filename = null;
		if(filePart != null && filePart.getSize() > 0) {
			String imageDirectory = getServletContext().getRealPath("/uploads");
			filename = FilesUtil.processFile(filePart, imageDirectory);
		}
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("product-name");
			int price = Integer.parseInt(request.getParameter("price"));
			String description = request.getParameter("description");
			int inventory = Integer.parseInt(request.getParameter("inventory"));
			ProductVO product = new ProductVO();
			product.setId(id);
			product.setName(name);
			if(filename != null) product.setImagePath(filename);
			product.setPrice(price);
			product.setDescription(description);
			product.setInventory(inventory);
			
			HttpSession session = request.getSession(false);
			String providerId = (String)session.getAttribute("id");
			
			productDAO.updateProduct(product, providerId);
			response.sendRedirect("/my-products");
		} catch (RuntimeException e) {
			System.err.println("upload error: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
