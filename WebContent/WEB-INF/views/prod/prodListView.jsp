<%@ page import="kr.or.ddit.prod.vo.OptionVO" %>
<%@ page import="kr.or.ddit.prod.vo.ProdVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상품 상세 페이지</title>
    <script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.7.1.js"></script>
    <style>
        /* 기존 스타일 */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .header {
            text-align: center;
            margin-bottom: 20px;
        }
        .product {
            display: flex;
            flex-wrap: wrap;
            max-width: 1000px;
            width: 100%;
        }
        .product-image {
            flex: 1;
            min-width: 300px;
            margin-right: 20px;
        }
        .product-image img {
            width: 100%;
            height: auto;
            border-radius: 8px;
        }
        .product-details {
            flex: 2;
            min-width: 300px;
        }
        .product-details h1 {
            margin-top: 0;
        }
        .product-details .price {
            font-size: 24px;
            color: #e74c3c;
            margin: 15px 0;
        }
        .product-details .description {
            margin: 15px 0;
            line-height: 1.6;
        }
        .buy-button {
            padding: 15px 30px;
            font-size: 18px;
            color: #fff;
            background: linear-gradient(45deg, #007bff, #0056b3);
            border: none;
            border-radius: 50px;
            cursor: pointer;
            transition: background 0.3s, transform 0.3s, box-shadow 0.3s;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        .buy-button:hover {
            background: linear-gradient(45deg, #0056b3, #007bff);
            transform: scale(1.05);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3);
        }
        .quantity-container {
            display: flex;
            align-items: center;
            margin: 20px 0;
        }
        .quantity-button {
            font-size: 24px;
            width: 50px;
            height: 50px;
            display: flex;
            align-items: center;
            justify-content: center;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 50%;
            cursor: pointer;
            transition: background-color 0.3s, box-shadow 0.3s;
        }
        .quantity-button:hover {
            background-color: #0056b3;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        .quantity-input {
            width: 80px;
            padding: 10px;
            text-align: center;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 20px;
            margin: 0 10px;
            /* Hide arrows in number input */
            -moz-appearance: textfield; /* Firefox */
            -webkit-appearance: none; /* Chrome, Safari, Edge */
        }
        .quantity-input::-webkit-inner-spin-button,
        .quantity-input::-webkit-outer-spin-button {
            -webkit-appearance: none; /* Chrome, Safari, Edge */
            margin: 0; /* Remove default margin */
        }
        select {
            font-size: 18px;
            padding: 10px;
            border-radius: 4px;
            border: 1px solid #ddd;
            width: 100%;
            max-width: 300px; /* 원하는 최대 너비로 조정 */
        }
        .info-container {
            display: flex;
            justify-content: space-between;
            width: 100%;
            max-width: 600px; /* 원하는 최대 너비로 조정 */
            margin: 20px 0;
        }
        .info-container p {
            font-size: 18px;
            margin: 0;
            font-weight: bold;
        }
        .info-container .price, .info-container .quantity {
            color: #333;
            background-color: #e9ecef;
            padding: 10px;
            border-radius: 5px;
        }
        .wishlist-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            position: absolute;
            bottom: 50px; /* 페이지 하단에서 50px 위로 설정 */
            right: 50px; /* 오른쪽에서 50px 떨어진 위치 */
            text-align: center;
        }

        .wishlist-container p {
            font-size: 16px;
            margin-bottom: 5px;
            font-weight: bold;
        }

        .wishlist-container a img {
            width: 40px; /* 버튼 이미지 크기 */
            height: 40px;
        }

        /* 컨테이너 스타일 */
        .container {
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            padding: 40px;
        }

        .product-image {
            flex: 2;
            position: relative;
        }

        .product-image img {
            width: 100%;
            height: auto;
            display: block;
        }

        /* 화살표 스타일 */
        .arrow-left, .arrow-right {
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
            background-color: rgba(0, 0, 0, 0.5);
            color: white;
            padding: 10px;
            cursor: pointer;
        }

        .arrow-left {
            left: 10px;
        }

        .arrow-right {
            right: 10px;
        }

        /* 상품 정보 스타일 */
        .product-details {
            flex: 3;
            padding-left: 40px;
        }

        /* 위시리스트, 좋아요, 장바구니 버튼 스타일 */
        .buttons-container {
            display: flex;
            justify-content: flex-start;
            gap: 20px;
            margin-top: 20px;
        }

        .buttons-container img {
            width: 40px;
            height: 40px;
        }

        /* 상품 옵션 선택 및 QnA 섹션 */
        .product-options, .qna-section {
            margin-top: 40px;
        }

        /* QnA 스타일 */
        .qna-section {
            margin-top: 60px;
        }

        .qna-section h2 {
            margin-bottom: 10px;
        }

    </style>
</head>
<body>
<%
    List<ProdVO> prodList = (List<ProdVO>) request.getAttribute("prodList");
    List<OptionVO> optionList = (List<OptionVO>) request.getAttribute("optionList");
    ProdVO pvo = prodList != null && !prodList.isEmpty() ? prodList.get(0) : new ProdVO(); // 첫 번째 상품을 예로 설정
%>
<div class="container">
    <div class="header">
        <h1>상품 상세 페이지</h1>
    </div>
    <div class="product">
        <!-- 상품 이미지와 화살표 -->
        <div class="product-image">
            <img src="<%= request.getContextPath() %>/images/prod/<%= pvo.getProd_image() %>" alt="<%= pvo.getProd_name() %>">
            <span class="arrow-left">&lt;</span>
            <span class="arrow-right">&gt;</span>
        </div>
        <div class="product-details">
            <h1><%= pvo.getProd_name() %></h1>
            <p class="price"><%= String.format("₩%,d", pvo.getProd_price()) %></p>
            <p class="description">
                <%= pvo.getProd_content() %>
            </p>
        </div>

        <!-- 버튼들 -->
        <div class="buttons-container">
            <!-- 위시리스트 버튼 -->
            <div class="wishlist-container">
                <p>위시리스트</p>
                <a href="javascript:void(0);"
                   id="wishlist-button-<%= pvo.getProd_id() %>"
                   onclick="wishToggle('<%= pvo.getProd_id() %>', this)">
                    <!-- 초기값을 inactive로 설정 -->
                    <img id="toggle-image-<%= pvo.getProd_id() %>"
                         src="<%= request.getContextPath() %>/images/inactive.png" alt="wishlist"/>
                </a>
            </div>
            <!-- 좋아요 버튼 -->
            <div class="likes-container">
                <p>좋아요</p>
                <a href="javascript:void(0);"
                   id="likes-button-<%= pvo.getProd_id() %>"
                   onclick="likesToggle('<%= pvo.getProd_id() %>', this)">
                    <!-- 초기값을 unlike로 설정 -->
                    <img id="toggle-likes-image-<%= pvo.getProd_id() %>"
                         src="<%= request.getContextPath() %>/images/unlike.jpg" alt="likes"/>
                </a>
            </div>
            <div class="cart">
                <img src="<%= request.getContextPath() %>/images/cart.png" alt="cart" />
            </div>
        </div>
    </div>

    <!-- 상품 옵션 선택 및 수량 조정 -->
    <div class="product-options">
        <h2>상품 옵션 선택</h2>
        <form id="optionForm" action="<%= request.getContextPath() %>/payMent.do" method="post">
            <input type="hidden" name="prod_id" value="<%= pvo.getProd_id() %>">
            <input type="hidden" name="quantity" id="modal-quantity" value="1">

            <p>상품 옵션 </p>
            <select id="option2" name="option2">
                <%
                    for (OptionVO option : optionList) {
                %>
                <option value="<%= option.getOption_id() %>"><%= option.getOption_name() %>-<%= option.getOption_value() %></option>
                <%
                    }
                %>
            </select>

            <br><br>
            <div class="quantity-container">
                <button type="button" class="quantity-button" id="decreaseQuantity">-</button>
                <input type="number" id="modal-quantity-input" class="quantity-input" value="1" min="1">
                <button type="button" class="quantity-button" id="increaseQuantity">+</button>
            </div>
            <div class="info-container">
                <p class="quantity">현재 수량: <span id="currentQuantity">1</span></p>
                <p class="price">가격: <span id="modal-price">₩<%= String.format("%,d", pvo.getProd_price()) %></span></p>
            </div>
            <button type="submit" class="buy-button">확인</button>
        </form>
    </div>
</div>

<script>
    //상세페이지열면 내 memId로 상품이 위시리스트에 등록되어있는지 확인함
    $(document).ready(function() {
        const prodId = '<%= pvo.getProd_id() %>'; // 현재 상품의 ID
        $.ajax({
            url: '<%= request.getContextPath() %>/wish/checkWishList.do',
            type: 'GET',
            data: { prodId: prodId },
            success: function(data) {
                const wishlistButton = $('#wishlist-button-' + prodId);
                const wishlistImage = $('#toggle-image-' + prodId);

                if (data.isWishlisted) {
                    // 위시리스트에 추가된 상태면 active 상태로 버튼과 이미지 변경
                    wishlistButton.attr('data-status', 'active');
                    wishlistImage.attr('src', '<%= request.getContextPath() %>/images/active.png');
                } else {
                    // 위시리스트에 추가되지 않은 상태면 inactive 상태로 버튼과 이미지 변경
                    wishlistButton.attr('data-status', 'inactive');
                    wishlistImage.attr('src', '<%= request.getContextPath() %>/images/inactive.png');
                }
            },
            error: function(xhr) {
                console.log('오류 발생: ' + xhr.status);
            }
        });
    });

    $(document).ready(function() {
        const prodId = '<%= pvo.getProd_id() %>'; // 현재 상품의 ID
        // 좋아요 상태 확인을 위한 AJAX 요청
        $.ajax({
            url: '<%= request.getContextPath() %>/likes/checkLikes.do',
            type: 'GET',
            data: { prodId: prodId },
            success: function(data) {
                const likesButton = $('#likes-button-' + prodId);
                const likesImage = $('#toggle-likes-image-' + prodId);

                if (data.isLikesChecked) {
                    // 좋아요가 눌린 상태면 liked 상태로 버튼과 이미지 변경
                    likesButton.attr('data-status', 'liked');
                    likesImage.attr('src', '<%= request.getContextPath() %>/images/like.jpg');
                } else {
                    // 좋아요가 눌리지 않은 상태면 unliked 상태로 버튼과 이미지 변경
                    likesButton.attr('data-status', 'unliked');
                    likesImage.attr('src', '<%= request.getContextPath() %>/images/unlike.jpg');
                }
            },
            error: function(xhr) {
                console.log('좋아요 상태 확인 오류 발생: ' + xhr.status);
            }
        });
    });


    // 수량 조정 및 가격 업데이트 기능
    var quantityInput = document.getElementById("modal-quantity-input");
    var modalPriceSpan = document.getElementById("modal-price");
    var currentQuantitySpan = document.getElementById("currentQuantity");
    var basePrice = <%= pvo.getProd_price() %>;

    // 수량 증가 버튼 클릭 시
    document.getElementById("increaseQuantity").onclick = function() {
        var quantity = parseInt(quantityInput.value, 10);
        quantityInput.value = quantity + 1;
        updatePrice();
    }

    // 수량 감소 버튼 클릭 시
    document.getElementById("decreaseQuantity").onclick = function() {
        var quantity = parseInt(quantityInput.value, 10);
        if (quantity > 1) {
            quantityInput.value = quantity - 1;
            updatePrice();
        }
    }

    // 수량 변경 시 가격 업데이트
    quantityInput.addEventListener('change', function() {
        updatePrice();
    });

    // 가격 업데이트 함수
    function updatePrice() {
        var quantity = parseInt(quantityInput.value, 10);
        if (isNaN(quantity) || quantity < 1) {
            quantity = 1;
        }
        var totalPrice = basePrice * quantity;
        modalPriceSpan.innerText = '₩' + totalPrice.toLocaleString();
        currentQuantitySpan.innerText = quantity;
    }

    // 페이지 로드 시 초기 가격 설정
    window.onload = function() {
        updatePrice(); // 페이지 로드 시 가격을 설정합니다.
    };

    // 위시리스트 활성화/비활성화 상태 변경 함수
    const wishToggle = (prodId, element) => {
        // 버튼의 현재 상태를 data-status 속성에서 가져옴
        const currentStatus = $(element).attr('data-status');
        console.log("최근 상태: ", currentStatus);

        const newStatus = currentStatus === 'active' ? 'inactive' : 'active';
        console.log("삼항연산 후 상태: ", newStatus);

        // Ajax로 상태 변경 후 UI 업데이트
        updateStatusOnServer(prodId, newStatus, element);
    };

    // Ajax 상태 변경 요청 함수
    const updateStatusOnServer = (prodId, newStatus, element) => {
        $.ajax({
            url: '<%=request.getContextPath()%>/wish/toggleWishStatus.do', // 서버로 요청 전송
            type: 'POST',
            data: {prod_id: prodId, status: newStatus}, // prod_id와 status를 서버로 전송
            success: () => {
                console.log("서버 상태 변경 완료: " + newStatus);

                // UI 업데이트 (이미지와 텍스트 변경)
                if (newStatus === 'inactive') {
                    $(element).find('img').attr('src', '<%=request.getContextPath()%>/images/inactive.png');
                    $(element).attr('data-status', 'inactive'); // 상태 갱신

                } else {
                    $(element).find('img').attr('src', '<%=request.getContextPath()%>/images/active.png');
                    $(element).attr('data-status', 'active'); // 상태 갱신
                }
            },
            error: xhr => {
                alert("오류 발생: " + xhr.status);
            }
        });
    }

    // 좋아요 활성화/비활성화 상태 변경 함수
    const likesToggle = (prodId, element) => {
        // 버튼의 현재 상태를 data-status 속성에서 가져옴
        const currentStatus = $(element).attr('data-status');
        console.log("현재 좋아요 상태: ", currentStatus);

        const newStatus = currentStatus === 'liked' ? 'unliked' : 'liked';
        console.log("새로운 좋아요 상태: ", newStatus);

        // Ajax로 상태 변경 후 UI 업데이트
        updateLikesStatusOnServer(prodId, newStatus, element);
    };

    // Ajax 좋아요 상태 변경 요청 함수
    const updateLikesStatusOnServer = (prodId, newStatus, element) => {
        $.ajax({
            url: '<%= request.getContextPath() %>/likes/toggleLikesStatus.do', // 서버로 요청 전송
            type: 'POST',
            data: {prod_id: prodId, status: newStatus}, // prod_id와 status를 서버로 전송
            success: () => {
                console.log("서버에서 좋아요 상태 변경 완료: " + newStatus);

                // UI 업데이트 (이미지와 텍스트 변경)
                if (newStatus === 'unliked') {
                    $(element).find('img').attr('src', '<%=request.getContextPath()%>/images/unlike.jpg');
                    $(element).attr('data-status', 'unliked'); // 상태 갱신
                } else {
                    $(element).find('img').attr('src', '<%=request.getContextPath()%>/images/like.jpg');
                    $(element).attr('data-status', 'liked'); // 상태 갱신
                }
            },
            error: xhr => {
                alert("좋아요 상태 변경 오류 발생: " + xhr.status);
            }
        });
    };
</script>
</body>
</html>
