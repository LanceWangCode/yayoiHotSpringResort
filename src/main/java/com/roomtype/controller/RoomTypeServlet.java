package com.roomtype.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.roomtype.model.RoomTypeService;
import com.roomtype.model.RoomTypeVO;

@WebServlet("/RoomTypeServlet")
public class RoomTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求aaaaaaaaa 我是謝銘修

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer room_type_id = new Integer(req.getParameter("room_type_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				RoomTypeService roomTypeService = new RoomTypeService();
				RoomTypeVO roomTypeVO = roomTypeService.getOneRoom(room_type_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("roomTypeVO",roomTypeVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/roomtype/updateRoomType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/roomtype/listAllRoomType.jsp");
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
				Integer room_type_id = new Integer(req.getParameter("room_type_id").trim());
				
				String room_type_name = req.getParameter("room_type_name");
				if (room_type_name == null || room_type_name.trim().length() == 0) {
					errorMsgs.add("房型名稱: 請勿空白");
				}
				
				String room_type_content = req.getParameter("room_type_content");
				if (room_type_content == null || room_type_content.trim().length() == 0) {
					errorMsgs.add("房型簡介: 請勿空白");
				}
				
				Integer room_type_price = null;
				try {
					room_type_price = new Integer(req.getParameter("room_type_price").trim());
				} catch (NumberFormatException e) {
					room_type_price = 0;
					errorMsgs.add("房型價格請填數字.");
				}
				
				Boolean room_type_sale_status = null;
				try {
					room_type_sale_status = new Boolean(req.getParameter("room_type_sale_status").trim());
				} catch (NumberFormatException e) {
					room_type_sale_status = true;
					errorMsgs.add("上下架狀態請勿空白.");
				}

				Integer room_type_amount = null;
				try {
					room_type_amount = new Integer(req.getParameter("room_type_amount").trim());
				} catch (NumberFormatException e) {
					room_type_amount = 0;
					errorMsgs.add("房型數量請填數字.");
				}
				
				//取評價原始值
				RoomTypeVO roomTypeVO =null;
				Integer room_total_person=null;
				Integer room_total_score=null;
				try {
					RoomTypeService roomTypeService = new RoomTypeService();
					roomTypeVO = roomTypeService.getOneRoom(room_type_id);
					
					room_total_person = new Integer(req.getParameter("room_total_person").trim());
					room_total_score = new Integer(req.getParameter("room_total_score").trim());
					
					System.out.println(room_total_person);
				} catch (Exception e) {
					e.printStackTrace();
				}

				

				roomTypeVO = new RoomTypeVO();
				roomTypeVO.setRoom_type_id(room_type_id);
				roomTypeVO.setRoom_type_name(room_type_name);
				roomTypeVO.setRoom_type_content(room_type_content);
				roomTypeVO.setRoom_type_price(room_type_price);
				roomTypeVO.setRoom_type_sale_status(room_type_sale_status);
				roomTypeVO.setRoom_type_amount(room_type_amount);
				
				roomTypeVO.setRoom_total_person(room_total_person);
				roomTypeVO.setRoom_total_score(room_total_score);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("RoomTypeVO", roomTypeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/roomtype/listAllRoomType.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				RoomTypeService roomTypeService = new RoomTypeService();
				roomTypeVO = roomTypeService.updateRoomType(room_type_id, room_type_name, room_type_amount, room_type_content, room_type_sale_status, room_total_person, room_total_score, room_type_price);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("roomTypeVO",roomTypeVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/roomtype/listAllRoomType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/updateRoom.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
