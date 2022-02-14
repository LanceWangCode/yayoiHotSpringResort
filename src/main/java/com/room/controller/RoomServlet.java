package com.room.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.room.model.RoomService;
import com.room.model.RoomVO;

@WebServlet("/RoomServlet")
public class RoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer roomid = new Integer(req.getParameter("room_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				RoomService roomService = new RoomService();
				RoomVO roomVO = roomService.getOneRoom(roomid);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("roomVO", roomVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/room/updateRoom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/listAllRoom.jsp");
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
				Integer roomid = new Integer(req.getParameter("room_id").trim());

				Integer room_type_id = null;
				try {
					room_type_id = new Integer(req.getParameter("room_type_id").trim());
				} catch (NumberFormatException e) {
					room_type_id = 0;
					errorMsgs.add("房型請填數字.");
				}

				Integer qtyofbeds = null;
				try {
					qtyofbeds = new Integer(req.getParameter("qtyofbeds").trim());
				} catch (NumberFormatException e) {
					qtyofbeds = 0;
					errorMsgs.add("床位請填數字.");
				}

				String room_guest_name = req.getParameter("room_guest_name");
				if (room_guest_name == null || room_guest_name.trim().length() == 0) {
					errorMsgs.add("房客姓名: 請勿空白");
				}

				Boolean room_sale_status = null;
				try {
					room_sale_status = new Boolean(req.getParameter("room_sale_status").trim());
				} catch (NumberFormatException e) {
					room_sale_status = true;
					errorMsgs.add("上下架狀態請勿空白.");
				}

				Integer room_status = null;
				try {
					room_status = new Integer(req.getParameter("room_status").trim());
				} catch (NumberFormatException e) {
					room_status = 1;
					errorMsgs.add("房間狀態請勿空白.");
				}

				RoomVO roomVO = new RoomVO();
				roomVO.setRoom_id(roomid);
				;
				roomVO.setRoom_type_id(room_type_id);
				roomVO.setQtyofbeds(qtyofbeds);
				roomVO.setRoom_guest_name(room_guest_name);
				roomVO.setRoom_sale_status(room_sale_status);
				roomVO.setRoom_status(room_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("roomVO", roomVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/listAllRoom.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				RoomService roomService = new RoomService();
				roomVO = roomService.updateRoom(roomid, room_type_id, qtyofbeds, room_guest_name, room_sale_status,
						room_status);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("roomVO", roomVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/room/listAllRoom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/updateRoom.jsp");
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
				Integer roomid = new Integer(req.getParameter("room_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				RoomService roomService = new RoomService();
				roomService.deleteRoom(roomid);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/room/listAllRoom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/listAllRoom.jsp");
				failureView.forward(req, res);
			}
		}

if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Integer room_type_id = null;
				try {
					room_type_id = new Integer(req.getParameter("room_type_id").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("房型號碼請填數字.");
				}
				
				Integer qtyofbeds = null;
				try {
					qtyofbeds = new Integer(req.getParameter("qtyofbeds").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("床位請填數字.");
				}

				String room_guest_name = " ".trim();
				

				Boolean room_sale_status = null;
				try {
					room_sale_status = new Boolean(req.getParameter("room_sale_status"));
				} catch (NumberFormatException e) {
					room_sale_status = true;
					errorMsgs.add("上下架狀態請勿空白.");
				}

				Integer room_status = null;
				try {
					room_status = new Integer(req.getParameter("room_status").trim());
				} catch (NumberFormatException e) {
					room_status = 1;
					errorMsgs.add("房間狀態請填數字.");
				}
				
				RoomVO roomVO = new RoomVO();
				roomVO.setRoom_type_id(room_type_id);
				roomVO.setQtyofbeds(qtyofbeds);
				roomVO.setRoom_guest_name(room_guest_name);
				roomVO.setRoom_sale_status(room_sale_status);
				roomVO.setRoom_status(room_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("roomVO", roomVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/addRoom.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				RoomService roomService = new RoomService();
				roomVO = roomService.addRoom(room_type_id, qtyofbeds, room_guest_name, room_sale_status, room_status);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/room/listAllRoom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/addRoom.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
