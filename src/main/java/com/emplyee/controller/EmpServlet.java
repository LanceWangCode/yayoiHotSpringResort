package com.emplyee.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emplyee.model.EmplyeeService;
import com.emplyee.model.EmplyeeVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;


@WebServlet("/EmpServlet")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String id = req.getParameter("id");

/******員工登入******/
		if ("login".equals(action)) { // 來自signIn.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer empno = new Integer(req.getParameter("empno").trim());
				if (empno == null) {
					errorMsgs.add("請輸入員工編號");
				}
				
				String emp_password = req.getParameter("emp_password");
				if (emp_password == null || (emp_password.trim()).length() == 0) {
					errorMsgs.add("請輸入密碼");
				}
				// Send the use back to the form, if there were errors

				/*************************** 2.開始查詢資料 *****************************************/
				EmplyeeService empSvc = new EmplyeeService();
				EmplyeeVO emplyeeVO = empSvc.logIn(empno, emp_password);
				if (emplyeeVO == null) {
					errorMsgs.add("員工編號或密碼錯誤，請重新輸入");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("emplyeeVO", emplyeeVO); // 資料庫取出的empVO物件,存入req
				HttpSession session = req.getSession();
				session.setAttribute("staff", emplyeeVO);
				session.setAttribute("employee", req.getParameter("empno"));
		      		        
				String url = "/back-end/admin/index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/login.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
