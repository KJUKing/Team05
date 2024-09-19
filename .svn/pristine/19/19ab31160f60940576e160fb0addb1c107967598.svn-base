<%@page import="kr.or.ddit.payment.vo.PaymentVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
    List<PaymentVO> payList = (List<PaymentVO>)request.getAttribute("payList");
    List<PaymentVO> couponList = (List<PaymentVO>)request.getAttribute("couponList");
    List<PaymentVO> cardList = (List<PaymentVO>)request.getAttribute("cardList");
	%>
<script>
        
    	$(()=>{
    		
       
    		$('#paymentInsert').on('click', function() {
    		    var totalPrice = $('#totalprice').text();  // 총 결제 금액
    		    var mileageUsed = $('#hiddenMileage').val();  // 사용한 마일리지 (hidden input에서 가져옴)
    		    var couponId = $('#coupon').val();  // 선택한 쿠폰 ID
    		    var cardId = $('#paymentMethod').val();  // 결제 수단

    		    // POST 방식으로 결제 정보 전송
    		    $.ajax({
    		        url: '<%=request.getContextPath()%>/payment/paymentInsert.do',
    		        type: 'POST',
    		        data: {
    		            totalprice: totalPrice,
    		            mile_used: mileageUsed,
    		            coup_id: couponId,
    		            card_id: cardId
    		        },
    		        success: function(res) {
    		            // 결제가 성공하면 메인 페이지로 이동
    		            window.location.href = '<%=request.getContextPath()%>/main';
    		        },
    		        error: function(xhr) {
    		            alert("결제 중 오류 발생: " + xhr.status);
    		        }
    		    });
    		});


        
    		$('#Mileage').on('click', function() {
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
    		        success: function(res) {
    		            // 마일리지 및 최종 가격 업데이트
    		            $('#totalprice').html(res.finalPrice);
    		            $('#mem_mileage').text(res.memMileage);
    		            $('#mileageInput').val(''); // 입력 필드 초기화

    		            // 사용한 마일리지 값을 hidden field에 저장
    		            $('#hiddenMileage').val(usedMileage);
    		        },
    		        error: function(xhr) {
    		            alert("오류 : " + xhr.status);
    		        },
    		        dataType: 'json'
    		    });
    		});

        
        var originalPrice = parseInt($('#totalprice').text()); // 원래 금액을 저장
        
        $('#coupon').on('change', function() {
            // 선택한 쿠폰 ID를 가져옴
            var coupId = $(this).val();

            if (coupId !== 'none') {
                // AJAX 요청을 통해 쿠폰 할인율 가져오기
                $.ajax({
                    url: '<%=request.getContextPath()%>/payment/couponDiscount.do', 
                    type: 'POST',
                    data: { coupId: coupId },
                    dataType: 'json',
                    success: function(res) {
                        var discount = res.couponDiscount;
                        var totalPrice = parseInt($('#totalprice').text()); // 총 가격 가져오기
                        var discountedPrice = totalPrice - (totalPrice * (discount / 100)); // 할인율 적용 
                        $('#totalprice').text(discountedPrice); // 할인된 가격 업데이트
                    },
                    error: function(xhr) {
                        alert("오류 : "+xhr.status);                    
                    }
                });
            }else{
            	$('#totalprice').text(originalPrice);
            }
        });
    })

        
    </script>
</head>
<body>
	<div class="container">
		<div class="title">주문 결제 페이지</div>

		<div class="prod-list">
			<% for (PaymentVO payVo : payList) { %>
			<div class="prod-item">
				<img src="<%=payVo.getProd_image()%>">
				<div class="prod-details">
					<p><%=payVo.getProd_name() %></p>
					<p><%=payVo.getOption_name() %>/<%=payVo.getOption_value() %></p>
					<p><%=payVo.getDcart_qty() %>개</p>
					<p>
						가격:
						<%=payVo.getProd_price() %></p>
				</div>
				<input type="button" name="cancel" id="cancel" value="취소">
			</div>
			<% } %>

			<!-- 주문자 정보 -->
			<div class="user-info">
				<h3>주문자 정보</h3>
				<% for (PaymentVO payVo : payList) { %>
				<p>
					이름:
					<%=payVo.getMem_name() %></p>
				<p>
					전화번호:
					<%=payVo.getMem_phone() %></p>
				<p>
					주소:
					<%=payVo.getMem_zipcode() %>
					<%=payVo.getMem_address() %>
					<%=payVo.getMem_detail_address() %></p>
				<% } %>
			</div>

			<!-- 배송 정보 -->
			<div class="delivery-info">
				<h3>배송 정보</h3>
				<% for (PaymentVO payVo : payList) { %>
				<p>
					이름:
					<%=payVo.getMem_name() %></p>
				<p>
					전화번호:
					<%=payVo.getMem_phone() %></p>
				<p>
					주소:
					<%=payVo.getMem_zipcode() %>
					<%=payVo.getMem_address() %>
					<%=payVo.getMem_detail_address() %></p>
				<% } %>
			</div>
		</div>

		<div class="payment-info">
			<div class="payment-summary">
				<!-- 쿠폰 사용 -->
				<label for="coupon">쿠폰 사용:</label> <select id="coupon">
					<option value="none">쿠폰 선택</option>
					<% for (PaymentVO payVo : couponList) { %>
					<option value="<%=payVo.getCoup_id()%>" name="coup_id"><%=payVo.getCoup_name() %></option>
					<% } %>
				</select>

				<!-- 보유 마일리지 -->
				<p>
					보유 마일리지: <span id="mem_mileage" name="mem_mileage"><%=payList.get(0).getMem_mileage()%></span>원
				</p>



				<!-- 마일리지 입력 -->
				<label for="mileage">마일리지 사용:</label> <input type="text"	name="mile_used" id="mileageInput" placeholder="사용할 마일리지 입력">
				<input type="button" class="btnMile" id="Mileage" value="적용">
				
				<!-- 마일리지 값을 저장하는 hidden input field 추가 -->
				<input type="hidden" id="hiddenMileage" name="hiddenMileage" value="">
				

				<!-- 총 가격 -->
				<p class="total-price">
					총가격: <span id="totalprice"><%=payList.get(0).getCart_price()%></span>원
				</p>
			</div>

			<!-- 결제 수단 선택 -->
			<label for="paymentMethod">결제 수단:</label> <select id="paymentMethod">
				<option>결제 수단 선택</option>
				<% for (PaymentVO payVo : cardList) { %>
				<option value="<%=payVo.getCard_id()%>" name="card_id"><%=payVo.getCard_name() %></option>
				<% } %>
			</select>
		</div>

		<div class="buttons">
			<input type="submit" class="btn btn-pay" id="paymentInsert"	value="결제">
			<input type="button" class="btn btn-cancel"	id="paymentcancel"
			 onclick="window.location.href='<%=request.getContextPath()%>/main';" value="취소">
		</div>
	</div>
</body>
</html>
