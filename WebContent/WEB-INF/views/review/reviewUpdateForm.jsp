<%@page import="kr.or.ddit.review.vo.ReviewVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 수정</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.7.1.js"></script>
<script type="text/javascript">
$(function(){
    $("#btnList").on("click", function(){
        location.href="<%=request.getContextPath()%>/myreviewList.do";
    });

    $("#btnUpdate").on("click", function(){
        $("#reviewForm").submit(); 
    });
})
</script>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f5f5dc; /* 연한 아이보리 배경색 */
        margin: 20px;
        color: #333;
    }
    h2 {
        text-align: center;
        color: #5b5b5b;
    }
    table {
        width: 80%;
        margin: 20px auto;
        border-collapse: collapse;
        background-color: #ffffff; /* 흰색 배경 */
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }
    td {
        padding: 12px;
        border: 1px solid #ddd;
        text-align: left;
        background-color: #f9f9f9; /* 연한 회색 */
    }
    tr:hover {
        background-color: #eaeae8; /* 더 연한 회색 */
    }
    input[type="button"], input[type="reset"] {
        background-color: #e0d3b2; /* 연한 아이보리 */
        color: #333;
        border: none;
        padding: 10px 15px;
        border-radius: 5px;
        cursor: pointer;
        margin: 5px;
        font-size: 16px;
    }
    input[type="button"]:hover, input[type="reset"]:hover {
        background-color: #d0c0a4; /* 조금 더 어두운 아이보리 */
    }
</style>
</head>
<body>
<h2>리뷰 수정</h2>
<% 
    ReviewVO rvo = (ReviewVO)request.getAttribute("reviewVo"); 
    if (rvo == null) {
        out.println("리뷰 정보를 찾을 수 없습니다.");
        return;
    }
%>
<form id="reviewForm" method="post" action="<%=request.getContextPath()%>/reviewUpdate.do">
    <input type="hidden" name="review_id" value="<%=rvo.getReview_id()%>">
    <table>
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
            <td><input type="number" name="review_star" value="<%=rvo.getReview_star()%>" min="1" max="5"></td>
        </tr>
        <tr>
            <td>리뷰 내용</td>
            <td><textarea name="review_content" rows="5" cols="40"><%=rvo.getReview_content()%></textarea></td>
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
                <input type="reset" value="취소">
                <input id="btnList" type="button" value="리뷰리스트목록">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
