let canvas, ctx;
let maze = [];
let playerPos = { x: 0, y: 0 };
let endPos = { x: 0, y: 0 };
const cellSize = 30;
const mazeSize = 10;
let isDrawing = false;
let path = [];

function initMaze() {
    canvas = document.getElementById('mazeCanvas');
    ctx = canvas.getContext('2d');
    
    generateMaze();
    drawMaze();
    
    canvas.addEventListener('mousedown', startDrawing);
    canvas.addEventListener('mousemove', draw);
    canvas.addEventListener('mouseup', stopDrawing);
    canvas.addEventListener('mouseout', stopDrawing);
}

function generateMaze() {
    // 모든 셀을 벽으로 초기화
    for (let i = 0; i < mazeSize; i++) {
        maze[i] = [];
        for (let j = 0; j < mazeSize; j++) {
            maze[i][j] = 1;
        }
    }

    // 시작점과 끝점 설정
    playerPos = { x: 1, y: 1 };  // 좌상단 모서리에서 한 칸 떨어진 위치
    endPos = { x: mazeSize - 2, y: mazeSize - 2 };  // 우하단 모서리에서 한 칸 떨어진 위치
    
    // 재귀적 백트래킹을 사용한 미로 생성
    carvePassagesFrom(playerPos.x, playerPos.y);

    // 시작점과 끝점이 벽으로 막히지 않도록 보장
    maze[playerPos.y][playerPos.x] = 0;
    maze[endPos.y][endPos.x] = 0;
}

function carvePassagesFrom(x, y) {
    maze[y][x] = 0;  // 현재 위치를 통로로 만듦
    const directions = [
        { dx: 0, dy: -1 }, // 상
        { dx: 1, dy: 0 },  // 우
        { dx: 0, dy: 1 },  // 하
        { dx: -1, dy: 0 }  // 좌
    ];

    // 방향을 무작위로 섞음
    for (let i = directions.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [directions[i], directions[j]] = [directions[j], directions[i]];
    }

    for (const dir of directions) {
        const newX = x + dir.dx * 2;  // 두 칸 이동
        const newY = y + dir.dy * 2;

        if (newX >= 0 && newX < mazeSize && newY >= 0 && newY < mazeSize && maze[newY][newX] === 1) {
            // 중간 벽을 제거
            maze[y + dir.dy][x + dir.dx] = 0;
            maze[newY][newX] = 0;
            carvePassagesFrom(newX, newY);
        }
    }
}

function drawMaze() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    
    // 배경을 흰색으로 칠합니다
    ctx.fillStyle = '#FFFFFF';
    ctx.fillRect(0, 0, canvas.width, canvas.height);
    
    // 미로의 벽을 그립니다
    for (let y = 0; y < mazeSize; y++) {
        for (let x = 0; x < mazeSize; x++) {
            if (maze[y][x] === 1) {
                ctx.fillStyle = '#000000';
                ctx.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
            } else {
                // 빈 공간에 연한 회색 테두리를 그려 구분을 돕습니다
                ctx.strokeStyle = '#CCCCCC';
                ctx.strokeRect(x * cellSize, y * cellSize, cellSize, cellSize);
            }
        }
    }
    
    // 시작점 그리기
    ctx.fillStyle = '#00FF00';
    ctx.fillRect(playerPos.x * cellSize, playerPos.y * cellSize, cellSize, cellSize);
    
    // 끝점 그리기
    ctx.fillStyle = '#FF0000';
    ctx.fillRect(endPos.x * cellSize, endPos.y * cellSize, cellSize, cellSize);

    // 경로 그리기
    if (path.length > 0) {
        ctx.strokeStyle = '#0000FF';
        ctx.lineWidth = 3;
        ctx.beginPath();
        ctx.moveTo(path[0].x * cellSize + cellSize / 2, path[0].y * cellSize + cellSize / 2);
        for (let i = 1; i < path.length; i++) {
            ctx.lineTo(path[i].x * cellSize + cellSize / 2, path[i].y * cellSize + cellSize / 2);
        }
        ctx.stroke();
    }
}

function startDrawing(e) {
    const rect = canvas.getBoundingClientRect();
    const x = Math.floor((e.clientX - rect.left) / cellSize);
    const y = Math.floor((e.clientY - rect.top) / cellSize);
    
    if (x === playerPos.x && y === playerPos.y) {
        isDrawing = true;
        path = [{x: playerPos.x, y: playerPos.y}];
        drawMaze();
    }
}

function draw(e) {
    if (!isDrawing) return;

    const rect = canvas.getBoundingClientRect();
    const x = Math.floor((e.clientX - rect.left) / cellSize);
    const y = Math.floor((e.clientY - rect.top) / cellSize);
    
    if (x >= 0 && x < mazeSize && y >= 0 && y < mazeSize && maze[y][x] === 0) {
        const lastPoint = path[path.length - 1];
        if (Math.abs(x - lastPoint.x) + Math.abs(y - lastPoint.y) === 1) {
            path.push({x, y});
            drawMaze();
            
            if (x === endPos.x && y === endPos.y) {
                isDrawing = false;
                document.getElementById('successMessage').style.display = 'block';
                document.getElementById('completeButton').style.display = 'block';
            }
        }
    }
}

function stopDrawing() {
    isDrawing = false;
}

function completeCaptcha() {
    console.log("completeCaptcha 함수 실행"); // 디버깅용 로그
    const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
    fetch(contextPath + '/member/mazeCaptcha.do', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: 'action=complete'
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            console.log("CAPTCHA 완료 처리 성공"); // 디버깅용 로그
            if (window.opener && typeof window.opener.captchaCompleted === 'function') {
                window.opener.captchaCompleted();
                window.close();
            } else {
                console.error("부모 창의 captchaCompleted 함수를 찾을 수 없습니다.");
            }
        }
    })
    .catch(error => console.error('Error:', error));
}

window.onload = initMaze;