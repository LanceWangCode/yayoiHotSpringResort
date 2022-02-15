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
		
		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				Integer no = null;
				try {
					no = new Integer(req.getParameter("no").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("���ǽж�Ʀr.");
				}
				
				String question = req.getParameter("question").trim();
				if (question == null || question.trim().length() == 0) {
					errorMsgs.add("���D�ФŪť�");
				}
				
				String answer = req.getParameter("answer").trim();
				if (answer == null || answer.trim().length() == 0) {
					errorMsgs.add("���нФŪť�");
				}

				Boolean status = null;
				try {
					status = new Boolean(req.getParameter("status"));
				} catch (NumberFormatException e) {
					status = true;
					errorMsgs.add("�W�U�[���A�ФŪť�.");
				}
				
				QAListVO qaListVO = new QAListVO();
				qaListVO.setNo(no);
				qaListVO.setQuestion(question);
				qaListVO.setAnswer(answer);
				qaListVO.setStatus(status);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("qaListVO", qaListVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/qaList/addQA.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				QAListService qaListService = new QAListService();
				qaListVO = qaListService.addQA(no, question, answer, status);
				

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/back-end/qaList/listAllQA.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/qaList/addQA.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer qa_id = new Integer(req.getParameter("qa_id"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				QAListService qaListService = new QAListService();
				QAListVO qaListVO = qaListService.getOneRoom(qa_id);
				
				

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("qaListVO", qaListVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/qaList/updateQA.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/qaList/listAllQA.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				Integer qa_id = new Integer(req.getParameter("qa_id").trim());

				Integer no = null;
				try {
					no = new Integer(req.getParameter("no").trim());
				} catch (NumberFormatException e) {
					no = 0;
					errorMsgs.add("��ܶ��ǽж�Ʀr.");
				}

				String question = req.getParameter("question");
				if (question == null || question.trim().length() == 0) {
					errorMsgs.add("���D: �ФŪť�");
				}
				String answer = req.getParameter("answer");
				if (answer == null || answer.trim().length() == 0) {
					errorMsgs.add("����: �ФŪť�");
				}

				Boolean status = null;
				try {
					status = new Boolean(req.getParameter("status").trim());
				} catch (NumberFormatException e) {
					status = true;
					errorMsgs.add("�W�U�[���A�ФŪť�.");
				}
				
				QAListVO qaListVO = new QAListVO();
				qaListVO.setQa_id(qa_id);
				qaListVO.setNo(no);
				qaListVO.setQuestion(question);
				qaListVO.setAnswer(answer);
				qaListVO.setStatus(status);

				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("qaListVO", qaListVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/qaList/updateQA.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				QAListService qaListService = new QAListService();
				qaListVO = qaListService.updateQA(qa_id, no, question, answer, status);
				
				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("qaListVO", qaListVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/back-end/qaList/listAllQA.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/qaList/updateQA.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				Integer qa_id = new Integer(req.getParameter("qa_id"));

				/*************************** 2.�}�l�R����� ***************************************/
				QAListService qaListService = new QAListService();
				qaListService.deleteRoom(qa_id);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/back-end/qaList/listAllQA.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/qaList/listAllQA.jsp");
				failureView.forward(req, res);
			}
		}
	}
	

}
