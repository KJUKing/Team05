<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>QnA</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="../js/jquery-3.7.1.js"></script>
    <script src="../js/qna.js"></script>
    <script src="../js/jquery.serializejson.min.js"></script>
    <link rel="stylesheet" href="qnaStyle.css">
    
    <style>
        div {
        }
        .container-main {
            border: 1px solid black;
            width: 1000px;
            height: 600px;
            position: absolute;
            left: 50%;
            transform: translate(-500px);
        }
    </style>
    
    <script>
        let currentPage = 1;
        let mypath = '<%=request.getContextPath()%>';

        $(document).ready(function() {
            // 화면에 qna 리스트 출력
            $.qnaList();
            
            // 페이지 번호 클릭 이벤트
            $(document).on('click', '.pageno', function(){
                currentPage = $(this).text();
                $.qnaList();
            });
            
            // 다음 페이지
            $(document).on('click', '#next', function(){
                currentPage = parseInt($('.pageno').last().text()) + 1;
                $.qnaList();
            });
            
            // 이전 페이지
            $(document).on('click', '#prev', function(){
                currentPage = $('.pageno').first().text() - 1;
                $.qnaList();
            });
            
            // 검색 기능
            $(document).on('click', '#search', function(){
                currentPage = 1;
                $.qnaList();
            });
        });
    </script>
</head>

<body>
    <div class="title">
        <h2>공지사항</h2>
    </div>
    
    <div class='container' id='container-main'>
        <div class='wrapper' id='wrapper-qna'>
            <form action="submitQnA.jsp" method="post">
                <div class='content' id="content-qna">
                    <select class="form-select" id="stype">
                        <option value="">선택</option>
                        <option value="writer">작성자</option>
                        <option value="subject">제목</option>
                        <option value="content">내용</option>
                    </select>
                    <input class="form-control me-2" type="text" id="sword" placeholder="Search">
                </div>
                <button type="submit">검색</button>
            </form>

            <!-- 게시글 리스트 출력 -->
            <div id="result"></div>
            <br><br>

            <!-- 페이지 번호 출력 -->
            <div id="pagelist"></div>

            <div class="container-fluid">
                <!-- 새 글쓰기 버튼 -->
                <a href="qnaInsert.jsp" class="btn btn-primary">글쓰기</a>
            </div>
        </div>
    </div>
</body>
</html>
