<%@page import="kr.or.ddit.prod.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@page import="java.text.NumberFormat"%>
<%@ page import="kr.or.ddit.util.PageVO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


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
    PageVO pageInfo = (PageVO) request.getAttribute("pageInfo");

    // 가격 포맷 설정 (NumberFormat 사용)
    NumberFormat currencyFormatter = NumberFormat.getInstance();
%>

<!-- 상품 목록 보기 제목과 글쓰기 버튼 -->
<div class="content-header" style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
    <button id="addBtn" style="padding: 10px 20px; background-color: #007bff; color: white; border: none; cursor: pointer;">
        상품 추가
    </button>
</div>

<!-- 검색창 추가 -->
<div class="search-bar" style="text-align: right; margin-bottom: 20px;">
    <form action="<%= request.getContextPath() %>/prod/prodList.do" method="get">
        <!-- 검색 조건 선택 -->
        <select name="stype" class="form-control" style="display: inline-block; width: auto; margin-right: 10px;">
            <option value="title">제목</option>
            <option value="content">내용</option>
        </select>

        <!-- 검색어 입력 -->
        <input type="text" name="sword" placeholder="검색어 입력" class="form-control" style="display: inline-block; width: auto;">

        <!-- 검색 버튼 -->
        <button type="submit" class="btn">검색</button>
    </form>
</div>

<!-- 상품 목록 그리드 -->
<div class="product-grid" style="display: flex; flex-wrap: wrap; justify-content: space-between;">
    <%
        for (ProdVO pvo : prodList) {
            String formattedPrice = currencyFormatter.format(pvo.getProd_price()); // 가격을 000,000 형식으로 포맷
    %>
        <div class="product-grid-item" style="flex-basis: 23%; margin-bottom: 20px; box-sizing: border-box;">
            <a href="<%=request.getContextPath()%>/prod/prodListView.do?prodId=<%= pvo.getProd_id() %>">
                <img src="<%= request.getContextPath() %>/images/prod/<%= pvo.getProd_image() %>" alt="<%= pvo.getProd_name() %>" style="width: 100%; height: auto;" />
                <br/>
                <%= pvo.getProd_name() %>
            </a>
            <!-- 가격 표시 -->
            <div class="product-price">
                <p>KRW <%= formattedPrice %></p>
            </div>
        </div>
    <%
        }
    %>
</div>

<!-- 페이지네이션 -->
<div class="pagination" style="text-align: center; margin-top: 20px;">
    <%
        if (pageInfo != null) {
            if (pageInfo.getStartPage() > 1) {
    %>
    <a href="<%=request.getContextPath()%>/prod/prodList.do?page=<%=pageInfo.getStartPage() - 1%>&stype=<%=request.getParameter("stype")%>&sword=<%=request.getParameter("sword")%>">이전</a>
    <%
        }
        for (int i = pageInfo.getStartPage(); i <= pageInfo.getEndPage(); i++) {
    %>
    <a href="<%=request.getContextPath()%>/prod/prodList.do?page=<%=i%>&stype=<%=request.getParameter("stype")%>&sword=<%=request.getParameter("sword")%>"><%=i%></a>
    <%
        }
        if (pageInfo.getEndPage() < pageInfo.getTotalPage()) {
    %>
    <a href="<%=request.getContextPath()%>/prod/prodList.do?page=<%=pageInfo.getEndPage() + 1%>&stype=<%=request.getParameter("stype")%>&sword=<%=request.getParameter("sword")%>">다음</a>
    <%
            }
        }
    %>
</div>


<script type="text/javascript">
    $(function(){
        $("#addBtn").on("click", function(){
            location.href = "<%=request.getContextPath()%>/prod/prodInsert.do";
        });
    });
</script>
