package com.roomOrderList.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.roomOrderList.model.RoomOrderListService;
import com.roomOrderList.model.RoomOrderListVO;
import com.roomSchedule.model.RoomScheduleVO;
import com.roomSchedule.model.RoomscheduleService;
import com.roomType.model.RoomTypeService;
import com.roomType.model.RoomTypeVO;

public class RoomOrderListServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
	
		req.setCharacterEncoding("UTF-8");
		String action =req.getParameter("action");
	
	
		if("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			try {
				
				String str = req.getParameter("room_order_list_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入住宿單明細編號");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/roomOrderList/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
			
				Integer room_order_list_id = null;
				try {
					room_order_list_id = new Integer(str);     //test----room_order_list_id = Integer.parseInt(str);

				} catch (Exception e) {				
					errorMsgs.add("請確認oderid格式");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/roomOrderList/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始查詢資料*****************************************/
			
				RoomOrderListService roomOrderListSvc = new RoomOrderListService();
				RoomOrderListVO roomOrderListVO = roomOrderListSvc.getOneRoom_order_list(room_order_list_id);
				if(roomOrderListVO == null) {
					errorMsgs.add("查無資料");					
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/roomOrderList/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("roomOrderListVO", roomOrderListVO);// 資料庫取出的empVO物件,存入req
				String url = "/back-end/roomOrderList/listOneRoomOrderList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*************************************/	
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/roomOrderList/select_page.jsp");
				failureView.forward(req, res);
			}		
		}
		
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer room_order_list_id = new Integer(req.getParameter("room_order_list_id"));
				/***************************2.開始查詢資料****************************************/
				RoomOrderListService roomOrderListSvc = new RoomOrderListService();
				RoomOrderListVO roomOrderListVO = roomOrderListSvc.getOneRoom_order_list(room_order_list_id);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("roomOrderListVO", roomOrderListVO);  // 資料庫取出的VO物件,存入req
				String url = "/back-end/roomOrderList/update_roomOrderList_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); //成功轉交 input.jsp
			    successView.forward(req, res);
			   				
			} catch (Exception e) {
                errorMsgs.add("無法取得要修改的資料"+ e.getMessage());
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/roomOrderList/listAllRoomOrderList.jsp");
                failureView.forward(req, res);
			}			
		}
		
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			
			try {		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				Integer room_order_list_id = new Integer(req.getParameter("room_order_list_id").trim());
				Integer room_id =new Integer( req.getParameter("room_id").trim());
				Integer room_order_id= new Integer(req.getParameter("room_order_id").trim());
				Integer number_of_people= new Integer(req.getParameter("number_of_people").trim());
				
				java.sql.Date arrival_date = null;
				try {
					arrival_date = java.sql.Date.valueOf(req.getParameter("arrival_date").trim());
				} catch (Exception e) {
					arrival_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期格式1");
				}
				
				java.sql.Date departure_date = null;
				try {
					departure_date = java.sql.Date.valueOf(req.getParameter("departure_date").trim());
				} catch (Exception e) {
					departure_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期格式2");
				}
				
				String special_req = req.getParameter("special_req").trim();
				if( special_req  == null || special_req.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}
				
				Integer room_price = new Integer(req.getParameter("room_price").trim());
				Integer service_order_id = new Integer(req.getParameter("service_order_id").trim());
				Integer room_type_id = new Integer(req.getParameter("room_type_id").trim());
				
				
				RoomOrderListVO roomOrderListVO = new RoomOrderListVO();
				roomOrderListVO.setRoom_order_list_id(room_order_list_id);
				roomOrderListVO.setRoom_id(room_id);
				roomOrderListVO.setRoom_order_id(room_order_id);
				roomOrderListVO.setNumber_of_people(number_of_people);
				roomOrderListVO.setArrival_date(arrival_date);
				roomOrderListVO.setDeparture_date(departure_date);
				roomOrderListVO.setSpecial_req(special_req);
				roomOrderListVO.setRoom_price(room_price);
				roomOrderListVO.setService_order_id(service_order_id);
				roomOrderListVO.setRoom_type_id(room_type_id);
				
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("roomOrderListVO", roomOrderListVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/roomOrderList/update_roomOrderList_input.jsp");
					failureView.forward(req, res);
					return;
				}
			
				/***************************2.開始修改資料*****************************************/
				RoomOrderListService roomOrderListSvc = new RoomOrderListService();
				roomOrderListVO = roomOrderListSvc.updateRoom_order_list(room_order_list_id, room_id, room_order_id, number_of_people, arrival_date, departure_date, special_req,room_price,service_order_id,room_type_id);
			
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("roomOrderListVO", roomOrderListVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/roomOrderList/listOneRoomOrderList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			
				/***************************其他可能的錯誤處理*************************************/				
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/roomOrderList/update_roomOrderList_input.jsp");
				failureView.forward(req, res);
			}			
		}
		
		if ("insert".equals(action)) {// 來自addEmp.jsp的請求  

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer room_id =new Integer( req.getParameter("room_id").trim());
				Integer room_order_id= new Integer(req.getParameter("room_order_id").trim());
				Integer number_of_people= new Integer(req.getParameter("number_of_people").trim());
				
				java.sql.Date arrival_date = null;
				try {
					arrival_date = java.sql.Date.valueOf(req.getParameter("arrival_date").trim());
					
				} catch (Exception e) {
					arrival_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期格式11");
				}
				
				java.sql.Date departure_date = null;
				try {
					departure_date = java.sql.Date.valueOf(req.getParameter("departure_date").trim());
		
				} catch (Exception e) {
					departure_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期格式22");
				}
				
				String special_req = req.getParameter("special_req").trim();
				if( special_req  == null || special_req.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}
				Integer room_price = new Integer(req.getParameter("room_price").trim());
				Integer service_order_id = new Integer(req.getParameter("service_order_id").trim());
				Integer room_type_id = new Integer(req.getParameter("room_type_id").trim());
				
				RoomOrderListVO roomOrderListVO = new RoomOrderListVO();
				roomOrderListVO.setRoom_id(room_id);
				roomOrderListVO.setRoom_order_id(room_order_id);
				roomOrderListVO.setNumber_of_people(number_of_people);
				roomOrderListVO.setArrival_date(arrival_date);
				roomOrderListVO.setDeparture_date(departure_date);
				roomOrderListVO.setSpecial_req(special_req);
				roomOrderListVO.setRoom_price(room_price);
				roomOrderListVO.setService_order_id(service_order_id);
				roomOrderListVO.setRoom_type_id(room_type_id);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("roomOrderListVO", roomOrderListVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/roomOrderList/addRoomOrderList.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
				RoomOrderListService roomOrderListSvc = new RoomOrderListService();
				roomOrderListVO = roomOrderListSvc.addRoom_order_list(room_id, room_order_id, number_of_people, arrival_date, departure_date, special_req,room_price,service_order_id,room_type_id);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/roomOrderList/listAllRoomOrderList.jsp";
			
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
			
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/roomOrderList/addRoomOrderList.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("delete".equals(action)) { // 來自listAllEmp.jsp
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數***************************************/
				Integer room_order_list_id = new Integer(req.getParameter("room_order_list_id"));
				
				/***************************2.開始刪除資料***************************************/
				RoomOrderListService roomOrderListSvc = new RoomOrderListService();
				roomOrderListSvc.deleteRoom_order_list(room_order_list_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/	
				String url = "/back-end/roomOrderList/listAllRoomOrderList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/				
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/roomOrderList/listAllRoomOrderList.jsp");
				failureView.forward(req, res);
			}		
		}
		if ("SaveDateAndShowCheckOut".equals(action)) {

		
			java.sql.Date arrival_date = null;

			arrival_date = java.sql.Date.valueOf(req.getParameter("arrival_date").trim());
			
			System.out.println(arrival_date);

			java.sql.Date departure_date = null;

			departure_date = java.sql.Date.valueOf(req.getParameter("departure_date").trim());

			RoomOrderListVO roomOrderListVO = new RoomOrderListVO();
			roomOrderListVO.setArrival_date(arrival_date);
			roomOrderListVO.setDeparture_date(departure_date);

			req.setAttribute("roomOrderListVO", roomOrderListVO);

			String url = "/front-end/roomOder/checkout.jsp";
	
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
		if ("SaveOder".equals(action)) {

		
			java.sql.Date arrival_date = null;

			arrival_date = java.sql.Date.valueOf(req.getParameter("arrival_date").trim());

			java.sql.Date departure_date = null;

			departure_date = java.sql.Date.valueOf(req.getParameter("departure_date").trim());
			//計算要住的晚上時間
			long stayDate = (departure_date.getTime() - arrival_date.getTime())/(24 * 60 * 60 * 1000L);
			
			
			
			//save 訂房資訊for Roomschedule for 如果當天日期這個房型都還沒有被訂過
			Integer room_type_id = new Integer(req.getParameter("room_type_id").trim()); // from Jsp 頁面
			// from :ROOM_TYPE 裡面的 ROOM_TYPE_AMOUNT房型數量
			RoomTypeService roomTypeSvc = new RoomTypeService();                         
			RoomTypeVO roomTypeVO = roomTypeSvc.getOneRoomType(room_type_id);
			Integer room_amount = roomTypeVO.getRoom_type_amount();
			
			Integer room_rsv_booked = 1;//
			java.sql.Date room_schedule_date = arrival_date;
			
			RoomScheduleVO roomScheduleVO = new RoomScheduleVO();
			roomScheduleVO.setRoom_type_id(room_type_id);
			roomScheduleVO.setRoom_schedule_date(room_schedule_date);
			roomScheduleVO.setRoom_amount(room_amount);
			roomScheduleVO.setRoom_rsv_booked(room_rsv_booked);
			RoomscheduleService roomscheduleSvc =new  RoomscheduleService();
			roomscheduleSvc.addRoomSchedule(room_type_id, room_schedule_date, room_amount, room_rsv_booked);
			String url = "/front-end/roomOrder/checkoutOK.jsp";
			RequestDispatcher sucessView = req.getRequestDispatcher(url);
			sucessView.forward(req, res);

//			RoomOrderListVO roomOrderListVO = new RoomOrderListVO();
//			roomOrderListVO.setArrival_date(arrival_date);
//			roomOrderListVO.setDeparture_date(departure_date);
//
//			req.setAttribute("roomOrderListVO", roomOrderListVO);
//
//			String url = "/front-end/checkout.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);

		}
	}
	
}
