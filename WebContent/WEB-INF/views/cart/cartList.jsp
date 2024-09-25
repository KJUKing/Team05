<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.image.vo.ImageVO"%>
<%@page import="kr.or.ddit.cart.vo.CartVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>장바구니 목록</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/jquery-3.7.1.js"></script>
    <style>
        body {
    font-family: Arial, sans-serif;
    margin: 20px;
    background-color: #f7f7f7; /* 아이보리 배경 */
}

.container {
    width: 80%;
    margin: 0 auto;
    background-color: #fff; /* 흰색 배경 */
    padding: 20px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    border-radius: 10px;
}

.title {
    text-align: center;
    font-size: 24px;
    margin-bottom: 20px;
    color: #333;
}

.cart-list, .cart-info {
    width: 100%;
    margin-bottom: 20px;
}

.cart-item {
    border: 1px solid #ddd;
    padding: 10px;
    margin-bottom: 10px;
    width: 100%; /* 전체 너비에 맞게 설정 */
    display: flex;
    align-items: center;
    background-color: #f9f9f9; /* 연한 회색 배경 */
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
}

.cart-item img {
    width: 150px; /* 이미지 크기 조정 */
    height: auto;
    margin-right: 20px;
    border-radius: 5px;
}

.cart-details {
    flex: 2; /* 내용이 넓은 영역을 차지하도록 */
    padding-right: 20px; /* 오른쪽 여백 추가 */
}

.cart-details p {
    margin: 5px 0;
    color: #333;
}

.buttons {
    display: flex;
    flex-direction: column; /* 버튼을 세로로 배치 */
    justify-content: space-between;
}

.btn {
    padding: 10px 20px;
    font-size: 16px;
    margin: 5px 0; /* 버튼 간격을 세로로 조정 */
    cursor: pointer;
    border-radius: 5px;
    border: none;
}

.btn-buy {
    background-color: #b9a69b;
    color: white;
}

.btn-cancel {
    background-color: #d6cfcf;
    color: #333;
}

.btn:hover {
    background-color: #d0cfcf; /* hover 시 더 어두운 색 */
}

.cart-info {
    border: 1px solid #ddd;
    padding: 20px;
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    width: 80%; /* container와 맞춰서 크기 설정 */
    margin: 0 auto; /* 중앙 정렬 */
}


.btn-buy-all {
    background-color: #b9a69b;
    color: white;
}

.btn-buy-all:hover {
    background-color: #a5948a;
}
.total-price{
	text-align : center;
}
h3{
	text-align : center;
}
    </style>

</head>
<body>
<div class="container">
    <h3>장바구니 목록</h3>
    

    <div class="cart-list">
	    <% 
	        List<CartVO> cartList = (List<CartVO>)request.getAttribute("cartList");
	        Map<String, ImageVO> imagesMap = (Map<String, ImageVO>) request.getAttribute("imagesMap");
	        double totalPrice = 0; // 총 가격 계산을 위한 변수 선언
	        
	        if (cartList != null && !cartList.isEmpty()) {
	            for (CartVO cartVo : cartList) {
	                double productPrice = cartVo.getProdPrice() * cartVo.getDcartQty(); // 개별 상품 가격 계산
	                totalPrice += productPrice; // 총 가격 누적
	    %>
	        <div class="cart-item" data-cart-id="<%=cartVo.getCartId()%>">
	            
	            <!-- 상품에 맞는 이미지 출력 -->
	            <% 
	                String prodId = cartVo.getProdId();
	                ImageVO image = imagesMap.get(prodId);  // 상품 ID에 맞는 이미지 가져오기
	            %>
	            <% if (image != null) { %>
	                <img src="<%=request.getContextPath() %>/images/prodImageView.do?imgId=<%= image.getImageId() %>" alt="첨부 이미지">
	            <% } else { %>
	                <p>이미지가 없습니다.</p>
	            <% } %>
	
	            <div class="cart-details">
	                <p>상품명: <%= cartVo.getProdName() %></p>
	                <p>옵션: <%= cartVo.getOptionName() %></p>
	                <p>수량: <%= cartVo.getDcartQty() %>개</p>
	                <p>가격: ₩<%= String.format("%,d", (int) cartVo.getCartPrice()) %>원</p>
	            </div>
	
	            <div class="buttons">
	                <input type="button" class="btn btn-buy" value="구매하기"
	                       onclick="window.location.href='<%=request.getContextPath()%>/cart/CartToPaymentList.do?cartId=<%=cartVo.getCartId()%>';"/>
	                <input type="button" class="btn btn-cancel" value="삭제"
	                       onclick="removeCartItem('<%=cartVo.getCartId()%>')"/>
	            </div>
	        </div>
	    <% 
	            } 
	        } else { 
	    %>
	        <p>장바구니에 상품이 없습니다.</p>
	    <% } %>
	</div>

    <div class="cart-info">
        <p class="total-price">총 가격: ₩<span id="totalPrice"><%= String.format("%,d", (int) totalPrice) %></span>원</p>

        <!-- 일괄구매 버튼 추가 -->
        <div class="buttons">
            <input type="button" class="btn btn-buy-all" value="일괄구매"
                   onclick="window.location.href='<%=request.getContextPath()%>/cart/CartToPaymentList.do?action=buyAll'"/>
        </div>
    </div>


</div>

<script>
    function removeCartItem(cartId) {
        if (confirm("해당 상품을 장바구니에서 삭제하시겠습니까?")) {
            $.ajax({
                url: '<%=request.getContextPath()%>/cart/removeCartItem.do',
                type: 'POST',
                data: {cart_id: cartId},
                success: function(res) {
                	// 서버에서 success 상태를 반환하면 DOM에서 해당 상품 삭제
                    if (res.status === "success") {
                        alert(res.message);
                        // 해당 cart-item 요소를 찾아서 삭제
                        $('div[data-cart-id="' + cartId + '"]').remove();
                    	window.location.reload(); // 페이지 새로고침
                    } else {
                        alert(res.message);
                    }
                },
                error: function(xhr) {
                    alert("삭제 중 오류 발생: " + xhr.status);
                }
            });
        }
    }
</script>
</body>
</html>
