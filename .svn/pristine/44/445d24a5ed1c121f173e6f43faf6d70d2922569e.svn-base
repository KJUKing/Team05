<%@page import="kr.or.ddit.image.vo.ImageVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.review.vo.ReviewVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 상세 보기</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        color: #333;
        margin: 20px;
    }

    h2 {
        text-align: center;
        color: #333;
        margin-bottom: 30px;
    }

    .review-container {
        max-width: 800px;
        margin: 0 auto;
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        border: 1px solid #ddd;
    }

    .review-info {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
    }

    .review-info td {
        padding: 10px;
        border-bottom: 1px solid #ddd;
        color: #555;
    }

    .review-info td:first-child {
        background-color: #f8f8f8;
        font-weight: bold;
        color: #333;
        width: 30%;
        text-align: left;
    }

    .review-info td:last-child {
        text-align: left;
        width: 70%;
    }

    /* 이미지 갤러리 */
    .image-gallery {
        display: flex;
        flex-wrap: wrap;
        gap: 15px;
        justify-content: flex-start;
        margin-top: 10px;
    }

    .image-gallery img {
        width: 200px;
        height: 200px;
        object-fit: cover;
        border-radius: 5px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        transition: transform 0.3s ease;
    }

    .image-gallery img:hover {
        transform: scale(1.05);
    }

    .no-images {
        text-align: center;
        color: #888;
        font-size: 14px;
    }

    /* 별점 표시 */
    .star-rating {
        color: gold;
        font-size: 18px;
    }

    .btn-group {
        text-align: center;
        margin-top: 20px;
        display: flex;
        justify-content: center;
        gap: 15px;
    }

    .btn-group input {
        padding: 10px 20px;
        font-size: 14px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        background-color: #e0d6d6;
        color: #333;
        transition: background-color 0.3s ease;
    }

    .btn-group input:hover {
        background-color: #d0cfcf;
    }

    .btn-delete {
        background-color: #f0a1a8;
        color: #fff;
    }

    .btn-delete:hover {
        background-color: #d98790;
    }
    
    
</style>
</head>
<body>

<%
    ReviewVO rvo = (ReviewVO) request.getAttribute("reviewVo");
    List<ImageVO> imageList = (List<ImageVO>) request.getAttribute("imageList");
    int starRating = rvo.getReview_star(); // 별점
    int emptyStars = 5 - starRating; // 빈 별 개수 계산
%>

<%
        String adminId = "";
        if (session.getAttribute("loggedInAdmin") != null) {
            adminId = ((kr.or.ddit.member.vo.AdminVO)session.getAttribute("loggedInAdmin")).getAdminId();
        }
%>

<h2>리뷰 상세 보기</h2>

<div class="review-container">
    <table class="review-info">
        <tbody>
            <tr>
                <td>리뷰 번호</td>
                <td><%= rvo.getReview_id() %></td>
            </tr>
            <tr>
                <td>작성자 이름</td>
                <td><%= rvo.getMem_name() %></td>
            </tr>
            <tr>
    <td>리뷰 별점</td>
    <td class="star-rating">
        <!-- 채워진 별 표시 -->
        <% for (int i = 0; i < rvo.getReview_star(); i++) { %>
            <span style="color: gold;">&#9733;</span> <!-- 노란색(금색) 채워진 별 -->
        <% } %>
        <!-- 빈 별 표시 -->
        <% for (int i = 0; i < 5 - rvo.getReview_star(); i++) { %>
            <span style="color: gold;">&#9734;</span> <!-- 노란색(금색) 빈 별 -->
        <% } %>
    </td>
</tr>

            <tr>
                <td>리뷰 내용</td>
                <td><%= rvo.getReview_content() %></td>
            </tr>
            <tr>
                <td>리뷰 작성 날짜</td>
                <td><%= rvo.getReview_date() %></td>
            </tr>
            <tr>
                <td>상품 이름</td>
                <td><%= rvo.getProd_name() %></td>
            </tr>
            <tr>
                <td>상품 옵션</td>
                <td><%= rvo.getOption_name() %></td>
            </tr>
        </tbody>
    </table>

    <!-- 이미지 갤러리 -->
    <div class="image-gallery">
        <% if (imageList != null && !imageList.isEmpty()) { %>
            <% for (ImageVO image : imageList) { %>
                <img src="<%=request.getContextPath() %>/PhotoView.do?image=<%= image.getImagePath() %>" alt="첨부 이미지">
            <% } %>
        <% } else { %>
            <p class="no-images">첨부된 이미지가 없습니다.</p>
        <% } %>
    </div>

    <!-- 수정/삭제 버튼 그룹 -->
    <div class="btn-group">
	    <% if (adminId == null || adminId.isEmpty()) { %> <!-- 관리자가 아닌 경우에만 버튼 표시 -->
	        <form method="get" action="<%= request.getContextPath() %>/reviewUpdate.do">
	            <input type="hidden" name="review_id" value="<%= rvo.getReview_id() %>">
	            <input type="submit" value="수정하기">
	        </form>
	        <form method="post" action="<%= request.getContextPath() %>/reviewDelete.do">
	            <input type="hidden" name="review_id" value="<%= rvo.getReview_id() %>">
	            <input type="submit" value="삭제하기" >
	        </form>
	    <% } %>
	    <form>
	        <input type="button" value="뒤로가기" onclick="history.back()">
	    </form>
	</div>

</div>

</body>
</html>
