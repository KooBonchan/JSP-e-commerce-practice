package com.ecommerce.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

/**
 * Servlet implementation class UploadProduct
 */
@MultipartConfig(location = "/img",
	fileSizeThreshold = 1024 * 1024,
	maxFileSize = 10 * 1024 * 1024,
	maxRequestSize = 50 * 1024 * 1024)
public class UploadProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadProduct() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		int uploadFilesizeLimit = 5 * 1024 * 1024; //5MB
		String encType = "utf-8";
		
		try {
			Part filePart = request.getPart("uploadFile");
			saveFile(filePart);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	private String saveFile(Part filePart) throws IOException{
		String uploadDirectory = "/product";
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(uploadDirectory);
		
		
		String filename = filePart.getSubmittedFileName();
		String filePath = uploadFilePath + "/" + filename;
		
		filePart.write(filePath);
		return filePath;
	}

}
