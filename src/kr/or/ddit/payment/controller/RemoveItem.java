package kr.or.ddit.payment.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.payment.service.IPaymentService;
import kr.or.ddit.payment.service.PaymentServiceImpl;


@WebServlet("/remove/removeItem.do")
public class RemoveItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String prodId = request.getParameter("prod_id");
		
		IPaymentService service = PaymentServiceImpl.getInstance();
		
		int cnt = service.removeItem(prodId);
		
		
		
	}

}
