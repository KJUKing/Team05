<%@page import="kr.or.ddit.coupon.vo.CouponVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Mileage & Coupons</title>
      <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #ffffff;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 100%;
            max-width: 900px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            padding: 40px;
            margin: 0 auto;
        }

        h2 {
            font-size: 24px;
            margin-bottom: 30px;
            text-align: center;
            color: #333;
        }

        .mileage-section, .coupon-section {
            margin-bottom: 20px;
        }

        .mileage-section table, .coupon-section table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
            background-color: #fff;
        }

        table, th, td {
            border: 1px solid #ddd;
            padding: 12px;
        }

        th {
            background-color: #f7f7f7;
            color: #333;
            width: 40%;
        }

        /* 총 마일리지 텍스트 크기 작게 */
        td {
            font-size: 14px; /* 텍스트 크기 줄임 */
            text-align: right;
        }

        .coupon-select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #fafafa;
        }

	  .button {
    width: 100%;
    padding: 15px;
    background-color: #e4dcdc; /* 이미지에서 추출한 연한 핑크/아이보리 색상 */
    border: none;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
    text-align: center;
}

.button:hover {
    background-color: #d3c5c5; /* hover 시 조금 더 어두운 톤 */
}


    </style>
</head>
<% List<CouponVO> coupList = (List<CouponVO>)request.getAttribute("coupList"); %>
<body>
    <div class="container">
        <h2>My Mileage & Coupon</h2>

        <!-- 마일리지 섹션 -->
        <div class="mileage-section">
            <h3>보유 마일리지</h3>
            <table>
                <tr>
                    <th>총 마일리지</th>
                    <td style="text-align: right;"><%=coupList != null && !coupList.isEmpty() ? coupList.get(0).getMem_mileage() : "0" %></td>
                </tr>
            </table>
        </div>

        <!-- 쿠폰 섹션 -->
        <div class="coupon-section">
            <h3>보유 쿠폰</h3>
            <select class="coupon-select">
                <% 
                    if (coupList != null && !coupList.isEmpty()) {
                        for (CouponVO coupon : coupList) {
                %>
                    <option value="<%= coupon.getCoup_id() %>"><%= coupon.getCoup_name() %></option>
                <% 
                        }
                    } else {
                %>
                    <option value="">사용 가능한 쿠폰이 없습니다.</option>
                <% 
                    }
                %>
            </select>
        </div>

        <!-- 뒤로 가기 버튼 -->
        <button class="button" onclick="history.back()">뒤로 가기</button>
    </div>
</body>
</html>
