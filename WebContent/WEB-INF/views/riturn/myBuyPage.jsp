<%@page import="kr.or.ddit.riturn.vo.RiturnVO"%>
<%@page import="java.util.List"%>
<%@ page import="kr.or.ddit.image.vo.ImageVO" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>나의 구매 내역</title>
<style type="text/css">
    body {
        font-family: Arial, sans-serif;
        margin: 20px;
        background-color: #f4f4f4;
    }

    h1 {
        text-align: center;
        color: #333;
        margin-bottom: 30px;
    }

    .purchase-history-container {
        width: 100%;
        max-width: 1000px;
        margin: 0 auto;
        padding: 20px;
        background-color: #fff;
        border-radius: 10px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }

    .purchase-item {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 10px 0;
        border-bottom: 1px solid #ddd;
    }

    .product-image {
        width: 100px;
        height: 100px;
        object-fit: cover;
        border: 1px solid #ddd;
        margin-right: 20px;
    }

    .product-info {
        flex: 1;
        padding-left: 15px;
    }

    .product-info p {
        margin: 5px 0;
        font-size: 14px;
        color: #333;
    }

    .buttons {
        display: flex;
        flex-direction: column;
        text-align: center;
    }

    .buttons button, .buttons input {
        margin-bottom: 5px;
        padding: 10px;
        background-color: #e0d6d6;
        border: none;
        cursor: pointer;
        width: 100px;
        border-radius: 5px;
    }

    .buttons button:hover, .buttons input:hover {
        background-color: #d0cfcf;
    }

    .btn {
        width: 100%;
        padding: 10px;
        margin-top: 15px;
        background-color: #e0d6d6; /* 리뷰 리스트 페이지의 버튼 색상과 일치 */
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 14px;
        color: #333;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }

    .btn:hover {
        background-color: #d0cfcf; /* 호버 시 조금 더 진한 색상 */
    }
    h3{
    	text-align:center;
    }
</style>
<script type="text/javascript">
window.onload = function() {  
    document.querySelector('.btn-main').onclick = function() {
        window.location.href = '<%=request.getContextPath()%>/main'; // 메인 페이지로 이동
    };
}
</script>
</head>
<body>
    <div class="purchase-history-container">
        <h3>나의 구매 내역</h3>
        <% List<RiturnVO> riturnList = (List<RiturnVO>)request.getAttribute("riturnList");
           Map<String, ImageVO> imagesMap = (Map<String, ImageVO>) request.getAttribute("imagesMap");
        %>
			
        <%
            String currentCartId = ""; 
            for (int i = 0; i < riturnList.size(); i++) { 
                RiturnVO riList = riturnList.get(i);
                
                // 새로운 cart_id가 나올 때마다 새로운 div를 생성
                if (!riList.getCart_id().equals(currentCartId)) { 
                    currentCartId = riList.getCart_id(); 
        %>
        <div class="purchase-item">
            <%
                String prodId = riturnList.get(i).getProd_id();
                ImageVO image = imagesMap.get(prodId); //상품에 맞는 이미지 갖고오기
            %>
            <% if (image != null) { %>
            <img src="<%= request.getContextPath() %>/images/prodImageView.do?imgId=<%= image.getImageId() %>" alt="첨부 이미지"
            class="product-image">
            <% } else { %>
            <p>이미지가 없습니다.</p>
            <% } %>
            <div class="product-info">
                <p><strong><%=riList.getProd_name() %></strong>
                    <% 
                        // 같은 cart_id 내에 상품이 여러 개일 경우 외 몇 개 출력
                        int cartItemCount = 0;
                        for (RiturnVO item : riturnList) {
                            if (item.getCart_id().equals(riList.getCart_id())) {
                                cartItemCount++;
                            }
                        }
                        if(cartItemCount > 1) { %>
                        	외 <%=cartItemCount - 1 %>개
                    <% } %>
                </p>
                <p><%=riList.getOption_name()%>/<%=riList.getOption_value()%> <%=riList.getDcart_qty()%>개</p>
                <p>상품 금액 : <%=riList.getProd_price()%>원</p>
                <p>구매한 총 금액 : <%=riList.getPay_price()%>원</p>
            </div>
            <div class="buttons">
                <form action="<%=request.getContextPath()%>/express/trackDelivery.do?cart_id=<%=riList.getCart_id()%>" method="get">
                    <input type="hidden" name="cartId" value="<%=riList.getCart_id()%>">
                    <input type="submit" value="배송 조회">
                </form>
                
                <form action="<%=request.getContextPath()%>/riturn/riturnProd.do?cart_id=<%=riList.getCart_id()%>" method="post">
                    <input type="hidden" name="cartId" value="<%=riList.getCart_id()%>">
                    <input type="submit" value="반품">
                </form>
               

                <form action="<%=request.getContextPath()%>/review/insertReview.do">
				<input type="submit" value="리뷰 작성">
                </form>
            </div>
        </div>
        <% } } %>
        <input type="button" class="btn btn-main" value="메인으로 돌아가기">
    </div>
</body>
</html>
