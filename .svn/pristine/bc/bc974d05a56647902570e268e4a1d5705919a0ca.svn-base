package kr.or.ddit.express.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.express.service.ExpressServiceImpl;
import kr.or.ddit.express.service.IExpressService;
import kr.or.ddit.express.vo.ExpressVO;


@WebServlet("/express/trackDelivery.do")
public class TrackDelivery extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String cartId = request.getParameter("cartId");
		
		IExpressService serivce = ExpressServiceImpl.getInstance();
		
		List<ExpressVO> exList = serivce.trackDelivery(cartId);
		
		request.setAttribute("exList", exList);
		
		request.getRequestDispatcher("/WEB-INF/views/express/trackDelivery.jsp").forward(request, response);
	}

}
