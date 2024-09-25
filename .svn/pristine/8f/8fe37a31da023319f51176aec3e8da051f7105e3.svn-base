<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>

<style type="text/css">
/* - 정석진 2024-09-23-  */
/* 전체 페이지 스타일 */
body {
    font-family: Arial, sans-serif;       /* 기본 폰트 설정 */
    display: flex;                        /* flexbox 레이아웃 사용 */
    justify-content: center;              /* 가로 중앙 정렬 */
    align-items: center;                  /* 세로 중앙 정렬 */
    height: 100vh;                        /* 뷰포트 높이의 100% */
    margin: 0;                            /* 기본 마진 제거 */
    background-color: #f0f0f0;            /* 배경색: 연한 회색 */
}

/* 폼 컨테이너 스타일 */
.container {
    background-color: white;              /* 배경색: 흰색 */
    padding: 2rem;                        /* 내부 여백 */
    border-radius: 8px;                   /* 모서리 둥글게 */
    box-shadow: 0 0 10px rgba(0,0,0,0.1); /* 그림자 효과 */
    width: 300px;                         /* 너비 설정 */
}

/* 제목 스타일 */
h2 {
    text-align: center;                   /* 텍스트 중앙 정렬 */
    color: #333;                          /* 글자색: 진한 회색 */
    margin-bottom: 20px;                  /* 아래쪽 여백 */
}

/* 폼 그룹 스타일 */
.form-group {
    margin-bottom: 15px;                  /* 아래쪽 여백 */
}

/* 입력 필드 공통 스타일 */
input[type="text"], 
input[type="password"], 
input[type="email"], 
input[type="tel"], 
input[type="date"] {
    width: 100%;                          /* 전체 너비 */
    padding: 10px;                        /* 내부 여백 */
    border: 1px solid #ddd;               /* 테두리: 연한 회색 */
    border-radius: 4px;                   /* 모서리 둥글게 */
    box-sizing: border-box;               /* 패딩을 너비에 포함 */
}

/* ID 체크 그룹 스타일 */
.id-check-group {
    display: flex;                        /* flexbox 사용 */
    justify-content: space-between;       /* 요소 사이 공간 분배 */
    align-items: center;                  /* 세로 중앙 정렬 */
}

/* ID 입력 필드 스타일 */
.id-check-group input[type="text"] {
    flex-grow: 1;                         /* 남은 공간 모두 차지 */
    margin-right: 10px;                   /* 오른쪽 여백 */
}

/* ID 중복 확인 버튼 스타일 */
#idCheck, .button {
    padding: 10px;                        /* 내부 여백 */
    background-color: #4CAF50;            /* 배경색: 초록색 */
    color: white;                         /* 글자색: 흰색 */
    border: none;                         /* 테두리 없음 */
    border-radius: 4px;                   /* 모서리 둥글게 */
    cursor: pointer;                      /* 마우스 오버 시 손가락 모양 */
    font-size: 0.8em;                     /* 글자 크기 */
}

/* ID 중복 확인 버튼 호버 효과 */
#idCheck:hover, .button:hover {
    background-color: #45a049;            /* 배경색: 더 진한 초록색 */
}

/* 제출 버튼 스타일 */
input[type="submit"] {
    width: 100%;                          /* 전체 너비 */
    padding: 10px;                        /* 내부 여백 */
    background-color: #4CAF50;            /* 배경색: 초록색 */
    color: white;                         /* 글자색: 흰색 */
    border: none;                         /* 테두리 없음 */
    border-radius: 4px;                   /* 모서리 둥글게 */
    cursor: pointer;                      /* 마우스 오버 시 손가락 모양 */
}

/* 제출 버튼 호버 효과 */
input[type="submit"]:hover {
    background-color: #45a049;            /* 배경색: 더 진한 초록색 */
}

/* ID 체크 결과 메시지 스타일 */
#idCheckResult {
    font-size: 0.9rem;                    /* 글자 크기 */
    margin-top: 5px;                      /* 위쪽 여백 */
}
.error {
    border-color: red !important;
}

