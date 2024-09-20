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
        // 상세 카트 입력
        service.insertDetailCart(cartId, optionId, prodId, quantity);
        //총합값
        double totalPrice = service.calculateTotalPrice(cartId);
        //결과값
        int result = service.updateCartTotalPrice(cartId, totalPrice);

        if (result == 1) {
            if ("buyNow".equals(action)) {
                // 바로 구매하기 -> Forward 방식으로 넘기기
                request.setAttribute("cartId", cartId);
                request.setAttribute("totalPrice", totalPrice);
                // Forward로 데이터를 POST 방식으로 넘기기
//                request.getRequestDispatcher("/payment/paymentInsert.do").forward(request, response);
                request.getRequestDispatcher("/main?view=paymentList").forward(request, response);
            } else if ("addToCart".equals(action)) {
                // 장바구니에 담기 -> 장바구니 목록 페이지로 리다이렉트
                response.sendRedirect(request.getContextPath() + "/cartList.do");
            }

        } else {
            System.out.println("등록 실패했습니다");
        }

    }
}
