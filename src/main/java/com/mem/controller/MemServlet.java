package com.mem.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import redis.clients.jedis.BitOP;
import redis.clients.jedis.Jedis;

import com.mem.model.MemService;
import com.mem.model.MemVO;

import util.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String id = req.getParameter("id");

		/** 會員註冊 **/
		if ("signup".equals(action)) { // 來自signUp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1-1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String mem_email = req.getParameter("mem_email");
				String emailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("電子郵件地址: 請勿空白");
				} else if (!mem_email.trim().matches(emailReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電子郵件地址格式錯誤");
				}

				String mem_password = req.getParameter("mem_password").trim();
				if (mem_password == null || mem_password.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}

				String remem_password = req.getParameter("remem_password").trim();
				if (remem_password == null || remem_password.trim().length() == 0) {
					errorMsgs.add("密碼重複請勿空白");
				} else if (!remem_password.equals(req.getParameter("mem_password"))) {
					errorMsgs.add("密碼與密碼重複不同");
				}

				String mem_name = req.getParameter("mem_name").trim();
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("姓名請勿空白");
				}

				String mem_phone = req.getParameter("mem_phone").trim();
				String phoneReg = "^09[0-9]{2}\\d{6}$";
				if (mem_phone == null || mem_phone.trim().length() == 0) {
					errorMsgs.add("手機請勿空白");
				} else if (!mem_phone.trim().matches(phoneReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("手機格式錯誤");
				}

				String mem_address = req.getParameter("mem_address");

				MemVO memVO = new MemVO();
				memVO.setMem_email(mem_email);
				memVO.setMem_password(mem_password);
				memVO.setMem_name(mem_name);
				memVO.setMem_phone(mem_phone);
				memVO.setMem_address(mem_address);

				/***************************
				 * 1-2.select帳號確認沒重複
				 ***************************************/
				MemService memSvc = new MemService();

				if (!memSvc.checkMem(mem_email)) {
					errorMsgs.add("Email地址重複");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/signup.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2-1.開始新增資料 ***************************************/
				memVO = memSvc.addMem(mem_email, mem_password, mem_name, mem_phone, mem_address);
				ServletContext context = getServletContext();
				InputStream in = context.getResourceAsStream("/front-end/mem/images/avatar.png");
				byte[] pic = memSvc.getPictureByteArray(in);
				memVO = memSvc.addMemPic(mem_email, pic);

				/***************************
				 * 2-2.寄送驗證信資料
				 ***************************************/
				Jedis jedis = new Jedis("localhost", 6379);
				RamdomCode code = new RamdomCode();
				String vercode = code.genAuthCode();

				jedis.set(mem_email, vercode);
				jedis.expire(mem_email, 60 * 60 * 24); // 存活時間24hr

				jedis.close();
				
				new Thread(() -> {
					MailService mailService = new MailService();
					String subject = "會員驗證信通知";
					String messageText = "親愛的會員您好: " + "\n" + "以下是您的驗證連結，請在24小時內點擊並啟用您的帳號!" + "\n" + req.getScheme() + "://"
							+ req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/mem/memver.do"
							+ "?" + "mem_email=" + mem_email + "&" + "vercode=" + vercode;

					mailService.sendMail(mem_email, subject, messageText);
				}).start();

//				MailService mailService = new MailService();
//				String subject = "會員驗證信通知";
//				String messageText = "親愛的會員您好: " + "\n" + "以下是您的驗證連結，請在24小時內點擊並啟用您的帳號!" + "\n" + req.getScheme() + "://"
//						+ req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/mem/memver.do"
//						+ "?" + "mem_email=" + mem_email + "&" + "vercode=" + vercode;
//
//				mailService.sendMail(mem_email, subject, messageText);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/mem/loadingsignup.jsp";

				res.setHeader("Refresh", "2; URL=" + req.getScheme() + "://" + req.getServerName() + ":"
						+ req.getServerPort() + req.getContextPath() + "/front-end/mem/login.jsp");

				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/signup.jsp");
				failureView.forward(req, res);
			}
		}

		/** 會員登入 **/
		if ("login".equals(action)) { // 來自signIn.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String mem_email = req.getParameter("mem_email");
				if (mem_email == null || (mem_email.trim()).length() == 0) {
					errorMsgs.add("請輸入電子信箱");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String mem_password = req.getParameter("mem_password");
				if (mem_password == null || (mem_password.trim()).length() == 0) {
					errorMsgs.add("請輸入密碼");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.logIn(mem_email, mem_password);
				if (memVO == null) {
					errorMsgs.add("帳號或密碼錯誤，請重新輸入");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("memVO", memVO); // 資料庫取出的empVO物件,存入req
				HttpSession session = req.getSession();
				session.setAttribute("user", memVO);
				session.setAttribute("account", req.getParameter("mem_email"));
				String location = (String) session.getAttribute("location");
				if (location != null) {
					session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
					res.sendRedirect(location);
					return;
				}

				// 設置cookie
				Cookie mailCookie, pwdCookie;
				String save = req.getParameter("save");
				// 設置cookie存活時間
				if (save != null) {
					mailCookie = new Cookie("mail", req.getParameter("mem_email"));
					pwdCookie = new Cookie("pwd", req.getParameter("mem_password"));
					mailCookie.setMaxAge(60 * 60 * 24 * 7);
					pwdCookie.setMaxAge(60 * 60 * 24 * 7);
					mailCookie.setPath("/CFA104G1");
					pwdCookie.setPath("/CFA104G1");
					// 回傳cookie
					res.addCookie(mailCookie);
					res.addCookie(pwdCookie);
				} else {
					Cookie[] cookies = req.getCookies();
					for (int i = 0; i < cookies.length; i++) {
						Cookie cookie = cookies[i];
						if ("mail".equals(cookie.getName())) {
							cookie.setValue("");
							cookie.setMaxAge(0);
							cookie.setPath("/CFA104G1");
							res.addCookie(cookie);
						} else if ("pwd".equals(cookie.getName())) {
							cookie.setValue("");
							cookie.setMaxAge(0);
							cookie.setPath("/CFA104G1");

							res.addCookie(cookie);
						}

					}
//		        	mailCookie = new Cookie("mail", null);
//			        pwdCookie = new Cookie("pwd",  null);
//		        	mailCookie.setMaxAge(0);
//		        	pwdCookie.setMaxAge(0);
				}

				String url = "/home.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/login.jsp");
				failureView.forward(req, res);
			}
		}

		/** 會員登出 **/
		if ("logout".equals(action)) {
			req.getSession().invalidate();
			res.sendRedirect(req.getContextPath() + "/home.jsp");
			return;
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer mem_id = new Integer(req.getParameter("mem_id").trim());

				String mem_name = req.getParameter("mem_name");
				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("姓名: 請勿空白");
				} else if (!mem_name.trim().matches(mem_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String mem_phone = req.getParameter("mem_phone").trim();
				if (mem_phone == null || mem_phone.trim().length() == 0) {
					errorMsgs.add("手機請勿空白");
				}

				String mem_address = req.getParameter("mem_address").trim();
				if (mem_address == null || mem_address.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}

				MemVO memVO = new MemVO();
				memVO.setMem_id(mem_id);
				memVO.setMem_name(mem_name);
				memVO.setMem_phone(mem_phone);
				memVO.setMem_address(mem_address);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/memUpdateData.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMem(mem_id, mem_name, mem_phone, mem_address);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/mem/memCenter.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/memUpdateData.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer mem_id = new Integer(req.getParameter("mem_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_id);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("memVO", memVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/mem/updateMemInput.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}

		if ("alterpwd".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer mem_id = new Integer(req.getParameter("mem_id").trim());

				String mem_password = req.getParameter("mem_password").trim();
				if (mem_password == null || mem_password.trim().length() == 0) {
					errorMsgs.add("原本密碼請勿空白");
				}

				String mem_password_new = req.getParameter("mem_password_new").trim();
				if (mem_password_new == null || mem_password_new.trim().length() == 0) {
					errorMsgs.add("新密碼請勿空白");
				}

				String remem_password_new = req.getParameter("remem_password_new").trim();
				if (remem_password_new == null || remem_password_new.trim().length() == 0) {
					errorMsgs.add("密碼重複請勿空白");
				} else if (!remem_password_new.equals(req.getParameter("mem_password_new"))) {
					errorMsgs.add("新密碼與密碼重複不同");
				}

				/***************************
				 * 1-2.select舊密碼確認正確
				 ***************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.checkPwd(mem_id, mem_password);
				if (memVO == null) {
					errorMsgs.add("舊密碼錯誤，請重新輸入");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/memUpdatePwd.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				memVO = memSvc.updatePwd(mem_id, mem_password_new);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/mem/memCenter.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/memUpdatePwd.jsp");
				failureView.forward(req, res);
			}
		}

		if ("updateAvtor".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				byte[] mem_pic = null;
				InputStream in = req.getPart("mem_pic").getInputStream();
				if (in.available() != 0) {
					mem_pic = new byte[in.available()];
					in.read(mem_pic);
					in.close();
				} else {
					errorMsgs.add("請上傳圖片");
				}

				String mem_email = req.getParameter("mem_email");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/memUpdateAvator.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				MemVO memVO = new MemVO();
				memVO.setMem_email(mem_email);
				memVO.setMem_pic(mem_pic);

				MemService memSvc = new MemService();
				memVO = memSvc.addMemPic(mem_email, mem_pic);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/mem/memCenter.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/memUpdateAvator.jsp");
				failureView.forward(req, res);
			}
		}

		if ("updateMemAll".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer mem_id = new Integer(req.getParameter("mem_id").trim());
				String mem_email = req.getParameter("mem_email");

				String mem_name = req.getParameter("mem_name");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if (!mem_name.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String mem_phone = req.getParameter("mem_phone").trim();
				String phoneReg = "^09[0-9]{2}\\d{6}$";
				if (mem_phone == null || mem_phone.trim().length() == 0) {
					errorMsgs.add("手機請勿空白");
				} else if (!mem_phone.trim().matches(phoneReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("手機格式錯誤");
				}

				String mem_address = req.getParameter("mem_address");

				Byte mem_status = new Byte(req.getParameter("mem_status").trim());

				MemVO memVO = new MemVO();
				memVO.setMem_id(mem_id);
				memVO.setMem_email(mem_email);
				memVO.setMem_name(mem_name);
				memVO.setMem_phone(mem_phone);
				memVO.setMem_address(mem_address);
				memVO.setMem_status(mem_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/updateMemInput.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMemAllData(mem_id, mem_name, mem_phone, mem_address, mem_status);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/mem/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/updateMemInput.jsp");
				failureView.forward(req, res);
			}
		}

		if ("resetpwd".equals(action)) { // 來自signUp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1-1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String mem_email = req.getParameter("mem_email");
				String emailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("電子郵件地址: 請勿空白");
				} else if (!mem_email.trim().matches(emailReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電子郵件地址格式錯誤");
				}

				MemVO memVO = new MemVO();
				memVO.setMem_email(mem_email);

				/***************************
				 * 1-2.select帳號確認沒重複
				 ***************************************/
				MemService memSvc = new MemService();

				if (memSvc.checkMem(mem_email)) {
					errorMsgs.add("該Email並沒有註冊過會員!");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/memResetPwd.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始重設密碼 ***************************************/
				RamdomCode code = new RamdomCode();
				String pwd = code.genAuthCode();

				memVO = memSvc.resetPwd(mem_email, pwd);
				new Thread(() -> {
					MailService mailService = new MailService();
					String subject = "變更密碼通知";
					String messageText = "親愛的會員您好: " + "\n" + "這是您的新密碼:" + pwd + "\n" + "請用此密碼登入後再變更您的密碼!";
					mailService.sendMail(mem_email, subject, messageText);
				}).start();
//				MailService mailService = new MailService();
//			    String subject = "變更密碼通知";
//			    String messageText = "親愛的會員您好: " + "\n" + 
//	                      "這是您的新密碼:" + pwd + "\n" +
//                          "請用此密碼登入後再變更您的密碼!"; 
//				mailService.sendMail(mem_email, subject, messageText);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/mem/loadingpwd.jsp";

				res.setHeader("Refresh", "2; URL=" + req.getScheme() + "://" + req.getServerName() + ":"
						+ req.getServerPort() + req.getContextPath() + "/front-end/mem/login.jsp");

				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/login.jsp");
				failureView.forward(req, res);
			}
		}

		if ("resetpwdajax".equals(action)) { // 來自signUp.jsp的請求

			JSONObject jsonObject = new JSONObject();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			try {
				String mem_email = req.getParameter("mem_email");
				String emailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
				if (mem_email == null || mem_email.trim().length() == 0) {
					jsonObject.put("error","電子郵件地址: 請勿空白");
				} else if (!mem_email.trim().matches(emailReg)) { // 以下練習正則(規)表示式(regular-expression)
					jsonObject.put("error","電子郵件地址格式錯誤");
				}
				MemVO memVO = new MemVO();
				memVO.setMem_email(mem_email);				
				MemService memSvc = new MemService();
				if (memSvc.checkMem(mem_email)) {
					jsonObject.put("error","該Email並沒有註冊過會員!");
				}
				// 我要怎麼寫出資料到前端
				if (!jsonObject.isNull("error")) {
					PrintWriter out = res.getWriter();
					out.print(jsonObject.toString());
			        return;
				}
//				/*********************** 1-1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//				String mem_email = req.getParameter("mem_email");
//				String emailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
//				if (mem_email == null || mem_email.trim().length() == 0) {
//					errorMsgs.add("電子郵件地址: 請勿空白");
//				} else if (!mem_email.trim().matches(emailReg)) { // 以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("電子郵件地址格式錯誤");
//				}
//
//				MemVO memVO = new MemVO();
//				memVO.setMem_email(mem_email);
//
//				/***************************
//				 * 1-2.select帳號確認沒重複
//				 ***************************************/
//				MemService memSvc = new MemService();
//
//				if (memSvc.checkMem(mem_email)) {
//					errorMsgs.add("該Email並沒有註冊過會員!");
//				}
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/memResetPwd.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//
//				/*************************** 2.開始重設密碼 ***************************************/
//				RamdomCode code = new RamdomCode();
//				String pwd = code.genAuthCode();
//
//				memVO = memSvc.resetPwd(mem_email, pwd);
//
//				MailService mailService = new MailService();
//				String subject = "變更密碼通知";
//				String messageText = "親愛的會員您好: " + "\n" + "這是您的新密碼:" + pwd + "\n" + "請用此密碼登入後再變更您的密碼!";
//				mailService.sendMail(mem_email, subject, messageText);
//				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//				String url = "/front-end/mem/loadingpwd.jsp";
//
//				res.setHeader("Refresh", "2; URL=" + req.getScheme() + "://" + req.getServerName() + ":"
//						+ req.getServerPort() + req.getContextPath() + "/front-end/mem/login.jsp");
//
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/login.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
