<%@page import="kr.or.ddit.express.vo.ExpressVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.7.1.js"></script>
<script type="text/javascript">

$(function(){
	
	$("#btnUpdate").on("click", function(){
		var form = document.getElementById("expressForm");
		form.method = "GET";
		form.action = "<%=request.getContextPath()%>/express/expressUpdate.do";
		form.submit();
	});
	
	$("#btnDelete").on("click", function(){
		var form = document.getElementById("expressForm");
		form.action = "<%=request.getContextPath()%>/express/expressDelete.do";
		form.submit();
	});
	
	$("#btnList").on("click", function(){
		location.href = "<%=request.getContextPath()%>/express/expressList.do";
	});
	
});

</script>
</head>
<body>

<% ExpressVO exVo = (ExpressVO)request.getAttribute("exVo");%>

<h2>배송업체 상세보기</h2>
<form name="expressForm" id="expressForm">
	<input type="hidden" id="ex_id" name="ex_id" value="<%=exVo.getEx_id()%>">	
<table border="1">
<today>
	<tr>
		<td>배송업체ID</td><td><%=exVo.getEx_id()%></td>
	</tr>
	<tr>
		<td>배송업체명</td><td><%=exVo.getEx_name()%></td>
	</tr>
	<tr>
		<td>배송업체전화번호</td><td><%=exVo.getEx_phone()%></td>
	</tr>
	<tr>
		<td colspan="2" style="text-align:center;">
			<input id="btnUpdate" type="button" value="수정">
			<input id="btnDelete" type="button" value="삭제">
			<input id="btnList" type="button" value="배송업체목록">
		</td>
	</tr>
</today>
</table>
</form>
</body>
</html>