package com.ecommerce.controller.auth;

import java.io.IOException;

import com.ecommerce.dao.MemberDAO;
import com.ecommerce.dto.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    MemberDAO memberDAO;
    {
        memberDAO= new MemberDAO();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		MemberVO member = memberDAO.login(id, password);
		if(member != null) {
			HttpSession session = request.getSession();
			session.setAttribute("username", member.getId());
			session.setAttribute("permission", member.isMerchant());
			session.setMaxInactiveInterval(30 * 1000);
			response.sendRedirect("home");
		} else {
			response.sendRedirect("login.html?error=invalid_credentials");
		}
		
	}
	
}
