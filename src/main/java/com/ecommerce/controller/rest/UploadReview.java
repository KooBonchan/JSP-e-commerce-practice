package com.ecommerce.controller.rest;

import java.io.BufferedReader;
import java.io.IOException;

import org.json.JSONObject;

import com.ecommerce.dao.ReviewDAO;
import com.ecommerce.dto.ReviewVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/product/upload-review")
public class UploadReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ReviewDAO reviewDAO;
	{
		reviewDAO = new ReviewDAO();
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		StringBuilder stringBuilder = new StringBuilder();
		String line;
		try(BufferedReader reader = request.getReader()){
			while((line = reader.readLine()) != null)
				stringBuilder.append(line);
		}
		
		try {
			JSONObject jsonObject = new JSONObject(stringBuilder.toString());
			String rawProdId = jsonObject.getString("prodId");
			int prodId = Integer.parseInt(rawProdId);
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("id");
			String content = jsonObject.getString("content");
			if(content.length() > 0) {
				ReviewVO review = new ReviewVO();
				review.setWriter(id);
				review.setContent(content);
				reviewDAO.createReview(prodId, review);
			}
		} catch(Exception ignored) {}
	}
	
}
