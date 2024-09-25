<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 대시보드</title>
<style>
/* 전체 페이지 설정 */
body {
    font-family: 'Roboto', sans-serif;
    margin: 0;
    padding: 0;
    background-color: #ffffff; /* 흰색 배경 */
    display: flex;
    flex-direction: column;
    min-height: 100vh;
}

.container {
    max-width: 1200px; /* 중앙 정렬을 위해 최대 너비 설정 */
    margin: 0 auto;
    padding: 2rem;
    flex-grow: 1;
}

h1 {
    text-align: center;
    color: #333;
    margin-bottom: 2rem;
    font-size: 1.8rem;
}

/* 카드 그리드 설정 */
.card-grid {
    display: grid;
    grid-template-columns: repeat(3, 1fr); /* 3개의 열로 구성 */
    gap: 20px;
}

.card {
    background-color: #fff;
    padding: 1.5rem;
    border-radius: 10px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
    text-align: center;
    transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.card:hover {
    transform: translateY(-5px);
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
}

.card h2 a {
    font-size: 1.3rem;
    color: #333;
    text-decoration: none;
    font-weight: bold;
}

.card h2 a:hover {
    text-decoration: none;
    color: #333;
}

.card p {
    font-size: 0.9rem;
    color: #555;
    margin-top: 10px;
    line-height: 1.4;
}

/* 로그아웃 버튼 */
.logout-button {
    background-color: #ff4d4f;
    color: white;
    border: none;
    padding: 0.75rem;
    font-size: 1.1rem;
    margin-top: 2rem;
    width: 100%;
    border-radius: 8px;
    cursor: pointer;
    transition: background-color 0.3s ease, transform 0.2s ease;
}

.logout-button:hover {
    background-color: #d43537;
    transform: scale(1.02);
}

.menu-button {
    padding: 0.75rem;
    font-size: 1rem;
    margin-top: 1rem;
    width: 100%;
    text-align: center;
    border-radius: 8px;
    background-color: #4CAF50;
    color: white;
    border: none;
    transition: background-color 0.3s ease;
}

.menu-button:hover {
    background-color: #45a049;
}
h3{
	text-align : center;
}

</style>
<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
</head>
<body>
    <div class="container">
  
        <h3>관리자 페이지</h3>

        <div class="card-grid">
            <div class="card">
                <h2><a href="<%=request.getContextPath()%>/member/memberList.do">회원 관리</a></h2>
                <p>회원 목록을 관리합니다.</p>
            </div>
            <div class="card">
                <h2><a href="<%=request.getContextPath()%>/coupon/couponList.do">쿠폰 관리</a></h2>
                <p>쿠폰을 관리합니다.</p>
            </div>
            <div class="card">
                <h2><a href="<%=request.getContextPath()%>/card/cardList.do">카드사 관리</a></h2>
                <p>카드사 정보를 관리합니다.</p>
            </div>
            <div class="card">
                <h2><a href="<%=request.getContextPath()%>/express/expressList.do">배송업체 관리</a></h2>
                <p>배송업체를 관리합니다.</p>
            </div>
            <div class="card">
                <h2><a href="<%=request.getContextPath()%>/riturn/riturnList.do">반품 내역</a></h2>
                <p>반품 내역을 확인합니다.</p>
            </div>
            <div class="card">
                <h2><a href="<%=request.getContextPath()%>/adminreportview.do">신고된 리뷰 관리</a></h2>
                <p>신고된 리뷰를 관리합니다.</p>
            </div>
            <div class="card">
                <h2><a href="<%=request.getContextPath()%>/notice/noticeList.do">공지사항 관리</a></h2>
                <p>공지사항을 관리합니다.</p>
            </div>
            <div class="card">
                <h2><a href="<%=request.getContextPath()%>/qnaMain.do">Q&A 관리</a></h2>
                <p>Q&A를 관리합니다.</p>
            </div>
            <div class="card">
                <h2><a href="<%=request.getContextPath()%>/prod/prodList.do">상품 관리</a></h2>
                <p>상품을 관리합니다.</p>
            </div>
        </div>
        
    <%--     <form action="<%=request.getContextPath()%>/member/logout.do" method="get">
                  <input type="submit" value="로그아웃" class="logout-button">
        </form> --%>
    </div>
</body>
</html>
