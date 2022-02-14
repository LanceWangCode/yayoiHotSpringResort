<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<style type="text/css">
html, body {
	font: 15px verdana, Times New Roman, arial, helvetica, sans-serif, Microsoft JhengHei;
	width: 90%;
	height: 90%;
	background: #eeeeda;
}

#userName {
	position: absolute;
	top: 50%;
	left: 50%;
	height: 30px;
	width: 250px;
	margin: -50px 121px 0 -130px;
}

#outPopUp {
	position: absolute;
	width: 500px;
	height: 300px;
	z-index: 15;
	top: 50%;
	left: 50%;
	margin: -200px 250px 0 -250px;
}

.button {
	background-color: #C99A5B;
	color: #ffffff;
	border-radius: 5px;
	position: absolute;
	width: 100px;
	height: 40px; 
	top : 50%;
	left: 50%;
	margin: 20px 200px 0 -50px;
}
</style>
<title>Join Us</title>
</head>
<body>
	<div style="float: right; border: 1px">
        <button type="button" class="close" onclick="closeParentModal();" style="position: fixed;right: 15px;top: 15px;">
        <span aria-hidden="true">&times;</span>
        </button>
	</div>
	<div id="outPopUp">
		<h1 align="center">傳送即時訊息～</h1>
		<form id="myForm" action="<%=request.getContextPath() %>/chat.do" method="POST">
			<input id="userName" name="userName" class="text-field" type="text" placeholder="Input user name" /> 
			<input id="chatnode" name="chatnode" class="text-field" type="hidden" value="client" /> 
			<input type="submit" id="send" class="button" value="送出" onclick="sendName();" />
		</form>
	</div>
</body>
<script>
	var inputUserName = document.getElementById("userName");
	inputUserName.focus();
	
	function sendName() {
		var userName = inputUserName.value.trim();
		if (userName === "") {
			alert("Input a user name");
			inputUserName.focus();
			return;
		} else {
			document.getElementById("myForm").submit();
		}
	}
	function closeParentModal() {
		window.parent.CloseModal(window.frameElement);
	}
</script>

</html>