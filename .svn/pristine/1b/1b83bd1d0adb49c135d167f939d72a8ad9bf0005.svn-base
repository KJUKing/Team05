package kr.or.ddit.coupon.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.coupon.service.CouponServiceImpl;
import kr.or.ddit.coupon.service.ICouponService;
import kr.or.ddit.coupon.vo.CouponVO;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/coupon/mileCoupon.do")
public class MileCoupon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// memId를 세션에서 가져옴
		HttpSession session = request.getSession();
		String memId = (String) session.getAttribute("memId");
		if (memId == null) {
			MemberVO loggedInMember = (MemberVO) session.getAttribute("loggedInMember");
			if (loggedInMember != null) {
				memId = loggedInMember.getMemId(); // loggedInMember에서 memId 가져오기
			}
		}
		if (memId != null) {
			ICouponService service = CouponServiceImpl.getInstance();

			List<CouponVO> coupList = service.mileCoupon(memId);

			request.setAttribute("coupList", coupList);

			request.getRequestDispatcher("/main?view=myCoupon").forward(request, response);
		}else {
            // memId가 없을 때 다른 페이지로 포워딩
            request.getRequestDispatcher("/main?view=").forward(request, response);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
