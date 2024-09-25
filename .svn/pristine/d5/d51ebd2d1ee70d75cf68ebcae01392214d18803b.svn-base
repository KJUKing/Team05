package kr.or.ddit.member.controller;

/**
 * 회원 삭제 컨트롤러
 * 
 * 이 서블릿은 회원 삭제 요청을 처리합니다.
 * URL 매핑: /member/deleteMember.do
 * 
 * 주요 기능:
 * 1. MemberService 초기화
 * 2. 로그인 상태 확인
 * 3. 회원 삭제 처리
 * 4. 삭제 결과에 따른 리다이렉션
 * 
 * 데이터 흐름:
 * 1. 클라이언트 요청 (회원 ID 포함) -> DeleteMemberController
 * 2. 세션 확인 후 MemberService를 통해 회원 삭제
 * 3. 삭제 결과에 따라 적절한 페이지로 리다이렉트
 * - 정석진 2024-09-23- 
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.MemberService;

@WebServlet("/member/deleteMember.do")
public class DeleteMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService;

	// 서블릿 초기화 메서드
	// baseURL을 가져와 MemberService를 초기화합니다.
	public void init() {
		String baseURL = getServletContext().getInitParameter("baseURL");
		memberService = MemberService.getInstance(baseURL);
	}

	// POST 요청 처리 메서드
	// 회원 삭제 요청을 처리하고 결과에 따라 적절한 페이지로 리다이렉트합니다.
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 세션 체크: 로그인하지 않은 사용자는 로그인 페이지로 리다이렉트
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInMember") == null) {
			response.sendRedirect(request.getContextPath() + "/member/login.do");
			return;
		}

		// 삭제할 회원의 ID를 요청 파라미터에서 가져옴
		String memId = request.getParameter("memId");

		if (memId != null && !memId.trim().isEmpty()) {
			// 회원 삭제 시도
			boolean isDeleted = memberService.deleteMember(memId);
			if (isDeleted) {
				// 삭제 성공 시 세션 무효화 (로그아웃)
				session.invalidate();
				// 로그인 페이지로 리다이렉트
				response.sendRedirect(request.getContextPath() + "/member/login.do");
			} else {
				// 삭제 실패 시 에러 메시지와 함께 회원 상세 페이지로 리다이렉트
				response.sendRedirect(
						request.getContextPath() + "/member/memberDetail.do?memId=" + memId + "&error=deleteFailed");
			}
		} else {
			// 유효하지 않은 회원 ID인 경우 회원 목록 페이지로 리다이렉트
			response.sendRedirect(request.getContextPath() + "/member/memberList.do");
		}
	}
}