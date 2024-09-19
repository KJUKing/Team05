<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="kr.or.ddit.member.vo.MemberVO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome to Our Website</title>
</head>
<body>
<h2>Welcome to Our Website</h2>

<%
    // 세션에서 loggedInMember 가져오기
    session = request.getSession();
    MemberVO loggedInMember = (MemberVO) session.getAttribute("loggedInMember");

    if (loggedInMember != null) {
        // 로그인된 경우
        
%>
<p>안녕하세요, <%= loggedInMember.getMemName() %>님!</p>
<a href="<%=request.getContextPath()%>/member/logout.do">로그아웃</a><br>
<%
} else {
    // 로그인되지 않은 경우
%>
<a href="<%=request.getContextPath()%>/member/login.do">로그인</a><br>
<a href="<%=request.getContextPath()%>/member/register.do">회원가입</a>
<%
    }
%>

<a href="<%=request.getContextPath()%>/member/memberList.do">회원 목록</a><br>
<a href="<%=request.getContextPath()%>/prod/wishList.do">위시리스트</a>
<a href="<%=request.getContextPath()%>/member/myPage.do">마이페이지</a>
  


</body>
</html>