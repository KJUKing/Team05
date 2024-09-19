<%@page import="kr.or.ddit.card.vo.CardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.7.1.js"></script>
<% List<CardVO> cardList = (List<CardVO>)request.getAttribute("cardList"); %>
<script type="text/javascript">
$(function(){
	$("#addBtn").on("click",function(){
		location.href="<%=request.getContextPath()%>/card/cardInsert.do";
	})
	
})
</script>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<td colspan="2" style="text-align: right;"><input type="button"
					id="addBtn" value="카드사추가"></td>
			</tr>
			<tr>
				<th>카드사ID</th>
				<th>카드사명</th>
			</tr>
		</thead>
		<tbody>
<%
    // cardList가 null인지 확인
    if (cardList == null || cardList.isEmpty()) {
%>
			<tr>
				<td colspan="2">등록된 카드사가 없습니다</td>
			</tr>
<%
    } else {
        // cardList가 null이 아니고 비어있지 않을 경우
        for (CardVO cardVo : cardList) {
%>
			<tr>
				<td><a href="<%=request.getContextPath()%>/card/cardView.do?card_id=<%=cardVo.getCard_id()%>"><%=cardVo.getCard_id() %></td>
				<td><%=cardVo.getCard_name() %></td>
			</tr>
<%
        }
    }
%>
		</tbody>
	</table>
</body>
</html>