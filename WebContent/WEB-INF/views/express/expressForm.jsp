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
	$("#btnList").on("click", function(){
		location.href="<%=request.getContextPath()%>/express/expressList.do";
	});
})
</script>
</head>
<body>
<h2>배송업체등록</h2>
<form id="expressForm" method="post" action="<%=request.getContextPath()%>/express/expressInsert.do">
<table border = "1">
<tbody>
	<tr>
		<td>배송업체명</td>
		<td>
			<input type="text" name="ex_name" id="ex_name">
		</td>
	</tr>
	<tr>
		<td>배송업체 전화번호</td>
		<td>
			<input type="text" name="ex_phone" id="ex_phone">
		</td>
	</tr>
	<tr>
		<td colspan="2" style="text-align:center;">
			<input type="submit" value="등록">
			<input type="reset" value="취소">
			<input type="button" id="btnList" value="배송업체목록">
		</td>
	</tr>

</tbody>
</table>
</form>
</body>
</html>