package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.MemberService;

@WebServlet("/member/checkEmailVerification.do")
public class CheckEmailVerificationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MemberService memberService;

    public void init() {
        String baseURL = getServletContext().getInitParameter("baseURL");
        memberService = MemberService.getInstance(baseURL);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        
        boolean isVerified = memberService.isEmailVerified(email);
        
        if (isVerified) {
            response.getWriter().write("verified");
        } else {
            response.getWriter().write("pending");
        }
    }
}