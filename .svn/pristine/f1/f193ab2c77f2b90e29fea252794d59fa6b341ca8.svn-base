package kr.or.ddit.member.controller;

/**
 * 마이페이지 접근을 처리하는 컨트롤러
 * 
 * 이 서블릿은 사용자의 마이페이지 요청을 처리합니다.
 * - 로그인된 사용자의 마이페이지 접근 처리
 * - 비로그인 상태의 사용자 처리
 * - 정석진 2024-09-23- 
 */
import kr.or.ddit.member.vo.MemberVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/member/myPage.do")
public class MyPageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 현재 세션을 가져옵니다.
        HttpSession session = request.getSession();
        // 세션에서 로그인된 사용자 정보를 가져옵니다.
        MemberVO loggedInMember = (MemberVO) session.getAttribute("loggedInMember");

        if (loggedInMember != null) {
            // 사용자 정보가 있으면 (로그인 상태)
            // 요청 속성에 사용자 정보를 설정합니다.
            request.setAttribute("loggedInMember", loggedInMember);
            // myPage.jsp로 포워딩합니다.
            request.getRequestDispatcher("/main?view=myPage").forward(request, response);
        } else {
            // 로그인되지 않은 경우 로그인 페이지로 리다이렉트
            // 현재 페이지 URL을 파라미터로 추가하여 로그인 후 다시 마이페이지로 돌아올 수 있게 합니다.
            String currentPage = request.getRequestURI();
            response.sendRedirect(request.getContextPath() + "/main?view=login&prevPage=" + currentPage);
        }
    }
}
