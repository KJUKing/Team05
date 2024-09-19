<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="kr.or.ddit.prod.vo.ProdVO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>위시리스트</title>
    <script src="<%= request.getContextPath() %>/js/jquery-3.7.1.js"></script>
    <script src="<%= request.getContextPath() %>/js/jquery.serializejson.min.js"></script>
    <script>
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
    </script>
</head>
<body>
<h2>위시리스트</h2>
<table border="1">
    <tr>
        <th>상품 ID</th>
        <th>상품 이름</th>
        <th>상품 설명</th>
        <th>상품 가격</th>
        <th>상품 이미지</th>
        <th>등록일</th>
        <th>판매 여부</th>
        <th>할인율</th>
        <th>카테고리 ID</th>
        <th>상태</th>
    </tr>
    <%
        List<ProdVO> wishList = (List<ProdVO>) request.getAttribute("wishList");

        if (wishList != null && !wishList.isEmpty()) {
            for (ProdVO prodVo : wishList) {
                String currentStatus = "active"; // 기본 상태는 활성화
    %>
    <tr>
        <td><%= prodVo.getProd_id() %></td>
        <td><%= prodVo.getProd_name() %></td>
        <td><%= prodVo.getProd_content() %></td>
        <td><%= prodVo.getProd_price() %></td>
        <td><%= prodVo.getProd_image() %></td>
        <td><%= prodVo.getProd_date() %></td>
        <td><%= prodVo.getProd_yn() %></td>
        <td><%= prodVo.getProd_sale() %></td>
        <td><%= prodVo.getDcate_id() %></td>
        <td>
            <a href="javascript:void(0);"
               onclick="wishToggle('<%= prodVo.getProd_id() %>', this)"
               data-status="<%= currentStatus %>"> <!-- 버튼에 상태 저장 -->
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
        <td colspan="9">위시리스트가 없습니다.</td>
    </tr>
    <%
        }
    %>
</table>
<a href="<%=request.getContextPath()%>/member/logout.do">로그아웃</a>
</body>
</html>
