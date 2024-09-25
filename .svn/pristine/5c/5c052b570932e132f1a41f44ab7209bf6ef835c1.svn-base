<%@ page import="java.util.List"%>
<%@ page import="kr.or.ddit.notice.vo.NoticeVO" %>
<%@ page import="kr.or.ddit.util.PageVO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="kr.or.ddit.member.vo.MemberVO" %>
<%@ page contentType="text/html;charset=UTF-8" %>

		<%
        String adminId = "";
        if (session.getAttribute("loggedInAdmin") != null) {
            adminId = ((kr.or.ddit.member.vo.AdminVO)session.getAttribute("loggedInAdmin")).getAdminId();
        }
        %>

<!-- 기존 JSP 코드 유지 -->
<div class="container">
    <h2 class="title">
        <a href="<%= request.getContextPath() %>/notice/noticeList.do" class="title-link">
            공지사항
        </a>
    </h2>

    <!-- 검색창 추가 -->
    <div class="search-bar" style="text-align: right; margin-bottom: 20px;">
        <form action="<%= request.getContextPath() %>/notice/noticeList.do" method="get">
            <select name="stype" class="form-control" style="display: inline-block; width: auto; margin-right: 10px;">
                <option value="title">제목</option>
                <option value="content">내용</option>
                <option value="title_content">제목+내용</option>
                <option value="writer">작성자</option>
            </select>

            <input type="text" name="sword" placeholder="검색어 입력" class="form-control" style="display: inline-block; width: auto;">

            <button type="submit" class="btn">검색</button>
        </form>
    </div>

    <table class="notice-table">
        <thead>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>
        </thead>
        <tbody>
        <%
            SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
            if (request.getAttribute("noticeList") != null) {
                List<NoticeVO> noticeList = (List<NoticeVO>) request.getAttribute("noticeList");
                for (NoticeVO notice : noticeList) {
                    String formattedDate = dateFormat.format(notice.getNotiDate());
        %>
        <tr>
            <td><%= notice.getNotiNo() %></td>
            <td><a href="<%= request.getContextPath() %>/notice/noticeDetail.do?notiNo=<%= notice.getNotiNo() %>
                &stype=<%= request.getParameter("stype") %>&sword=<%= request.getParameter("sword") %>" class="notice-link">
                <%= notice.getNotiTitle() %>
            </a></td>
            <td><%= notice.getAdminId() %></td>
            <td><%= formattedDate %></td>
        </tr>
        <%    }
        } %>
        </tbody>
    </table>

    <!-- 페이지네이션 -->
    <div class="pagination">
        <%
            PageVO pageInfo = (PageVO) request.getAttribute("pageInfo");

            if (pageInfo != null) {
                if (pageInfo.getStartPage() > 1) { %>
        <a href="<%= request.getContextPath() %>/notice/noticeList.do?page=<%= pageInfo.getStartPage() - 1 %>&stype=<%= request.getParameter("stype") %>&sword=<%= request.getParameter("sword") %>" class="page-link">이전</a>
        <% }
            for (int i = pageInfo.getStartPage(); i <= pageInfo.getEndPage(); i++) { %>
        <a href="<%= request.getContextPath() %>/notice/noticeList.do?page=<%= i %>&stype=<%= request.getParameter("stype") %>&sword=<%= request.getParameter("sword") %>" class="page-link <%= (pageInfo.getCurrentPage() == i) ? "active-page" : "" %>"><%= i %></a>
        <% }
            if (pageInfo.getEndPage() < pageInfo.getTotalPage()) { %>
        <a href="<%= request.getContextPath() %>/notice/noticeList.do?page=<%= pageInfo.getEndPage() + 1 %>&stype=<%= request.getParameter("stype") %>&sword=<%= request.getParameter("sword") %>" class="page-link">다음</a>
        <% }
        }
        %>
    </div>

    <!-- 글쓰기 버튼 (관리자만 표시) -->
    <%
        if ("admin".equals(adminId)) {
    %>
    <div class="write-btn">
        <a href="<%= request.getContextPath() %>/notice/insertNotice.do" class="btn">글쓰기</a>
    </div>
    <%
        }
    %>
</div>

<!-- 스타일링 -->
<style>
    .container {
        width: 80%;
        margin: 0 auto;
    }

    .title {
        font-size: 24px;
        font-weight: bold;
        margin-bottom: 20px;
    }

    .notice-table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
    }

    .notice-table th, .notice-table td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: left;
    }

    .notice-table th {
        background-color: #f2f2f2;
    }

		/* 링크 스타일 */
	a {
	    text-decoration: none;
	    color: black;
	}
	
	a:hover {
	    color: black;
	}
	
	.title-link {
	    font-size: 24px;
	    text-decoration: none;
	    color: black;
	}
	
	.notice-link {
	    text-decoration: none;
	    color: black;
	}
	
	.notice-link:hover {
	    color: #333;
	}
	
	/* 페이지네이션 스타일 */
	.pagination {
	    text-align: center;
	    margin-top: 20px;
	}
	
	.pagination a {
	    margin: 0 5px;
	    padding: 8px 12px;
	    text-decoration: none;
	    color: #007bff; /* 기본 링크 색상 */
	    border-radius: 4px;
	    border: 1px solid #ddd;
	    transition: background-color 0.3s, color 0.3s;
	}
	
	.pagination a:hover {
	    background-color: #f0f0f0;
	    color: #0056b3;
	}
	
	/* 현재 페이지 스타일 */
	.active-page {
	    background-color: #007bff;
	    color: white !important; /* 텍스트 색상 흰색 */
	    font-weight: bold; /* 현재 페이지 강조 */
	    pointer-events: none; /* 클릭 방지 */
	    text-decoration: none;
	    border: 1px solid #007bff;
	}

    .write-btn {
        text-align: right;
    }

    .write-btn button {
        padding: 8px 12px;
        background-color: #007bff;
        color: white;
        border: none;
        cursor: pointer;
    }

    .write-btn button:hover {
        background-color: #0056b3;
    }

    .btn {
        padding: 8px 12px;
        background-color: #007bff;
        color: white;
        text-decoration: none;
        border-radius: 4px;
        border: none;
        cursor: pointer;
    }

    .btn:hover {
        background-color: #0056b3;
    }
</style>
