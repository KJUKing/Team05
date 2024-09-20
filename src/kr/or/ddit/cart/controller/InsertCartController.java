package kr.or.ddit.cart.controller;

import kr.or.ddit.cart.service.CartServiceImpl;
import kr.or.ddit.cart.service.ICartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cart/insertCart.do")
public class InsertCartController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 폼에서 전달된 mem_id 값을 가져옴
        String memId = request.getParameter("mem_id");

        String prodId = request.getParameter("prod_id");
        String optionId = request.getParameter("option2");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String action = request.getParameter("action");

        // cart 테이블에 데이터 삽입 (장바구니생성)
        ICartService service = CartServiceImpl.getInstance();

        // 값 추출
        String cartId = service.insertCart(memId);
        System.out.println("cartId = " + cartId);

        int result = service.insertDetailCart(cartId, optionId, prodId, quantity);

        if (result == 1) {
            if ("buyNow".equals(action)) {
                // 바로 구매하기 -> 결제 상세 페이지로 리다이렉트
//                response.sendRedirect(request.getContextPath() + "/payment/paymentInsert.do?cartId=" + cartId + "&memId=" + memId);
                response.sendRedirect(request.getContextPath() + "/WEB-INF/views/payment/paymentList.jsp");
            } else if ("addToCart".equals(action)) {
                // 장바구니에 담기 -> 장바구니 목록 페이지로 리다이렉트
                response.sendRedirect(request.getContextPath() + "/cartList.do");
            }

        } else {
            System.out.println("등록 실패했습니다");
        }

    }
}
