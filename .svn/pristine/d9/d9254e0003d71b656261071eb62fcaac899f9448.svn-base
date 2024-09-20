<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="kr.or.ddit.review.vo.ReviewVO" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>리뷰 작성</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f9f9f9;
        }
        h1 {
            color: #333;
        }
        p {
            color: #ff0000;
        }
        form {
            background-color: #fff;
            padding: 20px;
            border: 1px solid #ddd;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        input, textarea, select {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border-radius: 5px;
            border: 1px solid #ddd;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h1>리뷰 작성</h1>

    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
        <p><%= errorMessage %></p>
    <%
        }

        // productInfoList를 가져옴
        List<ReviewVO> productInfoList = (List<ReviewVO>) request.getAttribute("productInfoList");

        if (productInfoList == null || productInfoList.isEmpty()) {
    %>
        <p>결제된 장바구니가 없습니다. 리뷰를 작성할 수 없습니다.</p>
    <%
        } else {
    %>

    <form action="<%= request.getContextPath() %>/review/insertReview.do" method="post" enctype="multipart/form-data">
        <label for="cartId">결제된 장바구니 선택:</label>
        <select id="cartId" name="cartId" onchange="updateHiddenFields()" required>
            <option value="">장바구니 선택</option>
            <% for (ReviewVO productInfo : productInfoList) { %>
                <option value="<%= productInfo.getCart_id() %>">
                    장바구니: <%= productInfo.getCart_id() %> | 상품: <%= productInfo.getProd_name() %> | 옵션: <%= productInfo.getOption_name() %> - <%= productInfo.getOption_value() %>
                </option>
            <% } %>
        </select><br>

        <!-- Hidden inputs for prodId and optionId -->
        <input type="hidden" id="prodId" name="prodId" value="">
        <input type="hidden" id="optionId" name="optionId" value="">

        <label for="reviewContent">리뷰 내용:</label>
        <textarea id="reviewContent" name="reviewContent" rows="5" required></textarea><br>

        <label for="reviewStar">별점:</label>
        <input type="number" id="reviewStar" name="reviewStar" min="1" max="5" required><br>

        <label for="reviewImage">이미지 업로드:</label>
        <input type="file" id="reviewImage" name="reviewImage" multiple><br>

        <input type="submit" value="리뷰 작성">
    </form>

    <%
        }
    %>

    <script>
        var productInfoList = [];

        <% if (productInfoList != null) { %>
            <% for (ReviewVO productInfo : productInfoList) { %>
                productInfoList.push({
                    cart_id: "<%= productInfo.getCart_id() %>",
                    prod_id: "<%= productInfo.getProd_id() %>",
                    option_id: "<%= productInfo.getOption_id() %>",
                    prod_name: "<%= productInfo.getProd_name() %>",
                    option_name: "<%= productInfo.getOption_name() %>",
                    option_value: "<%= productInfo.getOption_value() %>"
                });
            <% } %>
        <% } %>

        console.log(productInfoList);

        function updateHiddenFields() {
            var selectedCartId = document.getElementById("cartId").value;
            var prodIdField = document.getElementById("prodId");
            var optionIdField = document.getElementById("optionId");

            var selectedProduct = productInfoList.find(function(product) {
                return product.cart_id === selectedCartId;
            });

            if (selectedProduct) {
                prodIdField.value = selectedProduct.prod_id;
                optionIdField.value = selectedProduct.option_id;
            } else {
                prodIdField.value = "";
                optionIdField.value = "";
            }
        }
    </script>
</body>
</html>
