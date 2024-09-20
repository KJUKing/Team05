<%@page import="kr.or.ddit.riturn.vo.RiturnVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>반품 내역</title>
    <% List<RiturnVO> riList = (List<RiturnVO>)request.getAttribute("riList"); %>
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
            width: 70%;
            padding: 30px;
            background-color: #fff;
            border: 1px solid #ddd;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h2 {
            font-size: 28px;
            margin-bottom: 30px;
            border-bottom: 1px solid #ddd;
            padding-bottom: 10px;
        }

        .product-list {
            margin-bottom: 40px;
        }

        .product-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
            padding: 10px;
            border: 1px solid #ddd;
        }

        .product-image {
            width: 150px;
            height: 150px;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 14px;
            color: #666;
        }

        .product-details {
            flex-grow: 1;
            margin-left: 20px;
            text-align: left;
        }

        .product-details p {
            margin: 5px 0;
        }

        .home-button {
            padding: 10px 20px;
            background-color: #ddd;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .home-button:hover {
            background-color: #ccc;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>반품 내역</h2>

    <div class="product-list">
    	<% for(RiturnVO ri : riList){%>
        <div class="product-item">
            <div class="product-image"><%=ri.getProd_image() %></div>
            <div class="product-details">
                <p>상품 이름: <%=ri.getProd_name()%></p>
                <p>주문 번호: <%=ri.getPay_id() %></p>
                <p>반품 날짜: <%=ri.getRe_date() %></p>
                <p>반품 사유: <%=ri.getRe_reason() %></p>
            </div>
        </div>
		<%} %>
    </div>

    <button class="home-button" onclick="location.href='<%=request.getContextPath()%>/main'">홈으로</button>
</div>

</body>
</html>
