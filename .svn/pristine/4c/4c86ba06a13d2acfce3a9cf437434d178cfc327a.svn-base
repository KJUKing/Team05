<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>미로 게임 CAPTCHA</title>
<style>
body {
	font-family: Arial, sans-serif;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
	background-color: #f0f0f0;
}

#gameContainer {
	background-color: white;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	text-align: center;
	max-height: 90vh; /* 뷰포트 높이의 90%로 제한 */
	overflow-y: auto; /* 내용이 넘칠 경우 스크롤 허용 */
}

canvas {
	border: 1px solid #ddd;
	margin-top: 20px;
}

#successMessage {
	color: green;
	font-weight: bold;
	margin-top: 10px;
	display: none;
}

#completeButton {
	display: none;
	margin-top: 10px;
	padding: 10px 20px;
	background-color: #4CAF50;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}
</style>
</head>
<body>
	<div id="gameContainer">
		<h2>미로 게임 CAPTCHA</h2>
		<p>시작점(초록색)에서 끝점(빨간색)까지 클릭을 유지하며 경로를 그려주세요.</p>
		<canvas id="mazeCanvas" width="300" height="300"></canvas>
		<div id="successMessage">성공! 미로를 통과하셨습니다.</div>
		<button id="completeButton" onclick="completeCaptcha()">인증 완료</button>
	</div>
	<script src="<%=request.getContextPath()%>/js/mazeCaptcha.js"></script>
</body>
</html>