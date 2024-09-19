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


@WebServlet("/riturn/riturnProd.do")
public class RiturnProd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String cartId = request.getParameter("cartId");
		
		IRiturnService service = RiturnServiceImpl.getInstance();
		
		List<RiturnVO> riturnList = service.riturnList(cartId);
		
		request.setAttribute("riturnList", riturnList);
		
		request.getRequestDispatcher("/WEB-INF/views/riturn/riturnProd.jsp").forward(request, response);
	}

}
