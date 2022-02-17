<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomSchedule.model.*"%>

<%
RoomScheduleVO roomScheduleVO = (RoomScheduleVO) request.getAttribute("roomScheduleVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>住宿時程表 - addRoomSchedule.jsp</title>
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
	width: 650px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 2px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>




</head>
<body bgcolor='white'>
<table id="table-1">
	<tr><td>
		 <h3>住宿時程表 - addRoomSchedule.jsp</h3></td><td>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>


<h3>資料新增:</h3>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/roomSchedule/roomSchedule.do" name="form1">
<table>
	<tr>
		<td>房型編號:</td>
		<td><input type="TEXT" name="room_type_id" size="45" /></td>
	</tr>
	<tr>
		<td>入住日期:</td>
		<td><input name="room_schedule_date" id="room_schedule_date" type="text"></td>
	</tr>
	<tr>
		<td>房間數量:</td>
		<td><input type="TEXT" name="room_amount" size="45"/></td>
	</tr>
    <tr>
		<td>房間數量:</td>
		<td><input type="TEXT" name="room_rsv_booked" size="45"/></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</FORM>



</body>

<% 
  java.sql.Date room_schedule_date = null;
  try {
	  room_schedule_date = roomScheduleVO.getRoom_schedule_date();
	
	    
   } catch (Exception e) {
	   room_schedule_date= new java.sql.Date(System.currentTimeMillis());

   }
%>


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>


<script>
$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
	
	 var today = new Date();
  		$('#room_schedule_date').datetimepicker({
        beforeShowDay: function(date) {
    	  if (  date.getYear() <  today.getYear() || 
		           (date.getYear() == today.getYear() && date.getMonth() <  today.getMonth()) || 
		           (date.getYear() == today.getYear() && date.getMonth() == today.getMonth() && date.getDate() < today.getDate())
          ) {
               return [false, ""]
          }
          return [true, ""];
  		}});  
	 $('#room_schedule_date').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
	    maxDate:$('#departure_date').val()?$('#departure_date').val():false
	   })
	  },
	  timepicker:false,
	  value:   new Date(),
	 });
	 
	
});
        
//      1.以下為某一天之前的日期無法選擇
//      var today = new Date();
//      $('#arrival_date').datetimepicker({
//          beforeShowDay: function(date) {
//        	  if (  date.getYear() <  today.getYear() || 
//		           (date.getYear() == today.getYear() && date.getMonth() <  today.getMonth()) || 
//		           (date.getYear() == today.getYear() && date.getMonth() == today.getMonth() && date.getDate() < today.getDate())
//              ) {
//                   return [false, ""]
//              }
//              return [true, ""];
//      }});  
     
</script>




</html>