.error-message {
    color: red;
    font-size: 0.8em;
    margin-top: 5px;
}
/* 버튼 그룹 스타일 */
.button-group {
    display: flex;
    justify-content: space-between;
}

/* 버튼 공통 스타일 */
.btn {
    width: 48%;  /* 두 버튼이 나란히 놓이도록 조정 */
    padding: 10px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;
    text-align: center;
    text-decoration: none;  /* 링크 밑줄 제거 */
    transition: background-color 0.3s;
}

/* 주 버튼 스타일 (가입하기) */
.btn-primary {
    background-color: #4CAF50;
    color: white;
}

.btn-primary:hover {
    background-color: #45a049;
}

/* 보조 버튼 스타일 (뒤로가기) */
.btn-secondary {
    background-color: #f4f4f4;
    color: #333;
}

.btn-secondary:hover {
    background-color: #e7e7e7;
}
</style>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.7.1.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script type="text/javascript">
// 전역 변수 선언
let isIdChecked = false;
let isEmailVerified = false;
let isCaptchaCompleted = false;

$(document).ready(function() {
    // ID 중복 확인 로직
    $("#idCheck").on("click", function() {
        const memId = $("#memId").val().trim();
        if (!memId) {
            alert("ID를 입력하세요");
            return;
        }
             
        // Ajax를 사용하여 서버에 ID 중복 확인 요청
        $.ajax({
            url : '<%=request.getContextPath()%>/member/idCheck.do',
            type: 'POST',
            data : { "memId" : memId },
            success : function(result){
                if(result === "available"){
                    $("#idCheckResult").html("사용가능").css("color", "green");
                    isIdChecked = true;
                }else{
                    $("#idCheckResult").html("ID 중복 - 사용불가").css("color", "red");
                    isIdChecked = false;
                }
            },
            error : function(xhr){
                alert("상태 : " + xhr.status);
            }
        });
    });
    
    // ID 입력 필드 변경 시 중복 확인 초기화
    $("#memId").on("input", function(){
        isIdChecked = false;
        $("#idCheckResult").text("");
    });
        
    // 비밀번호 확인 검증
    $("#memPass2").on("input", function() {
        var pass1 = $("#memPass").val();
        var pass2 = $(this).val();
        if (pass1 !== pass2) {
            $(this).addClass("error");
            $("#passError").text("비밀번호가 일치하지 않습니다.").show();
        } else {
            $(this).removeClass("error");
            $("#passError").hide();
        }
    });

    // 이메일 형식 검증
    $("#memEmail").on("input", function() {
        var email = $(this).val();
        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            $(this).addClass("error");
            $("#emailError").text("올바른 이메일 형식이 아닙니다.").show();
        } else {
            $(this).removeClass("error");
            $("#emailError").hide();
        }
    });

    // 전화번호 형식 검증
    $("#memPhone").on("input", function() {
        var phone = $(this).val();
        var phoneRegex = /^010-\d{4}-\d{4}$/;
        if (!phoneRegex.test(phone)) {
            $(this).addClass("error");
            $("#phoneError").text("010-1234-5678 형식으로 입력하세요.").show();
        } else {
            $(this).removeClass("error");
            $("#phoneError").hide();
        }
    });
    
    // 이메일 인증 버튼 클릭 이벤트
    let emailVerificationSent = false;

    $("#emailVerify").on("click", function() {
        const email = $("#memEmail").val().trim();
        if (!email) {
            alert("이메일을 입력하세요");
            return;
        }
        
        if (!emailVerificationSent) {
            // 첫 번째 클릭: 이메일 인증 메일 발송
            $.ajax({
                url: '<%=request.getContextPath()%>/member/sendVerificationEmail.do',
                type: 'POST',
                data: { "email": email },
                success: function(result) {
                    if (result === "success") {
                        alert("인증 이메일이 발송되었습니다. 이메일을 확인해주세요.");
                        emailVerificationSent = true;
                        $("#emailVerify").val("인증 확인");
                    } else {
                        alert("이메일 발송에 실패했습니다. 다시 시도해주세요.");
                    }
                },
                error: function(xhr) {
                    alert("오류가 발생했습니다. 상태: " + xhr.status);
                }
            });
        } else {
            // 두 번째 클릭: 이메일 인증 상태 확인
            $.ajax({
                url: '<%=request.getContextPath()%>/member/checkEmailVerification.do',
                type: 'POST',
                data: { "email": email },
                success: function(result) {
                    if (result === "verified") {
                        alert("이메일 인증이 완료되었습니다.");
                        $("#emailVerify").prop("disabled", true).css("background-color", "#cccccc");
                        $("#memEmail").prop("readonly", true);
                        $("#emailVerificationStatus").text("이메일 인증 완료").css("color", "green");
                        isEmailVerified = true;
                    } else {
                        alert("이메일 인증이 아직 완료되지 않았습니다. 이메일을 확인해 주세요.");
                    }
                },
                error: function(xhr) {
                    alert("오류가 발생했습니다. 상태: " + xhr.status);
                }
            });
        }
    });
        
    // 폼 제출 시 최종 검증
    $("#registerForm").on("submit", function(e){
        e.preventDefault(); // 기본 제출 동작 방지
        let isValid = true;
        
        console.log("Form submitted. CAPTCHA completed:", isCaptchaCompleted); // 디버깅용 로그

        if(!isIdChecked){
            alert("ID 중복 확인을 해주세요.");
            return;
        }
        
        if (!isEmailVerified) {
            alert("이메일 인증을 완료해주세요.");
            return;
        }

        if (!isCaptchaCompleted) {
            alert("자동가입방지 게임을 완료해주세요.");
            return;
        }
        
        // 비밀번호 일치 여부 검사
        if ($("#memPass").val() !== $("#memPass2").val()) {
            $("#memPass2").addClass("error");
            $("#passError").text("비밀번호가 일치하지 않습니다.").show();
            isValid = false;
        }

        // 이메일 형식 검사
        if (!$("#memEmail").val().match(/^[^\s@]+@[^\s@]+\.[^\s@]+$/)) {
            $("#memEmail").addClass("error");
            $("#emailError").text("올바른 이메일 형식이 아닙니다.").show();
            isValid = false;
        }

        // 전화번호 형식 검사
        if (!$("#memPhone").val().match(/^010-\d{4}-\d{4}$/)) {
            $("#memPhone").addClass("error");
            $("#phoneError").text("010-1234-5678 형식으로 입력하세요.").show();
            isValid = false;
        }
        
        // 우편번호 입력 확인
        if ($("#postcode").val().trim() === "") {
            $("#postcode").addClass("error");
            if ($("#postcode").next(".error-message").length === 0) {
                $("<div class='error-message'>우편번호를 입력해주세요.</div>").insertAfter("#postcode");
            }
            isValid = false;
        } else {
            $("#postcode").removeClass("error");
            $("#postcode").next(".error-message").remove();
        }
     
        // 상세주소 입력 확인
        if ($("#detailAddress").val().trim() === "") {
            $("#detailAddress").addClass("error");
            if ($("#detailAddress").next(".error-message").length === 0) {
                $("<div class='error-message'>상세주소를 입력해주세요.</div>").insertAfter("#detailAddress");
            }
            isValid = false;
        }
        
        // 모든 필수 필드 검사
        $("input[required]").each(function() {
            if ($(this).val().trim() === "") {
                $(this).addClass("error");
                if ($(this).next(".error-message").length === 0) {
                    $("<div class='error-message'>이 입력란을 작성하세요.</div>").insertAfter(this);
                }
                isValid = false;
            } else {
                $(this).removeClass("error");
                $(this).next(".error-message").remove();
            }
        });
        
        // 모든 검증을 통과했다면 폼을 제출
        if (isValid) {
            console.log("All validations passed. Submitting form.");
            this.submit();
        }
    });
    
    // 상세주소 입력 필드 변경 감지
    $("#detailAddress").on("input", function() {
        if ($(this).val().trim() !== "") {
            $(this).removeClass("error");
            $(this).next(".error-message").remove();
        }
    });

    // 우편번호 입력 필드 변경 감지
    $("#postcode").on("input", function() {
        if ($(this).val().trim() !== "") {
            $(this).removeClass("error");
            $(this).next(".error-message").remove();
        }
    });
});

