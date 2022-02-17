package com.roomSchedule.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.roomSchedule.model.RoomScheduleVO;
import com.roomSchedule.model.RoomscheduleService;

/**
 * Servlet implementation class RoomScheduleServlet
 */
@WebServlet("/roomSchedule/roomSchedule.do")
public class RoomScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		
		if("getOne_For_display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String str = request.getParameter("room_schedule_id");
				if(str==null) {
					errorMsgs.add("請輸入訂單編號");
				}
				
				if (!errorMsgs.isEmpty()){
					RequestDispatcher failureView = request.getRequestDispatcher("/");
					failureView.forward(request, response);
					return;
				}
				Integer room_schedule_id = null;
				try {
					room_schedule_id = new Integer(str);					
					
				} catch (Exception e) {
					errorMsgs.add("請輸入數字格式");
				}
				if (!errorMsgs.isEmpty()){
					RequestDispatcher failureView = request.getRequestDispatcher("/");
					failureView.forward(request, response);
					return;
				}
				
				RoomscheduleService roomscheduleSvc = new RoomscheduleService();
				RoomScheduleVO roomScheduleVO = roomscheduleSvc.getOneRoomschedule(room_schedule_id);
				
				if(roomScheduleVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()){
					RequestDispatcher failureView = request.getRequestDispatcher("/");
					failureView.forward(request, response);
					return;
				}
				
				request.setAttribute("roomScheduleVO", roomScheduleVO);
				String url = "/";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);		
				
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/");
				failureView.forward(request, response);
			}			
		}
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				Integer room_schedule_id = new Integer(request.getParameter("room_schedule_id"));
				
				RoomscheduleService roomscheduleSvc = new RoomscheduleService();
				RoomScheduleVO roomScheduleVO = roomscheduleSvc.getOneRoomschedule(room_schedule_id);
				
				request.setAttribute("rooomSchedule", roomScheduleVO);
				
				String url = "/";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);		
				
			} catch (Exception e) {
				RequestDispatcher failureView = request.getRequestDispatcher("/");
				failureView.forward(request, response);
			}
		}
		
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer room_schedule_id = new Integer(request.getParameter("room_schedule_id").trim());
				Integer room_type_id = new Integer(request.getParameter("room_type_id").trim());
				Integer room_amount = new Integer(request.getParameter("room_amount").trim());
				Integer room_rsv_booked = new Integer(request.getParameter("room_rsv_booked").trim());
				
				java.sql.Date room_schedule_date = null;
				try {
					room_schedule_date = java.sql.Date.valueOf(request.getParameter("room_schedule_date").trim());
				} catch (Exception e) {
					room_schedule_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期格式1");
				}
				
				RoomScheduleVO roomScheduleVO = new RoomScheduleVO();
				roomScheduleVO.setRoom_schedule_id(room_schedule_id);
				roomScheduleVO.setRoom_type_id(room_type_id);
				roomScheduleVO.setRoom_schedule_date(room_schedule_date);
				roomScheduleVO.setRoom_amount(room_amount);
				roomScheduleVO.setRoom_rsv_booked(room_rsv_booked);
				
				if(!errorMsgs.isEmpty()) {
					request.setAttribute("rroomScheduleVO", roomScheduleVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/");
					failureView.forward(request, response);
					return;
				}
				
				RoomscheduleService roomscheduleSvc =new  RoomscheduleService();
				roomscheduleSvc.updateRoomschedule(room_type_id, room_schedule_date, room_amount, room_rsv_booked, room_schedule_id);
				request.setAttribute("roomScheduleVO", roomScheduleVO);
				String url = "/";
				RequestDispatcher sucessView = request.getRequestDispatcher(url);
				sucessView.forward(request, response);
				
				
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/");
				failureView.forward(request, response);
			}
		}
		if ("insert".equals(action)) {
			
			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer room_type_id = new Integer(request.getParameter("room_type_id").trim());
				Integer room_amount = new Integer(request.getParameter("room_amount").trim());
				Integer room_rsv_booked = new Integer(request.getParameter("room_rsv_booked").trim());
				
				java.sql.Date room_schedule_date = null;
				try {
					room_schedule_date = java.sql.Date.valueOf(request.getParameter("room_schedule_date").trim());
				} catch (Exception e) {
					room_schedule_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期格式1");
				}
				
				RoomScheduleVO roomScheduleVO = new RoomScheduleVO();
				roomScheduleVO.setRoom_type_id(room_type_id);
				roomScheduleVO.setRoom_schedule_date(room_schedule_date);
				roomScheduleVO.setRoom_amount(room_amount);
				roomScheduleVO.setRoom_rsv_booked(room_rsv_booked);
				
				if(!errorMsgs.isEmpty()) {
					request.setAttribute("roomScheduleVOO", roomScheduleVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/roomScheduleaddRoomSchedule.jsp");
					failureView.forward(request, response);
					return;
				}
				
				RoomscheduleService roomscheduleSvc =new  RoomscheduleService();
				roomscheduleSvc.addRoomSchedule(room_type_id, room_schedule_date, room_amount, room_rsv_booked);
				String url = "/back-end/roomSchedule/showAllRoomSchedule.jsp";
				RequestDispatcher sucessView = request.getRequestDispatcher(url);
				sucessView.forward(request, response);
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/roomScheduleaddRoomSchedule.jsp");
				failureView.forward(request, response);
			}
		
		}
		

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer room_schedule_id = new Integer(request.getParameter("room_schedule_id").trim());

				RoomscheduleService roomscheduleSvc =new  RoomscheduleService();
				roomscheduleSvc.deleteRoomschedule(room_schedule_id);
				
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
		
	
		
	}

	
}
