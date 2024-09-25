package kr.or.ddit.member.controller;

/**
 * 이메일 인증을 담당하는 컨트롤러
 * 
 * 이 서블릿은 회원가입 후 이메일 인증 요청을 처리합니다.
 * URL 매핑: /member/verifyEmail.do
 * 
 * 주요 기능:
 * 1. 이메일 인증 링크로부터 전달받은 이메일과 토큰을 검증
 * 2. 인증 성공 시 회원의 이메일 인증 상태를 업데이트
 * 3. 인증 결과에 따른 적절한 페이지로 사용자를 안내
 * 
 * 데이터 흐름:
 * 1. 사용자가 이메일의 인증 링크 클릭 -> GET 요청 발생
 * 2. 컨트롤러에서 이메일과 토큰 파라미터 추출 및 검증
 * 3. MemberService를 통해 이메일 인증 처리
 * 4. 인증 결과에 따라 적절한 뷰로 포워딩
 * - 정석진 2024-09-23- 
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.MemberService;

@WebServlet("/member/verifyEmail.do")
public class EmailVerificationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService;

	// 서블릿 초기화 메서드
    // MemberService 인스턴스를 생성하고 초기화합니다.
	public void init() {
		String baseURL = getServletContext().getInitParameter("baseURL");
		memberService = MemberService.getInstance(baseURL);
	}

    /** GET 요청 처리 메서드
    * 이메일 인증 링크를 통해 접근했을 때의 처리를 담당합니다.
	* @param request 클라이언트의 요청 정보를 담고 있는 HttpServletRequest 객체
    * @param response 클라이언트에게 응답을 보내기 위한 HttpServletResponse 객체
    * @throws ServletException 서블릿 처리 중 오류 발생 시
    * @throws IOException 입출력 처리 중 오류 발생 시
    */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 요청 파라미터에서 이메일과 토큰 추출
		String email = request.getParameter("email");
		String token = request.getParameter("token");
		// 이메일과 토큰이 존재하는 경우에만 처리
        if (email != null && token != null) {
            // MemberService를 통해 이메일 인증 수행
            if (memberService.verifyEmail(email, token)) {
                // 이메일 인증 성공
                memberService.setEmailVerified(email, true);
                request.setAttribute("verificationSuccess", true);
                request.setAttribute("verifiedEmail", email);
                // 인증 성공 페이지로 포워딩
                request.getRequestDispatcher("/WEB-INF/views/member/emailVerificationConfirm.jsp").forward(request, response);
           
				return;
			}
		}
	}
}