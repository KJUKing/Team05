<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.image.vo.ImageVO"%>
<%@page import="kr.or.ddit.review.vo.ReviewVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 상세 보기</title>
<style>
   <style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f1e8; /* 아이보리 계열 배경 */
        margin: 20px;
        color: #333;
    }

    h2 {
        color: #4b4b4b;
        text-align: center;
        margin-bottom: 20px;
    }

    table {
        width: 100%;
        max-width: 800px;
        margin: 0 auto;
        border-collapse: collapse;
        background-color: #fff;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    td {
        padding: 15px;
        border-bottom: 1px solid #ddd;
        color: #555;
    }

    td:first-child {
        background-color: #f7f4e8; /* 조금 더 진한 아이보리 배경 */
        font-weight: bold;
        color: #333;
        width: 30%;
    }

    tr:last-child td {
        border-bottom: none;
    }

    .image-gallery {
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        gap: 10px;
        margin-top: 20px;
    }

    .image-gallery img {
        width: 150px;
        height: 150px;
        object-fit: cover;
        border-radius: 8px;
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
     .btn-group {
        text-align: center;
        margin-top: 20px;
    }

    .btn-group input {
        padding: 10px 20px;
        margin: 5px;
        font-size: 16px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        background-color: #e0d3b2;
        color: #333;
        transition: background-color 0.3s ease;
    }

    .btn-group input:hover {
        background-color: #d0c0a4;
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
%>

<h2>리뷰 상세 보기</h2>

<table>
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
            <td><%= rvo.getReview_star() %></td>
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
        <tr>
            <td>첨부 이미지</td>
            <td>
                <div class="image-gallery">
                <h3>기존 이미지</h3>
                
                <% if (imageList != null && !imageList.isEmpty()) { %>
                    <% for (ImageVO image : imageList) { 
                        // 이미지 경로에서 파일명만 추출하여 PhotoViewNotice 서블릿으로 전달
                        String fileName = image.getImagePath().substring(image.getImagePath().lastIndexOf('/') + 1);
                    %>
                        <img src="<%=request.getContextPath() %>/PhotoView.do?image=<%= image.getImagePath() %>" alt="첨부 이미지" class="notice-image">
                    <% } %>
                <% } else { %>
                    <p>첨부된 이미지가 없습니다.</p>
                <% } %>
                </div>
            </td>
        </tr>
    </tbody>
    
</table>
<!-- 버튼 그룹 -->
<div class="btn-group">
    <form method="get" action="<%= request.getContextPath() %>/reviewUpdate.do">
        <input type="hidden" name="review_id" value="<%= rvo.getReview_id() %>">
        <input type="submit" value="수정하기">
    </form>
    <form method="post" action="<%= request.getContextPath() %>/reviewDelete.do">
        <input type="hidden" name="review_id" value="<%= rvo.getReview_id() %>">
        <input type="submit" value="삭제하기" class="btn-delete">
    </form>
</div>

</body>
</html>
