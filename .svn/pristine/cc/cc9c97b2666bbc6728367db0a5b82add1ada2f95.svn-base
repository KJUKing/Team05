<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="kr.or.ddit.member.vo.MemberVO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 목록</title>
</head>
<body>
    <h2>회원 목록</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>이름</th>
            <th>이메일</th>
            <th>전화번호</th>
            <th>생년월일</th>
            <th>우편번호</th>
            <th>주소</th>
            <th>상세주소</th>
            <th>마일리지</th>
            <th>상태</th>
            <th>상세보기</th>
        </tr>
        <%
            // 날짜 포맷터 선언
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            // request에서 memberList 가져오기
            List<MemberVO> memberList = (List<MemberVO>)request.getAttribute("memberList");
            // 멤버 리스트 반복문
            for(MemberVO member : memberList) {
        %>
            <tr>
                <td><%= member.getMemId() %></td>
                <td><%= member.getMemName() %></td>
                <td><%= member.getMemEmail() %></td>
                <td><%= member.getMemPhone() %></td>
                <td>
                    <% if (member.getMemBirth() != null) { %>
                        <%= dateFormat.format(member.getMemBirth()) %>
                    <% } %>
                </td>
                <td><%= member.getMemZipcode() %></td>
                <td><%= member.getMemAddress() %></td>
                <td><%= member.getMemDetailAddress() %></td>
                <td><%= member.getMemMileage() %></td>
                <td><%= member.getMemStatus() == 1 ? "활성" : "비활성" %></td>
                <td>
                    <a href="<%= request.getContextPath() %>/member/memberDetail.do?memId=<%= member.getMemId() %>">상세보기</a>
                </td>
            </tr>
        <%
            }
        %>
    </table>
    <a href="<%=request.getContextPath()%>/member/login.do">로그아웃</a>
</body>
</html>