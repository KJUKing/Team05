package kr.or.ddit.riturn.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.riturn.service.IRiturnService;
import kr.or.ddit.riturn.service.RiturnServiceImpl;
import kr.or.ddit.riturn.vo.RiturnVO;


@WebServlet("/riturn/riturnView.do")
public class RiturnView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		IRiturnService service = RiturnServiceImpl.getInstance();
		
		List<RiturnVO> riList = service.riturnView();
		
		request.setAttribute("riList", riList);
		
		request.getRequestDispatcher("/main?view=return").forward(request, response);
	}

}
