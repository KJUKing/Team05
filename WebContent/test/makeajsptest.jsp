<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
    <style>
        canvas {
            border: 1px solid black;
        }
    </style>
    <script>
	    window.onload = function() {
	    	
	    const canvas = document.getElementById('myCanvas');
	    const ctx = canvas.getContext('2d');
	
	    // 원의 초기 위치 및 속도
	    let x = canvas.width / 2;
	    let y = canvas.height / 2;
	    let dx = 2;
	    let dy = 2;
	    const radius = 20;
	
	    function drawBall() {
	        // 캔버스 지우기
	        ctx.clearRect(0, 0, canvas.width, canvas.height);
	        
	        // 원 그리기
	        ctx.beginPath();
	        ctx.arc(x, y, radius, 0, Math.PI * 2);
	        ctx.fillStyle = 'blue';
	        ctx.fill();
	        ctx.closePath();
	    }
	
	    function update() {
	        // 공이 벽에 부딪히면 튕기기
	        if(x + dx > canvas.width - radius || x + dx < radius) {
	            dx = -dx;
	        }
	        if(y + dy > canvas.height - radius || y + dy < radius) {
	            dy = -dy;
	        }
	
	        // 공의 위치 업데이트
	        x += dx;
	        y += dy;
	
	        drawBall();
	    }
	
	    // 애니메이션 시작
	    setInterval(update, 10);
	    }
    </script>
</head>
<body>
	<canvas id="myCanvas" width="400" height="400"></canvas>
    <script src="script.js"></script>
    
    commit test
</body>
</html>