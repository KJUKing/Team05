<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="kr.or.ddit.member.vo.MemberVO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 목록</title>
    <style>
    /* - 정석진 2024-09-23-  */
      body {
    font-family: 'Arial', sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 20px;
}

h2 {
    text-align: center;
    color: #333;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
    background-color: #fff;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

table, th, td {
    border: 1px solid #ddd;
}

th, td {
    padding: 12px;
    text-align: center;
}

th {
    background-color: #f2f2f2;
    color: #333;
}

td {
    color: #666;
}

tr:nth-child(even) {
    background-color: #f9f9f9;
}

tr:hover {
    background-color: #f1f1f1;
}

a {
    color: #007bff;
    text-decoration: none;
}

a:hover {
    text-decoration: underline;
}

/* 버튼 스타일 */
.action-links {
    display: flex;
    justify-content: flex-end;
    margin-top: 20px;
}

.action-links a {
    padding: 10px 20px;
    font-size: 16px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    background-color: #e0d6d6;
    color: #333;
    transition: background-color 0.3s ease;
    text-decoration: none; /* 링크의 밑줄 제거 */
}

.action-links a:hover {
    background-color: #d0cfcf;
}

/* 각 버튼의 색상 구분 */
.action-links a:first-child {
    background-color: #5bc0de; /* 뒤로가기 버튼은 파란색 */
}

.action-links a:first-child:hover {
    background-color: #46b8da;
}

.action-links a:last-child {
    background-color: #d9534f; /* 로그아웃 버튼은 빨간색 */
}

.action-links a:last-child:hover {
    background-color: #c9302c;
}

    </style>
</head>
<body>
    <h2>회원 목록</h2>
    <table>
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
                
            </tr>
        <%
            }
        %>
    </table>
    <div class="action-links">
        <a href="javascript:history.back()">뒤로가기</a>
        <a href="<%=request.getContextPath()%>/member/login.do">로그아웃</a>
    </div>
</body>
</html>
