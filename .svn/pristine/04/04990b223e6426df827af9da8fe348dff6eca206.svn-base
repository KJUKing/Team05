<%@page import="kr.or.ddit.riturn.vo.RiturnVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>반품 페이지</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .container {
            width: 70%;
            margin: 0 auto;
            border: 1px solid #ddd;
            padding: 20px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            font-size: 24px;
        }
        .product-info {
            display: flex;
            justify-content: space-between;
            margin-bottom: 20px;
        }
        .product-image {
            width: 150px;
            height: 150px;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .details {
            flex-grow: 1;
            margin-left: 20px;
        }
        .details p {
            margin: 5px 0;
        }
        .return-info {
            margin-top: 20px;
        }
        .return-info select, .return-info input {
            width: 100%;
            padding: 5px;
            margin-bottom: 10px;
        }
        .buttons {
            text-align: center;
            margin-top: 20px;
        }
        .buttons button {
            padding: 10px 20px;
            font-size: 16px;
            margin: 0 10px;
            border: none;
            cursor: pointer;
        }
        .submit-btn {
            background-color: #f0c040;
        }
        .cancel-btn {
            background-color: #ddd;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            // AJAX로 반품 폼 전송
            $('#returnForm').on('submit', function(event){
                event.preventDefault(); // 폼의 기본 동작을 막음 (페이지 이동 방지)

                // 폼 데이터 수집
                var formData = $(this).serialize();

                // AJAX로 서버에 폼 데이터 전송
                $.ajax({
                    url: '<%=request.getContextPath()%>/riturn/riturnInsert.do',
                    type: 'POST',
                    data: formData,
                    success: function(res) {
                        // 서버가 성공적으로 처리한 경우 alert 창 띄움
                        alert("반품이 완료되었습니다.");
                        // alert 창에서 확인을 누르면 메인 페이지로 이동
                        window.location.href = '<%=request.getContextPath()%>/main'; // 메인 페이지로 이동
                    },
                    error: function(xhr) {
                        // 서버 처리 중 오류 발생 시 처리
                        alert("반품 처리 중 오류가 발생했습니다: " + xhr.status);
                    }
                });
            });
        });
    </script>
</head>
<body>
<% List<RiturnVO> riturnList = (List<RiturnVO>)request.getAttribute("riturnList"); %>

    <div class="container">
        <h1>반품 페이지</h1>

        <form id="returnForm">
            <div class="product-info">
                <img src="<%=riturnList.get(0).getProd_image() %>" class="product-image">
                <div class="details">
                    <p>주문 번호 : <%=riturnList.get(0).getPay_id() %></p>
                    <p><%=riturnList.get(0).getProd_name()%></p>
                    <p><%=riturnList.get(0).getOption_name()%>/<%=riturnList.get(0).getOption_value()%> <%=riturnList.get(0).getDcart_qty()%>개</p>
                    <p>사용한 마일리지 : <%=riturnList.get(0).getMile_used()%>원</p>
                    <p>결제한 금액: <%=riturnList.get(0).getPay_price()%>원</p>
                    <input type="hidden" name="mile_used" value="<%=riturnList.get(0).getMile_used()%>">
                    <input type="hidden" name="pay_id" value="<%=riturnList.get(0).getPay_id() %>">
                    <input type="hidden" name="mem_id" value="<%=riturnList.get(0).getMem_id()%>">
                </div>
            </div>

            <div class="return-info">
                <label for="reason">반품 사유:</label>
                <select id="reason" name="re_reason">
                    <option value="다른 상품이 배송">다른 상품이 배송</option>
                    <option value="상품이 불량">상품이 불량</option>
                    <option value="단순 변심">단순 변심</option>
                </select>

                <label for="resolution">해결 방법:</label>
                <select id="resolution">
                    <option value="1">문 앞</option>
                    <option value="2">직접 방문</option>
                </select>

                <label for="quantity">반품 개수:</label>
                <input type="number" id="quantity" name="re_qty" min="1" max="<%=riturnList.get(0).getDcart_qty()%>" value="1">
            </div>

            <div class="buttons">
                <button type="submit" class="submit-btn">반품 신청</button>
                <button type="button" class="cancel-btn" onclick="window.location.href='<%=request.getContextPath()%>/main';">취소</button>
            </div>
        </form>

    </div>
    

</body>
</html>
