package kr.or.ddit.wish.controller;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.wish.service.IWishListService;
import kr.or.ddit.wish.service.WishListServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/wish/checkWishList.do")
public class CheckWishListController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //세션 로그인 정보 갖고오기
        HttpSession session = request.getSession();
        MemberVO loggedInMember = (MemberVO) session.getAttribute("loggedInMember");

        boolean isWishListed = false; // 기본값은 false (위시리스트에 없는 상태)

        if (loggedInMember != null) {
            //세션 memId추출
            String memId = loggedInMember.getMemId();

            //클라이언트에서 전달된 prodId 파라미터 가져오기
            String prodId = request.getParameter("prodId");

            IWishListService service = WishListServiceImpl.getInstance();

            isWishListed = service.isProductWishListed(memId, prodId);

            //결과를 JSON형식으로 응답함
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("{\"isWishlisted\": " + isWishListed + "}");
            out.flush();
        }

    }
}
