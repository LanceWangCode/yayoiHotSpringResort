<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.roomSchedule.model.*"%>
<%@ page import="com.roomOrderList.model.*"%>

<%
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>新增住宿日期 TEST</title>

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
		 <h3>新增住宿日期 TEST</h3></td><td>
		 <h4><a href="">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/roomOrderList/room_order_list.do" name="form1">
<table>

	<tr>
		<td>住宿房間:</td>
		<td><input name="arrival_date" id="arrival_date" type="text"></td>
	</tr>
	<tr>
		<td>退房日期:</td>
		<td><input name="departure_date" id="departure_date" type="text"></td>
	</tr>
	

	

</table>
<br>
<input type="hidden" name="action" value="SaveDateAndShowCheckOut">
<input type="submit" value="送出新增">
</FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
//   java.sql.Date arrival_date = null;
//   java.sql.Date departure_date = null;
//   try {
// 	  arrival_date = roomScheduleVO.getArrival_date();
// 	  departure_date = roomScheduleVO.getDeparture_date();
	    
//    } catch (Exception e) {
// 	   arrival_date = new java.sql.Date(System.currentTimeMillis());
// 	   departure_date = new java.sql.Date(System.currentTimeMillis());
//    }
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
  		$('#arrival_date').datetimepicker({
  		format:'Y-m-d',
  		value:  new Date(),
  	    timepicker:false,
        beforeShowDay: function(date) {
    	  if (  date.getYear() <  today.getYear() || 
		           (date.getYear() == today.getYear() && date.getMonth() <  today.getMonth()) || 
		           (date.getYear() == today.getYear() && date.getMonth() == today.getMonth() && date.getDate() < today.getDate())
          ) {
               return [false, ""]
          }
          return [true, ""];
  		}});  
	
	 
	 $('#departure_date').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
	    minDate:$('#arrival_date').val()?$('#arrival_date').val():false
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