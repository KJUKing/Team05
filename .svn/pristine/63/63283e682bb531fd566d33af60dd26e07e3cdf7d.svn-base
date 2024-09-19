package kr.or.ddit.member.controller;

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

@WebServlet("/member/memberList.do")
public class MemberListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MemberService memberService;

    public void init() {
        String baseURL = getServletContext().getInitParameter("baseURL");
        memberService = MemberService.getInstance(baseURL);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 세션 체크: 로그인 상태 확인
        // getSession(false)는 새 세션을 생성하지 않고 기존 세션이 없으면 null을 반환
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInMember") == null) {
            // 로그인되지 않은 상태라면 로그인 페이지로 리다이렉트
            response.sendRedirect(request.getContextPath() + "/member/login.do");
            return;
        }

        // 로그인된 상태라면 회원 목록을 가져와서 JSP로 전달
        List<MemberVO> members = memberService.getAllMembers();
        request.setAttribute("memberList", members);
        request.getRequestDispatcher("/WEB-INF/views/member/memberList.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}