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
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f0f0f0;
        }

        /* 컨테이너 스타일 */
        .container {
            background-color: white;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            width: 400px;
        }

        /* 제목 스타일 */
        h2 {
            text-align: center;
            color: #333;
        }

        /* 폼 그룹 스타일 */
        .form-group {
            margin-bottom: 15px;
        }

        /* 라벨 스타일 */
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        /* 입력 필드 스타일 */
        input[type="text"], input[type="email"], input[type="tel"], input[type="password"], input[type="date"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        /* 우편번호 검색 버튼 스타일 */
        .postcode-btn {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 8px 15px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 14px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 4px;
        }

        /* 버튼 컨테이너 스타일 */
        .button-container {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;    /* 상단 여백 추가 */
        }
        
        /* 공통 버튼 스타일 */
        .btn {
            padding: 10px 5px;A
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            color: white;
            width: 18%;  /* 버튼 너비를 좀 더 좁게 설정 */
            text-align: center;
            text-decoration: none;
            display: inline-block;
            transition: background-color 0.3s;
            white-space: nowrap;  /* 버튼 텍스트가 줄바꿈되지 않도록 설정 */
            overflow: hidden;    /* 텍스트가 버튼을 벗어나지 않도록 설정 */
            text-overflow: ellipsis;  /* 텍스트가 버튼을 벗어날 경우 ... 으로 표시 */
        }
        
         /* 수정 버튼 스타일 */
        .btn-edit, .btn-update {
            background-color: #4CAF50;        /* 초록색 배경 */       
        }                                                        
        .btn-edit:hover, .btn-update:hover {                     
            background-color: #45a049;        /* 호버 시 더 진한 초록색 */
        }                                                        
                                                                 
        /* 삭제 버튼 스타일 */                                   
        .btn-delete {                                            
            background-color: #f44336;        /* 빨간색 배경 */       
        }                                                        
        .btn-delete:hover {                                      
            background-color: #da190b;        /* 호버 시 더 진한 빨간색 */
        }                                                        
                                                                 
        /* 목록으로 버튼 스타일 */                               
        .btn-back {                                              
            background-color: #2196F3;        /* 파란색 배경 */       
        }                                                        
        .btn-back:hover {                                        
            background-color: #0b7dda;        /* 호버 시 더 진한 파란색 */
        }                                                        
                                                                 
        /* 로그아웃 버튼 스타일 */                               
        .btn-logout {                                            
            background-color: #555555;        /* 회색 배경 */        
        }                                                        
        .btn-logout:hover {                                      
            background-color: #333333;        /* 호버 시 더 진한 회색 */ 
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
                <button type="button" class="btn btn-back" onclick="history.back()">뒤로가기</button>
                <button type="button" class="btn btn-delete" onclick="deleteMember()">회원 삭제</button>
                <button type="button" class="btn btn-back" onclick="location.href='<%=request.getContextPath()%>/member/memberList.do'">목록으로</button>
                <button type="button" class="btn btn-logout" onclick="logout()">로그아웃</button>
            </div>
        </form>
    </div>
</body>
</html>