package kr.or.ddit.payment.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.payment.service.IPaymentService;
import kr.or.ddit.payment.service.PaymentServiceImpl;
import kr.or.ddit.payment.vo.PaymentVO;

@WebServlet("/payment/paymentInsert.do")
public class PaymentInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cartId = request.getParameter("cartId");
		String memId = request.getParameter("memId");

		// doPost로 파라미터 넘기기
		request.setAttribute("cartId", cartId);
		request.setAttribute("memId", memId);

		// doPost 호출
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cartId = (String) request.getAttribute("cartId");
		String memId = (String) request.getAttribute("memId");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		// 파라미터 받기
		int payPrice = Integer.valueOf(request.getParameter("totalprice"));
		String mileageUsedStr = request.getParameter("mile_used");
		int mileUsed = (mileageUsedStr != null && !mileageUsedStr.isEmpty()) ? Integer.valueOf(mileageUsedStr) : 0;
		String coupId = request.getParameter("coup_id");
		String cardId = request.getParameter("card_id");

		// PaymentVO 객체 생성 및 값 설정
		PaymentVO payVo = new PaymentVO();
		payVo.setPay_price(payPrice);
		payVo.setMile_used(mileUsed); // 마일리지 사용 값 설정
		payVo.setCoup_id(coupId);
		payVo.setCard_id(cardId);
		payVo.setMem_id(memId);  // memId 설정
		payVo.setCart_id(cartId); // cartId 설정

		// 서비스 호출
		IPaymentService service = PaymentServiceImpl.getInstance();
		int cnt = service.paymentInsert(payVo);

		if (cnt > 0) {
			// 결제 성공 후 메인 페이지로 이동
			// 결제 금액의 3%를 마일리지로 적립
			int mileageBonus = (int) (payPrice * 0.03);
			service.payMileage(memId, mileageBonus);
			response.sendRedirect(request.getContextPath() + "/main?view=index");
		} else {
			// 결제 실패 처리
			request.setAttribute("errorMessage", "결제에 실패했습니다.");
			request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
		}

	}

}
