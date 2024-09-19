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


@WebServlet("/express/expressInsert.do")
public class ExpressInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/views/express/expressForm.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String exName = request.getParameter("ex_name");
		String exPhone = request.getParameter("ex_phone");
		
		ExpressVO exVo = new ExpressVO();
		exVo.setEx_name(exName);
		exVo.setEx_phone(exPhone);
		
		IExpressService service = ExpressServiceImpl.getInstance();
		
		int cnt = service.insertExpress(exVo);
		
		response.sendRedirect(request.getContextPath()+"/express/expressList.do");
	}

}
