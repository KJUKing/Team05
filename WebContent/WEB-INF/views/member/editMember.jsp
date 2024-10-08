<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.or.ddit.member.vo.MemberVO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 정보 수정</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <style>
    /* - 정석진 2024-09-23-  */
        /* 전체 페이지 스타일 */
body {
    font-family: Arial, sans-serif;                 /* Arial 폰트 사용 */
    display: flex;                                  /* Flexbox 레이아웃 사용 */
    justify-content: center;                        /* 가로 중앙 정렬 */
    align-items: center;                            /* 세로 중앙 정렬 */
    height: 100vh;                                  /* 뷰포트 높이의 100% */
    margin: 0;                                      /* 바깥 여백 제거 */
}

/* 컨테이너 스타일 */
.container {
    background-color: rgba(230, 226, 211, 0.8);     /* 반투명 베이지색 배경 */
    padding: 2rem;                                  /* 안쪽 여백 */
    border-radius: 8px;                             /* 둥근 모서리 */
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);       /* 그림자 효과 */
    width: 350px;                                   /* 컨테이너 너비 */
    backdrop-filter: blur(5px);                     /* 배경 흐림 효과 */
}

/* 제목 스타일 */
h2 {
    text-align: center;                             /* 텍스트 중앙 정렬 */
    color: #4a4a4a;                                 /* 진한 회색 텍스트 */
    margin-bottom: 1.5rem;                          /* 아래쪽 여백 */
}

/* 폼 그룹 스타일 */
.form-group {
    margin-bottom: 15px;                            /* 아래쪽 여백 */
}

/* 라벨 스타일 */
label {
    display: block;                                 /* 블록 레벨 요소로 표시 */
    margin-bottom: 5px;                             /* 아래쪽 여백 */
    color: #666;                                    /* 중간 회색 텍스트 */
    font-size: 0.9em;                               /* 글자 크기 */
}

/* 입력 필드 스타일 */
input[type="text"], input[type="email"], input[type="tel"], input[type="password"], input[type="date"] {
    width: 100%;                                    /* 전체 너비 */
    padding: 10px;                                  /* 안쪽 여백 */
    border: 1px solid #d4cfc0;                      /* 연한 베이지색 테두리 */
    border-radius: 4px;                             /* 둥근 모서리 */
    background-color: rgba(255, 255, 255, 0.8);     /* 반투명 흰색 배경 */
    transition: border-color 0.3s ease;             /* 테두리 색상 변화 효과 */
    box-sizing: border-box;                         /* 박스 크기 계산 방식 */
}

/* 입력 필드 포커스 스타일 */
input[type="text"]:focus, 
input[type="email"]:focus, 
input[type="tel"]:focus, 
input[type="password"]:focus, 
input[type="date"]:focus {
    border-color: #b0a090;                          /* 포커스 시 진한 베이지색 테두리 */
    outline: none;                                  /* 기본 아웃라인 제거 */
}

/* 우편번호 검색 버튼 스타일 */
.postcode-btn {
    background-color: #7a6f5d;                      /* 갈색 배경 */
    color: white;                                   /* 흰색 텍스트 */
    border: none;                                   /* 테두리 없음 */
    padding: 10px 15px;                             /* 안쪽 여백 */
    text-align: center;                             /* 텍스트 중앙 정렬 */
    text-decoration: none;                          /* 텍스트 장식 없음 */
    display: inline-block;                          /* 인라인 블록으로 표시 */
    font-size: 14px;                                /* 글자 크기 */
    margin: 4px 2px;                                /* 바깥 여백 */
    cursor: pointer;                                /* 커서 모양 변경 */
    border-radius: 4px;                             /* 둥근 모서리 */
    transition: background-color 0.3s ease;         /* 배경색 변화 효과 */
}

/* 우편번호 검색 버튼 호버 스타일 */
.postcode-btn:hover {
    background-color: #5f5648;                      /* 호버 시 진한 갈색 */
}

/* 버튼 컨테이너 스타일 */
.button-container {
    display: flex;                                  /* Flexbox 레이아웃 사용 */
    justify-content: space-between;                 /* 버튼 사이 공간 균등 분배 */
    margin-top: 20px;                               /* 위쪽 여백 */
}

/* 버튼 공통 스타일 */
.btn {
    padding: 10px 5px;                              /* 안쪽 여백 */
    border: none;                                   /* 테두리 없음 */
    border-radius: 4px;                             /* 둥근 모서리 */
    cursor: pointer;                                /* 커서 모양 변경 */
    font-size: 14px;                                /* 글자 크기 */
    color: white;                                   /* 흰색 텍스트 */
    width: 22%;                                     /* 버튼 너비 */
    text-align: center;                             /* 텍스트 중앙 정렬 */
    text-decoration: none;                          /* 텍스트 장식 없음 */
    display: flex;                                  /* Flexbox 레이아웃 사용 */
    justify-content: center;                        /* 가로 중앙 정렬 */
    align-items: center;                            /* 세로 중앙 정렬 */
    transition: background-color 0.3s;              /* 배경색 변화 효과 */
    height: 40px;                                   /* 모든 버튼의 높이를 동일하게 설정 */
}

/* 수정 버튼 스타일 */
.btn-update {
    background-color: #7a6f5d;                      /* 갈색 배경 */
}
/* 수정 버튼 호버 스타일 */
.btn-update:hover {
    background-color: #5f5648;                      /* 호버 시 진한 갈색 */
}

