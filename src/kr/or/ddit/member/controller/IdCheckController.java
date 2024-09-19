package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.MemberService;

// URL 매핑: /member/idCheck.do로 접근 시 이 서블릿이 처리함
@WebServlet("/member/idCheck.do")
public class IdCheckController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MemberService memberService;

    // 서블릿 초기화 시 MemberService 인스턴스 생성
    public void init() {
        String baseURL = getServletContext().getInitParameter("baseURL");
        memberService = MemberService.getInstance(baseURL);
    }

    // POST 요청 처리: AJAX를 통한 ID 중복 체크
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청에서 memId 파라미터 추출
        String memId = request.getParameter("memId");
        
        // MemberService를 통해 ID 사용 가능 여부 확인
        boolean isAvailable = memberService.isIdAvailable(memId);
        
        // 응답 설정
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        
        // ID 사용 가능 여부에 따라 응답 전송
        if (isAvailable) {
            // ID 사용 가능
            response.getWriter().write("available");
        } else {
            // ID 사용 불가능 (이미 존재하는 ID)
            response.getWriter().write("unavailable");
        }
    }
}