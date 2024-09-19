package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/logout.do")
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 현재 세션을 가져옵니다. 세션이 없으면 새로 생성하지 않습니다.
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            // 세션에 있는 모든 속성을 제거합니다.
            session.invalidate();
        }
        
        // 로그아웃 후 로그인 페이지로 리다이렉트합니다.
        response.sendRedirect(request.getContextPath() + "/member/login.do");
    }

    // GET 요청도 POST로 처리합니다. (브라우저에서 직접 URL 입력 등의 경우 대비)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}