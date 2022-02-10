<%@page import="com.room.model.RoomVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room.model.*"%>

<%
  RoomVO roomVO = (RoomVO) request.getAttribute("roomVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>房間資料修改 - update_room.jsp</title>

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
	<tr><td>
		 <h3>房間資料修改</h3>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="room.do" name="form1">
<table>
	<tr>
		<td>房間編號:<font color=red><b>*</b></font></td>
		<td><%=roomVO.getRoom_id()%></td>
	</tr>
	<tr>
		<td>房型類別:</td>
		<td><input type="TEXT" name="room_type_id" size="45" value="<%=roomVO.getRoom_type_id()%>" /></td>
	</tr>
	<tr>
		<td>床位:</td>
		<td><input type="TEXT" name="qtyofbeds" size="45"	value="<%=roomVO.getQtyofbeds()%>" /></td>
	</tr>
	<tr>
		<td>住客名單:</td>
		<td><input type="TEXT" name="room_guest_name" size="45"	value="<%=roomVO.getRoom_guest_name()%>" /></td>
	</tr>
	<tr>
		<td>上下架狀態:</td>
		<td><input type="TEXT" name="room_sale_status" size="45"	value="<%=roomVO.getRoom_sale_status()%>" /></td>
	</tr>
	<tr>
		<td>房間狀態:</td>
		<td><input type="TEXT" name="room_status" size="45" value="<%=roomVO.getRoom_status()%>" /></td>
	</tr>

	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="room_id" value="<%=roomVO.getRoom_id()%>">
<input type="submit" value="送出修改"></FORM>
</body>
</html>