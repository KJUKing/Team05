package kr.or.ddit.prod.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.prod.service.IOptionService;
import kr.or.ddit.prod.service.OptionServiceImpl;
import kr.or.ddit.prod.vo.OptionVO;

@WebServlet("/prod/optionInsert.do")
public class OptionInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		String optionName = request.getParameter("optionName");
		String optionValue = request.getParameter("optionValue");

		System.out.println("옵션 인서트 Name : " + optionName);
		System.out.println("옵션 인서트 Value : " + optionValue);
		
		IOptionService optService = OptionServiceImpl.getInstance();
		
		OptionVO optvo = new OptionVO();
		optvo.setOption_name(optionName);
		optvo.setOption_value(optionValue);
		
		optService.insertOption(optvo);
		
		response.sendRedirect(request.getContextPath() + "/prodList.do");
		
	}

}
