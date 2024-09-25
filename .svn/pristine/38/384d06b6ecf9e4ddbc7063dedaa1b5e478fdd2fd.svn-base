<%@page import="kr.or.ddit.prod.vo.OptionVO"%>
<%@page import="kr.or.ddit.prod.vo.DeCateVO"%>
<%@page import="kr.or.ddit.prod.vo.MainCateVO"%>
<%@page import="kr.or.ddit.prod.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="kr.or.ddit.util.PageVO" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
<style type="text/css">
    .content-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 20px;
    }

    #addBtn {
        padding: 10px 20px;
        background-color: #d1b48e; /* 베이지색 */
        color: white;
        border: none;
        cursor: pointer;
        border-radius: 5px;
        transition: background-color 0.3s;
    }

    #addBtn:hover {
        background-color: #b89f76; /* 호버 시 색상 */
    }

    .search-bar {
        text-align: center; /* 가운데 정렬 */
        margin: 20px 0; /* 위아래 여백 추가 */
    }

    .search-bar select,
    .search-bar input,
    .search-bar button {
        padding: 10px;
        margin-right: 10px;
        border-radius: 5px;
        border: 1px solid #ccc;
        font-size: 14px;
    }

    .search-bar button {
        background-color: #d1b48e; /* 베이지색 */
        color: white;
        border: none;
        cursor: pointer;
    }

    .search-bar button:hover {
        background-color: #b89f76; /* 호버 시 색상 */
    }

    .product-grid {
        display: flex;
        flex-wrap: wrap;
        justify-content: space-between;
    }

    .product-grid-item {
        flex-basis: 23%;
        margin-bottom: 20px;
        box-sizing: border-box;
        background-color: #ffffff;
        border-radius: 5px;
        transition: box-shadow 0.3s;
    }

    .product-grid-item:hover {
        box-shadow: 0 4px 8px rgba(0,0,0,0.2);
    }

    .product-price {
        margin-top: 10px;
        font-weight: bold;
    }

    .pagination {
        text-align: center;
        margin-top: 20px;
    }

    .pagination a {
        margin: 0 5px;
        padding: 8px 12px;
        background-color: #d1b48e; /* 베이지색 */
        color: white;
        border-radius: 5px;
        text-decoration: none;
    }

    .pagination a:hover {
        background-color: #b89f76; /* 호버 시 색상 */
    }
    
    .product-title {
	    color: black; /* 기본 색상 */
	    text-decoration: none; /* 기본적으로 밑줄 없음 */
	    transition: text-decoration 0.3s; /* 밑줄 전환 효과 */
	}
	
	.product-title:hover {
	    text-decoration: underline; /* 마우스를 올리면 밑줄 추가 */
	}
	
	.title {
    font-size: 24px; /* 글자 크기 */
    font-weight: bold; /* 글자 두께 */
    color: #333; /* 글자 색상 */
    margin-bottom: 20px; /* 아래쪽 여백 */
    text-align: center; /* 가운데 정렬 */
    padding: 10px; /* 패딩 */
    border-bottom: 2px solid #d1b48e; /* 아래쪽 경계선 */
	}
	
</style>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.7.1.js"></script>
<script type="text/javascript">
$(function(){
    $("#addBtn").on("click", function(){
        location.href = "<%=request.getContextPath()%>/prod/prodInsert.do";
    });
});
</script>

<%
    List<ProdVO> prodList = (List<ProdVO>) request.getAttribute("prodList");
    List<MainCateVO> mcList = (List<MainCateVO>)request.getAttribute("mcList");
    List<DeCateVO> dcList = (List<DeCateVO>)request.getAttribute("dcList");
    List<OptionVO> optList = (List<OptionVO>)request.getAttribute("OptionList");

    PageVO pageInfo = (PageVO) request.getAttribute("pageInfo");

    // 가격 포맷 설정 (NumberFormat 사용)
    NumberFormat currencyFormatter = NumberFormat.getInstance();
%>

<%
    String adminId = "";
    if (session.getAttribute("loggedInAdmin") != null) {
        adminId = ((kr.or.ddit.member.vo.AdminVO)session.getAttribute("loggedInAdmin")).getAdminId();
    }
%>

<%
    if ("admin".equals(adminId)) {
%>
<div class="content-header">
    <button id="addBtn">상품 추가</button>
</div>
<% } %>

<h2 class="title">Bottom</h2>
<div class="product-grid">
<%
    for (ProdVO pvo : prodList) {
        String formattedPrice = currencyFormatter.format(pvo.getProd_price());
%>
    <div class="product-grid-item">
        <a href="<%=request.getContextPath()%>/prod/prodListView.do?prodId=<%= pvo.getProd_id() %>">
            <% 
            String imgid = "";
            if(pvo.getImageList() != null && pvo.getImageList().size() > 0) {
                imgid = String.valueOf(pvo.getImageList().get(0).getImageId());
            }
            %>
            <img src="<%= request.getContextPath() %>/images/prodImageView.do?imgId=<%=imgid%>" alt="<%= pvo.getProd_name() %>" style="width: 100%; height: auto;" />
            <br/>
            <a href="<%=request.getContextPath()%>/prod/prodListView.do?prodId=<%= pvo.getProd_id() %>" class="product-title">
			    <%= pvo.getProd_name() %>
			</a>
        </a>
        <div class="product-price">
            <%= formattedPrice %> 원
        </div>
    </div>
<%
    }
%>
</div>

<div class="pagination">
<%
    if (pageInfo != null) {
        if (pageInfo.getStartPage() > 1) {
%>
    <a href="<%=request.getContextPath()%>/prod/prodBottomList.do?page=<%=pageInfo.getStartPage() - 1%>&stype=<%=request.getParameter("stype")%>&sword=<%=request.getParameter("sword")%>">이전</a>
<%
        }
        for (int i = pageInfo.getStartPage(); i <= pageInfo.getEndPage(); i++) {
%>
    <a href="<%=request.getContextPath()%>/prod/prodBottomList.do?page=<%=i%>&stype=<%=request.getParameter("stype")%>&sword=<%=request.getParameter("sword")%>"><%=i%></a>
<%
        }
        if (pageInfo.getEndPage() < pageInfo.getTotalPage()) {
%>
    <a href="<%=request.getContextPath()%>/prod/prodBottomList.do?page=<%=pageInfo.getEndPage() + 1%>&stype=<%=request.getParameter("stype")%>&sword=<%=request.getParameter("sword")%>">다음</a>
<%
        }
    }
%>
</div>

<div class="search-bar">
    <form action="<%= request.getContextPath() %>/prod/prodBottomList.do" method="get">
        <select name="stype">
            <option value="title">제목</option>
            <option value="content">내용</option>
        </select>
        <input type="text" name="sword" placeholder="검색어 입력">
        <button type="submit">검색</button>
    </form>
</div>

</body>
</html>
