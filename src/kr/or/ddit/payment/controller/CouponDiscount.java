package kr.or.ddit.payment.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import kr.or.ddit.payment.service.IPaymentService;
import kr.or.ddit.payment.service.PaymentServiceImpl;
import kr.or.ddit.payment.vo.PaymentVO;


@WebServlet("/payment/couponDiscount.do")
public class CouponDiscount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

        // 클라이언트로부터 쿠폰 ID를 받음
        String coupId = request.getParameter("coupId");

        // 쿠폰 할인율을 데이터베이스에서 조회 (가상의 서비스 메소드 사용)
        IPaymentService service = PaymentServiceImpl.getInstance();
        PaymentVO payVo = service.couponDiscount(coupId);

        // JSON 응답 준비
        response.setContentType("application/json; charset=UTF-8"); 
        PrintWriter out = response.getWriter();
        
        // JSON으로 할인율을 반환
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("couponDiscount", payVo.getCoup_amount());

        out.print(jsonResponse);
        out.flush();
	}

}
