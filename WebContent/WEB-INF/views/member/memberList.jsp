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
    /* 전체 페이지 스타일 */
body {
    font-family: 'Arial', sans-serif;                /* Arial 폰트 사용 */
    background-color: #f4f4f4;                       /* 연한 회색 배경 설정 */
    margin: 0;                                       /* 바깥 여백 제거 */
    padding: 20px;                                   /* 안쪽 여백 설정 */
}

/* 제목 스타일 */
h2 {
    text-align: center;                              /* 제목 중앙 정렬 */
    color: #333;                                     /* 진한 회색 텍스트 색상 설정 */
}

/* 테이블 스타일 */
table {
    width: 100%;                                     /* 테이블 전체 너비 설정 */
    border-collapse: collapse;                       /* 셀 경계선 병합 */
    margin: 20px 0;                                  /* 상하 여백 설정 */
    background-color: #fff;                          /* 테이블 배경색 흰색 설정 */
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);         /* 테이블에 그림자 효과 추가 */
}

/* 테이블 셀 테두리 스타일 */
table, th, td {
    border: 1px solid #ddd;                          /* 연한 회색 테두리 설정 */
}

/* 테이블 헤더와 셀 공통 스타일 */
th, td {
    padding: 12px;                                   /* 셀 안쪽 여백 설정 */
    text-align: center;                              /* 텍스트 중앙 정렬 */
}

/* 테이블 헤더 스타일 */
th {
    background-color: #f2f2f2;                       /* 연한 회색 배경 설정 */
    color: #333;                                     /* 진한 회색 텍스트 색상 설정 */
}

/* 테이블 셀 스타일 */
td {
    color: #666;                                     /* 중간 회색 텍스트 색상 설정 */
}

/* 짝수 행 스타일 */
tr:nth-child(even) {
    background-color: #f9f9f9;                       /* 매우 연한 회색 배경 설정 */
}

/* 행 호버 효과 */
tr:hover {
    background-color: #f1f1f1;                       /* 호버 시 연한 회색 배경 설정 */
}

/* 링크 스타일 */
a {
    color: #007bff;                                  /* 파란색 링크 색상 설정 */
    text-decoration: none;                           /* 밑줄 제거 */
}

/* 링크 호버 효과 */
a:hover {
    text-decoration: underline;                      /* 호버 시 밑줄 표시 */
}

/* 버튼 컨테이너 스타일 */
.action-links {
    display: flex;                                   /* 플렉스박스 레이아웃 사용 */
    justify-content: flex-end;                       /* 버튼을 오른쪽으로 정렬 */
    margin-top: 20px;                                /* 상단 여백 설정 */
}

/* 버튼 공통 스타일 */
.action-links a {
    padding: 10px 20px;                              /* 버튼 안쪽 여백 설정 */
    font-size: 16px;                                 /* 글꼴 크기 설정 */
    border: none;                                    /* 테두리 제거 */
    border-radius: 5px;                              /* 둥근 모서리 설정 */
    cursor: pointer;                                 /* 마우스 오버 시 포인터 모양 변경 */
    background-color: #e0d6d6;                       /* 기본 배경색 설정 */
    color: #333;                                     /* 텍스트 색상 설정 */
    transition: background-color 0.3s ease;          /* 배경색 전환 효과 */
    text-decoration: none;                           /* 링크 밑줄 제거 */
}

/* 버튼 호버 효과 */
.action-links a:hover {
    background-color: #d0cfcf;                       /* 호버 시 배경색 변경 */
}

/* 뒤로가기 버튼 스타일 */
.action-links a:first-child {
    background-color: #5bc0de;                       /* 파란색 배경 설정 */
}

/* 뒤로가기 버튼 호버 효과 */
.action-links a:first-child:hover {
    background-color: #46b8da;                       /* 호버 시 진한 파란색으로 변경 */
}

/* 로그아웃 버튼 스타일 */
.action-links a:last-child {
    background-color: #d9534f;                       /* 빨간색 배경 설정 */
}

/* 로그아웃 버튼 호버 효과 */
.action-links a:last-child:hover {
    background-color: #c9302c;                       /* 호버 시 진한 빨간색으로 변경 */
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
