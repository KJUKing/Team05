<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.express.vo.ExpressVO"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>YeongeunManyPlease</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-3.7.1.js"></script>
<script type="text/javascript">
	$(function(){
		$('#addBtn').on('click',function(){
			location.href = "<%=request.getContextPath()%>/express/expressInsert.do";
		})
	})
</script>
</head>
<%
	List<ExpressVO> exList = (List<ExpressVO>)request.getAttribute("exList");
%>
<body>
	<table border="1">
		<thead>
			<tr>
				<td colspan="3" style="text-align: right;"><input type="button"
					id="addBtn" value="배송업체추가"></td>
			</tr>
			<tr>
				<th>배송업체ID</th>
				<th>배송업체명</th>
				<th>배송업체전화번호</th>
			</tr>
		</thead>
		<tbody>
			<%
    // exList가 null인지 확인
    if (exList == null || exList.isEmpty()) {
%>
			<tr>
				<td colspan="3">등록된 배송업체가 없습니다</td>
			</tr>
			<%
    } else {
        // exList가 null이 아니고 비어있지 않을 경우
        for (ExpressVO exVo : exList) {
%>
			<tr>
				<td><a href="<%=request.getContextPath()%>/express/expressView.do?ex_id=<%=exVo.getEx_id()%>"><%=exVo.getEx_id() %></a></td>
				<td><%=exVo.getEx_name() %></td>
				<td><%=exVo.getEx_phone() %></td>
			</tr>
			<%
        }
    }
%>
		</tbody>
	</table>
</body>
</html>
