<%@page import="com.qalist.model.QAListVO"%>
<%@page import="com.qalist.model.QAListService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%
QAListService qaListService = new QAListService();
List<QAListVO> list = qaListService.getAll();
pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>QA管理</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/css/styles.css"
	rel="stylesheet" />
<%@ include file= "/back-end/framework/include.file" %>
	
<style>
button, input {
	background-color: rgb(167, 243, 227);
	border-radius: 20em;
}

#big {
	width: 80%;
	padding: auto;
}
</style>
</head>
<body class="sb-nav-fixed">
<%@ include file= "/back-end/framework/header.file" %>
<div class="container">            
	<div id="layoutSidenav_content">
		<main>
			<div class="container-fluid px-4" id="big">
				<h1 class="mt-4" style="text-align: center;">QA管理</h1>
				<div class="card mb-4">
					<div class="card-header">
						<input type="Submit"  value="新增QA" onclick="location.href='<%=request.getContextPath()%>/back-end/qaList/addQA.jsp'" />
					</div>
					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th>問答編號</th>
									<th>顯示順序</th>
									<th>問題</th>
									<th>答覆</th>
									<th>狀態</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="qaListVO" items="${list}">
									<tr>
										<td>${qaListVO.qa_id}</td>
										<td>${qaListVO.no}</td>
										<td>${qaListVO.question}</td>
										<td>${qaListVO.answer}</td>
										<td>${qaListVO.status}</td>
										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/qaList/qaList.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="修改"> <input
													type="hidden" name="qa_id" value="${qaListVO.qa_id}">
												<input type="hidden" name="action" value="getOne_For_Update">
											</FORM>
										</td>
										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/qaList/qaList.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="刪除"> <input
													type="hidden" name="qa_id" value="${qaListVO.qa_id}">
												<input type="hidden" name="action" value="delete">
											</FORM>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</main>
	</div>
</div>
<%@ include file= "/back-end/framework/footer.file" %>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
		crossorigin="anonymous"></script>
	<script>
        window.addEventListener('DOMContentLoaded', event => {
            // Simple-DataTables
            // https://github.com/fiduswriter/Simple-DataTables/wiki

            const datatablesSimple = document.getElementById('datatablesSimple');
            if (datatablesSimple) {
                new simpleDatatables.DataTable(datatablesSimple);
            }
        });
    </script>
</body>
</html>