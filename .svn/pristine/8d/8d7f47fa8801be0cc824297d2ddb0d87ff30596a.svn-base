<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비밀번호 재설정</title>
    <style>
    /* - 정석진 2024-09-23-  */
        body {
            font-family: Arial, sans-serif;
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
        }
        h2 {
            color: #333;
        }
        form {
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 5px;
        }
        label {
            display: block;
            margin-top: 10px;
        }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 15px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .error {
            color: red;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
     <h2>비밀번호 재설정</h2>
    <% if (request.getAttribute("error") != null) { %>
        <p class="error"><%= request.getAttribute("error") %></p>
    <% } %>
    <form id="resetForm" action="${pageContext.request.contextPath}/member/resetPassword.do" method="post" onsubmit="return validateForm()">
        <label for="memId">아이디:</label>
        <input type="text" id="memId" name="memId" value="${param.memId}" required readonly>
        
        <label for="token">토큰:</label>
        <input type="text" id="token" name="token" value="${param.token}" required readonly>
        
        <label for="newPassword">새 비밀번호:</label>
        <input type="password" id="newPassword" name="newPassword" required>
        
        <label for="confirmPassword">비밀번호 확인:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required>
        
        <input type="submit" value="비밀번호 변경">
    </form>

    <script>
    function validateForm() {
        var newPassword = document.getElementById("newPassword").value;
        var confirmPassword = document.getElementById("confirmPassword").value;
        
        if (newPassword !== confirmPassword) {
            alert("새 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
            return false;
        }
              
        return true;
    }

    // 비밀번호 변경 성공 시 처리
    <% if (request.getAttribute("success") != null) { %>
        alert("비밀번호가 성공적으로 변경되었습니다.");
        window.close();
    <% } %>
    </script>
</body>
</html>