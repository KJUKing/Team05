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
<style>
body {
    background-color: #ffffff; /* 흰색 배경 */
    font-family: Arial, sans-serif;
    margin: 20px;
}

h2 {
    color: #333;
    text-align: center;
    margin-bottom: 20px;
}

input[type="text"] {
    width: 100%;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type="button"], input[type="submit"], input[type="reset"] {
    padding: 10px 20px;
    font-size: 16px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    background-color: #e0d6d6;
    color: #333;
}

input[type="button"]:hover, input[type="submit"]:hover, input[type="reset"]:hover {
    background-color: #d0cfcf;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin: 20px auto;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    background-color: #fff;
    border: none; /* 테두리 제거 */
}

th, td {
    padding: 15px;
    text-align: left;
    border-bottom: 1px solid #ddd;
    color: #333;
}

td {
    text-align: center;
}

th {
    background-color: #f8f8f8; /* 연한 회색 */
}

tr:nth-child(even) {
    background-color: #fafafa; /* 연한 회색 */
}

tr:hover {
    background-color: #f0f0f0; /* 더 연한 회색 */
}

td[colspan="2"] {
    text-align: center;
}

h3 {
    text-align: center;
}
</style>
</head>
<body>
<h3>배송업체 등록</h3>
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