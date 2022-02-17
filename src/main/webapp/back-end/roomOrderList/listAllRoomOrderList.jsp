<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomOrderList.model.*"%>

<%
RoomOrderListService roomOrderListService = new RoomOrderListService();
List<RoomOrderListVO> list = roomOrderListService.getall();
pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>住宿訂單明細管理</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/css/styles.css"
	rel="stylesheet" />
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
	<div id="layoutSidenav_content">
		<main>
			<div class="container-fluid px-4" id="big">
				<h1 class="mt-4" style="text-align: center;">住宿訂單明細管理</h1>
				<div class="card mb-4">
					<div class="card-header">
					</div>
					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th>住宿訂單明細編號</th>
									<th>房間編號</th>
									<th>房型編號</th>
									<th>人數</th>
									<th>住房日期</th>
									<th>退房日期</th>
									<th>房間價格</th>
									<th>住宿訂單編號</th>
									<th>服務訂單編號</th>
									<th>特殊要求</th>
									<th>操作</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="roomOrderListVO" items="${list}">
									<tr>
										<td>${roomOrderListVO.room_order_list_id}</td>
										<td>${roomOrderListVO.room_id}</td>
										<td>${roomOrderListVO.room_type_id}</td>
										<td>${roomOrderListVO.number_of_people}</td>
										<td>${roomOrderListVO.arrival_date}</td>
										<td>${roomOrderListVO.departure_date}</td>
										<td>${roomOrderListVO.room_price}</td>
										<td>${roomOrderListVO.room_order_id}</td>
										<td>${roomOrderListVO.service_order_id}</td>
										<td>${roomOrderListVO.special_req}</td>
										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/roomOrderList/roomOrderList.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="修改"> <input
													type="hidden" name="room_order_list_id" value="${roomOrderListVO.room_order_list_id}">
												<input type="hidden" name="action" value="getOne_For_Update">
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