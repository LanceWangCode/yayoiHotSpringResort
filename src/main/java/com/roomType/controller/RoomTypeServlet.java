package com.roomType.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mysql.cj.jdbc.Blob;
import com.room.model.RoomService;
import com.room.model.RoomVO;
import com.roomType.model.*;
import com.roomTypePic.model.RoomTypePicService;
import com.roomTypePic.model.RoomTypePicVO;


@WebServlet("/RoomTypeServlet")
@MultipartConfig(fileSizeThreshold = 1024 *1024 , maxFileSize = 5*1024*1024, maxRequestSize = 5*100*1024*1024)
public class RoomTypeServlet extends HttpServlet {
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
				Integer room_type_id = new Integer(req.getParameter("room_type_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				RoomTypeService roomTypeService = new RoomTypeService();
				RoomTypeVO roomTypeVO = roomTypeService.getOneRoomType(room_type_id);

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
				Integer room_total_person =0;
				Integer room_total_score =0;
				
				try {
					RoomTypeService roomTypeService = new RoomTypeService();
					RoomTypeVO roomTypeVO = roomTypeService.getOneRoomType(room_type_id);
					
					room_total_person = roomTypeVO.getRoom_total_person();
					room_total_score = roomTypeVO.getRoom_total_score();
				} catch (Exception e) {
					e.printStackTrace();
				}
				// ========================================
				
				//修改照片
				InputStream is = null;
				byte[] room_pic = null;
				Part filePart = null ;
				try {
					filePart = req.getPart("room_type_pic");
					if (filePart != null) {
						is = filePart.getInputStream();
						room_pic = new byte[is.available()];
						is.read(room_pic);
						is.close();
					}
				} catch (Exception e) {
					
				}
				

				RoomTypeVO roomTypeVO = new RoomTypeVO();
				roomTypeVO.setRoom_type_id(room_type_id);
				roomTypeVO.setRoom_type_name(room_type_name);
				roomTypeVO.setRoom_type_content(room_type_content);
				roomTypeVO.setRoom_type_price(room_type_price);
				roomTypeVO.setRoom_type_sale_status(room_type_sale_status);
				roomTypeVO.setRoom_type_amount(room_type_amount);
				
				roomTypeVO.setRoom_total_person(room_total_person);
				roomTypeVO.setRoom_total_score(room_total_score);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("roomTypeVO", roomTypeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/roomtype/updateRoomType.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				RoomTypeService roomTypeService = new RoomTypeService();
				roomTypeVO = roomTypeService.updateRoomType(room_type_id, room_type_name, room_type_amount, room_type_content, room_type_sale_status, room_total_person, room_total_score, room_type_price);
				
				RoomTypePicVO roomTypePicVO =new RoomTypePicVO();
				roomTypePicVO.setRoom_type_id(room_type_id);
				roomTypePicVO.setRoom_pic(room_pic);
				
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("roomTypeVO",roomTypeVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/roomtype/listAllRoomType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/roomtype/updateRoomType.jsp");
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
				Integer room_type_id = new Integer(req.getParameter("room_type_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				RoomTypeService roomTypeService = new RoomTypeService();
				roomTypeService.deleteRoomType(room_type_id);
				
				RoomTypePicService roomTypePicService =new RoomTypePicService();
				roomTypePicService.deleteRoomTypePicByRoomTypeId(room_type_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/roomtype/listAllRoomType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/roomtype/listAllRoomType.jsp");
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
					errorMsgs.add("價格請填數字.");
				}

				Boolean room_type_sale_status = null;
				try {
					room_type_sale_status = new Boolean(req.getParameter("room_type_sale_status"));
				} catch (NumberFormatException e) {
					room_type_sale_status = true;
					errorMsgs.add("上下架狀態請勿空白.");
				}

				Integer room_type_amount = null;
				try {
					room_type_amount = new Integer(req.getParameter("room_type_amount").trim());
				} catch (NumberFormatException e) {
					room_type_amount = 1;
					errorMsgs.add("房型數量請填數字.");
				}
				
				InputStream is = null;
				byte[] room_pic = null;
				Part filePart = req.getPart("room_type_pic");
				if (filePart != null) {
					is = filePart.getInputStream();
					room_pic = new byte[is.available()];
					is.read(room_pic);
					is.close();
				}
				
				
				
				
				RoomTypeVO roomTypeVO = new RoomTypeVO();
				roomTypeVO.setRoom_type_name(room_type_name);
				roomTypeVO.setRoom_type_content(room_type_content);
				roomTypeVO.setRoom_type_price(room_type_price);
				roomTypeVO.setRoom_type_sale_status(room_type_sale_status);
				roomTypeVO.setRoom_type_amount(room_type_amount);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("roomTypeVO", roomTypeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/roomtype/addRoomType.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				RoomTypeService roomTypeService  = new RoomTypeService();
				roomTypeVO = roomTypeService.addRoomType(room_type_name, room_type_amount, room_type_content, room_type_sale_status, 0, 0, room_type_price);
				//取得PK主鍵
				Integer roomtypeid =roomTypeVO.getRoom_type_id();
				
				RoomTypePicVO roomTypePicVO =new RoomTypePicVO();
				roomTypePicVO.setRoom_type_id(roomtypeid);
				roomTypePicVO.setRoom_pic(room_pic);
				
				
				RoomTypePicService roomTypePicService = new RoomTypePicService();
				roomTypePicVO = roomTypePicService.addRoomTypePic(roomtypeid, room_pic);
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/roomtype/listAllRoomType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/roomtype/addRoomType.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("toUpdatePIC".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer room_type_id = new Integer(req.getParameter("room_type_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				RoomTypeService roomTypeService = new RoomTypeService();
				RoomTypeVO roomTypeVO = roomTypeService.getOneRoomType(room_type_id);
				System.out.println(roomTypeVO.getRoom_type_id());

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("roomTypeVO",roomTypeVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/roomPic/listAllRoomPic.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/roomtype/listAllRoomType.jsp");
				failureView.forward(req, res);
			}
		}
		
	}

}
