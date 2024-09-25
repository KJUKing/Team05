<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="kr.or.ddit.member.vo.MemberVO"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Page</title>
<style>
/* 기본 body 스타일 */
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #ffffff;
	display: flex;
	flex-direction: column;
	min-height: 100vh;
}

/* 메인 콘텐츠 영역 */
.main-content {
	flex-grow: 1;
	padding: 20px;
}

/* 푸터 스타일 */
.footer {
	background-color: #e8e4d9;
	color: #333;
	text-align: center;
	padding: 10px 0;
	width: 100%;
	margin-top: auto;
}

/* 헤더 스타일 */
.header-top {
	display: flex;
	justify-content: space-between;
	align-items: center;
	background-color: #ffffff;
	padding: 20px;
	border-bottom: 1px solid #ddd;
}

.header-top {
	display: flex;
	justify-content: center; /* 로고를 수평 가운데 정렬 */
	align-items: center; /* 로고를 수직 가운데 정렬 */
	background-color: #ffffff;
	padding: 20px;
	border-bottom: 1px solid #ddd;
	position: relative;
}

.header-options {
	position: absolute;
	right: 20px; /* 메뉴를 오른쪽에 위치 */
}
/* 네비게이션 스타일 */
.main-nav ul {
	list-style-type: none;
	padding: 0;
	background-color: #f4f0e6;
	overflow: hidden;
}

.main-nav li {
	float: left;
}

.main-nav li a {
	display: block;
	padding: 14px 20px;
	color: #333;
	text-align: center;
	text-decoration: none;
}

.main-nav li a:hover {
	background-color: #e0dbcf;
	color: #333;
}

.container {
	width: 70%;
	max-width: 1200px;
	padding: 15px; /* 기존 30px에서 15px로 패딩을 줄임 */
	background-color: #faf9f7; /* 배경색 설정 */
	border: 1px solid #ddd;
	box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
	text-align: center;
	margin: 20px auto;
	border-radius: 10px;
}

h2 {
	font-size: 28px;
	margin-bottom: 30px;
}

.grid-menu {
	display: grid;
	grid-template-columns: repeat(2, 1fr); /* 두 개의 열로 나누기 */
	grid-gap: 40px;
	padding: 20px;
	margin: 0 auto;
	justify-items: center; /* 항목을 수평 가운데 정렬 */
	text-align: center; /* 글자를 가운데 정렬 */
}

.menu-item {
	background-color: #fff;
	padding: 20px;
	border: 1px solid #ddd;
	border-radius: 10px;
	width: 100%; /* 각 항목이 그리드의 전체 너비를 차지하도록 설정 */
	max-width: 300px; /* 카드의 최대 너비 설정 */
}

.menu-item h3 {
	font-size: 18px; /* 제목 크기를 조금 줄임 */
	margin-bottom: 10px;
	color: #333;
}

.menu-item p {
	font-size: 12px; /* 설명 크기를 줄임 */
	color: #666;
	line-height: 1.4; /* 가독성을 위한 줄 간격 */
}
.menu-item h3 a {
    text-decoration: none; /* 밑줄 없애기 */
    color: #333; /* 링크 텍스트를 검정색으로 설정 */
}

.menu-item h3 a:hover,
.menu-item h3 a:visited {
    color: #333; /* 방문한 후에도 검정색 유지 */
}

.menu-item h3 a:active {
    color: #333; /* 클릭했을 때도 검정색 유지 */
}

</style>
</head>
<body>





	<!-- 메인 콘텐츠 -->
	<div class="main-content">
		<%
            // 세션에서 전달된 사용자 정보 가져오기
            MemberVO loggedInMember = (MemberVO) request.getAttribute("loggedInMember");
            if (loggedInMember != null) {
        %>
		<div class="container">
			<p><%= loggedInMember.getMemName() %>님의 마이페이지입니다.
			</p>

			<div class="grid-menu">
			
				<div class="menu-item">
					<h3><a href="<%=request.getContextPath()%>/main?view=index">Home</a></h3>
					<p>메인 페이지로 이동합니다.</p>
					</div>
			
				<div class="menu-item">
					<h3><a href="<%=request.getContextPath()%>/member/logout.do">Logout</a></h3>
					<p>회원님의 로그인 상태를 비회원으로 전환합니다.</p>
				</div>
				
				<div class="menu-item">
					<h3><a href="<%=request.getContextPath()%>/member/editMember.do?memId=<%= loggedInMember.getMemId() %>">Profile</a>
</h3>			<p>회원이신 고객님의 개인정보를 관리하는 공간입니다.</p>
				</div>
				
				<div class="menu-item">
					<h3><a href="<%=request.getContextPath()%>/myreviewList.do?memId=<%=loggedInMember.getMemId()%>">Review</a></h3>
					<p>고객님께서 작성하신 리뷰를 관리하는 공간입니다.</p>
				</div>
				<div class="menu-item">
					<h3><a href="<%=request.getContextPath()%>/riturn/myBuyPage.do?memId=<%=loggedInMember.getMemId()%>">Order</a></h3>
					<p>고객님께서 주문하신 상품의 주문 내역을 확인하실 수 있습니다.</p>
				</div>
				<div class="menu-item">
					<h3><a href="<%=request.getContextPath()%>/prod/wishList.do">Wishlist</a></h3>
					<p>관심상품으로 등록하신 상품의 목록을 보여드립니다.</p>
				</div>
				<div class="menu-item">
					<h3><a href="<%=request.getContextPath()%>/coupon/mileCoupon.do?memId=<%=loggedInMember.getMemId()%>">Mileage/Coupon</a></h3>
					<p>고객님의 보유하고 계신 쿠폰과 마일리지 내역을 보여드립니다.</p>

				</div>
				
				<div class="menu-item">
					<h3><a href="<%=request.getContextPath()%>/riturn/riturnView.do?memId=<%=loggedInMember.getMemId()%>">Return</a></h3>
					<p>고객님께서 반품하신 상품의 반품 내역을 확인하실 수 있습니다.</p>

				</div>
					
					
				</div>
			</div>


		</div>
		<%
            } else {
        %>
		<p>로그인 정보가 없습니다.</p>
		<%
            }
        %>
	</div>



</body>
</html>
