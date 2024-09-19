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

import kr.or.ddit.prod.service.DeCateServiceImpl;
import kr.or.ddit.prod.service.IDeCateService;
import kr.or.ddit.prod.service.IOptionService;
import kr.or.ddit.prod.service.OptionServiceImpl;
import kr.or.ddit.prod.vo.DeCateVO;
import kr.or.ddit.prod.vo.OptionVO;


@WebServlet("/prod/deCate.do")
public class DeCate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		String mainId = request.getParameter("mainId");
		
		IDeCateService service = DeCateServiceImpl.getInstance();
		
		List<DeCateVO> deCateList = service.getIdDeCate(mainId);
		
		Gson gson = new Gson();
		String resultJson = gson.toJson(deCateList);
		
		PrintWriter out = response.getWriter();
		out.write(resultJson);
		response.flushBuffer();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
