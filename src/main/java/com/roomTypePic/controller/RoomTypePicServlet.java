package com.roomTypePic.controller;

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

import com.roomType.model.RoomTypeService;
import com.roomType.model.RoomTypeVO;
import com.roomTypePic.model.RoomTypePicService;
import com.roomTypePic.model.RoomTypePicVO;

@WebServlet("/RoomTypePicServlet")
@MultipartConfig(fileSizeThreshold = 1024 *1024 , maxFileSize = 5*1024*1024, maxRequestSize = 5*100*1024*1024)
public class RoomTypePicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer room_photo_id = new Integer(req.getParameter("room_photo_id"));
				Integer room_type_id = new Integer(req.getParameter("room_type_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				
				RoomTypePicService roomTypePicService =new RoomTypePicService();
				roomTypePicService.deleteRoomType(room_photo_id);
				RoomTypePicVO roomTypePicVO = new RoomTypePicVO();
				roomTypePicVO.setRoom_type_id(room_type_id);
				
				RoomTypeVO roomTypeVO = new RoomTypeVO();
				roomTypeVO.setRoom_type_id(room_type_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("roomTypePicVO",roomTypePicVO);
				req.setAttribute("roomTypeVO",roomTypeVO);
				String url = "/back-end/roomPic/listAllRoomPic.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/roomPic/listAllRoomPic.jsp");
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
					errorMsgs.add("價格請填數字.");
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
				
				
				RoomTypePicVO roomTypePicVO =new RoomTypePicVO();
				roomTypePicVO.setRoom_type_id(room_type_id);
				roomTypePicVO.setRoom_pic(room_pic);
				
				RoomTypeVO roomTypeVO = new RoomTypeVO();
				roomTypeVO.setRoom_type_id(room_type_id);
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("roomTypePicVO", roomTypePicVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/roomPic/addRoomPic.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/

				
				
				RoomTypePicService roomTypePicService = new RoomTypePicService();
				roomTypePicVO = roomTypePicService.addRoomTypePic(room_type_id, room_pic);
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("roomTypePicVO",roomTypePicVO);
				req.setAttribute("roomTypeVO",roomTypeVO);
				String url = "/back-end/roomPic/listAllRoomPic.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/roomPic/addRoomPic.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Insert".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer room_type_id = new Integer(req.getParameter("room_type_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				RoomTypePicService roomTypePicService = new RoomTypePicService();
				RoomTypePicVO roomTypePicVO = new RoomTypePicVO();
				roomTypePicVO.setRoom_type_id(room_type_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("roomTypePicVO",roomTypePicVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/roomPic/addRoomPic.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/roomPic/listAllRoomPic.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("Update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer room_photo_id = new Integer(req.getParameter("room_photo_id").trim());
				
				
				//取房型原始值
				Integer room_type_id = 0;
				
				try {
					RoomTypePicService roomTypePicService = new RoomTypePicService();
					RoomTypePicVO roomTypePicVO = roomTypePicService.getOneRoomType(room_photo_id);
					
					room_type_id = roomTypePicVO.getRoom_type_id();
				} catch (Exception e) {
					errorMsgs.add("房型號碼錯誤");
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
					e.printStackTrace();
					System.out.println(room_photo_id);
					System.out.println(room_type_id);
					System.out.println(room_pic);
				}
				
				
				RoomTypePicVO roomTypePicVO = new  RoomTypePicVO();
				roomTypePicVO.setRoom_photo_id(room_photo_id);
				roomTypePicVO.setRoom_type_id(room_type_id);
				roomTypePicVO.setRoom_pic(room_pic);
				

				RoomTypeVO roomTypeVO = new RoomTypeVO();
				roomTypeVO.setRoom_type_id(room_type_id);
				
				System.out.println(roomTypeVO.getRoom_type_id());
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("roomTypePicVO", roomTypePicVO); // 含有輸入格式錯誤的empVO物件,也存入req
					req.setAttribute("roomTypeVO",roomTypeVO); // 資料庫update成功後,正確的的empVO物件,存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/roomPic/listAllRoomPic.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/
				RoomTypePicService roomTypePicService = new RoomTypePicService();
				roomTypePicVO =roomTypePicService.updateRoomTypePic(room_photo_id, room_type_id, room_pic);
				
				
				
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("roomTypeVO",roomTypeVO); // 資料庫update成功後,正確的的empVO物件,存入req
				req.setAttribute("roomTypePicVO",roomTypePicVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/roomPic/listAllRoomPic.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/roomPic/listAllRoomPic.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
