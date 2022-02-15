<%@page import="com.roomTypePic.model.RoomTypePicVO"%>
<%@page import="com.roomTypePic.model.RoomTypePicService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomType.model.*"%>
<%
int id = 0;
RoomTypeVO roomTypeVO=null;
if(request.getAttribute("roomTypeVO")!=null){
	roomTypeVO = (RoomTypeVO) request.getAttribute("roomTypeVO");
	id = roomTypeVO.getRoom_type_id();
}else{
	RoomTypePicVO roomTypePicVO = (RoomTypePicVO) request.getAttribute("roomTypePicVO");
	id = roomTypePicVO.getRoom_type_id();
}

%>

<%
RoomTypePicService roomService = new RoomTypePicService();
List<RoomTypePicVO> list = roomService.getFKPic(id);
pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>照片管理</title>
<%@ include file= "/back-end/framework/include.file" %>

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
<%@ include file= "/back-end/framework/header.file" %>
<div class="container">            

	<div id="layoutSidenav_content">
		<main>
			<div class="container-fluid px-4" id="big">
				<h1 class="mt-4" style="text-align: center;">房型${roomTypeVO.room_type_id}:照片管理</h1>
				<div class="card mb-4">
					<div class="card-header">
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/roomType/roomTypePicServlet.do"style="margin-bottom: 0px;">
												<input type="submit" value="新增照片"> 
												<input type="hidden" name="room_type_id"value="${roomTypeVO.room_type_id}"> 
												<input type="hidden" name="action" value="getOne_For_Insert">
						</FORM>
						<input type="Submit" value="返回房型管理"
							onclick="location.href='<%=request.getContextPath()%>/back-end/roomtype/listAllRoomType.jsp'" />
					</div>
					<div class="card-header"></div>
					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th>照片編號</th>
									<th>照片</th>
									<th>操作</th>
									<th>操作</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="picVO" items="${list}">
									<tr>
										<td>${picVO.room_photo_id}</td>
										<td><img
											src="<%=request.getContextPath()%>/roomType/roomTypePicId.do?room_photo_id=${picVO.room_photo_id}"
											alt=""
											class="img-fluid d-none d-md-block rounded mb-2 shadow " width="200px" height = "200px"></td>
										<td>
											<FORM METHOD="post" enctype="multipart/form-data" ACTION="<%=request.getContextPath()%>/roomType/roomTypePicServlet.do" style="margin-bottom: 0px;">
												
												<input type="file" name="room_type_pic" size="50" />
												
												
												<input type="submit" value="修改"> <input
													type="hidden" name="room_photo_id"
													value="${picVO.room_photo_id}"> <input
													type="hidden" name="action" value="Update">
											</FORM>
										</td>
										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/roomType/roomTypePicServlet.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="刪除"> 
												<input type="hidden" name="room_photo_id" value="${picVO.room_photo_id}"> 
												<input type="hidden" name="room_type_id" value="${picVO.room_type_id}"> 
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