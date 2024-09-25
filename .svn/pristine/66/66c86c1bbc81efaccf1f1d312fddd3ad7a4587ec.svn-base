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
        }

        .container {
            width: 80%;
            margin: 0 auto;
        }

        .title {
            text-align: center;
            font-size: 24px;
            margin-bottom: 20px;
        }

        .cart-list, .cart-info {
            display: inline-block;
            width: 100%;
            vertical-align: top;
            margin-bottom: 20px;
        }

        .cart-item {
            border: 1px solid #ccc;
            padding: 10px;
            margin-bottom: 10px;
            width: 100%;
            display: flex;
            align-items: center;
        }

        .cart-item img {
            width: 100px;
            margin-right: 20px;
        }

        .cart-details {
            flex: 1;
        }

        .cart-info {
            border: 1px solid #ccc;
            padding: 10px;
            margin-bottom: 10px;
            width: 100%;
        }

        .total-price {
            text-align: left;
            margin-top: 20px;
        }

        .buttons {
            text-align: center;
            margin-top: 20px;
        }

        .btn {
            padding: 10px 20px;
            font-size: 16px;
            margin: 5px;
            cursor: pointer;
        }

        .btn-buy {
            background-color: red;
            color: white;
            border: none;
        }

        .btn-cancel {
            background-color: gray;
            color: white;
            border: none;
        }
    </style>

</head>
<body>
<div class="container">
    <div class="title">장바구니 목록</div>
    

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
                   onclick="window.location.href='<%=request.getContextPath()%>/cart/CartToPaymentList.do?action=buyAll';"/>
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
