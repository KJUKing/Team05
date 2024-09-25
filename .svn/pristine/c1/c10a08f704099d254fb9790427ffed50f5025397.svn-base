<%@page import="kr.or.ddit.payment.vo.PaymentVO" %>
<%@page import="java.util.List" %>
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

        .prod-list, .payment-info {
            display: inline-block;
            width: 100%;
            vertical-align: top;
            margin-bottom: 20px;
        }

        .prod-item {
            border: 1px solid #ccc;
            padding: 10px;
            margin-bottom: 10px;
            width: 100%;
            display: flex; /* 추가: Flexbox 사용 */
            align-items: center; /* 이미지와 텍스트를 수직으로 가운데 정렬 */
        }

        .prod-item img {
            width: 100px;
            margin-right: 20px; /* 이미지와 텍스트 사이에 간격 추가 */
        }

        .prod-details {
            flex: 1; /* 텍스트 영역이 이미지 오른쪽에 배치되고 남은 공간을 차지 */
        }

        .payment-info, .user-info, .delivery-info {
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

        .btn-pay {
            background-color: red;
            color: white;
            border: none;
        }

        .btn-cancel {
            background-color: gray;
            color: white;
            border: none;
        }

        select, input[type="text"] {
            margin-top: 10px;
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }

        .payment-summary {
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            height: 250px; /* 총 정보 높이 */
        }

        .payment-summary p {
            margin: 5px 0;
        }

        .btnMile {
            width: 50px;
        }
    </style>
    <%
        List<PaymentVO> payList = (List<PaymentVO>) request.getAttribute("payList");
        List<PaymentVO> couponList = (List<PaymentVO>) request.getAttribute("couponList");
        List<PaymentVO> cardList = (List<PaymentVO>) request.getAttribute("cardList");
    %>
    <script>

        $(() => {

            $(document).ready(() => {
                // 결제 시 쿠폰 최종 확인
                $('#paymentInsert').on('click', function () {
                    var selectedProdId = $('input[name="couponProd"]:checked').val();  // 선택한 상품 ID
                    var coupId = $('#coupon').val();  // 선택한 쿠폰 ID 확인

                    if (coupId !== 'none' && !selectedProdId) {
                        // 쿠폰은 선택했는데 상품을 선택하지 않은 경우 오류 처리
                        alert('쿠폰을 사용할 상품을 선택해주세요.');
                        return false;  // 결제 중단
                    }

                    // 모든 hidden input의 cart_id와 상품별 가격 및 수량을 가져옴
                    var cartIds = [];
                    var prodPrices = [];
                    var dcartQtys = [];

                    $('input[name="cart_id[]"]').each(function () {
                        cartIds.push($(this).val());
                    });

                    $('input[name="prod_price[]"]').each(function () {
                        prodPrices.push(parseInt($(this).val()));  // 상품 개별 가격 배열 저장
                    });

                    $('input[name="dcart_qty[]"]').each(function () {
                        dcartQtys.push(parseInt($(this).val()));  // 수량 배열 저장
                    });

                    var discountedProdPrices = [];  // 할인 적용 후의 가격 배열

                    // 쿠폰 할인율을 서버에서 받아오는 AJAX
                    $.ajax({
                        url: '<%=request.getContextPath()%>/payment/couponDiscount.do',  // 경로 수정
                        type: 'POST',
                        data: { coupId: coupId },
                        dataType: 'json',
                        success: function (res) {
                            var discountRate = res.couponDiscount;  // 서버에서 받아온 할인율

                            // 각 상품에 대한 처리
                            $('input[name="prod_price[]"]').each(function (index) {
                                var prodPrice = prodPrices[index];
                                var quantity = dcartQtys[index];
                                var totalProdPrice = prodPrice * quantity;  // 개별 상품 총 가격

                                // 쿠폰이 적용된 상품인지 확인
                                var currentProdId = $('input[name="couponProd"]').eq(index).val();
                                if (currentProdId === selectedProdId && coupId !== 'none') {
                                    // 쿠폰 적용 상품일 경우 할인 적용
                                    totalProdPrice = totalProdPrice * (1 - discountRate / 100);  // 할인율 적용
                                }

                                discountedProdPrices.push(totalProdPrice);  // 할인 적용된 가격 저장
                            });

                            // 서버로 개별 상품 가격 및 수량 정보 전송
                            $.ajax({
                                url: '<%=request.getContextPath()%>/payment/paymentInsert.do',
                                type: 'POST',
                                data: {
                                    cart_ids: cartIds,  // cart_id 배열
                                    prod_prices: discountedProdPrices,  // 할인된 개별 상품 가격 배열
                                    dcart_qtys: dcartQtys,  // 수량 배열
                                    coup_id: coupId !== 'none' ? coupId : null,  // 선택된 쿠폰 ID
                                    card_id: $('#paymentMethod').val(),  // 결제 수단
                                    mem_id: $('#mem_id').val(),  // 회원 ID
                                    mile_used: $('#hiddenMileage').val()  // 사용 마일리지
                                },
                                traditional: true,  // 배열 전송 허용
                                success: function (res) {
                                    alert("결제가 완료되었습니다.");
                                    window.location.href = '<%=request.getContextPath()%>/main';
                                },
                                error: function (xhr) {
                                    alert("결제 중 오류 발생: " + xhr.status);
                                }
                            });
                        },
                        error: function (xhr) {
                            console.log("할인율 가져오기 오류: " + xhr.status);
                        }
                    });
                });
            });


            $('#Mileage').on('click', function () {
                var usedMileage = parseInt($('#mileageInput').val());
                var mem_mileage = parseInt($('#mem_mileage').text());
                var totalprice = parseInt($('#totalprice').text());

                // 유효성 검사 추가
                if (isNaN(usedMileage) || usedMileage <= 0) {
                    alert('사용할 마일리지를 올바르게 입력하세요.');
                    return;
                }
                if (usedMileage > mem_mileage) {
                    alert('보유한 마일리지보다 많이 사용할 수 없습니다.');
                    return;
                }

                // AJAX 요청으로 마일리지 업데이트
                $.ajax({
                    url: '<%=request.getContextPath()%>/payment/mileageUpdate.do',
                    type: 'POST',
                    data: {
                        usedMileage: usedMileage,
                        mem_mileage: mem_mileage,
                        totalprice: totalprice
                    },
                    success: function (res) {
                        // 마일리지 및 최종 가격 업데이트
                        $('#totalprice').html(res.finalPrice);
                        $('#mem_mileage').text(res.memMileage);
                        $('#mileageInput').val(''); // 입력 필드 초기화

                        // 사용한 마일리지 값을 hidden field에 저장
                        $('#hiddenMileage').val(usedMileage);
                    },
                    error: function (xhr) {
                        alert("오류 : " + xhr.status);
                    },
                    dataType: 'json'
                });
            });

            // 쿠폰 선택 시 라디오 버튼 활성화 또는 비활성화
            $('#coupon').on('change', function () {
                var coupId = $(this).val();  // 선택한 쿠폰 ID 가져오기

                if (coupId === 'none') {
                    // 쿠폰이 선택되지 않았을 때
                    $('input[name="couponProd"]').prop('disabled', true);  // 라디오 버튼 비활성화
                    resetTotalPrice();  // 원래 가격으로 복구
                } else {
                    // 쿠폰이 선택되었을 때
                    $('input[name="couponProd"]').prop('disabled', false);  // 라디오 버튼 활성화
                }
            });

            var originalTotalPrice = parseInt($('#totalprice').text());  // 원래 총 가격 저장

            // 라디오 버튼으로 상품 선택 시 쿠폰 적용
            $('input[name="couponProd"]').on('click', function () {
                var selectedProdId = $(this).val();  // 선택한 상품 ID 가져오기

                // 이미 선택된 라디오 버튼을 다시 클릭했을 때 해제 처리
                if ($(this).is(':checked') && $(this).data('waschecked') === true) {
                    $(this).prop('checked', false);  // 선택 해제
                    $(this).data('waschecked', false);  // 상태 초기화
                    resetTotalPrice();  // 원래 가격으로 복구
                } else {
                    $('input[name="couponProd"]').data('waschecked', false);  // 모든 라디오 버튼 상태 초기화
                    $(this).data('waschecked', true);  // 현재 클릭된 라디오 버튼에 상태 저장

                    var coupId = $('#coupon').val();  // 선택한 쿠폰 ID 가져오기
                    if (coupId !== 'none' && selectedProdId) {
                        applyCoupon(coupId, selectedProdId);  // 쿠폰 적용
                    } else {
                        alert("먼저 적용할 상품을 선택하세요.");
                    }
                }
            });

            // 쿠폰 적용 함수
            function applyCoupon(coupId, selectedProdId) {
                $.ajax({
                    url: '<%=request.getContextPath()%>/payment/couponDiscount.do',
                    type: 'POST',
                    data: { coupId: coupId },
                    dataType: 'json',
                    success: function (res) {
                        var discount = res.couponDiscount;  // 쿠폰 할인율 가져오기
                        var totalPrice = 0;  // 최종 가격 초기화

                        // 각 상품별 가격 합산 및 할인 적용
                        $('input[name="prod_price[]"]').each(function (index) {
                            var prodPrice = parseInt($(this).val());  // 상품 가격 가져오기
                            var quantity = parseInt($('input[name="dcart_qty[]"]').eq(index).val());  // 상품 수량 가져오기
                            var currentProdId = $('input[name="couponProd"]').eq(index).val();  // 현재 상품 ID 가져오기

                            if (currentProdId === selectedProdId) {
                                // 선택한 상품에만 쿠폰 적용
                                var discountedPrice = prodPrice * quantity * (1 - discount / 100);
                                totalPrice += discountedPrice;
                            } else {
                                // 다른 상품은 원래 가격 유지
                                totalPrice += prodPrice * quantity;
                            }
                        });
                        $('#totalprice').text(totalPrice.toLocaleString());  // 최종 가격 갱신
                    },
                    error: function (xhr) {
                        console.log("쿠폰 적용 중 오류 발생: " + xhr.status);
                    }
                });
            }

            // 원래 가격 복구 함수 (쿠폰 해제 시 호출)
            function resetTotalPrice() {
                var originalTotalPrice = 0;

                // 각 상품의 원래 가격과 수량을 기준으로 총 가격 계산
                $('input[name="prod_price[]"]').each(function (index) {
                    var prodPrice = parseInt($(this).val());  // 상품 가격 가져오기
                    var quantity = parseInt($('input[name="dcart_qty[]"]').eq(index).val());  // 상품 수량 가져오기
                    originalTotalPrice += prodPrice * quantity;  // 원래 가격 계산
                });

                $('#totalprice').text(originalTotalPrice.toLocaleString());  // 총 가격을 원래 가격으로 복구
            }






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
        })


    </script>
</head>
<body>
<div class="container">
    <div class="title">주문 결제 페이지</div>

    <div class="prod-list">
		<% for (PaymentVO payVo : payList) { %>
		<div class="prod-item" data-prod-id="<%=payVo.getProd_id()%>">
			<img src="<%= request.getContextPath() %>/images/prodImageView.do?imgId=<%=payVo.getProd_image()%>" alt="<%= payVo.getProd_name() %>">
			<div class="prod-details">
				<p>상품명: <%= payVo.getProd_name() %></p>
				<p>옵션: <%= payVo.getOption_name() %>/<%= payVo.getOption_value() %></p>
				<p>수량: <%= payVo.getDcart_qty() %>개</p>
				<p>가격: ₩<%= String.format("%,d", Integer.parseInt(payVo.getProd_price())) %>원</p>

				<!-- 쿠폰을 특정 상품에만 적용하는 라디오 버튼 -->
				<label>
					<input type="radio" name="couponProd" value="<%= payVo.getProd_id() %>"> 이 상품에 쿠폰 적용
				</label>
			</div>

			<!-- 각각의 cart_id와 그에 따른 정보를 배열로 전송 -->
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
                <p>보유 마일리지: <span id="mem_mileage" name="mem_mileage"><%=payList.get(0).getMem_mileage()%></span>원</p>

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
                <p class="total-price">총 가격: <span id="totalprice"><%= String.format("%,d", totalPrice) %></span>원</p>
            </div>
        </div>

        <!-- 결제 수단 선택 -->
        <label for="paymentMethod">결제 수단:</label> <select id="paymentMethod">
        <option>결제 수단 선택</option>
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
