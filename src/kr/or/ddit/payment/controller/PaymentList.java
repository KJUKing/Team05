package kr.or.ddit.payment.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.payment.service.IPaymentService;
import kr.or.ddit.payment.service.PaymentServiceImpl;
import kr.or.ddit.payment.vo.PaymentVO;


@WebServlet("/payment/paymentList.do")
public class PaymentList extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		IPaymentService service = PaymentServiceImpl.getInstance();
		List<PaymentVO> payList = service.paymentList2();
		request.setAttribute("payList", payList);
		 
		List<PaymentVO> couponList = service.couponList();
		request.setAttribute("couponList", couponList);
		
		List<PaymentVO> cardList = service.cardList();
		request.setAttribute("cardList", cardList);
		
		
		
		request.getRequestDispatcher("/WEB-INF/views/payment/paymentList.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
