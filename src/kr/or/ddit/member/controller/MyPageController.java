package kr.or.ddit.member.controller;

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
        HttpSession session = request.getSession();
        MemberVO loggedInMember = (MemberVO) session.getAttribute("loggedInMember");

        if (loggedInMember != null) {
            //사용자 정보가 있으면 myPage.jsp로 이동
            request.setAttribute("loggedInMember", loggedInMember);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/member/myPage.jsp");
            dispatcher.forward(request, response);
        } else {
            // 로그인되지 않은 경우 로그인 페이지로 리다이렉트
            response.sendRedirect(request.getContextPath() + "/member/login.do");
        }
    }
}
