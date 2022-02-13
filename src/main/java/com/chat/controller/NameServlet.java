package com.chat.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NameServlet extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String userName = req.getParameter("userName");
		String chatNode = req.getParameter("chatnode");
		String dispatchUrl = null;
		req.setAttribute("userName", userName);
		
		if("server".equals(chatNode))
			dispatchUrl = "/back-end/chat/chat.jsp";
		else 
			dispatchUrl = "/front-end/chat/chat.jsp";
			
		RequestDispatcher dispatcher = req.getRequestDispatcher(dispatchUrl);
		dispatcher.forward(req, res);
	}
}
