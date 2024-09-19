package kr.or.ddit.member.controller;

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

    public void init() {
        String baseURL = getServletContext().getInitParameter("baseURL");
        memberService = MemberService.getInstance(baseURL);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                response.sendRedirect(request.getContextPath() + "/member/memberDetail.do?memId=" + memId + "&error=deleteFailed");
            }
        } else {
            // 유효하지 않은 회원 ID인 경우 회원 목록 페이지로 리다이렉트
            response.sendRedirect(request.getContextPath() + "/member/memberList.do");
        }
    }
}