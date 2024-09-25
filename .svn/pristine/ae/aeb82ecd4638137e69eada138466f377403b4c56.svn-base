package kr.or.ddit.member.controller;

/**
 * 로그인 처리를 담당하는 컨트롤러
 * 
 * 이 서블릿은 사용자의 로그인 요청을 처리합니다.
 * - GET 요청: 로그인 페이지 표시
 * - POST 요청: 로그인 처리 및 세션 생성
 * - 일반 회원과 관리자 로그인 구분 처리
 * - 로그인 성공/실패에 따른 리다이렉션
 * - 정석진 2024-09-23- 
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.vo.AdminVO;
import kr.or.ddit.member.vo.MemberVO;

//@WebServlet 어노테이션: 이 클래스가 서블릿임을 선언하고, URL 매핑을 지정하고 /member/login.do" 경로로 들어오는 요청을 이 서블릿이 처리
@WebServlet("/member/login.do")
//HttpServlet을 상속받아 서블릿으로 동작할 수 있게 합니다.
//HttpServlet은 HTTP 프로토콜을 사용하는 웹 애플리케이션을 위한 추상 클래스입니다.
public class LoginController extends HttpServlet {
	// serialVersionUID: 직렬화된 객체의 버전 관리를 위한 고유 식별자입니다.
	private static final long serialVersionUID = 1L; // 1L은 이 값이 long 타입임을 나타냅니다.
	// MemberService 인스턴스를 저장할 변수입니다.
    // 이 서비스 객체를 통해 회원 관련 비즈니스 로직을 처리합니다.
	private MemberService memberService;

	// @Override: 부모 클래스(HttpServlet)의 init 메소드를 오버라이드합니다.
    // 서블릿이 초기화될 때 한 번 실행되는 메소드
	@Override
	public void init() throws ServletException {
		super.init(); // 부모 클래스의 init 메소드를 먼저 호출
		//설정된 baseURL 파라미터 값을 가져옵니다.
		String baseURL = getServletContext().getInitParameter("baseURL");
		// MemberService 인스턴스를 생성하고 초기화합니다.
        // getInstance 메소드는 싱글톤 패턴을 구현
		memberService = MemberService.getInstance(baseURL);
	}

	// GET 요청 처리 (로그인 페이지 표시)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 저장된 아이디 쿠키 확인
		Cookie[] cookies = request.getCookies();
		// 쿠키가 존재하는 경우에만 처리합니다.
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				// "savedId"라는 이름의 쿠키를 찾습니다.
				if ("savedId".equals(cookie.getName())) {
					// 찾은 쿠키의 값을 request의 속성으로 설정합니다.
					request.setAttribute("savedId", cookie.getValue());
					break; // 쿠키를 찾았으므로 루프를 종료합니다
				}
			}
		}
		 // 이전 페이지 URL을 세션에 저장
        String referer = request.getHeader("Referer"); // Referer 헤더는 현재 요청을 보낸 페이지의 URL을 나타냅니다.
        // "/member/login.do"를 포함하지 않는 경우에만 처리
        // 이는 로그인 페이지에서 새로고침했을 때 Referer가 로그인 페이지 자체가 되는 것을 방지합니다.
        if (referer != null && !referer.contains("/member/login.do")) {
        	// 세션에 이전 페이지 URL을 저장합니다.
            request.getSession().setAttribute("prevPage", referer);
        }
        
        // 로그인 페이지로 포워딩
        // WEB-INF 폴더 내의 JSP 파일로 직접 포워딩합니다.
        request.getRequestDispatcher("/WEB-INF/views/member/Login.jsp").forward(request, response);
    }

	// POST 요청 처리 (로그인 처리)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 폼에서 전송된 파라미터들을 받아옵니다.
        String memId = request.getParameter("memId");
        String memPass = request.getParameter("memPass");
        String saveId = request.getParameter("saveId");
        String userType = request.getParameter("userType");
        // 세션에 저장된 이전 페이지 URL을 가져옵니다.이는 로그인 폼에서 hidden 필드로 전달됩니다
        String prevPage = (String) request.getSession().getAttribute("prevPage");
        
        System.out.println("login prevPage = " + prevPage);// 디버깅용 로그
        
        try {
            boolean loginSuccessful = false;
            // 기본 리다이렉트 URL을 메인 페이지로 설정합니다. 
            String redirectURL = request.getContextPath() + "/main?view=index"; // 기본 리다이렉트 URL
            // 현재 세션을 가져오거나, 없으면 새로 생성합니다.
            HttpSession session = request.getSession();
            // 세션 유지 시간을 30분(1800초)으로 설정
            session.setMaxInactiveInterval(1800);
            // 관리자 로그인 처리
            if ("admin".equals(userType)) {
                AdminVO admin = memberService.loginAdmin(memId, memPass);
                // 관리자 정보를 세션에 저장합니다.
                if (admin != null) {
                    session.setAttribute("loggedInAdmin", admin);
                    session.setAttribute("userType", "admin");
                    loginSuccessful = true;
                    // 관리자는 항상 관리자 대시보드로 리다이렉트
                    redirectURL = request.getContextPath() + "/member/adminDashboard.do";
                }
            } else {
            	// 일반 회원 로그인 처리
                MemberVO member = memberService.getMemberById(memId);
                if (member != null && memberService.login(memId, memPass)) {
                	// 회원 정보를 세션에 저장합니다.
                    session.setAttribute("loggedInMember", member);
                    session.setAttribute("userType", "member");
                    loginSuccessful = true;
                 // 이전 페이지가 있고 마이페이지였다면 마이페이지로 리다이렉트
                    if (prevPage != null && prevPage.contains("/member/myPage.do")) {
                        redirectURL = request.getContextPath() + "/member/myPage.do";
                    } else if (prevPage != null && !prevPage.isEmpty()) {
                        redirectURL = prevPage;
                    }
                }
            }

            if (loginSuccessful) { 
            	// 로그인 성공 시 아이디 저장 처리를 합니다.
                handleSaveId(request, response, memId, saveId);
                // 세션에서 prevPage 속성 제거
                session.removeAttribute("prevPage");
                // 설정된 URL로 리다이렉트합니다.
                response.sendRedirect(redirectURL);
                return; // 메소드 실행을 여기서 종료.
            }
	            
	            // 로그인 실패 처리
	            request.setAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
	            // 로그인 페이지로 다시 포워딩합니다.
	            request.getRequestDispatcher("/WEB-INF/views/member/Login.jsp").forward(request, response);
	        } catch (Exception e) {
	        	// 예외 발생 시 스택 트레이스를 출력합니다.
	            e.printStackTrace();
	            request.setAttribute("error", "로그인 중 오류가 발생했습니다.");
	            // 에러 메시지와 함께 로그인 페이지로 다시 포워딩합니다.
	            request.getRequestDispatcher("/WEB-INF/views/member/Login.jsp").forward(request, response);
	        }
	    }

	// 아이디 저장 처리 메소드
	private void handleSaveId(HttpServletRequest request, HttpServletResponse response, String memId, String saveId) {
		// "아이디 저장" 체크박스가 선택된 경우
		if ("on".equals(saveId)) {
		    // 새 쿠키를 생성하여 아이디를 저장합니다
			Cookie cookie = new Cookie("savedId", memId);
			cookie.setMaxAge(7 * 24 * 60 * 60); // 7일 동안 유효
			response.addCookie(cookie);// 응답에 쿠키를 추가합니다
		} else {
			// "아이디 저장"이 선택되지 않은 경우, 기존 쿠키를 삭제합니다.
			Cookie cookie = new Cookie("savedId", "");
			cookie.setMaxAge(0); // 쿠키의 유효 기간을 0으로 설정하여 즉시 만료
			response.addCookie(cookie);
		}
	}
}