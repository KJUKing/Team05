package kr.or.ddit.cart.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.cart.service.CartServiceImpl;
import kr.or.ddit.cart.service.ICartService;

@WebServlet("/cart/removeCartItem.do")
public class RemoveCartItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// cart_id 파라미터 가져오기
		String cartId = request.getParameter("cart_id");

		if (cartId != null) {
			ICartService cartService = CartServiceImpl.getInstance();

			// 해당 cartId에 해당하는 장바구니 항목 삭제
			int result = cartService.removeCartItem(cartId);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			if (result > 0) {
				// 성공적으로 삭제된 경우 JSON 형식으로 성공 메시지 반환
				response.getWriter().write("{\"status\": \"success\", \"message\": \"삭제되었습니다.\"}");
			} else {
				// 삭제 실패 시 실패 메시지 반환
				response.getWriter().write("{\"status\": \"error\", \"message\": \"삭제에 실패했습니다.\"}");
			}
		}
	}
}
