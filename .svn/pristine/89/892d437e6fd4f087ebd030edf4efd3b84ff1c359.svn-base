<%@page import="kr.or.ddit.express.vo.ExpressVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>배송 조회</title>
    <% List<ExpressVO> exList = (List<ExpressVO>)request.getAttribute("exList"); %>
    <style>
        /* 전체 페이지 레이아웃 */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f2f2f2;
            color: #333;
            margin: 0;
            padding: 0;
        }

        /* 컨테이너 */
        .container {
            width: 90%;
            max-width: 600px;
            margin: 20px auto;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        /* 제목 */
        h1 {
            text-align: center;
            font-size: 24px;
            color: #3366cc;
            margin-bottom: 20px;
        }

        /* 배송 상태 바 */
        .status-bar {
            display: flex;
            justify-content: space-around;
            margin-bottom: 20px;
            padding: 10px 0;
        }

        .status-bar div {
            text-align: center;
            flex-grow: 1;
        }

        .status-bar div p {
            margin-top: 10px;
            font-weight: bold;
            font-size: 15px;
            color: #555;
        }

        .icon {
            width: 30px;
            height: 30px;
            border-radius: 50%;
            background-color: #cccccc;
            margin: 0 auto;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        /* 현재 단계 표시 */
        .active {
            background-color: #3366cc;
            color: white;
        }

        /* 기본 정보 테이블 */
        .info-table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
        }

        .info-table td {
            padding: 10px;
            border-bottom: 1px solid #eeeeee;
        }

        .info-table td:first-child {
            font-weight: bold;
            color: #333333;
        }

        /* 버튼 스타일 */
        .btn {
            width: 100%;
            padding: 8px;
            margin-top: 15px;
            background-color: #ffcc99; /* 따뜻한 색상 */
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
            color: #333;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .btn:hover {
            background-color: #ffb366; /* 호버 시 조금 더 진한 색상 */
        }

    </style>

    <script type="text/javascript">
        // 날짜에 따른 상태 변경
        function getDeliveryStatus(orderDate) {
            var today = new Date();
            var orderDateObj = new Date(orderDate);

            // 주문 후 경과 일수를 계산
            var diffTime = Math.abs(today - orderDateObj);
            var diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

            if (diffDays <= 1) {
                return "상품 준비중";
            } else if (diffDays <= 3) {
                return "배송중";
            } else if (diffDays > 3) {
                return "배송 완료";
            } else {
                return "상태 확인 불가";
            }
        }

        // 페이지 로딩 후 상태 표시
        window.onload = function() {
            var orderDate = "<%=exList.get(0).getPay_date()%>"; // DB에서 가져온 주문 날짜
            var status = getDeliveryStatus(orderDate);

            // 배송 상태를 표시할 요소
            var statusElements = document.querySelectorAll(".icon");
            var statusIndex = 0;

            if (status === "상품 준비중") {
                statusIndex = 0;
            } else if (status === "배송중") {
                statusIndex = 1;
            } else if (status === "배송 완료") {
                statusIndex = 2;
            }

            // 해당 상태에 아이콘 색상 변경
            statusElements[statusIndex].classList.add("active");

            // 페이지 이동 기능 추가
            document.querySelector('.btn-back').onclick = function() {
                window.location.href = '<%=request.getContextPath()%>/riturn/myBuyPage.do'; // 나의 구매내역으로 이동
            };

            document.querySelector('.btn-main').onclick = function() {
                window.location.href = '<%=request.getContextPath()%>/main'; // 메인 페이지로 이동
            };
        }
    </script>
</head>
<body>
    <div class="container">
        <h1>배송 조회</h1>

        <!-- 상태 바 -->
        <div class="status-bar">
            <div>
                <div class="icon">📦</div>
                <p>상품 준비중</p>
            </div>
            <div>
                <div class="icon">🚚</div>
                <p>배송중</p>
            </div>
            <div>
                <div class="icon">🏠</div>
                <p>배송 완료</p>
            </div>
        </div>

        <!-- 기본 정보 테이블 -->
        <table class="info-table">
            <tr>
                <td>주문 번호</td>
                <td><%=exList.get(0).getPay_id()%></td>
            </tr>
            <tr>
                <td>택배회사</td>
                <td><%=exList.get(0).getEx_name()%></td>
            </tr>
            <tr>
                <td>상품 정보</td>
                <td><%=exList.get(0).getProd_name()%></td>
            </tr>
            <tr>
                <td>보내는 분</td>
                <td>yeongeunManyPlease</td>
            </tr>
            <tr>
                <td>받는 분</td>
                <td><%=exList.get(0).getMem_name()%></td>
            </tr>
        </table>

        <input type="button" class="btn btn-back" value="나의 구매내역으로 돌아가기">
        <input type="button" class="btn btn-main" value="메인으로 돌아가기">
    </div>
</body>
</html>
