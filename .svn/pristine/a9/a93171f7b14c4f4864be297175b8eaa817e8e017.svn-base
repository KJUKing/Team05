package kr.or.ddit.member.controller;

/**
 * 로그아웃 처리를 담당하는 컨트롤러
 * 
 * 이 서블릿은 사용자의 로그아웃 요청을 처리합니다.
 * - 세션을 무효화하여 로그아웃 처리
 * - 정석진 2024-09-23- 
 */
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet 어노테이션: 이 클래스가 서블릿임을 선언하고, URL 매핑을 지정하고 "/member/logout.do" 경로로 들어오는 요청을 이 서블릿이 처리
@WebServlet("/member/logout.do")
//HttpServlet을 상속받아 서블릿으로 동작할 수 있게 합니다.
public class LogoutController extends HttpServlet {
	// serialVersionUID: 직렬화된 객체의 버전 관리를 위한 고유 식별자입니다.
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 현재 세션을 가져옵니다. 세션이 없으면 null을 반환합니다.
        HttpSession session = request.getSession(false);
        String userType = null;
        
        String returnUrl = null;
        // Referer 헤더를 통해 이전 페이지 URL을 가져옵니다.
        String referer = request.getHeader("Referer");
        // 이전 페이지가 로그아웃 페이지가 아닌 경우에만 returnUrl을 설정합니다.
        if (referer != null && !referer.contains("/member/logout.do")) {
        	returnUrl =  referer;
        }
        // 세션이 존재하는 경우 처리
        if (session != null) {
        	// 세션에서 사용자 타입 정보를 가져옵니다.
            userType = (String) session.getAttribute("userType");
            
            // 세션 무효화 전에 세션 속성을 로깅 (디버깅 목적)
            System.out.println("Session attributes before invalidate:");
            Enumeration<String> attributeNames = session.getAttributeNames();
            while (attributeNames.hasMoreElements()) {
                String name = attributeNames.nextElement();
                System.out.println(name + ": " + session.getAttribute(name));
            }
            
            // 세션을 무효화하여 로그아웃 처리합니다.
            session.invalidate();
        }

        // returnUrl이 설정된 경우, URL 디코딩을 시도합니다.
        if (returnUrl != null) {
            try {
                returnUrl = URLDecoder.decode(returnUrl, "UTF-8");
            } catch (IllegalArgumentException e) {
                // 디코딩 중 오류가 발생하면 무시하고 원래 값 사용
            }
        }

        System.out.println("User type: " + userType); // 디버깅용 로그
        System.out.println("Return URL: " + returnUrl); // 디버깅용 로그

        // 사용자 타입에 따른 리다이렉트 처리
        if ("admin".equals(userType)) {
            // 관리자는 메인 페이지로 리다이렉트
            response.sendRedirect(request.getContextPath() + "/main?view=index");
        } else if (returnUrl != null && !returnUrl.isEmpty()) {
        	// 디버깅을 위한 로그 출력
        	System.out.println("Context Path: " + request.getContextPath());
        	System.out.println("Full URL after redirect: " + request.getContextPath() + (returnUrl.startsWith("/") ? returnUrl : "/" + returnUrl));
            // 일반 회원은 이전 페이지로 리다이렉트
        	response.sendRedirect( returnUrl);
        } else {
            // returnUrl이 없는 경우 기본적으로 메인 페이지로 리다이렉트
            response.sendRedirect(request.getContextPath() + "/main?view=index");
        }
        
    }

    // GET 요청 처리 메소드 
    // 브라우저에서 직접 URL 입력 등의 GET 요청도 POST로 처리합니다.
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}