package com.ecommerce.controller.auth;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;

import com.ecommerce.dao.MemberDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/id-check") // by REST
public class IdCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
    MemberDAO memberDAO;
    {
        memberDAO= new MemberDAO();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		String id = request.getParameter("id");
		
		JSONObject jsonResponse = new JSONObject();
    	jsonResponse.put("exists", memberDAO.existsId(id));
    	PrintWriter out = response.getWriter();
        out.print(jsonResponse.toString());
        out.flush();
		
	}
	
}
