<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.emp.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>
<%


if (session == null)
{
	String redirectURL = request.getContextPath()+"/index.jsp?status=fail";
	response.sendRedirect(redirectURL);
} else {
	String username= (String)request.getSession().getAttribute("username");
	if(username == null) {
		String redirectURL = request.getContextPath()+"/index.jsp?status="+username;
		response.sendRedirect(redirectURL);
	}
}
%>
<%
  EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>���u��� - listOneEmp.jsp</title>
<%@ include file="/back-end/framework/include.file" %>
<style>
  table#table-1 {
    min-width: 650px;
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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>
<%@ include file="/back-end/framework/header.file" %>
<div class="container">
<!-- <h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>  -->
<table id="table-1">
	<tr><td>
		 <h3>���u��� - ListOneEmp.jsp</h3>
		 <h4><a href="<%=request.getContextPath()+"/back-end/emp/select_page.jsp" %>"><img src="<%=request.getContextPath()+"/back-end/emp/images/back1.gif" %>" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>���u�s��</th>
		<th>���u�m�W</th>
		<th>¾��</th>
		<th>���Τ��</th>
		<th>�~��</th>
		<th>����</th>
		<th>����</th>
	</tr>
	<tr>
		<td><%=empVO.getEmpno()%></td>
		<td><%=empVO.getEname()%></td>
		<td><%=empVO.getJob()%></td>
		<td><%=empVO.getHiredate()%></td>
		<td><%=empVO.getSal()%></td>
		<td><%=empVO.getComm()%></td>
		<td><%=empVO.getDeptno()%></td>
	</tr>
</table>
</div>
<%@ include file="/back-end/framework/footer.file" %>
</body>
</html>