package kr.or.ddit.coupon.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.coupon.service.CouponServiceImpl;
import kr.or.ddit.coupon.service.ICouponService;


@WebServlet("/coupon/deleteCoupon.do")
public class DeleteCoupon extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String coupId = request.getParameter("coup_id");
		
		ICouponService service = CouponServiceImpl.getInstance();
		
		int cnt = service.deleteCoupon(coupId);
		
		
	}

}
