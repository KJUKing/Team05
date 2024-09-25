<%@page import="kr.or.ddit.card.vo.CardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카드사 리스트</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.7.1.js"></script>
<% List<CardVO> cardList = (List<CardVO>)request.getAttribute("cardList"); %>
<script type="text/javascript">
$(function(){
	$("#addBtn").on("click",function(){
		location.href="<%=request.getContextPath()%>/card/cardInsert.do";
	})
})
</script>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 20px;
        background-color: #f4f4f4;
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
    .button-container input, .button {
        padding: 10px 20px;
        font-size: 16px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        background-color: #e0d6d6;
        color: #333;
    }
    .button-container input:hover, .button:bover {
        background-color: #d0cfcf;
    }
    h3{
    	text-align : center;
    }
</style>
</head>
<body>
<h3>카드사 리스트</h3>

<div class="button-container">
    <input id="addBtn" type="button" value="카드사 추가">
    <button class="button" onclick="location.href='<%=request.getContextPath()%>/member/adminDashboard.do'">뒤로 가기</button>
</div>

<%
    if (cardList == null || cardList.isEmpty()) {
%>
    <p>등록된 카드사가 없습니다.</p>
<%
    } else {
%>
<table>
    <thead>
        <tr>
            <th>카드사 ID</th>
            <th>카드사명</th>
        </tr>
    </thead>
    <tbody>
        <%
            for (CardVO cardVo : cardList) {
        %>
            <tr>
                <td><a href="<%=request.getContextPath()%>/card/cardView.do?card_id=<%=cardVo.getCard_id()%>" style="color: #007BFF;"><%=cardVo.getCard_id() %></a></td>
                <td><%=cardVo.getCard_name() %></td>
            </tr>
        <%
            }
        %>
    </tbody>
</table>
<%
    }
%>
</body>
</html>
