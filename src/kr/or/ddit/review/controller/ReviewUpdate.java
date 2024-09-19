package kr.or.ddit.review.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.express.service.ExpressServiceImpl;
import kr.or.ddit.express.service.IExpressService;
import kr.or.ddit.express.vo.ExpressVO;
import kr.or.ddit.review.service.IReviewService;
import kr.or.ddit.review.service.ReviewServiceImpl;
import kr.or.ddit.review.vo.ReviewVO;

/**
 * Servlet implementation class ReviewUpdate
 */
@WebServlet("/reviewUpdate.do")
public class ReviewUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String reviewId = request.getParameter("review_id");
		
		IReviewService service = ReviewServiceImpl.getInstance();
        ReviewVO rvo = (ReviewVO)service.reviewDetail(reviewId);

		

        request.setAttribute("reviewVo", rvo);
		
		
		
		request.getRequestDispatcher("/WEB-INF/views/review/reviewUpdateForm.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String reviewId = request.getParameter("review_id");
		
		String reviewStarStr = request.getParameter("review_star");
		String reviewContent = request.getParameter("review_content");
		System.out.println(reviewContent);
		
		 int reviewStar = 0;
	        try {
	            reviewStar = Integer.parseInt(reviewStarStr);
	        } catch (NumberFormatException e) {
	            // handle error, e.g., log it or set a default value
	            e.printStackTrace();
	        }

		ReviewVO rvo = new ReviewVO();
		rvo.setReview_id(reviewId);
		rvo.setReview_star(reviewStar);
		rvo.setReview_content(reviewContent);
		
		IReviewService service = ReviewServiceImpl.getInstance();
		
		int cnt = service.updateReview(rvo);
		
		   if (cnt > 0) {
		        // 업데이트 성공 시 목록 페이지로 리다이렉트
		        response.sendRedirect(request.getContextPath() + "/reviewList.do");
		    } else {
		        // 업데이트 실패 시 에러 페이지로 리다이렉트하거나 적절한 처리를 추가
		        response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
		    }
		
	}

}