/* 삭제 버튼 스타일 */
.btn-delete {
    background-color: #d32f2f;                      /* 빨간색 배경 */
}
/* 삭제 버튼 호버 스타일 */
.btn-delete:hover {
    background-color: #b71c1c;                      /* 호버 시 진한 빨간색 */
}

/* 뒤로 버튼 스타일 */
.btn-back {
    background-color: #9e9e9e;                      /* 회색 배경 */
}
/* 뒤로 버튼 호버 스타일 */
.btn-back:hover {
    background-color: #757575;                      /* 호버 시 진한 회색 */
}

/* 로그아웃 버튼 스타일 */
.btn-logout {
    background-color: #607d8b;                      /* 청회색 배경 */
}
/* 로그아웃 버튼 호버 스타일 */
.btn-logout:hover {
    background-color: #455a64;                      /* 호버 시 진한 청회색 */
}
            </style>
    <script>
    $(document).ready(function() {
        // 폼 제출 시 비밀번호 확인
        $("#editForm").submit(function(event) {
            var password = $("#memPass").val();
            var confirmPassword = $("#confirmPass").val();
            
            if(password !== confirmPassword) {
                alert("비밀번호가 일치하지 않습니다.");
                event.preventDefault(); // 폼 제출 중지
            }
        });
    });

    // 다음 우편번호 검색 API 함수
    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색 결과에서 우편번호와 주소 정보를 가져와 입력 필드에 삽입
                document.getElementById('memZipcode').value = data.zonecode;
                document.getElementById('memAddress').value = data.address;
                document.getElementById('memDetailAddress').focus();
            }
        }).open();
    }

    // 회원 삭제 함수
    function deleteMember() {
    if(confirm('정말로 회원을 삭제하시겠습니까?')) {
        // 동적으로 폼을 생성하여 POST 요청 전송
        var form = document.createElement('form');
        form.method = 'POST';
        form.action = '<%=request.getContextPath()%>/member/deleteMember.do';
        var input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'memId';
        input.value = document.getElementById('memIdHidden').value; // hidden 필드에서 값을 가져옴
        form.appendChild(input);
        document.body.appendChild(form);
        form.submit();
    }
}

    // 로그아웃 함수
    function logout() {
        if(confirm('로그아웃 하시겠습니까?')) {
            location.href = '<%=request.getContextPath()%>/member/logout.do';
        }
    }
    </script>
</head>
<body>
    <div class="container">
        <h2>회원 정보 수정</h2>
        
        <% 
            // 서버에서 전달받은 회원 정보를 변수에 저장
            MemberVO member = (MemberVO) request.getAttribute("member");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String birthDate = (member.getMemBirth() != null) ? sdf.format(member.getMemBirth()) : "";
        %>
        
        <!-- 회원 정보 수정 폼 hidden 필드로 전송 -->
        <form id="editForm" action="<%=request.getContextPath()%>/member/editMember.do" method="post">
            <input type="hidden" id="memIdHidden" name="memId" value="<%= member.getMemId() %>">

            <!-- 이름 입력 필드 -->
            <div class="form-group">
                <label for="memName">이름:</label>
                <input type="text" id="memName" name="memName" value="<%= member.getMemName() %>" required>
            </div>
            
            <!-- 새 비밀번호 입력 필드 -->
            <div class="form-group">
                <label for="memPass">새 비밀번호:</label>
                <input type="password" id="memPass" name="memPass" required>
            </div>
            
            <!-- 비밀번호 확인 입력 필드 -->
            <div class="form-group">
                <label for="confirmPass">비밀번호 확인:</label>
                <input type="password" id="confirmPass" name="confirmPass" required>
            </div>
            
            <!-- 생년월일 입력 필드 -->
            <div class="form-group">
                <label for="memBirth">생년월일:</label>
                <input type="date" id="memBirth" name="memBirth" value="<%= birthDate %>">
            </div>
            
            <!-- 이메일 입력 필드 -->
            <div class="form-group">
                <label for="memEmail">이메일:</label>
                <input type="email" id="memEmail" name="memEmail" value="<%= member.getMemEmail() %>" required>
            </div>
            
            <!-- 전화번호 입력 필드 -->
            <div class="form-group">
                <label for="memPhone">전화번호:</label>
                <input type="tel" id="memPhone" name="memPhone" value="<%= member.getMemPhone() %>" required>
            </div>
            
            <!-- 우편번호 입력 필드 -->
            <div class="form-group">
                <label for="memZipcode">우편번호:</label>
                <input type="text" id="memZipcode" name="memZipcode" value="<%= member.getMemZipcode() %>" readonly>
                <button type="button" class="postcode-btn" onclick="execDaumPostcode()">우편번호 검색</button>
            </div>
            
            <!-- 주소 입력 필드 -->
            <div class="form-group">
                <label for="memAddress">주소:</label>
                <input type="text" id="memAddress" name="memAddress" value="<%= member.getMemAddress() %>" readonly>
            </div>
            
            <!-- 상세주소 입력 필드 -->
            <div class="form-group">
                <label for="memDetailAddress">상세주소:</label>
                <input type="text" id="memDetailAddress" name="memDetailAddress" value="<%= member.getMemDetailAddress() %>">
            </div>
            
            <!-- 버튼 그룹 -->
            <div class="button-container">
                <button type="submit" class="btn btn-update">수정</button>
                <button type="button" class="btn btn-back" onclick="history.back()">뒤로</button>
                <button type="button" class="btn btn-delete" onclick="deleteMember()">탈퇴</button>
                <button type="button" class="btn btn-logout" onclick="logout()">로그아웃</button>
            </div>
        </form>
    </div>
</body>
</html>