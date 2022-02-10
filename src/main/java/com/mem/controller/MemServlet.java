package com.mem.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemService;
import com.mem.model.MemVO;

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

				/*************************** 2.開始新增資料 ***************************************/
				memVO = memSvc.addMem(mem_email, mem_password, mem_name, mem_phone, mem_address);
				ServletContext context = getServletContext();
				InputStream in = context.getResourceAsStream("/front-end/mem/images/avatar.png");
				byte[] pic = memSvc.getPictureByteArray(in);
				memVO = memSvc.addMemPic(mem_email, pic);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/mem/login.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/signup.jsp");
				failureView.forward(req, res);
			}
		}

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
		           session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
		           res.sendRedirect(location);            
		           return;
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
//				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//				Integer mem_id = new Integer(req.getParameter("mem_id").trim());
//				Byte mem_status = new Byte(req.getParameter("mem_status").trim());
//
//				MemVO memVO = new MemVO();
//				memVO.setMem_id(mem_id);
//				memVO.setMem_status(mem_status);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/listAllMem.jsp");
//					failureView.forward(req, res);
//					return; // 程式中斷
//				}
//
//				/*************************** 2.開始修改資料 *****************************************/
//				MemService memSvc = new MemService();
//				memVO = memSvc.updateMemStatus(mem_id, mem_status);
//
//				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//				req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/back-end/mem/listAllMem.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/listAllMem.jsp");
//				failureView.forward(req, res);
//			}
//		}

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

	}

}
