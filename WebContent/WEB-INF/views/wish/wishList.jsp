<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.wish.vo.WishListVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="kr.or.ddit.prod.vo.ProdVO" %>
<%@ page import="kr.or.ddit.image.vo.ImageVO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>위시리스트</title>
    <script src="<%= request.getContextPath()%>/js/jquery-3.7.1.js"></script>
    <script>
        // 위시리스트 활성화/비활성화 상태 변경 함수
        const wishToggle = (prodId, element) => {
            const currentStatus = $(element).attr('data-status');
            const newStatus = currentStatus === 'active' ? 'inactive' : 'active';

            updateStatusOnServer(prodId, newStatus, element);
        };

        // Ajax 상태 변경 요청 함수
        const updateStatusOnServer = (prodId, newStatus, element) => {
            $.ajax({
                url: '<%=request.getContextPath()%>/wish/toggleWishStatus.do',
                type: 'POST',
                data: {prod_id: prodId, status: newStatus},
                success: () => {
                    if (newStatus === 'inactive') {
                        $(element).find('img').attr('src', '<%=request.getContextPath()%>/images/inactive.png');
                        $(element).attr('data-status', 'inactive');
                    } else {
                        $(element).find('img').attr('src', '<%=request.getContextPath()%>/images/active.png');
                        $(element).attr('data-status', 'active');
                    }
                },
                error: xhr => {
                    alert("오류 발생: " + xhr.status);
                }
            });
        }
    </script>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f4;
        }
        h1 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
            background-color: #fff;
            border: 1px solid #ddd;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        th, td {
            padding: 12px;
            text-align: center; /* 테이블 셀 내용을 중앙 정렬 */
        }
        th {
            background-color: #f8f8f8;
            color: #333;
        }
        tr {
            margin-bottom: 15px; /* 각 행 사이 간격을 띄움 */
        }
        tr:nth-child(even) {
            background-color: #fafafa;
        }
        tr:hover {
            background-color: #f0f0f0;
        }
        .product-image {
            width: 100px;
            height: auto;
        }
        /* 테이블 전체 간격 조절 */
        td {
            padding: 15px 10px; /* 셀 내부 간격 조정 */
        }
        h3 {
            text-align: center;
        }
    </style>
</head>
<body>
<h3>My WishList</h3>

<table>
    <thead>
    <tr>
        <th>상품 이미지</th>
        <th>상품 이름</th>
        <th>상품 가격</th>
        <th>상태</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<ProdVO> wishList = (List<ProdVO>) request.getAttribute("wishList");
        Map<String, ImageVO> imagesMap = (Map<String, ImageVO>) request.getAttribute("imagesMap"); // Map으로 받은 이미지 데이터
        NumberFormat currencyFormatter = NumberFormat.getInstance(); // 가격 포맷 설정

        if (wishList != null && !wishList.isEmpty()) {
            for (ProdVO prodVo : wishList) {
                String prodId = prodVo.getProd_id();
                ImageVO imageVo = imagesMap.get(prodId); // Map에서 이미지 가져오기
                
                String formattedPrice = currencyFormatter.format(prodVo.getProd_price()); // 가격을 000,000 형식으로 포맷
                String currentStatus = "active"; // 기본 상태는 활성화
    %>
    <tr>
        <td>
            <% if (imageVo != null) { %>
                <img class="product-image" src="<%=request.getContextPath() %>/images/prodImageView.do?imgId=<%= imageVo.getImageId() %>" alt="첨부 이미지">
            <% } else { %>
                <p>이미지가 없습니다.</p>
            <% } %>
        </td>
        <td>
            <a href="<%=request.getContextPath()%>/prod/prodListView.do?prodId=<%= prodVo.getProd_id() %>"><%= prodVo.getProd_name() %></a>
        </td>
        <td>KRW <%= formattedPrice %></td>
        <td>
            <a href="javascript:void(0);"
               onclick="wishToggle('<%= prodVo.getProd_id() %>', this)"
               data-status="<%= currentStatus %>">
                <img id="toggle-image-<%= prodVo.getProd_id() %>"
                     src="<%=request.getContextPath()%>/images/active.png" alt="active"/>
            </a>
        </td>
    </tr>
    <%
            }
        } else {
    %>
    <tr>
        <td colspan="4">위시리스트가 없습니다.</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

</body>
</html>