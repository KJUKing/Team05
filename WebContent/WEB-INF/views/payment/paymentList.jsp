<%@page import="kr.or.ddit.image.vo.ImageVO"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.payment.vo.PaymentVO" %>
<%@page import="java.util.List" %>
<%@ page import="kr.or.ddit.image.vo.ImageVO" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>결제 페이지</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/jquery-3.7.1.js"></script>
    <style>
        body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background-color: #f8f9fa; /* 배경색을 부드러운 회색으로 설정 */
    color: #333;
	}
	
	.container {
	    width: 80%; /* 전체 컨테이너 너비 설정 */
	    margin: 0 auto;
	    padding: 20px;
	    background-color: #fff;
	    border-radius: 8px;
	    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
	    box-sizing: border-box; /* padding과 border를 포함한 크기 조정 */
	}
	
	.prod-list, .user-info, .delivery-info {
	    width: 100%; /* 모든 박스의 너비를 100%로 설정 */
	    margin-bottom: 20px;
	    box-sizing: border-box; /* padding과 border를 포함한 크기 조정 */
	}
	
	
	.prod-item {
	    border: 1px solid #e0e0e0;
	    padding: 15px;
	    margin-bottom: 15px;
	    width: 100%; /* 박스 너비를 100%로 설정하여 부모 컨테이너에 맞춤 */
	    display: flex;
	    justify-content: flex-start; /* 이미지와 텍스트를 왼쪽 정렬 */
	    align-items: center; /* 이미지와 텍스트 수직 중앙 정렬 */
	    border-radius: 6px;
	    background-color: #fafafa;
	    box-sizing: border-box; /* padding과 border를 포함한 크기 조정 */
	}
	
	.prod-item img {
	    width: 120px; /* 이미지 크기 설정 */
	    margin-right: 20px; /* 이미지와 텍스트 사이의 간격 */
	    border-radius: 4px;
	}
	
	.prod-details {
	    flex: 1; /* 텍스트가 남은 공간을 차지 */
	    font-size: 16px;
	    color: #444;
	}
	
	.user-info, .delivery-info {
	    border: 1px solid #e0e0e0;
	    padding: 15px;
	    margin-bottom: 15px;
	    border-radius: 6px;
	    background-color: #fafafa;
	    box-sizing: border-box;
	}

	.total-price {
	    text-align: left;
	    margin-top: 20px;
	    font-size: 20px;
	    font-weight: bold;
	    color: #555;
	}

	.buttons {
	    text-align: center;
	    margin-top: 20px;
	}
	
	.btn {
	    padding: 12px 25px;
	    font-size: 16px;
	    margin: 5px;
	    cursor: pointer;
	    border: none;
	    border-radius: 5px;
	    transition: background-color 0.3s ease, transform 0.2s ease; /* 애니메이션 추가 */
	}
	
	.btn-pay {
	    background-color: #f4f0e6; 
	    color: #333;
	}
	
	.btn-cancel {
	    background-color: #f4f0e6; 
	    color: #333;
	}
	
	.btn:hover {
	    transform: scale(1.05); /* 마우스를 올렸을 때 살짝 확대 */
	    background-color: #f4f0e6; 
	}
	
	select, input[type="text"] {
	    margin-top: 10px;
	    width: 100%;
	    padding: 10px;
	    border: 1px solid #ccc;
	    border-radius: 4px;
	    box-sizing: border-box;
	    font-size: 16px;
	    background-color: #f4f0e6;
	}
	
	.payment-summary {
	    display: flex;
	    flex-direction: column;
	    justify-content: space-between;
	    height: 250px;
	}
	
	.payment-summary p {
	    margin: 5px 0;
	}
	
	.btnMile {
	    width: 100px;
	    background-color: #f4f0e6; 
	    color: #333;
	    border-radius: 4px;
	    border: none;
	    cursor: pointer;
	    transition: background-color 0.3s ease;
	}
	
	.btnMile:hover {
	    background-color: #f4f0e6; 
	}
	#applyMileage {
    width: 80px;
    background-color: #e8d8d8; /* 버튼 배경색을 2번째 사진 색상으로 설정 */
    color: #333; /* 텍스트 색상 */
    padding: 10px 20px;
    border: none; /* 테두리 제거 */
    border-radius: 5px; /* 둥근 모서리 */
    font-size: 13px;
    cursor: pointer;
    display: block;
    margin: 0 auto; /* 가운데 정렬 */
    text-align: center;
    transition: background-color 0.3s ease; /* 부드러운 호버 애니메이션 */
	}

	#applyMileage:hover {
    background-color: #d3c2c2; /* 호버 시 배경색 변경 */
    color: #333; /* 텍스트 색상 유지 */
	}
	.highlighted-text {
    font-size: 14px; /* 원하는 크기로 폰트 크기 조정 */
    color: #333; /* 폰트 색상 (필요 시 설정 가능) */
    text-align:center;
    font-weight: bold; /* 폰트를 두껍게 설정 */
    
}


    </style>
    <%
        List<PaymentVO> payList = (List<PaymentVO>) request.getAttribute("payList");
        List<PaymentVO> couponList = (List<PaymentVO>) request.getAttribute("couponList");
        List<PaymentVO> cardList = (List<PaymentVO>) request.getAttribute("cardList");
        Map<String, ImageVO> imagesMap = (Map<String, ImageVO>) request.getAttribute("imagesMap");
    %>
    <script>
        $(document).ready(function () {
            // 쉼표를 제거한 후 정수로 변환
            var originalTotalPrice = parseInt($('#totalprice').text().replace(/,/g, ''));

            var globalMileage = 0;   // 전역 변수로 마일리지 저장

            var globalDiscount = 0;  // 전역 변수로 할인율 저장

            // 쿠폰과 마일리지 기본 비활성화
            $('#coupon').prop('disabled', true);
            $('#mileageInput').prop('disabled', true);
            $('input[name="couponOrMileage"]').prop('disabled', true); // 라디오 버튼도 비활성화

            // 1. 상품 선택 시 쿠폰과 마일리지를 라디오 버튼으로 제어
            $('input[name="couponProd"]').on('change', function () {
                if ($(this).is(':checked')) {
                    // 상품이 선택되면 라디오 버튼으로 쿠폰 또는 마일리지를 선택할 수 있게 처리
                    $('input[name="couponOrMileage"]').prop('disabled', false);
                } else {
                    // 상품이 해제되면 다시 초기 상태로
                    $('input[name="couponOrMileage"]').prop('checked', false).prop('disabled', true);
                    $('#coupon').val('none').prop('disabled', true);
                    $('#mileageInput').val('').prop('disabled', true);
                    $('#totalprice').text(originalTotalPrice.toLocaleString());
                    globalDiscount = 0;  // 할인율 초기화
                    globalMileage = 0;   // 마일리지 초기화
                }
            });

            // 2. 라디오 버튼으로 쿠폰과 마일리지 활성화 제어
            $('input[name="couponOrMileage"]').on('change', function () {
                if ($(this).val() === 'coupon') {
                    // 쿠폰 선택 시 마일리지는 초기화하고 비활성화
                    $('#mileageInput').val(0).prop('disabled', true);
                    $('#coupon').prop('disabled', false);
                    globalMileage = 0;   // 마일리지 초기화
                } else if ($(this).val() === 'mileage') {
                    // 마일리지 선택 시 쿠폰은 초기화하고 비활성화
                    $('#coupon').val('none').prop('disabled', true);
                    $('#mileageInput').prop('disabled', false);

                    // 원래 가격 복구
                    $('#totalprice').text(originalTotalPrice.toLocaleString());
                    globalDiscount = 0;  // 할인율 초기화
                }
            });


            // 2-1. 쿠폰 or 마일리지 선택 시 가격 원상복구 후 할인 적용 및 중복 제거
            $('#coupon').on('change', function () {
                var coupId = $(this).val();  // 선택한 쿠폰 ID
                var selectedCartId = $('input[name="couponProd"]:checked').attr('data-cart-id');

                console.log('고른 카트아이디' , selectedCartId);

                // 2-1. 쿠폰이 선택되지 않은 경우 (none) 원래 가격 복구
                if (coupId === 'none' || !coupId) {  // 쿠폰 선택이 없거나 none인 경우
                    $('#totalprice').text(originalTotalPrice.toLocaleString());  // 원래 가격으로 복구
                    globalDiscount = 0;  // 할인율 초기화
                    coupId = null;  // 쿠폰 ID를 null로 설정하여 DB에 저장
                    return;
                }



                // 2-2. 쿠폰과 장바구니 항목이 선택된 경우 쿠폰 할인 적용
                if (!selectedCartId) {
                    alert("쿠폰을 적용할 장바구니 항목을 선택하세요.");
                    return;
                }

                // 쿠폰 할인 적용 (선택된 장바구니 항목에만 적용)
                $.ajax({
                    url: '<%=request.getContextPath()%>/payment/couponDiscount.do',
                    type: 'POST',
                    data: {coupId: coupId},
                    dataType: 'json',
                    success: function (res) {
                        globalDiscount = res.couponDiscount;  // 전역 변수에 할인율 저장
                        var totalPrice = 0;

                        // 각 장바구니 항목별 가격 합산 및 할인 적용
                        $('input[name="cart_id[]"]').each(function (index) {
                            var cartId = $(this).val();  // 현재 장바구니 항목의 cart_id
                            var prodPrice = parseInt($('input[name="prod_price[]"]').eq(index).val());  // 상품 가격
                            var quantity = parseInt($('input[name="dcart_qty[]"]').eq(index).val());  // 수량

                            // 선택한 장바구니 항목에만 쿠폰 적용
                            if (cartId === selectedCartId) {
                                var discountedPrice = prodPrice * quantity * (1 - globalDiscount  / 100);
                                totalPrice += discountedPrice;
                            } else {
                                // 다른 장바구니 항목은 원래 가격 유지
                                totalPrice += prodPrice * quantity;
                            }
                        });

                        // '원'을 제거하고 쉼표 포맷 적용
                        $('#totalprice').text(totalPrice.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ','));  // 최종 가격 갱신
                    },
                    error: function (xhr) {
                        console.log("쿠폰 적용 중 오류 발생: " + xhr.status);
                    }
                });
            });


         // 3. 마일리지 적용 시 가격 갱신
            $('#applyMileage').on('click', function () {
                var mileageUsed = parseInt($('#mileageInput').val()) || 0;  // 사용 마일리지
                var availableMileage = parseInt($('#mem_mileage').text().replace(/,/g, '')) || 0;  // 보유 마일리지 (쉼표 제거)
                globalMileage = mileageUsed;  // 전역 변수에 저장
                var selectedCartId = $('input[name="couponProd"]:checked').attr('data-cart-id');

                if (!selectedCartId) {
                    alert("마일리지를 적용할 장바구니 항목을 선택하세요.");
                    return;
                }

                // 사용 마일리지가 원래 가격보다 크거나, 보유 마일리지보다 많으면 적용 불가
                if (mileageUsed > originalTotalPrice) {
                    alert("사용할 마일리지가 총 가격보다 클 수 없습니다.");
                    return;
                }

                if (mileageUsed > availableMileage) {
                    alert("보유 마일리지를 초과할 수 없습니다.");
                    return;
                }

                var totalPrice = 0;

                // 각 장바구니 항목별 가격 합산 및 할인 적용
                $('input[name="cart_id[]"]').each(function (index) {
                    var cartId = $(this).val();  // 현재 장바구니 항목의 cart_id
                    var prodPrice = parseInt($('input[name="prod_price[]"]').eq(index).val());  // 상품 가격
                    var quantity = parseInt($('input[name="dcart_qty[]"]').eq(index).val());  // 수량

                    // 선택한 장바구니 항목에만 마일리지 적용
                    if (cartId === selectedCartId) {
                        var finalPrice = (prodPrice * quantity) - globalMileage;
                        totalPrice += finalPrice > 0 ? finalPrice : 0;  // 최종 가격이 0 이하로 내려가지 않게 설정
                        console.log("마일리지 적용 상품 가격: ", finalPrice);
                    } else {
                        totalPrice += prodPrice * quantity;  // 다른 상품은 원래 가격 유지
                    }
                });

                // '원'을 제거하고 쉼표 포맷 적용
                $('#totalprice').text(totalPrice.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ','));  // 최종 가격 갱신

                // 보유 마일리지 업데이트 (화면에서 보유 마일리지 값 갱신)
                var updatedMileage = availableMileage - mileageUsed;  // 보유 마일리지에서 사용한 마일리지를 뺌
                $('#mem_mileage').text(updatedMileage);  // 화면에서 보유 마일리지 값을 텍스트로 갱신
            });


            // 4. 결제 정보 전송
            $('#paymentInsert').on('click', function () {
                var totalPrice = $('#totalprice').text();  // 총 결제 금액
                // 총 결제 금액 로그 출력
                console.log("아작스 전 적용 Total Price: ", totalPrice);
                var mileageUsed = globalMileage || 0;  // 사용한 마일리지 값 전송
                var memMileage = parseInt($('#mem_mileage').text()); // 사용후 남은 마일리지
                var couponId = $('#coupon').val();  // 선택한 쿠폰 ID
                var cardId = $('#paymentMethod').val();  // 결제 수단
                var memId = $('#mem_id').val();  // 회원 ID
                var selectedCartId = $('input[name="couponProd"]:checked').data('cart-id'); // 쿠폰이 적용된 cart_id
				
                // 모든 hidden input의 cart_id, prod_price, dcart_qty 값을 배열로 가져옴
                var cartIds = [];
                var prodPrices = [];
                var coupIds = [];  // 각 상품에 적용된 쿠폰 ID 리스트
                var mileageValues = [];  // 각 상품에 적용된 마일리지 값 리스트
                
                if(cardId == "none"){
                    alert("결제 수단을 선택 해주세요");
                    return;  // 결제 진행을 멈춤
                }

                $('input[name="cart_id[]"]').each(function (index) {
                    var cartId = $(this).val();
                    cartIds.push(cartId);

                    // 화면에 표시된 최종 가격을 사용 (여기서 할인된 가격도 포함될 수 있음)
                    var displayedPrice = parseInt($('input[name="prod_price[]"]').eq(index).val().replace(/,/g, ''));
                    var quantity = parseInt($('input[name="dcart_qty[]"]').eq(index).val());

                    if (cartId === selectedCartId) {
                        // 마일리지나 쿠폰이 적용된 상품에 대한 최종 가격 계산
                        var finalPrice = globalMileage > 0 ? (displayedPrice * quantity) - globalMileage : displayedPrice * quantity;
                        prodPrices.push(finalPrice > 0 ? finalPrice : 0);  // 최종 가격이 0 이하로 내려가지 않게 설정
                        coupIds.push(couponId);  // 해당 상품에 적용된 쿠폰 ID
                        mileageValues.push(globalMileage);  // 선택된 상품에만 마일리지 적용
                        // 개별 상품 가격 로그 출력
                        console.log("적용된 Product Prices: ", prodPrices);
                    } else {
                        // 나머지 상품은 원래 가격 유지
                        prodPrices.push(displayedPrice * quantity);  // 원래 가격 유지
                        coupIds.push(null);  // 쿠폰 미적용 처리
                        mileageValues.push(0);  // 마일리지 적용 안함
                    }
                });

                console.log("보낸 상품 가격 목록: ", prodPrices);
                // 로그 추가
                console.log("총 결제 금액: ", totalPrice);

                // POST 방식으로 결제 정보 전송
                $.ajax({
                    url: '<%=request.getContextPath()%>/payment/paymentInsert.do',
                    type: 'POST',
                    data: {
                        totalprice: totalPrice,  // 마일리지 적용 후 총 결제 금액 전송
                        mile_used: mileageValues,  // 각 상품에 적용된 마일리지 값 전송
                        coup_ids: coupIds,  // 복수의 coup_id 배열로 전송 (각 상품별로 적용된 쿠폰)
                        card_id: cardId,
                        mem_mileage : memMileage,
                        mem_id: memId,
                        cart_ids: cartIds,  // 복수의 cart_id 배열로 전송
                        prod_prices: prodPrices,  // 복수의 prod_price 배열로 전송
                    },
                    traditional: true, // 배열을 전송할 때 사용
                    success: function (res) {
                        alert("결제가 완료되었습니다.");
                        window.location.href = '<%=request.getContextPath()%>/main';
                    },
                    error: function (xhr) {
                        alert("결제 중 오류 발생: " + xhr.status);
                    }
                });
            });
        });


        $('.cancel-btn').on('click', function () {
            var prodId = $(this).data('prod-id');  // 클릭한 상품의 prod_id 가져오기

            // AJAX 요청으로 해당 상품 삭제
            $.ajax({
                url: '<%=request.getContextPath()%>/remove/removeItem.do',  // 상품 삭제 처리할 서버 URL
                type: 'POST',
                data: {
                    prod_id: prodId
                },
                success: function (res) {
                    // 성공적으로 삭제되면 해당 상품을 화면에서 제거
                    $('div[data-prod-id="' + prodId + '"]').remove();
                    alert('상품이 삭제되었습니다.');
                },
                error: function (xhr) {
                    alert("상품 삭제 중 오류 발생: " + xhr.status);
                }
            });
        });


    </script>
