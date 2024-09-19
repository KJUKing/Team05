package kr.or.ddit.prod.controller;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.prod.vo.ProdVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/prod/wishList.do")
public class ProdToWish extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 세션에서 로그인한 회원 정보 가져오기
        HttpSession session = request.getSession();
        MemberVO loggedInMember = (MemberVO) session.getAttribute("loggedInMember");

        if (loggedInMember != null) {
            // 세션에서 memId 추출
            String memId = loggedInMember.getMemId();

            // 서비스 객체 생성 호출
            IProdService service = ProdServiceImpl.getInstance();
            // 위시리스트 정보 불러오기
            List<ProdVO> wishList = service.getWishListByMemberId(memId);

            // request에 결과값 저장
            request.setAttribute("wishList", wishList);
//            request.getRequestDispatcher("/WEB-INF/views/wish/wishList.jsp").forward(request, response);
//            response.sendRedirect(request.getContextPath() + "/main?view=wishList");
            request.getRequestDispatcher("/main?view=wishList").forward(request, response);
        } else {
            // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
            response.sendRedirect(request.getContextPath() + "/member/login.do");
        }
    }
}
