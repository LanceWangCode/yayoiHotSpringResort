<%@page import="com.room.model.RoomVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.roomType.model.*"%>
<%@ page import="com.roomTypePic.model.*"%>

<%
RoomTypePicVO roomTypePicVO = (RoomTypePicVO) request.getAttribute("roomTypePicVO");
%>

<!-- 等另一遍DAO出來再用 -->
<%-- <%= empVO == null %>--${empVo.deptno}--  --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>照片新增</title>
<%@ include file= "/back-end/framework/include.file" %>

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
<%@ include file= "/back-end/framework/header.file" %>
<div class="container">            

	<table id="table-1">
		<tr>
			<td>
				<h3>照片新增 </h3>
			</td>
		</tr>
	</table>

	<h3>照片新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" enctype="multipart/form-data" ACTION="<%=request.getContextPath()%>/roomType/roomTypePicServlet.do" name="form1">
		<table>
			<tr>
				<td>房型名稱:</td>
				<td><input type="TEXT" name="room_type_id" size="45"
					value="<%= roomTypePicVO.getRoom_type_id()%>"  /></td>
			</tr>
			<tr>
				<td>房型圖片</td>
				<td><input type="file" name="room_type_pic" size="50" /></td>
			</tr>

			

		</table>
		<br> 
		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="送出新增">
	</FORM>

</div>
<%@ include file= "/back-end/framework/footer.file" %>
	
</body>
</html>