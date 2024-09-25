package kr.or.ddit.member.controller;

/**
 * 회원 목록 조회를 담당하는 컨트롤러
 * 
 * 이 서블릿은 관리자가 모든 회원의 목록을 조회할 수 있도록 합니다.
 * - 관리자 로그인 상태 확인
 * - 모든 회원 정보 조회 및 회원 목록 페이지로 전달
 * - 정석진 2024-09-23- 
 */
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.AdminVO;

@WebServlet("/member/memberList.do")
public class MemberListController extends HttpServlet {
	// 직렬화를 위한 serialVersionUID
    private static final long serialVersionUID = 1L;
    private MemberService memberService;// MemberService 인스턴스 선언
    // 서블릿 초기화 메소드
    public void init() {
    	//AppConfig 에서 baseURL 파라미터 값을 가져옵니다.(보통 web.xml)
        String baseURL = getServletContext().getInitParameter("baseURL");
        // MemberService 인스턴스를 초기화합니다. (싱글톤 패턴 사용)
        memberService = MemberService.getInstance(baseURL);
    }
    // GET 요청 처리 메소드
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 현재 세션을 가져옵니다. 세션이 없으면 null을 반환합니다.
        HttpSession session = request.getSession(false);
        
        // 세션이 있고, 어드민으로 로그인한 경우
        if (session != null && session.getAttribute("loggedInAdmin") != null) {
            // 회원 목록을 가져와서 JSP로 전달
            List<MemberVO> members = memberService.getAllMembers();
            // 회원 목록을 request 속성에 설정합니다.
            request.setAttribute("memberList", members);
            // 관리자용 회원 목록 페이지로 포워딩합니다.
            request.getRequestDispatcher("/main?view=adminMemList").forward(request, response);
        } else {
            // 어드민이 아닌 경우 로그인 페이지로 리다이렉트
            response.sendRedirect(request.getContextPath() + "/member/login.do");
        }
    }
    // POST 요청 처리 메소드
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// POST 요청도 GET 메소드로 처리합니다.
        doGet(request, response);
    }
}