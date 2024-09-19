<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>

<div class="container">
    <h2 class="title">공지사항 작성</h2>

    <form action="<%= request.getContextPath() %>/notice/insertNotice.do" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="notiTitle">제목</label>
            <input type="text" id="notiTitle" name="notiTitle" required class="form-control">
        </div>
        <div class="form-group">
            <label for="notiContent">내용</label>
            <textarea id="notiContent" name="notiContent" rows="10" required class="form-control"></textarea>
        </div>

        <!-- 이미지 등록 버튼 추가 -->
        <div class="form-group">
            <label for="notiImage">이미지 등록</label>
            <input type="file" id="notiImage" name="notiImage" class="form-control" multiple>
        </div>

        <!-- 작성하기 및 목록으로 돌아가기 버튼 위치 변경 -->
        <div class="form-group buttons">
            <button type="submit" class="submit-btn">작성하기</button>
            <a href="<%= request.getContextPath() %>/notice/noticeList.do" class="back-btn-link">목록으로 돌아가기</a>
        </div>
    </form>
</div>

<!-- 스타일링 -->
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

    .form-group.buttons {
        display: flex;
        justify-content: flex-end;
        align-items: center;
    }
</style>
