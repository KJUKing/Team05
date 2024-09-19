package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.vo.MemberVO;

// URL 매핑: /member/memberDetail.do로 접근 시 이 서블릿이 처리함
@WebServlet("/member/memberDetail.do")
public class MemberDetailController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MemberService memberService;

    // 서블릿 초기화 시 MemberService 인스턴스 생성
    public void init() {
        String baseURL = getServletContext().getInitParameter("baseURL");
        memberService = MemberService.getInstance(baseURL);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 세션 체크: 로그인 상태가 아니면 로그인 페이지로 리다이렉트
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInMember") == null) {
            // 로그인 되지 않은 상태, 로그인 페이지로 리다이렉트
            response.sendRedirect(request.getContextPath() + "/member/login.do");
            return;
        }

        // URL 파라미터에서 회원 ID 추출
        String memId = request.getParameter("memId");
        // 회원 서비스를 통해 해당 ID의 회원 정보 조회
        MemberVO member = memberService.getMemberById(memId);

        if (member != null) {
            // 조회된 회원 정보를 request 속성에 저장
            request.setAttribute("memberDetail", member);
            // memberDetail.jsp로 포워딩하여 회원 정보 표시
            request.getRequestDispatcher("/WEB-INF/views/member/memberDetail.jsp").forward(request, response);
        } else {
            // 회원 정보가 없으면 회원 목록 페이지로 리다이렉트
            response.sendRedirect(request.getContextPath() + "/member/memberList.do");
        }
    }

    // POST 요청도 GET 메서드로 처리
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}