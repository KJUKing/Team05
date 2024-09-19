package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import kr.or.ddit.member.service.MemberService;

// 비밀번호 재설정 요청을 처리하는 서블릿
@WebServlet("/member/resetPassword.do")
public class PasswordResetController extends HttpServlet {
    // 로깅을 위한 Logger 인스턴스
    private static final Logger logger = Logger.getLogger(PasswordResetController.class);
    // 회원 서비스 인스턴스
    private MemberService memberService;

    // 서블릿 초기화 메소드
    public void init() {
        // 애플리케이션의 기본 URL을 가져와 MemberService 인스턴스를 초기화
        String baseURL = getServletContext().getInitParameter("baseURL");
        memberService = MemberService.getInstance(baseURL);
    }
    
    // GET 요청 처리 메소드 (비밀번호 재설정 페이지 표시)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청 파라미터에서 회원 ID와 토큰을 가져옴
        String memId = request.getParameter("memId");
        String token = request.getParameter("token");
        // 요청 속성에 회원 ID와 토큰을 설정
        request.setAttribute("memId", memId);
        request.setAttribute("token", token);
        // 비밀번호 재설정 페이지로 포워딩
        request.getRequestDispatcher("/WEB-INF/views/member/resetPassword.jsp").forward(request, response);
    }
    
    // POST 요청 처리 메소드 (비밀번호 재설정 실행)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청 파라미터에서 필요한 정보를 가져옴
        String memId = request.getParameter("memId");
        String token = request.getParameter("token");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        logger.debug("Received reset password request - MemId: " + memId + ", Token: " + token);

        // 새 비밀번호와 확인 비밀번호가 일치하는지 확인
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "새 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
            request.getRequestDispatcher("/WEB-INF/views/member/resetPassword.jsp").forward(request, response);
            return;
        }

        // 토큰의 유효성 검사
        boolean isTokenValid = memberService.verifyResetToken(memId, token);
        logger.debug("Token verification result: " + isTokenValid);

        if (isTokenValid) {
            // 비밀번호 재설정 실행
            if (memberService.resetPassword(memId, newPassword)) {
                request.setAttribute("success", true);
                request.getRequestDispatcher("/WEB-INF/views/member/resetPassword.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "비밀번호 재설정에 실패했습니다. 다시 시도해주세요.");
                request.getRequestDispatcher("/WEB-INF/views/member/resetPassword.jsp").forward(request, response);
            }
        }
    }
}