<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>이메일 인증 확인</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script>
        function confirmVerification() {
            window.opener.emailVerificationSuccess('${verifiedEmail}');
            window.close();
        }
    </script>
</head>
<body>
    <h2>이메일 인증 성공</h2>
    <p>이메일 인증이 완료되었습니다. 회원가입 페이지로 돌아가 인증 확인 버튼을 눌러주세요.</p>
    <button onclick="window.close()">창 닫기</button>
</body>
</html>