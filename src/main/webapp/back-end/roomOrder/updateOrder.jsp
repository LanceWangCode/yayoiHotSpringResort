<%@page import="com.room.model.RoomVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.roomOrder.model.*"%>

<%
RoomOrderVO roomOrderVo = (RoomOrderVO) request.getAttribute("roomOrderVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�ж���ƭק� - update_room.jsp</title>

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
	width: 550px;
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
		 <h3>�ж���ƭק�</h3>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/roomOrder/RoomOrderServlet.do" name="form1">
<table>
	<tr>
		<td>��Эq��s��:<font color=red><b>*</b></font></td>
		<td><%=roomOrderVo.getRoom_order_id()%></td>
	</tr>
	<tr>
		<td>�|���s��:<font color=red><b>*</b></font></td>
		<td><%=roomOrderVo.getMem_id()%></td>
	</tr>
	<tr>
		<td>�q����:</td>
		<td><%=roomOrderVo.getOrder_date()%></td>
	</tr>
	<tr>
		<td>�q�檬�A:</td>
		<td><input type="TEXT" name="room_order_status" size="45"	value="<%=roomOrderVo.getRoom_order_status()%>" /></td>
	</tr>
	<tr>
		<td>�q���`���B:</td>
		<td><input type="TEXT" name="room_charge" size="45"	value="<%=roomOrderVo.getRoom_charge()%>" /></td>
	</tr>
	<tr>
		<td>��J����:</td>
		<td><input type="TEXT" name="room_review" size="45"	value="<%=roomOrderVo.getRoom_review()%>" /></td>
	</tr>

	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="order_date" value="<%=roomOrderVo.getOrder_date()%>">
<input type="hidden" name="mem_id" value="<%=roomOrderVo.getMem_id()%>">
<input type="hidden" name="room_order_id" value="<%=roomOrderVo.getRoom_order_id()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>
</html>