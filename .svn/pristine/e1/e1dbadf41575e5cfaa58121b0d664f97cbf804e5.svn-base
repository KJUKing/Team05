<%@ page import="java.util.List" %>
<%@ page import="kr.or.ddit.notice.vo.NoticeVO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="kr.or.ddit.image.vo.ImageVO" %>
<%@ page import="kr.or.ddit.util.PageVO" %>
<%@ page import="kr.or.ddit.member.vo.MemberVO" %>

<%
    NoticeVO notice = (NoticeVO) request.getAttribute("notice");
    List<ImageVO> imageList = (List<ImageVO>) request.getAttribute("imageList"); // 이미지 리스트 받기
    PageVO pageInfo = (PageVO) request.getAttribute("pageInfo");  // PageVO 받기
    String stype = request.getParameter("stype"); // 검색 타입 받기
    String sword = request.getParameter("sword"); // 검색어 받기

    // 세션에서 로그인된 사용자 정보 가져오기
    MemberVO loggedInMember = (MemberVO) session.getAttribute("loggedInMember");
    String memId = (loggedInMember != null) ? loggedInMember.getMemId() : null;

    if (notice == null) {
        out.println("공지사항을 찾을 수 없습니다.");
    }
%>
<!-- 삭제 확인을 위한 스크립트 -->
<script>
    function confirmDelete(notiNo) {
        if (confirm("정말 삭제하시겠습니까?")) {
            location.href = "<%= request.getContextPath() %>/notice/deleteNotice.do?notiNo=" + notiNo;
        }
    }
</script>

<div class="container">
    <h2 class="title"><%= notice != null ? notice.getNotiTitle() : "공지사항 없음" %></h2>
    <div class="notice-meta">
        <span>작성일: <%= notice != null ? notice.getNotiDate() : "" %></span>
        <span>작성자: <%= notice != null ? notice.getAdminId() : "" %></span>
    </div>

    <!-- 본문과 이미지가 함께 출력되는 영역 -->
    <div class="notice-content">
        <!-- 이미지 리스트 출력 -->
        <% if (imageList != null && !imageList.isEmpty()) { %>
        <div class="image-container">
            <% for (ImageVO image : imageList) { %>
            <img src="<%= request.getContextPath() + image.getImagePath() %>" alt="첨부 이미지" class="notice-image">
            <% } %>
        </div>
        <% } %>

        <!-- 본문 내용 -->
        <div class="content-box">
            <%= notice != null ? notice.getNotiContent() : "내용 없음" %>
        </div>
    </div>

    <!-- 버튼 영역 -->
    <div class="button-group">
        <% if ("admin".equals(memId)) {  // 관리자만 수정, 삭제 버튼 표시 %>
        <a href="<%= request.getContextPath() %>/notice/updateNotice.do?notiNo=<%= notice.getNotiNo() %>"
           class="btn">수정</a>
        <a href="javascript:void(0);" class="btn" onclick="confirmDelete(<%= notice.getNotiNo() %>)">삭제</a>
        <% } %>
        
        <!-- 필터링된 목록으로 돌아가기 (stype, sword, page 유지) -->
        <a href="<%= request.getContextPath() %>/notice/noticeList.do?page=<%= pageInfo != null ? pageInfo.getCurrentPage() : 1 %>
        &stype=<%= request.getParameter("stype") != null ? request.getParameter("stype") : "" %>
        &sword=<%= request.getParameter("sword") != null ? request.getParameter("sword") : "" %>"
           class="btn">목록으로 돌아가기</a>
    </div>
</div>

<!-- 스타일링 -->
<style>
    .container {
        width: 80%;
        margin: 0 auto;
        font-family: Arial, sans-serif;
    }

    .title {
        font-size: 24px;
        font-weight: bold;
        margin-bottom: 10px;
    }

    .notice-meta {
        margin-bottom: 20px;
        font-size: 14px;
        color: gray;
    }

    .notice-meta span {
        margin-right: 20px;
    }

    /* 본문과 이미지가 함께 출력되는 영역 */
    .notice-content {
        margin-bottom: 30px;
    }

    .content-box {
        min-height: 300px;
        padding: 20px;
        box-sizing: border-box;
    }

    /* 이미지 출력 영역 - 본문과 함께 */
    .image-container {
        margin-bottom: 20px;
    }

    .notice-image {
        width: auto;
        height: auto;
        margin-bottom: 15px;
        border: 1px solid #ddd;
        padding: 5px;
        background-color: #f9f9f9;
    }

    .button-group {
        text-align: right;
        display: flex;
        justify-content: flex-end;
        margin-bottom: 40px; /* 푸터와 버튼 사이에 여유 공간을 주기 위한 마진 추가 */
    }

    .button-group a {
        padding: 8px 12px;
        background-color: #007bff;
        color: white;
        text-decoration: none;
        border-radius: 4px;
        margin-left: 10px;
    }

    .button-group a:hover {
        background-color: #0056b3;
    }
</style>
