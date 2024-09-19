<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="kr.or.ddit.member.vo.MemberVO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Page</title>
</head>
<body>
<h2>My Page</h2>

<%
    // 세션에서 전달된 사용자 정보 가져오기
    MemberVO loggedInMember = (MemberVO) request.getAttribute("loggedInMember");

    if (loggedInMember != null) {
%>
<p><%= loggedInMember.getMemName() %>님의 마이페이지입니다.</p>
<a href="<%=request.getContextPath()%>/member/logout.do">로그아웃</a><br>
<a href="<%=request.getContextPath()%>/member/memberList.do">회원 목록</a><br>
<a href="<%=request.getContextPath()%>/prod/wishList.do">위시리스트</a>
<a href="<%=request.getContextPath()%>/index.jsp">홈으로</a>
<a href="<%=request.getContextPath()%>/express/expressList.do">배송업체</a>  
<a href="<%=request.getContextPath()%>/card/cardList.do">카드사</a>
<a href="<%=request.getContextPath()%>/reviewList.do">리뷰</a>
<a href="<%=request.getContextPath()%>/payment/paymentList.do">주문결제</a>
<a href="<%=request.getContextPath()%>/adminreportview.do">신고된리뷰보기</a>
<a href="<%=request.getContextPath()%>/riturn/myBuyPage.do">나의 구매내역</a>

<%
    }
%>

</body>
</html>
