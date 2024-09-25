<%@page import="kr.or.ddit.coupon.vo.CouponVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>쿠폰 목록</title>
    <style>
 .container {
        width: 100%;
        padding: 20px;
        background-color: #fff;
        border: 1px solid #ddd;
        box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
    }

    h1 {
        text-align: center;
        margin-bottom: 30px;
    }

    .actions {
        text-align: right;
        margin-bottom: 20px;
    }

    .add-coupon-btn, .back-btn {
        padding: 10px 20px;
        color: #fff;
        border: none;
        border-radius: 5px;
        font-size: 16px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    .add-coupon-btn {
        background-color: #b9a69b;
        margin-right: 10px;
    }

    .add-coupon-btn:hover {
        background-color: #a29088;
    }

    .back-btn {
        background-color: #b9a69b;
    }

    .back-btn:hover {
        background-color: #a29088;
    }

    table {
        width: 100%;
        border-collapse: collapse;
    }

    table, th, td {
        border: 1px solid #ddd;
    }

    th, td {
        padding: 12px;
        text-align: center;
    }

    th {
        background-color: #f4f4f4;
    }

    tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    .status-active {
        color: green;
        font-weight: bold;
    }

    .status-expired {
        color: red;
        font-weight: bold;
    }

    .delete-btn {
        padding: 5px 10px;
        background-color: #ff4d4d;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 14px;
        transition: background-color 0.3s;
    }

    .delete-btn:hover {
        background-color: #ff3333;
    }
	h3{
		text-align : center;
	}
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        function deleteCoupon(coupId) {
            if (confirm("정말 이 쿠폰을 삭제하시겠습니까?")) {
                $.ajax({
                    url: '<%=request.getContextPath()%>/coupon/deleteCoupon.do', // 실제 삭제 요청을 처리할 서블릿 URL
                    type: 'POST',
                    data: {coup_id: coupId},
                    success: function(res) {
                        alert('쿠폰이 삭제되었습니다.');
                        location.reload(); // 페이지 새로고침으로 삭제된 쿠폰을 반영
                    },
                    error: function(xhr) {
                        alert("오류 : " + xhr.status);
                    }
                });
            }
        }
    </script>
</head>
<body>

<% List<CouponVO> couponList = (List<CouponVO>)request.getAttribute("couponList"); %>

<div class="container">
    <h3>쿠폰 목록</h3>

    <div class="actions">
        <button class="add-coupon-btn" onclick="location.href='<%=request.getContextPath()%>/coupon/couponInsert.do'">쿠폰 추가</button>
        <button class="back-btn" onclick="history.back()">뒤로가기</button>
    </div>

    <table>
        <thead>
            <tr>
                <th>쿠폰 ID</th>
                <th>회원 ID</th>
                <th>쿠폰 이름</th>
                <th>생성일</th>
                <th>유효기간</th>
                <th>할인율</th>
                <th>쿠폰 상태</th>
                <th>삭제</th>
            </tr>
        </thead>
        <tbody>
            <% if (couponList != null) {
                for (CouponVO coupon : couponList) { %>
                    <tr>
                        <td><%= coupon.getCoup_id() %></td>
                        <td><%= coupon.getMem_id() %></td>
                        <td><%= coupon.getCoup_name() %></td>
                        <td><%= coupon.getCoup_cdate() %></td>
                        <td><%= coupon.getCoup_edate() %></td>
                        <td><%= coupon.getCoup_amount() %>%</td>
                        <td class="<%= "1".equals(coupon.getCoup_status()) ? "status-active" : "status-expired" %>">
                            <%= "1".equals(coupon.getCoup_status()) ? "사용 가능" : "만료됨" %>
                        </td>
                        <td>
                            <button class="delete-btn" onclick="deleteCoupon('<%= coupon.getCoup_id() %>')">삭제</button>
                        </td>
                    </tr>
            <% }
            } else { %>
                <tr>
                    <td colspan="8">쿠폰 목록이 없습니다.</td>
                </tr>
            <% } %>
        </tbody>
    </table>
</div>

</body>
</html>
