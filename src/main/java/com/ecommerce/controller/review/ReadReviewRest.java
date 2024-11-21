package com.ecommerce.controller.review;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;

import com.ecommerce.dao.ReviewDAO;
import com.ecommerce.dto.ReviewVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/read-review-block")
public class ReadReviewRest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ReviewDAO reviewDAO;
	{
		reviewDAO = new ReviewDAO();
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		int block;
		try {
			block = Integer.parseInt(request.getParameter("block"));
			if(block < 1) block = 1;
		} catch(Exception ignored) {
			block = 1;
		}
		try {
			int prodId = Integer.parseInt(request.getParameter("prodId"));
			List<ReviewVO> reviews = reviewDAO.readReviewBlock(prodId, block);
			
			JSONArray jsonArray = new JSONArray();
			for(var review : reviews) {
				jsonArray.put(review.toJSONObject());
			}
			
			var out = response.getWriter();
			out.print(jsonArray);
			out.flush();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
	
}
