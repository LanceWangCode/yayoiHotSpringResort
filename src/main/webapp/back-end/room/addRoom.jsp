<%@page import="com.room.model.RoomVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room.model.*"%>

<%
RoomVO roomVO = (RoomVO) request.getAttribute("roomVO");
%>

<!-- ���t�@�MDAO�X�ӦA�� -->
<%-- <%= empVO == null %>--${empVo.deptno}--  --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�ж��s�W - addRoom.jsp</title>

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
				<h3>�ж��s�W - addRoom.jsp</h3>
			</td>
		</tr>
	</table>

	<h3>��Ʒs�W:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/room/room.do" name="form1">
		<table>
			<tr>
				<td>�Ы��W��:</td>
				<td><input type="TEXT" name="room_type_id" size="45"
					value="<%=(roomVO == null) ? "2" : roomVO.getRoom_type_id()%>" /></td>
			</tr>
			<tr>
				<td>�ɦ�:</td>
				<td><input type="TEXT" name="qtyofbeds" size="45"
					value="<%=(roomVO == null) ? "2" : roomVO.getQtyofbeds()%>" /></td>
			</tr>
			
			<tr>
				<td>�W�U�[���A:</td>
				<td><input type="TEXT" name="room_sale_status" size="45"
					value="<%=(roomVO == null) ? "true" : roomVO.getRoom_sale_status()%>" /></td>
			</tr>
			<tr>
				<td>�ж����A:</td>
				<td><input type="TEXT" name="room_status" size="45"
					value="<%=(roomVO == null) ? "1" : roomVO.getRoom_status()%>" /></td>
			</tr>

			

		</table>
		<br> 
		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="�e�X�s�W">
	</FORM>
</body>
</html>