</head>
<body>
<div class="container">
    <div class="title">주문 결제 페이지</div>

    <div class="prod-list">
        <% for (PaymentVO payVo : payList) { %>
        <div class="prod-item" data-prod-id="<%=payVo.getProd_id()%>">

            <!-- 상품에 맞는 이미지 출력 -->
            <%
                String prodId = payVo.getProd_id();
                ImageVO image = imagesMap.get(prodId); //상품에 맞는 이미지 갖고오기
            %>
            <% if (image != null) { %>
            <img src="<%= request.getContextPath() %>/images/prodImageView.do?imgId=<%= image.getImageId() %>" alt="첨부 이미지">
            <% } else { %>
            <p>이미지가 없습니다.</p>
            <% } %>
            <div class="prod-details">
                <p>상품명: <%= payVo.getProd_name() %>
                </p>
                <p>옵션: <%= payVo.getOption_name() %>/<%= payVo.getOption_value() %>
                </p>
                <p>수량: <%= payVo.getDcart_qty() %>개</p>
                <p>가격: ₩<%= String.format("%,d", Integer.parseInt(payVo.getProd_price()) * Integer.parseInt(String.valueOf(payVo.getDcart_qty()))) %>원</p>

                <!-- 쿠폰을 특정 상품에만 적용하는 라디오 버튼 -->
                <label>
                    <input type="radio" name="couponProd" data-cart-id="<%= payVo.getCart_id() %>" value="<%= payVo.getProd_id() %>"> 이 상품에 쿠폰 적용
                </label>
            </div>

            <!-- 각각의 cart_id와 그에 따른 정보를 배열로 전송 -->
            <input type="hidden" name="prod_id[]" value="<%= payVo.getProd_id() %>">
            <input type="hidden" name="cart_id[]" value="<%= payVo.getCart_id() %>">
            <input type="hidden" name="prod_price[]" value="<%= payVo.getProd_price() %>">
            <input type="hidden" name="dcart_qty[]" value="<%= payVo.getDcart_qty() %>">
        </div>
        <% } %>


        <!-- 주문자 정보 -->
        <div class="user-info">
            <h3>주문자 정보</h3>
            <p>
                이름:
                <%=payList.get(0).getMem_name() %>
            </p>
            <p>
                전화번호:
                <%=payList.get(0).getMem_phone() %>
            </p>
            <p>
                주소:
                <%=payList.get(0).getMem_zipcode() %>
                <%=payList.get(0).getMem_address() %>
                <%=payList.get(0).getMem_detail_address() %>
            </p>
        </div>

        <!-- 배송 정보 -->
        <div class="delivery-info">
            <h3>배송 정보</h3>
            <p>
                이름:
                <%=payList.get(0).getMem_name() %>
            </p>
            <p>
                전화번호:
                <%=payList.get(0).getMem_phone() %>
            </p>
            <p>
                주소:
                <%=payList.get(0).getMem_zipcode() %>
                <%=payList.get(0).getMem_address() %>
                <%=payList.get(0).getMem_detail_address() %>
            </p>
        </div>
    </div>

    <div class="payment-info">
        <div class="payment-info">
            <div class="payment-summary">
                <label><input type="radio" name="couponOrMileage" value="coupon"> 쿠폰 적용</label>
                <!-- 쿠폰 사용 -->
                <label for="coupon">쿠폰 사용:</label>
                <select id="coupon">
                    <option value="none">쿠폰 선택</option>
                    <% for (PaymentVO payVo : couponList) { %>
                    <option value="<%=payVo.getCoup_id()%>" name="coup_id"><%=payVo.getCoup_name() %>
                    </option>
                    <% } %>
                </select>

                <!-- 보유 마일리지 -->
			<p class="highlighted-text">보유 마일리지 : <span id="mem_mileage" name="mem_mileage"><%=payList.get(0).getMem_mileage()%></span></p>
			<label><input type="radio" name="couponOrMileage" value="mileage"> 마일리지 적용</label>
			<!-- 마일리지 입력 폼 -->
			<label for="mileageInput">사용할 마일리지:</label>
			<input type="number" id="mileageInput" name="mileageInput" placeholder="0" min="0" disabled>
			<!-- 처음에 비활성화 -->
			
	
			<button id="applyMileage">적용</button>
	

                <!-- 총 가격 계산 -->
                <%
                    int totalPrice = 0;
                    for (PaymentVO payVo : payList) {
                        // 가격과 수량 모두 String일 경우 int로 변환 후 연산
                        int prodPrice = Integer.parseInt(payVo.getProd_price()); // 가격을 정수로 변환
                        int dcartQty = Integer.parseInt(String.valueOf(payVo.getDcart_qty())); // 수량을 정수로 변환
                        totalPrice += prodPrice * dcartQty; // 총합 계산
                    }
                %>
                <!-- 총 가격 표시 -->
                <p class="highlighted-text">총 가격 : <span id="totalprice"><%= String.format("%,d", totalPrice) %></span>KRW</p>
            </div>
        </div>

        <!-- 결제 수단 선택 -->
        <label for="paymentMethod">결제 수단:</label> 
        <select id="paymentMethod">
	        <option value="none">결제 수단 선택</option>
	        <% for (PaymentVO payVo : cardList) { %>
	        <option value="<%=payVo.getCard_id()%>" name="card_id"><%=payVo.getCard_name() %>
	        </option>
	        <% } %>
    	</select>
    </div>

    <div class="buttons">
        <input type="hidden" id="mem_id" name="mem_id" value="<%= payList.get(0).getMem_id() %>">

        <input type="submit" class="btn btn-pay" id="paymentInsert" value="결제">
        <input type="button" class="btn btn-cancel" id="paymentcancel"
               onclick="window.location.href='<%=request.getContextPath()%>/main';" value="취소">
    </div>


</div>
</body>
</html>
