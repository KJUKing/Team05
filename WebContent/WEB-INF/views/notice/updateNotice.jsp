<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="kr.or.ddit.notice.vo.NoticeVO" %>
<%@ page import="kr.or.ddit.image.vo.ImageVO" %>

<%
    NoticeVO notice = (NoticeVO) request.getAttribute("notice");
    List<ImageVO> imageList = (List<ImageVO>) request.getAttribute("imageList"); // 이미지 리스트 받기
%>

<div class="container">
    <h2 class="title">공지사항 수정</h2>

    <form action="<%= request.getContextPath() %>/notice/updateNotice.do" method="post" enctype="multipart/form-data">
        <!-- 공지사항 번호 hidden 필드 -->
        <input type="hidden" name="notiNo" value="<%= notice.getNotiNo() %>">

        <div class="form-group">
            <label for="notiTitle">제목</label>
            <input type="text" id="notiTitle" name="notiTitle" value="<%= notice.getNotiTitle() %>" required class="form-control">
        </div>

        <div class="form-group">
            <label for="notiContent">내용</label>
            <textarea id="notiContent" name="notiContent" rows="10" required class="form-control"><%= notice.getNotiContent() %></textarea>
        </div>

        <!-- 기존 이미지 리스트 출력 -->
        <div class="form-group">
            <h3>기존 이미지</h3>
            <div class="image-list">
                <% if (imageList != null && !imageList.isEmpty()) { %>
                <% for (ImageVO image : imageList) { %>
                <div class="existing-image">
                    <img src="<%= request.getContextPath() + image.getImagePath() %>" alt="첨부 이미지" class="img-thumbnail">
                    <!-- 이미지 삭제 체크박스 -->
                    <label class="delete-label">
                        <input type="checkbox" name="deleteImage" value="<%= image.getImageId() %>"> 삭제
                    </label>
                </div>
                <% } %>
                <% } else { %>
                <p>기존에 첨부된 이미지가 없습니다.</p>
                <% } %>
            </div>
        </div>


        <!-- 새 이미지 추가 -->
        <div class="form-group">
            <label for="notiImage">새 이미지 추가</label>
            <input type="file" id="notiImage" name="notiImage" multiple class="form-control">
        </div>

        <!-- 버튼들 -->
        <div class="form-group buttons">
            <button type="submit" class="btn btn-primary">수정하기</button>
            <a href="<%= request.getContextPath() %>/notice/noticeList.do" class="btn btn-secondary">목록으로 돌아가기</a>
        </div>
    </form>
</div>

<style>
    .container {
        width: 60%;
        margin: 0 auto;
    }

    .title {
        font-size: 24px;
        font-weight: bold;
        margin-bottom: 20px;
    }

    .form-group {
        margin-bottom: 20px;
    }

    label {
        display: block;
        font-size: 16px;
        font-weight: bold;
        margin-bottom: 5px;
    }

    .form-control {
        width: 100%;
        padding: 8px;
        font-size: 16px;
        border: 1px solid #ddd;
        border-radius: 4px;
    }

    textarea.form-control {
        height: 200px;
        resize: none;
    }

    /* 기존 이미지를 가로로 나열하기 위한 스타일 */
    .image-list {
        display: flex;
        flex-wrap: wrap;
        gap: 20px;
    }

    .existing-image {
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .img-thumbnail {
        max-width: 150px;
        height: auto;
        margin: 10px;
        border: 1px solid #ddd;
        border-radius: 4px;
    }

    .delete-label {
        margin-top: 5px;
    }

    .form-group.buttons {
        display: flex;
        justify-content: space-between;
    }

    .btn {
        padding: 10px 20px;
        text-align: center;
    }

    .submit-btn {
        padding: 10px 20px;
        background-color: #007bff;
        color: white;
        border: none;
        cursor: pointer;
        border-radius: 4px;
        margin-right: 10px;
    }

    .submit-btn:hover {
        background-color: #0056b3;
    }

    .back-btn-link {
        padding: 8px 12px;
        background-color: #007bff;
        color: white;
        text-decoration: none;
        border-radius: 4px;
    }

    .back-btn-link:hover {
        background-color: #0056b3;
    }
</style>

