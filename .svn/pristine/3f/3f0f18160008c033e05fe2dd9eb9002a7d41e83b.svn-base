package kr.or.ddit.member.controller;

/**
 * 관리자 대시보드 컨트롤러
 * 
 * 이 서블릿은 관리자 대시보드 페이지에 대한 요청을 처리합니다.
 * URL 매핑: /member/adminDashboard.do
 * 
 * 주요 기능:
 * 1. 관리자 로그인 상태 확인
 * 2. 관리자 대시보드 페이지로 포워딩
 * 
 * 데이터 흐름:
 * 1. 클라이언트 요청 -> AdminDashboardController
 * 2. 세션에서 관리자 정보 확인
 * 3. 로그인 상태에 따라 대시보드 페이지로 포워딩 또는 로그인 페이지로 리다이렉트
 * - 정석진 2024-09-23- 
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.vo.AdminVO;

@WebServlet("/member/adminDashboard.do")
public class AdminDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// GET 요청 처리 메서드
	// 관리자 로그인 상태를 확인하고 원하는 페이지로 이동시킵니다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		AdminVO admin = (AdminVO) session.getAttribute("loggedInAdmin");

		// 로그인 상태 확인
		if (admin == null) {
			// 로그인되지 않은 경우 로그인 페이지로 리다이렉트
			response.sendRedirect(request.getContextPath() + "/member/login.do");
			return;
		}
		// 로그인된 경우 관리자 대시보드 페이지로 포워딩
		request.getRequestDispatcher("/main?view=admin").forward(request, response);
	}
}