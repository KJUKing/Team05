<%@page import="kr.or.ddit.review.vo.ReviewVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 상세 보기</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.7.1.js"></script>

<script type="text/javascript">
$(function(){
    $("#btnList").on("click", function(){
        location.href = "<%=request.getContextPath()%>/reviewList.do";
    });

    $("#btnUpdate").on("click", function(){
        var form = document.getElementById("reviewForm");
        form.method = "GET";
        form.action = "<%=request.getContextPath()%>/reviewUpdate.do";
        form.submit();
    });

    $("#btnDelete").on("click", function(){
        var form = document.getElementById("reviewForm");
        form.action = "<%=request.getContextPath()%>/reviewDelete.do";
        form.submit();
    });
});
</script>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 20px;
        background-color: #f4f4f4; /* 연한 아이보리 배경 */
    }
    h2 {
        color: #333;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
        background-color: #fff; /* 흰색 배경 */
        border: 1px solid #ddd;
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }
    td {
        padding: 12px;
        border-bottom: 1px solid #ddd;
    }
    td:first-child {
        background-color: #f9f9f9; /* 연한 아이보리 */
        color: #333;
        font-weight: bold;
    }
    tr:last-child td {
        border-bottom: none;
    }
    input[type="button"] {
        padding: 10px 20px;
        font-size: 16px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        margin-left: 10px;
    }
    #btnUpdate {
        background-color: #d6cfcf; /* 연한 아이보리 계열 */
        color: #333;
    }
    #btnDelete {
        background-color: #e6b8b8; /* 연한 핑크 계열 */
        color: #fff;
    }
    #btnList {
        background-color: #b9a69b; /* 중간 아이보리 계열 */
        color: #fff;
    }
</style>
</head>
<body>
<%
    ReviewVO rvo = (ReviewVO)request.getAttribute("reviewVo");
%>

<h2>리뷰 상세 보기</h2>
<form name="reviewForm" id="reviewForm">
    <input type="hidden" id="review_id" name="review_id" value="<%=rvo.getReview_id()%>">  

<table>
    <tbody>
        <tr>
            <td>리뷰 번호</td>
            <td><%=rvo.getReview_id()%></td>
        </tr>
        <tr>
            <td>작성자 이름</td>
            <td><%=rvo.getMem_name()%></td>
        </tr>
        <tr>
            <td>리뷰 별점</td>
            <td><%=rvo.getReview_star()%></td>
        </tr>
        <tr>
            <td>리뷰 내용</td>
            <td><%=rvo.getReview_content()%></td>
        </tr>
        <tr>
            <td>리뷰 작성 날짜</td>
            <td><%=rvo.getReview_date()%></td>
        </tr>
        <tr>
            <td>내가 구매한 상품 이름</td>
            <td><%=rvo.getProd_name()%></td>
        </tr>
        <tr>
            <td>그 상품의 옵션</td>
            <td><%=rvo.getOption_name()%></td>
        </tr>    
        <tr>
            <td colspan="2" style="text-align:center;">
                <input id="btnUpdate" type="button" value="수정"> 
                <input id="btnDelete" type="button" value="삭제"> 
                <input id="btnList" type="button" value="리뷰 리스트로 돌아가기">
            </td>
        </tr>
    </tbody>
</table>
</form>
</body>
</html>
