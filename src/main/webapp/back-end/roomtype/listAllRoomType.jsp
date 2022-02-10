<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomtype.model.*"%>

<%
RoomTypeService roomTypeService  = new RoomTypeService();
List<RoomTypeVO> list = roomTypeService.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>房型管理</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/css/styles.css"
	rel="stylesheet" />
<style>
button {
	background-color: rgb(167, 243, 227);
	border-radius: 20em;
}
.explainP{
	width: 100%;
	word-break: break-all;
}
#big {
	width: 90%;
	padding: auto;
}
</style>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
	crossorigin="anonymous"></script>

</head>

<body class="sb-nav-fixed">


	<div id="layoutSidenav_content">
		<main>
			<div class="container-fluid px-4" id="big">
				<h1 class="mt-4" style="text-align: center;">房型管理</h1>
				<div class="card mb-4">
					<div class="card-header">
						<button>新增房型</button>
					</div>


					<!-- table -->

					<table id="shoppingCart"
						class="table table-condensed table-responsive">
						<thead>
							<tr>
								<th style="width: 5%">編號</th>
								<th style="width: 60%">房間</th>
								<th style="width: 8%">價格</th>
								<th style="width: 10%">上下架狀態</th>
								<th style="width: 5%">數量</th>
								<th style="width: 12%; text-align: center;">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="roomTypeVO" items="${list}">
							<tr>
								<td>${roomTypeVO.room_type_id}</td>
								<td data-th="Product">
									<div class="row">
										<div class="col-md-3 text-start">
											<img src="./img/bestroom.jpeg" alt=""
												class="img-fluid d-none d-md-block rounded mb-2 shadow ">
										</div>
										<div class="col-md-9 text-start mt-sm-2">
											<h4>${roomTypeVO.room_type_name}</h4>
											<p class="font-weight-light explainP">${roomTypeVO.room_type_content}</p>
										</div>
									</div>
								</td>
								<td data-th="Price">${roomTypeVO.room_type_price}</td>
								<td>${roomTypeVO.room_type_sale_status}</td>
								<td data-th="Quantity">${roomTypeVO.room_type_amount}</td>
								<td class="actions" data-th="">
									<div class="text-end" >
										<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/roomType/roomType.do"
												style="margin-bottom: 0px; display: inline;">
												<input type="submit" value="修改"> 
												<input type="hidden" name="room_type_id" value="${roomTypeVO.room_type_id}">
												<input type="hidden" name="action" value="getOne_For_Update">
										</FORM>
										<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/roomType/roomType.do"
												style="margin-bottom: 0px; display: inline;">
												<input type="submit" value="刪除"> 
												<input type="hidden" name="room_type_id" value="${roomTypeVO.room_type_id}">
												<input type="hidden" name="action" value="delete">
										</FORM>
									</div>
								</td>
							</tr>
							</c:forEach>
								
						</tbody>
					</table>

				</div>
			</div>
		</main>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-popRpmFF9JQgExhfw5tZT4I9/CI5e2QcuUZPOVXb1m7qUmeR2b50u+YFEYe1wgzy"
		crossorigin="anonymous"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="js/scripts.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
		crossorigin="anonymous"></script>
	<script src="js/datatables-simple-demo.js"></script>
</body>
</html>