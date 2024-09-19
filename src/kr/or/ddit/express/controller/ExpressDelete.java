package kr.or.ddit.express.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.express.service.ExpressServiceImpl;
import kr.or.ddit.express.service.IExpressService;


@WebServlet("/express/expressDelete.do")
public class ExpressDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String exId = request.getParameter("ex_id");
		
		IExpressService service = ExpressServiceImpl.getInstance();
		
		int cnt = service.deleteExpress(exId);
		
		response.sendRedirect(request.getContextPath()+"/express/expressList.do");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
