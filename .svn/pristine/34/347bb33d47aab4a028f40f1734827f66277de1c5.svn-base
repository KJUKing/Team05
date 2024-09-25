package kr.or.ddit.coupon.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.coupon.service.CouponServiceImpl;
import kr.or.ddit.coupon.service.ICouponService;
import kr.or.ddit.coupon.vo.CouponVO;


@WebServlet("/coupon/couponList.do")
public class CouponList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		ICouponService service = CouponServiceImpl.getInstance();
		
		List<CouponVO> couponList = service.couponList();
		
		request.setAttribute("couponList", couponList);
		
		request.getRequestDispatcher("/main?view=adminCoupon").forward(request, response);
	
	}

}
