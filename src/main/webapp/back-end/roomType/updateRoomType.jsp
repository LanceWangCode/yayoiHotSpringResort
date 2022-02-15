<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.roomTypePic.model.*"%>
<%@ page import="com.roomType.model.*"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>

<%
RoomTypeVO roomTypeVO = (RoomTypeVO) request.getAttribute("roomTypeVO");
//EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<% 
RoomTypePicService roomTypePicService = new RoomTypePicService();
List<RoomTypePicVO> list = roomTypePicService.getAll();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>房型資料修改</title>
<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>
</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>房間資料修改</h3>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" enctype="multipart/form-data" ACTION="roomType.do" name="form1">
		<table>
			<tr>
				<td>房型編號:<font color=red><b>*</b></font></td>
				<td><%=roomTypeVO.getRoom_type_id()%></td>
			</tr>
			<tr>
				<td>房型名稱:</td>
				<td><input type="TEXT" name="room_type_name" size="45"
					value="<%=roomTypeVO.getRoom_type_name()%>" /></td>
			</tr>
			<tr>
				<td>房型簡介:</td>
				<td><input type="TEXT" name="room_type_content" size="45"
					value="<%=roomTypeVO.getRoom_type_content()%>" /></td>
			</tr>
			<tr>
				<td>房型價格:</td>
				<td><input type="TEXT" name="room_type_price" size="45"
					value="<%=roomTypeVO.getRoom_type_price()%>" /></td>
			</tr>
			<tr>
				<td>房型上下架狀態:</td>
				<td><input type="TEXT" name="room_type_sale_status" size="45"
					value="<%=roomTypeVO.getRoom_type_sale_status()%>" /></td>
			</tr>
			<tr>
				<td>房型數量:</td>
				<td><input type="TEXT" name="room_type_amount" size="45"
					value="<%=roomTypeVO.getRoom_type_amount()%>" /></td>
			</tr>
			<tr>
				<td>房型圖片</td>
				<td><input type="file" name="room_type_pic" size="50" /></td>
			</tr>
			
<%-- 			<c:forEach var="roomTypePicVO" items="${list}"><tr> --%>
<!-- 				<td> -->
<%-- 				<img src="<%=request.getContextPath()%>/roomType/roomTypePic.do?room_type_id=${roomTypeVO.room_type_id}" --%>
<!-- 				 alt=""	width="100" height="100" > -->
<!-- 				</td> -->
<!-- 				</tr> -->
<%-- 			</c:forEach> --%>
			



		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="room_type_id"
			value="<%=roomTypeVO.getRoom_type_id()%>"> <input
			type="submit" value="送出修改">
	</FORM>
</body>
</html>