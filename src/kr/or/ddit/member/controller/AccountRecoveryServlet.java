package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import kr.or.ddit.member.service.MemberService;

// 계정 복구 관련 요청을 처리하는 서블릿
@WebServlet("/member/accountRecovery.do")
public class AccountRecoveryServlet extends HttpServlet {
    // 로깅을 위한 Logger 인스턴스
    private static final Logger logger = Logger.getLogger(AccountRecoveryServlet.class);
    // 회원 서비스 인스턴스
    private MemberService memberService;

    // 서블릿 초기화 메소드
    public void init() {
        // 애플리케이션의 기본 URL을 가져와 MemberService 인스턴스를 초기화
        String baseURL = getServletContext().getInitParameter("baseURL");
        memberService = MemberService.getInstance(baseURL);
    }

    // POST 요청 처리 메소드
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 요청 인코딩 설정
            request.setCharacterEncoding("utf-8");
            // 요청 파라미터에서 action 값을 가져옴
            String action = request.getParameter("action");
            // 응답 설정
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            
            logger.info("Received action: " + action);

            // action 값에 따라 적절한 메소드 호출
            if ("findId".equals(action)) {
                handleFindId(request, response);
            } else if ("findPassword".equals(action)) {
                handleFindPassword(request, response);
            } else {
                logger.warn("Unknown action: " + action);
                response.getWriter().write("잘못된 요청입니다.");
            }
        } catch (Exception e) {
            logger.error("AccountRecoveryServlet에서 예외 발생: ", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("서버 오류가 발생했습니다.");
        }
    }

    // 아이디 찾기 처리 메소드
    private void handleFindId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 요청 파라미터에서 필요한 정보를 가져옴
        String name = request.getParameter("name");
        String birthdate = request.getParameter("birthdate");
        String phone = request.getParameter("phone");

        // MemberService를 통해 아이디 찾기 수행
        String foundId = memberService.findIdByNameBirthdatePhone(name, birthdate, phone);
        if (foundId != null && !foundId.isEmpty()) {
            response.getWriter().write("회원님의 아이디는 " + foundId + "입니다.");
        } else {
            response.getWriter().write("아이디를 찾을 수 없습니다.");
        }
    }

    // 비밀번호 찾기 처리 메소드
    private void handleFindPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 요청 파라미터에서 필요한 정보를 가져옴
        String id = request.getParameter("id");
        String email = request.getParameter("email");
      
        try {
            // 유효한 사용자인지 확인
            if (memberService.isValidUserForPasswordReset(id, email)) {
                // 이메일 제공자 추출
                String emailProvider = extractEmailProvider(email);
                // 비밀번호 재설정 이메일 발송
                memberService.sendPasswordResetEmail(email, emailProvider, id);
                response.getWriter().write("비밀번호 재설정 이메일이 발송되었습니다. 이메일을 확인해주세요.");
            } else {
                response.getWriter().write("일치하는 사용자 정보를 찾을 수 없습니다.");
            }
        } catch (RuntimeException e) {
            logger.error("비밀번호 재설정 이메일 발송 중 오류 발생: " + e.getMessage(), e);
            response.getWriter().write("이메일 발송 중 오류가 발생했습니다. 나중에 다시 시도해 주세요.");
        }
    }

    // 이메일 주소에서 이메일 제공자 추출 메소드
    private String extractEmailProvider(String email) {
        String domain = email.substring(email.indexOf("@") + 1);
        return domain.substring(0, domain.indexOf(".")); // "naver", "gmail" 등을 반환
    }
}