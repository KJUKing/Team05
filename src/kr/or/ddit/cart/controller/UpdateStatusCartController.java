package kr.or.ddit.cart.controller;

import kr.or.ddit.cart.service.CartServiceImpl;
import kr.or.ddit.cart.service.ICartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cart/updateStatusCart.do")
public class UpdateStatusCartController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 클라이언트로부터 전달된 cartId와 status 값을 받음
        String cartId = request.getParameter("cartId");
        String status = request.getParameter("status");

        // cartId 또는 status가 null이거나 비어있다면 에러 반환
        if (cartId == null || cartId.isEmpty() || status == null || status.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid cartId or status");
            return;
        }

        ICartService service = CartServiceImpl.getInstance();
        // 카트 상태를 업데이트
        int result = service.updateCartStatus(cartId, status);

        // 업데이트 결과에 따라 응답 처리
        if (result > 0) {
            response.getWriter().write("Cart status updated successfully.");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update cart status.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // GET 요청도 POST로 처리
        doPost(request, response);
    }
}
