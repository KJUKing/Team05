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
		location.href="<%=request.getContextPath()%>/card/cardList.do";
	});
})
</script>
</head>
<body>
<h2>카드사등록</h2>
<form id="cardForm" method="post" action="<%=request.getContextPath()%>/card/cardInsert.do">
<table border = "1">
<tbody>
	<tr>
		<td colspan="2">카드사ID은 자동으로 생성됩니다.</td>
	</tr>
	<tr>
		<td>카드명</td>
		<td>
			<input type="text" name="card_name" id="card_name">
		</td>
	</tr>
	<tr>
		<td colspan="2" style="text-align:center;">
			<input type="submit" value="등록">
			<input type="reset" value="취소">
			<input type="button" id="btnList" value="카드사목록">
		</td>
	</tr>

</tbody>
</table>
</form>
</body>
</html>