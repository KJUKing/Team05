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
<h3>카드사 등록</h3>
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