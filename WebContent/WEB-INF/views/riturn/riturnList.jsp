<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.image.vo.ImageVO"%>
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
    <% 
    	List<RiturnVO> riList = (List<RiturnVO>)request.getAttribute("riList"); 
    	Map<String, ImageVO> imagesMap = (Map<String, ImageVO>) request.getAttribute("imagesMap");
    %>
    <style>
    body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 20px;
    background-color: #ffffff; /* 흰색 배경 */
    display: flex;
    flex-direction: column;
    min-height: 100vh;
}

.container {
    width: 100%;
    max-width: 900px;
    margin: 0 auto;
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
    background-color: #fafafa; /* 연한 회색 배경 */
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
    color: #333; /* 텍스트 진한 회색 */
}

button.home-button, button.back-button {
    padding: 10px 20px;
    background-color: #e0d6d6; /* 연한 베이지색 */
    border: none;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
    color: #fff; /* 버튼 텍스트 흰색 */
    transition: background-color 0.3s;
    margin-right: 10px; /* 버튼 간 간격 추가 */
}

button.home-button:hover, button.back-button:hover {
    background-color: #d0cfcf; /* 버튼 호버 시 더 진한 색 */
}


.footer {
    background-color: #e8e4d9; /* 푸터 색상 */
    text-align: center;
    padding: 10px 0;
    color: #333;
    margin-top: auto;
    width: 100%;
}
h3{
	text-align:center;
}
    </style>
</head>
<body>

<div class="container">
    <h3>반품 내역</h3>

    <div class="product-list">
    	<% for(RiturnVO ri : riList){%>
        <div class="product-item">
            <!-- 상품에 맞는 이미지 출력 -->
	            <% 
	                String prodId = ri.getProd_id();
	                ImageVO image = imagesMap.get(prodId);  // 상품 ID에 맞는 이미지 가져오기
	            %>
	            <% if (image != null) { %>
	                <img class="product-image"  src="<%=request.getContextPath() %>/images/prodImageView.do?imgId=<%= image.getImageId() %>" alt="첨부 이미지">
	            <% } else { %>
	                <p>이미지가 없습니다.</p>
	            <% } %>
            <div class="product-details">
                <p>회원 ID: <%=ri.getMem_id()%></p>
                <p>회원 이름: <%=ri.getMem_name()%></p>
                <p>상품 이름: <%=ri.getProd_name()%></p>
                <p>주문 번호: <%=ri.getPay_id() %></p>
                <p>반품 날짜: <%=ri.getRe_date() %></p>
                <p>반품 사유: <%=ri.getRe_reason() %></p>
            </div>
        </div>
		<%} %>
    </div>

    <div>
        <button class="back-button" onclick="history.back()">뒤로가기</button>
        <button class="home-button" onclick="location.href='<%=request.getContextPath()%>/main'">홈으로</button>
    </div>
</div>

</body>
</html>