// 우편번호 검색 함수 (Daum 우편번호 서비스 사용)
function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById('address').value = data.address;
            document.getElementById('detailAddress').focus();
            
            // 우편번호가 입력되면 에러 메시지와 에러 클래스 제거
            $("#postcode").removeClass("error");
            $("#postcode").next(".error-message").remove();
        }
    }).open();
}

function emailVerificationSuccess(email) {
    isEmailVerified = true;
    $("#emailVerify").prop("disabled", true).css("background-color", "#cccccc");
    $("#memEmail").prop("readonly", true);
    $("#emailVerificationStatus").text("이메일 인증 완료").css("color", "green");
}

//자동 입력방지 미로게임
function openCaptcha() {
    var width = 400;
    var height = 480;
    var left = (screen.width / 2) - (width / 2);
    var top = (screen.height / 2) - (height / 2);
    
    window.open('<%=request.getContextPath()%>/member/mazeCaptcha.do', 'Captcha', 
        'width=' + width + 
        ',height=' + height + 
        ',left=' + left + 
        ',top=' + top + 
        ',resizable=no,scrollbars=no,status=no');
}

// 이 함수를 window 객체에 바인딩
window.captchaCompleted = function() {
    isCaptchaCompleted = true; // 전역 변수 업데이트
    $("#captchaButton").css("background-color", "#cccccc").prop("disabled", true);
    $("#captchaStatus").text("인증 완료").css("color", "green");
    console.log("CAPTCHA completed set to:", isCaptchaCompleted); // 디버깅용 로그
}
</script>
</head>
<body>
<div class="container">
    <h2>회원가입</h2>
    <form id="registerForm" method="post" action="<%=request.getContextPath()%>/member/register.do">
        <div class="form-group id-check-group">
            <input type="text" name="memId" id="memId" placeholder="아이디" required>
            <input id="idCheck" type="button" value="중복확인">
        </div>
        <div id="idCheckResult"></div>
        
        <div class="form-group">
            <input type="password" name="memPass" id="memPass" placeholder="비밀번호" required>
        </div>
        
        <div class="form-group">
            <input type="password" name="memPass2" id="memPass2" placeholder="비밀번호 확인" required>
            <div id="passError" class="error-message"></div>
        </div>
        
        <div class="form-group">
            <input type="text" name="memName" placeholder="이름" required>
        </div>
        
        <div class="form-group">
             <input type="email" name="memEmail" id="memEmail" placeholder="이메일" required>
             <input id="emailVerify" type="button" value="이메일 인증">
             <div id="emailVerificationStatus"></div>
             <div id="emailError" class="error-message"></div>
        </div>
        
        <div class="form-group">
            <input type="tel" name="memPhone" id="memPhone" placeholder="전화번호 (010-1234-5678)" required>
            <div id="phoneError" class="error-message"></div>
        </div>
        
        <div class="form-group">
            <input type="date" name="memBirth" required>
        </div>
        
        <div class="form-group">
            <input type="text" id="postcode" name="memZipcode" placeholder="우편번호" readonly>
            <input type="button" onclick="execDaumPostcode()" value="우편번호 찾기" class="button">
        </div>
        <div class="form-group">
            <input type="text" id="address" name="memAddress" placeholder="주소" readonly>
        </div>
        <div class="form-group">
            <input type="text" id="detailAddress" name="memDetailAddress" placeholder="상세주소">
        </div>
        <div class="form-group">
            <input type="button" id="captchaButton" value="자동가입방지 게임" onclick="openCaptcha()">
            <span id="captchaStatus"></span>
        </div>
        
        <div class="form-group button-group">
    <input type="submit" value="가입하기" class="btn btn-primary">
    <button type="button" class="btn btn-secondary" onclick="history.back()">뒤로가기</button>
</div>
    </form>
</div>
</body>
</html>