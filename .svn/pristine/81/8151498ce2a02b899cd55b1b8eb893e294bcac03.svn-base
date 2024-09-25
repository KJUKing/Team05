package kr.or.ddit.review.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.review.service.IReviewService;
import kr.or.ddit.review.service.ReviewServiceImpl;
import kr.or.ddit.review.vo.ReviewVO;

/**
 * Servlet implementation class MyreviewList
 */
@WebServlet("/myreviewList.do")
public class MyreviewList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        HttpSession session = request.getSession();
        MemberVO loggedInMember = (MemberVO)session.getAttribute("loggedInMember");
        
        if(loggedInMember!=null) {
        	String memId =loggedInMember.getMemId();
        	IReviewService service = ReviewServiceImpl.getInstance();
        	
        	List<ReviewVO>myreviewList = service.getMyReview(memId);
        	
        	request.setAttribute("myreviewList", myreviewList);
        	request.getRequestDispatcher("/main?view=myreview").forward(request, response);
        	
        	
        }else {
        	response.sendRedirect(request.getContextPath()+"/member/login.do");
        }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
