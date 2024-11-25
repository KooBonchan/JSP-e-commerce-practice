package com.ecommerce.controller.product;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.ecommerce.controller.util.FilesUtil;
import com.ecommerce.dao.ProductDAO;
import com.ecommerce.dto.ProductVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class UploadProduct
 */
@MultipartConfig(location = "c:\\temp",
	fileSizeThreshold = 1024 * 1024,
	maxFileSize = 5 * 1024 * 1024,
	maxRequestSize = 50 * 1024 * 1024)
@WebServlet("/product/create")
public class UploadProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   
    ProductDAO productDAO;
    {
    	productDAO = new ProductDAO();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.sendRedirect("/home");
    }
    	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		Part filePart = request.getPart("image");
		String filename = null;
		if(filePart != null) {
			String imageDirectory = getServletContext().getRealPath("/uploads");
			filename = FilesUtil.processFile(filePart, imageDirectory);
			
		}
		
		try {
			String name = request.getParameter("product-name");
			int price = Integer.parseInt(request.getParameter("price"));
			String description = request.getParameter("description");
			int inventory = Integer.parseInt(request.getParameter("inventory"));
			ProductVO product = new ProductVO();
			product.setName(name);
			if(filename != null) product.setImagePath(filename);
			product.setPrice(price);
			product.setDescription(description);
			product.setInventory(inventory);
			
			HttpSession session = request.getSession(false);
			String providerId = (String)session.getAttribute("id");
			
			productDAO.createProduct(product, providerId);
			response.sendRedirect("/my-products");
		} catch (RuntimeException e) {
			System.err.println("upload error: " + e.getMessage());
		}
	}
}