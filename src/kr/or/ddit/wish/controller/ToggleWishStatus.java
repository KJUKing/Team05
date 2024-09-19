package kr.or.ddit.wish.controller;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.wish.service.IWishListService;
import kr.or.ddit.wish.service.WishListServiceImpl;
import kr.or.ddit.wish.vo.WishListVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/wish/toggleWishStatus.do")
public class ToggleWishStatus extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String prodId = request.getParameter("prod_id"); // 상품 ID
        String status = request.getParameter("status");  // 현재 상태
        System.out.println("받은 상태: " + status);
        String memId = ((MemberVO) request.getSession().getAttribute("loggedInMember")).getMemId(); // 로그인된 사용자 ID

        // VO에 값 담기
        WishListVO wishVo = new WishListVO();
        wishVo.setMem_id(memId);
        wishVo.setProd_id(prodId);

        IWishListService service = WishListServiceImpl.getInstance();
        int result = 0;
        String newStatus = "";  // 새로운 상태를 저장할 변수

        // 상태에 따라 처리 분기
        if ("active".equals(status)) {
            // 활성화 상태 -> 비활성화 (삭제)
            result = service.addToWishList(wishVo);
            newStatus = "active";  // 상태 바꾸기
        } else if ("inactive".equals(status)) {
            // 비활성화 상태 -> 활성화 (추가)
            result = service.removeFromWishList(wishVo);
            newStatus = "inactive";  // 상태 바꾸기
        }

        if (result > 0) {
            // 성공 시 새로운 상태를 응답으로 보냄
            response.getWriter().write(newStatus);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.getWriter().write("실패");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}