package com.qalist.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qalist.model.QAListService;
import com.qalist.model.QAListVO;
import com.room.model.RoomService;
import com.room.model.RoomVO;

@WebServlet("/QAListServlet")
public class QAListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Integer no = null;
				try {
					no = new Integer(req.getParameter("no").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("順序請填數字.");
				}
				
				String question = req.getParameter("question").trim();
				if (question == null || question.trim().length() == 0) {
					errorMsgs.add("問題請勿空白");
				}
				
				String answer = req.getParameter("answer").trim();
				if (answer == null || answer.trim().length() == 0) {
					errorMsgs.add("答覆請勿空白");
				}

				Boolean status = null;
				try {
					status = new Boolean(req.getParameter("status"));
				} catch (NumberFormatException e) {
					status = true;
					errorMsgs.add("上下架狀態請勿空白.");
				}
				
				QAListVO qaListVO = new QAListVO();
				qaListVO.setNo(no);
				qaListVO.setQuestion(question);
				qaListVO.setAnswer(answer);
				qaListVO.setStatus(status);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("qaListVO", qaListVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/qaList/addQA.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				QAListService qaListService = new QAListService();
				qaListVO = qaListService.addQA(no, question, answer, status);
				

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/qaList/listAllQA.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/qaList/addQA.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer qa_id = new Integer(req.getParameter("qa_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				QAListService qaListService = new QAListService();
				QAListVO qaListVO = qaListService.getOneRoom(qa_id);
				
				

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("qaListVO", qaListVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/qaList/updateQA.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/qaList/listAllQA.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer qa_id = new Integer(req.getParameter("qa_id").trim());

				Integer no = null;
				try {
					no = new Integer(req.getParameter("no").trim());
				} catch (NumberFormatException e) {
					no = 0;
					errorMsgs.add("顯示順序請填數字.");
				}

				String question = req.getParameter("question");
				if (question == null || question.trim().length() == 0) {
					errorMsgs.add("問題: 請勿空白");
				}
				String answer = req.getParameter("answer");
				if (answer == null || answer.trim().length() == 0) {
					errorMsgs.add("答案: 請勿空白");
				}

				Boolean status = null;
				try {
					status = new Boolean(req.getParameter("status").trim());
				} catch (NumberFormatException e) {
					status = true;
					errorMsgs.add("上下架狀態請勿空白.");
				}
				
				QAListVO qaListVO = new QAListVO();
				qaListVO.setQa_id(qa_id);
				qaListVO.setNo(no);
				qaListVO.setQuestion(question);
				qaListVO.setAnswer(answer);
				qaListVO.setStatus(status);

				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("qaListVO", qaListVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/qaList/updateQA.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				QAListService qaListService = new QAListService();
				qaListVO = qaListService.updateQA(qa_id, no, question, answer, status);
				
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("qaListVO", qaListVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/qaList/listAllQA.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/qaList/updateQA.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer qa_id = new Integer(req.getParameter("qa_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				QAListService qaListService = new QAListService();
				qaListService.deleteRoom(qa_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/qaList/listAllQA.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/qaList/listAllQA.jsp");
				failureView.forward(req, res);
			}
		}
	}
	

}
