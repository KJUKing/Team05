package kr.or.ddit.report.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.report.service.IReportService;
import kr.or.ddit.report.service.ReportServiceImpl;
import kr.or.ddit.report.vo.ReportVO;

/**
 * Servlet implementation class ReportView
 */
@WebServlet("/reportView.do")
public class ReportView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //String memId = request.getParameter("mem_id");
	    
		HttpSession session = request.getSession();
		MemberVO loggedInMember = (MemberVO) session.getAttribute("loggedInMember");
		String memId = loggedInMember.getMemId();
        String reviewId = request.getParameter("review_id");

        // 받은 데이터를 로그에 출력하거나 처리하기 위한 로직
        System.out.println("신고된 회원 ID: " + memId);
        System.out.println("신고된 리뷰 ID: " + reviewId);

        // 신고 데이터 생성
        ReportVO reportVo = new ReportVO();
        reportVo.setMem_id(memId);
        reportVo.setReview_id(reviewId);

        // 서비스 호출
        IReportService service = ReportServiceImpl.getInstance();
        try {
            int cnt = service.reportInsert(reportVo);
            if (cnt > 0) {
                // 성공적으로 삽입된 경우
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/report/reportSuccess.jsp");
                dispatcher.forward(request, response);
            } else {
                // 실패한 경우 처리
            	  RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/report/reportFail.jsp");
	                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace(); // 예외 로그
           
        }
	}

	
	

}
