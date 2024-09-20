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
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f8f8;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            width: 80%;
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
            background-color: #b9a69b;
        }

        .back-btn {
            background-color: #b9a69b;
        }

        .back-btn:hover {
            background-color: #b9a69b;
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
    </style>
</head>
<body>

<% List<CouponVO> couponList = (List<CouponVO>)request.getAttribute("couponList"); %>

<div class="container">
    <h1>쿠폰 목록</h1>

    <div class="actions">
        <button class="add-coupon-btn" onclick="location.href='<%=request.getContextPath()%>/coupon/couponInsert.do'">쿠폰 추가</button>
        <button class="back-btn" onclick="history.back()">뒤로가기</button>
    </div>

    <table>
        <thead>
            <tr>
                <th>쿠폰 ID</th>
                <th>쿠폰 이름</th>
                <th>생성일</th>
                <th>유효기간</th>
                <th>할인율</th>
                <th>쿠폰 상태</th>
            </tr>
        </thead>
        <tbody>
            <% if (couponList != null) {
                for (CouponVO coupon : couponList) { %>
                    <tr>
                        <td><%= coupon.getCoup_id() %></td>
                        <td><%= coupon.getCoup_name() %></td>
                        <td><%= coupon.getCoup_cdate() %></td>
                        <td><%= coupon.getCoup_edate() %></td>
                        <td><%= coupon.getCoup_amount() %>%</td>
                        <td class="<%= "1".equals(coupon.getCoup_status()) ? "status-active" : "status-expired" %>">
                            <%= "1".equals(coupon.getCoup_status()) ? "사용 가능" : "만료됨" %>
                        </td>
                    </tr>
            <% }
            } else { %>
                <tr>
                    <td colspan="6">쿠폰 목록이 없습니다.</td>
                </tr>
            <% } %>
        </tbody>
    </table>
</div>

</body>
</html>
