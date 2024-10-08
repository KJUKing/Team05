package kr.or.ddit.member.controller;

/**
 * 회원 정보 수정을 담당하는 컨트롤러
 * 
 * 이 서블릿은 회원 정보 수정 요청을 처리합니다.
 * - GET 요청: 회원 정보 수정 페이지 표시
 * - POST 요청: 수정된 회원 정보를 처리하고 저장
 * - 로그인 상태 확인 및 권한 체크
 * URL 매핑: /member/editMember.do
 * - 정석진 2024-09-23- 
 */
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/member/editMember.do")
public class EditMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService;

	// 서블릿 초기화 메서드
    // MemberService 인스턴스를 생성하고 초기화합니다.
	public void init() {
		String baseURL = getServletContext().getInitParameter("baseURL");
		memberService = MemberService.getInstance(baseURL);
	}

	/**
     * GET 요청 처리 메서드
     * 회원 정보 수정 폼을 표시합니다.
     * 
     * @param request 클라이언트의 요청 정보를 담고 있는 HttpServletRequest 객체
     * @param response 클라이언트에게 응답을 보내기 위한 HttpServletResponse 객체
     * @throws ServletException 서블릿 처리 중 오류 발생 시
     * @throws IOException 입출력 처리 중 오류 발생 시
     */
	// TODO: 회원 정보를 조회하고 수정 폼에 표시하는 로직 구현
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 세션 체크: 로그인하지 않은 사용자는 로그인 페이지로 리다이렉트
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInMember") == null) {
			response.sendRedirect(request.getContextPath() + "/member/login.do");
			return;
		}

		// URL 파라미터에서 회원 ID 추출
		String memId = request.getParameter("memId");
		if (memId == null || memId.trim().isEmpty()) {
			// 유효하지 않은 회원 ID인 경우 회원 목록 페이지로 리다이렉트
			response.sendRedirect(request.getContextPath() + "/member/memberList.do");
			return;
		}

		// 회원 정보 조회
		MemberVO member = memberService.getMemberById(memId);
		if (member != null) {
			// 조회된 회원 정보를 request 속성에 저장
			request.setAttribute("member", member);
			// 회원 정보 수정 페이지로 포워딩
			request.getRequestDispatcher("/WEB-INF/views/member/editMember.jsp").forward(request, response);
		} else {
			// 회원 정보가 없는 경우 회원 목록 페이지로 리다이렉트
			response.sendRedirect(request.getContextPath() + "/member/memberList.do");
		}
	}

	/**
     * POST 요청 처리 메서드
     * 수정된 회원 정보를 처리하고 저장합니다.
     * 
     * @param request 클라이언트의 요청 정보를 담고 있는 HttpServletRequest 객체
     * @param response 클라이언트에게 응답을 보내기 위한 HttpServletResponse 객체
     * @throws ServletException 서블릿 처리 중 오류 발생 시
     * @throws IOException 입출력 처리 중 오류 발생 시
     */
	// TODO: 수정된 회원 정보를 받아 처리하는 로직 구현
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 세션 체크: 로그인하지 않은 사용자는 로그인 페이지로 리다이렉트
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInMember") == null) {
			response.sendRedirect(request.getContextPath() + "/member/login.do");
			return;
		}

		// 요청 파라미터의 인코딩 설정
		request.setCharacterEncoding("UTF-8");

		// 폼에서 전송된 데이터로 MemberVO 객체 생성
		MemberVO member = new MemberVO();
		member.setMemId(request.getParameter("memId"));
		member.setMemName(request.getParameter("memName"));
		member.setMemPass(request.getParameter("memPass"));
		member.setMemEmail(request.getParameter("memEmail"));
		member.setMemPhone(request.getParameter("memPhone"));
		member.setMemZipcode(request.getParameter("memZipcode"));
		member.setMemAddress(request.getParameter("memAddress"));
		member.setMemDetailAddress(request.getParameter("memDetailAddress"));

		// 생년월일 처리
		String birthStr = request.getParameter("memBirth");
		if (birthStr != null && !birthStr.isEmpty()) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date birthDate = sdf.parse(birthStr);
				member.setMemBirth(birthDate);
			} catch (ParseException e) {
				e.printStackTrace();
				// 생년월일 형식이 잘못된 경우 에러 메시지 설정
				request.setAttribute("error", "생년월일 형식이 올바르지 않습니다.");
				request.setAttribute("member", member);
				request.getRequestDispatcher("/WEB-INF/views/member/editMember.jsp").forward(request, response);
				return;
			}
		} else {
			// 생년월일이 입력되지 않은 경우, 기존 데이터 유지
			MemberVO existingMember = memberService.getMemberById(member.getMemId());
			if (existingMember != null) {
				member.setMemBirth(existingMember.getMemBirth());
			}
		}

		// 회원 정보 업데이트
		if (memberService.updateMember(member)) {
			// 업데이트 성공 시 상세 페이지로 리다이렉트
			response.sendRedirect(request.getContextPath() + "/member/memberDetail.do?memId=" + member.getMemId());
		} else {
			// 업데이트 실패 시 에러 메시지 설정 후 수정 페이지로 다시 포워딩
			request.setAttribute("error", "회원 정보 수정에 실패했습니다.");
			request.setAttribute("member", member);
			request.getRequestDispatcher("/WEB-INF/views/member/editMember.jsp").forward(request, response);
		}
	}
}