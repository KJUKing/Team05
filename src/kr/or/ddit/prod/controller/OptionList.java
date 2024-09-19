package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.prod.service.IOptionService;
import kr.or.ddit.prod.service.OptionServiceImpl;
import kr.or.ddit.prod.vo.OptionVO;

@WebServlet("/prod/optionList.do")
public class OptionList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String opName = request.getParameter("optionName");
		String opValue = request.getParameter("optionValue");
		
		System.out.println("opName : ^^^^^ " + opName);
		System.out.println("opValue : ^^^^^ " + opValue);
		
		OptionVO optvo = new OptionVO();
		optvo.setOption_name(opName);
		optvo.setOption_value(opValue);
		
		IOptionService service = OptionServiceImpl.getInstance();
		service.insertOption(optvo);
		
		//response.sendRedirect(request.getContextPath() + "/prodList.do");
		
		
		
		
	}

}
