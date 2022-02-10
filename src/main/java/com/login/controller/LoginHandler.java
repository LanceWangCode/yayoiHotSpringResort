package com.login.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.model.*;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/admin/login")
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username =  request.getParameter("username");
		String password =  request.getParameter("password");
		
		LoginDAO dao = new LoginDAO();
		LoginVO loginVo = dao.selectByUsernameAndPassowrd(username, password);
		
		if (loginVo == null) {
			// redirect to index.html
			response.sendRedirect(request.getContextPath()+"/back-end/admin/login.jsp?error=LoginFail");
			System.out.println("Login Fail");			
		} else {
			// redirect to admin.html
			HttpSession session=request.getSession();  
	        session.setAttribute("username",loginVo.getEmp_name());  
			response.sendRedirect(request.getContextPath()+"/back-end/admin/dashboard.jsp");
			System.out.println(loginVo.getEmp_name());
		}
	}

}
