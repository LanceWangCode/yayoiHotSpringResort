package com.mem.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mem.model.MemService;
import com.mem.model.MemVO;

import redis.clients.jedis.Jedis;
import util.MailService;
import util.RamdomCode;


public class MemVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1-1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String mem_email = req.getParameter("mem_email");
				String vercode = req.getParameter("vercode");
				Jedis jedis = new Jedis("localhost", 6379);
				if ( jedis.get(mem_email)!= null && vercode.equals(jedis.get(mem_email))) {
					MemService memSvc = new MemService();
					memSvc.updateMemStatus(mem_email, (byte)1);
					jedis.close();
					
					String url = "/front-end/mem/loadingSuccess.jsp";
					
					res.setHeader("Refresh", "2; URL=" + 
					req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/front-end/mem/login.jsp");
					
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);
					return;
				}else {
					jedis.close();
					RequestDispatcher failureView = req.getRequestDispatcher("/home.jsp");
					failureView.forward(req, res);
					return;
				}
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/home.jsp");
				failureView.forward(req, res);
			}
		}
	
}
