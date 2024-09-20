<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="kr.or.ddit.member.vo.MemberVO" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f8f8;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            width: 60%;
            padding: 30px;
            background-color: #fff;
            border: 1px solid #ddd;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h2 {
            font-size: 28px;
            margin-bottom: 30px;
        }

        .grid-menu {
            display: grid;
            grid-template-columns: repeat(2, 1fr); /* 2개의 열로 구성 */
            grid-gap: 20px; /* 각 항목 간격 */
        }

        .grid-menu a {
            display: flex;
            justify-content: center;
            align-items: center;
            text-decoration: none;
            color: black;
            font-size: 18px;
            font-weight: bold;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 10px;
            transition: background-color 0.3s, color 0.3s;
        }

        .grid-menu a:hover {
            background-color: #f0f0f0;
            color: #000;
        }

        .grid-menu a:active {
            background-color: #ccc;
        }
    </style>
</head>
<body>

<%
    // 세션에서 전달된 사용자 정보 가져오기
    MemberVO loggedInMember = (MemberVO) request.getAttribute("loggedInMember");
    if (loggedInMember != null) {
%>
    <div class="container">
        <h2>My Page</h2>
        <p><%= loggedInMember.getMemName() %>님의 마이페이지입니다.</p>

        <div class="grid-menu">
            <a href="<%=request.getContextPath()%>/index.jsp">홈으로</a>
            <a href="<%=request.getContextPath()%>/member/editMember.do?memId=<%= loggedInMember.getMemId() %>">개인정보 수정</a>
            <a href="<%=request.getContextPath()%>/member/memberList.do">회원 목록</a>
            <a href="<%=request.getContextPath()%>/prod/wishList.do">위시리스트</a>
            <a href="<%=request.getContextPath()%>/express/expressList.do">배송업체</a>  
            <a href="<%=request.getContextPath()%>/card/cardList.do">카드사</a>
            <a href="<%=request.getContextPath()%>/myreviewList.do?memId=<%=loggedInMember.getMemId()%>">나의 리뷰</a>
            <a href="<%=request.getContextPath()%>/adminreportview.do">신고된리뷰보기</a>
            <a href="<%=request.getContextPath()%>/payment/paymentList.do?memId=<%=loggedInMember.getMemId()%>">장바구니</a>
            <a href="<%=request.getContextPath()%>/riturn/myBuyPage.do">나의 구매내역</a>
            <a href="<%=request.getContextPath()%>/riturn/riturnView.do">반품 내역</a>
            <a href="<%=request.getContextPath()%>/coupon/couponList.do">쿠폰 목록</a>
        </div>
    </div>
<%
    } else {
%>
    <p>로그인 정보가 없습니다.</p>
<%
    }
%>

</body>
</html>
