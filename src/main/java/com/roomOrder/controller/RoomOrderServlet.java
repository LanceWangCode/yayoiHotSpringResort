package com.roomOrder.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.roomOrder.model.RoomOrderService;
import com.roomOrder.model.RoomOrderVO;

@WebServlet("/RoomOrderServlet")
public class RoomOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		
		if("getOne_For_Dispaly".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();			
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String str = request.getParameter("room_order_id");
				if(str == null || (str.trim().length() == 0)) {
					errorMsgs.add("請輸入住宿訂單編號");
				}
								
				if (!errorMsgs.isEmpty()){
					RequestDispatcher failureView = request.getRequestDispatcher("/");
					failureView.forward(request, response);
					return;
				}
				
				Integer room_order_id =null;
				try {
					room_order_id = new Integer(str);
					
				} catch (Exception e) {
					errorMsgs.add("請輸入數字格式");
				}
				if (!errorMsgs.isEmpty()){
					RequestDispatcher failureView = request.getRequestDispatcher("select_page.jsp");
					failureView.forward(request, response);
					return;
				}
				RoomOrderService RoomOrderSvc = new RoomOrderService();
				RoomOrderVO roomOrderVO = RoomOrderSvc.getOneRoomOrder(room_order_id);
				
				if (roomOrderVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()){
					RequestDispatcher failureView = request.getRequestDispatcher("/");
					failureView.forward(request, response);
					return;
				}
				
				request.setAttribute("roomOrderVO", roomOrderVO);
				String url = "/";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);				
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/");
				failureView.forward(request, response);
			}
			
		}

		if ("getOne_For_Dispaly".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer room_order_id = new Integer(request.getParameter("room_order_id"));
				
				RoomOrderService roomOrderService = new RoomOrderService();
				RoomOrderVO roomOrderVO =roomOrderService.getOneRoomOrder(room_order_id);
				
				request.setAttribute("roomOederVO", roomOrderVO);
				String url = "/";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);	

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料"+ e.getMessage());
                RequestDispatcher failureView = request.getRequestDispatcher("/");
            	failureView.forward(request, response);
			}

		}
		
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer room_order_id = new Integer(request.getParameter("room_order_id").trim());
				Integer mem_id =new Integer(request.getParameter("mem_id").trim());
				
				java.sql.Date order_date = null;
				try {
					order_date = java.sql.Date.valueOf(request.getParameter("order_date").trim());
				} catch (Exception e) {
					order_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期格式1");
				}
				System.out.println(order_date);
				
				Integer room_order_status =new Integer(request.getParameter("room_order_status").trim());
				Integer room_charge =new Integer(request.getParameter("room_charge").trim());
				Integer room_review =new Integer(request.getParameter("room_review").trim());
				
				
				RoomOrderVO roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoom_order_id(room_order_id);
				roomOrderVO.setMem_id(mem_id);
				roomOrderVO.setOrder_date(order_date);
				roomOrderVO.setRoom_order_status(room_order_status);
				roomOrderVO.setRoom_charge(room_charge);
				roomOrderVO.setRoom_review(room_review);
				
				if(!errorMsgs.isEmpty()) {
					request.setAttribute("roomOrderVO", roomOrderVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/roomOrder/updateOrder.jsp");
					failureView.forward(request, response);
					return;
				}
				
				RoomOrderService roomOrderSvc = new RoomOrderService();
				roomOrderVO = roomOrderSvc.updateRoomOder(room_order_id, mem_id, order_date, room_order_status, room_charge, room_review);
				
				request.setAttribute("roomOrderVO", roomOrderVO);
				String url = "/back-end/roomOrder/listAllOrder.jsp";
				RequestDispatcher sucessView = request.getRequestDispatcher(url);
				sucessView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/roomOrder/updateOrder.jsp");
				failureView.forward(request, response);
			} 
						
		}
		
		if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				Integer mem_id =new Integer(request.getParameter("mem_id").trim());
				
				java.sql.Date order_date = null;
				try {
					order_date = java.sql.Date.valueOf(request.getParameter("order_date").trim());
				} catch (Exception e) {
					order_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期格式1");
				}
				
				Integer room_order_status =new Integer(request.getParameter("room_order_status").trim());
				Integer room_charge =new Integer(request.getParameter("room_charge").trim());
				Integer room_review =new Integer(request.getParameter("room_review").trim());
				
				RoomOrderVO roomOrderVO = new RoomOrderVO();
			
				roomOrderVO.setMem_id(mem_id);
				roomOrderVO.setOrder_date(order_date);
				roomOrderVO.setRoom_order_status(room_order_status);
				roomOrderVO.setRoom_charge(room_charge);
				roomOrderVO.setRoom_review(room_review);
				
				if(!errorMsgs.isEmpty()) {
					request.setAttribute("roomOrderVO", roomOrderVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/");
					failureView.forward(request, response);
					return;
				}
				
				RoomOrderService roomOrderSvc = new RoomOrderService();
				roomOrderVO = roomOrderSvc.addRoomOrder(mem_id, order_date, room_order_status, room_charge, room_review);
				
				String url = "/";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(request, response);
				
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/");
				failureView.forward(request, response);
			}
					
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer room_order_id = new Integer(request.getParameter("room_order_id").trim());
				
				RoomOrderService roomOrderSvc = new RoomOrderService();
				roomOrderSvc.deleteRoomOrder(room_order_id);
				
				String url = "/";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(request, response);
				
			} catch (Exception e) {
				
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/");
				failureView.forward(request, response);
				// TODO: handle exception
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer room_order_id = new Integer(request.getParameter("room_order_id"));
				/*************************** 2.開始查詢資料 ****************************************/
				RoomOrderService roomOrderService = new RoomOrderService();
				RoomOrderVO roomOrderVO = roomOrderService.getOneRoomOrder(room_order_id);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				request.setAttribute("roomOrderVO",roomOrderVO); // 資料庫取出的empVO物件,存入req
				System.out.println( "有道這唷");
				String url = "/back-end/roomOrder/updateOrder.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/roomOrder/listAllOrder.jsp");
				failureView.forward(request, response);
			}
		}
		
		
		
		
		
	}

}
