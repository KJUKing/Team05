package kr.or.ddit.coupon.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.coupon.service.CouponServiceImpl;
import kr.or.ddit.coupon.service.ICouponService;
import kr.or.ddit.coupon.vo.CouponVO;


@WebServlet("/coupon/allInsert.do")
public class AllInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
		
		String coupEdate = request.getParameter("coup_edate");
	    int coupAmount = Integer.parseInt(request.getParameter("coup_amount"));
	    String coupName = request.getParameter("coup_name");

	    CouponVO coupon = new CouponVO();
	    coupon.setCoup_edate(coupEdate);
	    coupon.setCoup_amount(coupAmount);
	    coupon.setCoup_name(coupName);

	    ICouponService service = CouponServiceImpl.getInstance();

	    // 전체 회원에게 쿠폰 지급
	    service.batchCoupInsertAll();
	    service.batchCouponInsertAll(coupon);

	    response.sendRedirect(request.getContextPath() + "/coupon/couponList.do");
	}


}
