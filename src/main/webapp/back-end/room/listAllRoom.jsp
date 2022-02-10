<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.room.model.*"%>

<%
RoomService roomService = new RoomService();
List<RoomVO> list = roomService.getAll();
pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>房間管理</title>
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
				<h1 class="mt-4" style="text-align: center;">房間管理</h1>
				<div class="card mb-4">
					<div class="card-header">
						<input type="Submit"  value="新增房間" onclick="location.href='<%=request.getContextPath()%>/back-end/room/addRoom.jsp'" />
					</div>
					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th>房間號碼</th>
									<th>房型類別</th>
									<th>床位</th>
									<th>住客名單</th>
									<th>上下架狀態</th>
									<th>房間狀態</th>
									<th>操作</th>
									<th>操作</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="roomVO" items="${list}">
									<tr>
										<td>${roomVO.room_id}</td>
										<td>${roomVO.room_type_id}</td>
										<td>${roomVO.qtyofbeds}</td>
										<td>${roomVO.room_guest_name}</td>
										<td>${roomVO.room_sale_status}</td>
										<td>${roomVO.room_status}</td>
										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/room/room.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="修改"> <input
													type="hidden" name="room_id" value="${roomVO.room_id}">
												<input type="hidden" name="action" value="getOne_For_Update">
											</FORM>
										</td>
										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/room/room.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="刪除"> <input
													type="hidden" name="room_id" value="${roomVO.room_id}">
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