package kr.or.ddit.payment.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.cart.service.CartServiceImpl;
import kr.or.ddit.cart.service.ICartService;
import kr.or.ddit.payment.service.IPaymentService;
import kr.or.ddit.payment.service.PaymentServiceImpl;
import kr.or.ddit.payment.vo.PaymentVO;

@WebServlet("/payment/paymentInsert.do")
public class PaymentInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		// 파라미터 받기
		String[] cartIds = request.getParameterValues("cart_ids");  // 여러 개의 cart_id를 배열로 받음
		String[] prodPricesStr = request.getParameterValues("prod_prices");  // 개별 상품의 할인된 가격 배열
		String[] dcartQtys = request.getParameterValues("dcart_qtys");  // 각 상품의 수량 배열
		String coupId = request.getParameter("coup_id");
		if (coupId == null || coupId.isEmpty()) {
			coupId = null;  // 쿠폰이 선택되지 않은 경우 null로 처리
		}
		String cardId = request.getParameter("card_id");
		String memId = request.getParameter("mem_id");
		String mileageUsedStr = request.getParameter("mile_used");
		int mileUsed = (mileageUsedStr != null && !mileageUsedStr.isEmpty()) ? Integer.valueOf(mileageUsedStr) : 0;

		// 서비스 호출 준비
		IPaymentService paymentService = PaymentServiceImpl.getInstance();
		ICartService cartService = CartServiceImpl.getInstance();

		// 각 cartId에 대해 결제 처리
		for (int i = 0; i < cartIds.length; i++) {
			String cartId = cartIds[i];
			int quantity = Integer.parseInt(dcartQtys[i]);  // 각 상품의 수량 처리
			int prodPrice = Integer.parseInt(prodPricesStr[i]);  // 개별 상품의 할인된 가격 처리

			// PaymentVO 객체 생성 및 값 설정
			PaymentVO payVo = new PaymentVO();
			payVo.setPay_price(prodPrice);  // 개별 상품 가격 설정
			payVo.setMile_used(mileUsed);  // 마일리지 사용 값 설정
			payVo.setCoup_id(coupId);
			payVo.setCard_id(cardId);
			payVo.setCart_id(cartId);

			// 결제 삽입 처리
			int cnt = paymentService.paymentInsert(payVo);

			if (cnt > 0) {
				// 결제 금액의 3%를 마일리지로 적립
				int mileageBonus = (int) (prodPrice * 0.03);  // 개별 상품 가격 기준으로 마일리지 적립
				paymentService.payMileage(memId, mileageBonus);

				// 쿠폰 상태 업데이트 (쿠폰이 존재하는 경우)
				if (coupId != null && !coupId.isEmpty()) {
					paymentService.updateCouponStatus(coupId);
				}

				// 각 cartId의 상태를 COMPLETED로 업데이트
				cartService.updateCartStatus(cartId, "COMPLETED");
			}
		}



	// 결제 성공 후 메인 페이지로 이동
		response.sendRedirect(request.getContextPath() + "/main");
	}
}



