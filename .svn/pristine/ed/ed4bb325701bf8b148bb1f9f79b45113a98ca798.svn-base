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
<style>
	body {
    font-family: Arial, sans-serif;
    margin: 20px;
    background-color: #f4f4f4;
}
h1, h2 {
    color: #333;
    text-align: center;
    margin-bottom: 20px;
}
table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
    background-color: #fff;
    border: 1px solid #ddd;
    box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}
th, td {
    padding: 12px;
    border-bottom: 1px solid #ddd;
    text-align: center;
}
th {
    background-color: #f8f8f8;
    color: #333;
}
tr:nth-child(even) {
    background-color: #fafafa;
}
tr:hover {
    background-color: #f0f0f0;
}
.button-container {
    text-align: right;
    margin-bottom: 20px;
}
.button-container input, .button, input[type="submit"], input[type="reset"], input[type="button"] {
    padding: 10px 20px;
    font-size: 16px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    background-color: #e0d6d6;
    color: #333;
    transition: background-color 0.3s ease;
}
.button-container input:hover, .button:hover, input[type="submit"]:hover, input[type="reset"]:hover, input[type="button"]:hover {
    background-color: #d0cfcf;
}
td[colspan="2"] {
    text-align: center;
    color: #555;
    font-weight: bold;
}
h3{
	text-align : center;
}
</style>
</head>
<body>
<% CardVO cardVo = (CardVO)request.getAttribute("cardVo");%>

<h3>카드사 상세보기</h3>
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