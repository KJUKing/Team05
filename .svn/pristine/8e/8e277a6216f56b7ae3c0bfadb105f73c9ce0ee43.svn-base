package kr.or.ddit.express.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.express.service.ExpressServiceImpl;
import kr.or.ddit.express.service.IExpressService;
import kr.or.ddit.express.vo.ExpressVO;


@WebServlet("/express/expressUpdate.do")
public class ExpressUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String exId = request.getParameter("ex_id");
		
		IExpressService service = ExpressServiceImpl.getInstance();
		ExpressVO exVo = service.detailExpress(exId);
		
		request.setAttribute("exVo", exVo);
		
		request.getRequestDispatcher("/main?view=adminExpressUpdate")
		.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String exId = request.getParameter("ex_id");
		String exName = request.getParameter("ex_name");
		String exPhone = request.getParameter("ex_phone");
		
		ExpressVO exVo = new ExpressVO();
		exVo.setEx_name(exName);
		exVo.setEx_phone(exPhone);
		exVo.setEx_id(exId);
		
		IExpressService service = ExpressServiceImpl.getInstance();
		
		int cnt = service.updateExpress(exVo);
		
		response.sendRedirect(request.getContextPath()+"/express/expressList.do");
	}

}
