package kr.or.ddit.payment.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.cart.service.CartServiceImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.payment.service.IPaymentService;
import kr.or.ddit.payment.service.PaymentServiceImpl;
import kr.or.ddit.payment.vo.PaymentVO;


@WebServlet("/payment/paymentList.do")
public class PaymentList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		//memId갖고오는거임
		HttpSession session = request.getSession();
		String memId = (String) session.getAttribute("memId");

		if (memId == null) {
			MemberVO loggedInMember = (MemberVO) session.getAttribute("loggedInMember");
			if (loggedInMember != null) {
				memId = loggedInMember.getMemId(); // loggedInMember에서 memId 가져오기
			}
		}

        String cartId = CartServiceImpl.getInstance().getRecentPendingCart(memId);

		IPaymentService paymentService = PaymentServiceImpl.getInstance();

		List<PaymentVO> payList = paymentService.paymentList(memId, cartId);

		for (PaymentVO payment : payList) {
			System.out.println(payment);
			System.out.println("payment.getCart_id() = " + payment.getCart_id());
		}
		System.out.println("payList size: " + payList.size());


		request.setAttribute("payList", payList);
		List<PaymentVO> couponList = paymentService.couponList(memId);
		request.setAttribute("couponList", couponList);

		List<PaymentVO> cardList = paymentService.cardList();
		request.setAttribute("cardList", cardList);

		request.getRequestDispatcher("/main?view=paymentList").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
