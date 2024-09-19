<%@page import="kr.or.ddit.card.vo.CardVO"%>
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
	
	$("#btnDelete").on("click", function(){
		var form = document.getElementById("cardForm");
		form.action = "<%=request.getContextPath()%>/card/cardDelete.do";
		form.submit();
	});
	
	$("#btnList").on("click", function(){
		location.href = "<%=request.getContextPath()%>/card/cardList.do";
	});
	
});

</script>
</head>
<body>
<% CardVO cardVo = (CardVO)request.getAttribute("cardVo");%>

<h2>카드사 상세보기</h2>
<form name="cardForm" id="cardForm">
	<input type="hidden" id="card_id" name="card_id" value="<%=cardVo.getCard_id()%>">	
<table border="1">
<today>
	<tr>
		<td>카드사ID</td><td><%=cardVo.getCard_id()%></td>
	</tr>
	
	<tr>
		<td>카드사명</td><td><%=cardVo.getCard_name()%></td>
	</tr>
	<tr>
		<td colspan="2" style="text-align:center;">
			<input id="btnDelete" type="button" value="삭제">
			<input id="btnList" type="button" value="카드사목록">
		</td>
	</tr>
</today>
</table>
</form>
</body>
</html>