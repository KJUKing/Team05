<%@ page import="kr.or.ddit.member.vo.MemberVO" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%
    session = request.getSession();
    MemberVO loggedInMember = (MemberVO) session.getAttribute("loggedInMember");
%>
<html>
<head>
    <title>Main Layout</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
<!-- 공통 헤더 -->
<div class="header-top">
  <img src="<%=request.getContextPath()%>/images/연근옷장로고.jpg" alt="연근옷장로고.jpg" style="height: 80px;">
   
    <div class="header-options">
        <ul>
            <% if (loggedInMember != null) { %>
            <li>안녕하세요, <%= loggedInMember.getMemName() %>님!</li>
            <li><a href="<%=request.getContextPath()%>/member/logout.do">로그아웃</a></li>
            <% } else { %>
            <li><a href="<%=request.getContextPath()%>/member/login.do">로그인</a></li>
            <li><a href="<%=request.getContextPath()%>/member/register.do">회원가입</a></li>
            <% } %>
        </ul>
    </div>
</div>

<!-- 네비게이션 바 -->
<nav class="main-nav">
    <ul>
        <li><a href="<%=request.getContextPath()%>/main?view=index">New Arrivals</a></li>
        <li><a href="<%=request.getContextPath()%>/main?view=best">Best</a></li>
        <li><a href="<%=request.getContextPath()%>/main?view=all">All</a></li>
        <li><a href="<%=request.getContextPath()%>/main?view=outer">Outer</a></li>
        <li><a href="<%=request.getContextPath()%>/main?view=top">Top</a></li>
        <li><a href="<%=request.getContextPath()%>/main?view=bottom">Bottom</a></li>
        <li><a href="<%=request.getContextPath()%>/main?view=acc">Acc</a></li>
    </ul>
</nav>

<!-- 메인 콘텐츠 -->
<div class="main-content">
    <jsp:include page="${contentPage}" />
</div>

<!-- 공통 푸터 -->
<div class="footer">
    <p>CUSTOMER CENTER 042.256.2569</p>
    <p>MON-FRI [10:00 - 17:00] WEEKEND, HOLIDAYS OFF</p>
    <p>Lotus Root Market No.5 All rights reserved</p>
</div>
</body>
</html>
