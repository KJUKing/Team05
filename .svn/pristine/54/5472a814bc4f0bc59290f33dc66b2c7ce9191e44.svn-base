package kr.or.ddit.review.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.prod.vo.ProdVO;
import kr.or.ddit.review.service.IReviewService;
import kr.or.ddit.review.service.ReviewServiceImpl;
import kr.or.ddit.review.vo.ReviewVO;


@WebServlet("/review/prodReview.do")
public class ProdReview extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String prodId = request.getParameter("prodId");
		String optionId = request.getParameter("optionId");
		
		ReviewVO reviewVo = new ReviewVO();
		reviewVo.setProd_id(prodId);
		reviewVo.setOption_id(optionId);
		
		IReviewService service = ReviewServiceImpl.getInstance();
		
		List<ReviewVO> reviewList = service.prodReview(reviewVo);
		
		request.setAttribute("reviewList", reviewList);
		
		request.getRequestDispatcher("/WEB-INF/views/review/prodReview.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
