package kr.or.ddit.member.controller;

/**
 * 이메일 인증 확인 서블릿
 * 
 * 이 서블릿은 사용자의 이메일 인증 상태를 확인하는 요청을 처리합니다.
 * URL 매핑: /member/checkEmailVerification.do
 * 
 * 주요 기능:
 * 1. MemberService 초기화
 * 2. 이메일 인증 상태 확인
 * 3. 인증 상태에 따른 응답 전송
 * 
 * 데이터 흐름:
 * 1. 클라이언트 요청 (이메일 주소 포함) -> CheckEmailVerificationServlet
 * 2. MemberService를 통해 이메일 인증 상태 확인
 * 3. 인증 상태에 따른 응답을 클라이언트에게 전송
 * - 정석진 2024-09-23- 
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.MemberService;

@WebServlet("/member/checkEmailVerification.do")
public class CheckEmailVerificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService;

	// 서블릿 초기화 메서드
    // baseURL을 가져와 MemberService를 초기화합니다.
	public void init() {
		String baseURL = getServletContext().getInitParameter("baseURL");
		memberService = MemberService.getInstance(baseURL);
	}

	// POST 요청 처리 메서드
    // 이메일 주소를 받아 인증 상태를 확인하고 결과를 응답합니다.
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");

		boolean isVerified = memberService.isEmailVerified(email);

		// 인증 상태에 따른 응답 전송
		if (isVerified) {
			response.getWriter().write("verified");
		} else {
			response.getWriter().write("pending");
		}
	}
}