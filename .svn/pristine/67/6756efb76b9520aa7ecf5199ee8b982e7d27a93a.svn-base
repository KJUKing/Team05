package kr.or.ddit.review.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.review.service.IReviewService;
import kr.or.ddit.review.service.ReviewServiceImpl;
import kr.or.ddit.review.vo.ReviewVO;

@WebServlet("/reviewDetail.do")
public class ReviewDetail extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        
        String reviewId = request.getParameter("review_id");
		/*
		 * if (reviewId == null || reviewId.isEmpty()) { // 파라미터가 없을 경우, 오류 페이지로 리디렉션
		 * response.sendRedirect(request.getContextPath() + "/errorPage.jsp"); return; }
		 */

        IReviewService service = ReviewServiceImpl.getInstance();
        ReviewVO rvo = (ReviewVO)service.reviewDetail(reviewId);

		/*
		 * if (rvo == null) { // 리뷰가 없는 경우, 오류 페이지로 리디렉션
		 * response.sendRedirect(request.getContextPath() + "/errorPage.jsp"); return; }
		 */

        request.setAttribute("reviewVo", rvo);
        request.getRequestDispatcher("/WEB-INF/views/review/reviewDetail.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

