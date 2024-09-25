package kr.or.ddit.qna.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.prod.vo.ProdVO;
import kr.or.ddit.qna.service.QnaService;

@WebServlet("/getAllProdName.do")
public class GetAllProdName extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		QnaService service = QnaService.getInstance();
		
		ProdVO vo = new ProdVO();
		
		List<ProdVO> allProdNames = service.getAllProdName(vo);
		System.out.println(allProdNames.get(0));
		System.out.println(allProdNames.get(1));
		System.out.println(allProdNames.get(2));
		System.out.println(allProdNames.get(3));
		
		// request.setAttribute("apn", allProdNames);
		
        // JSON으로 직렬화
        String json = new Gson().toJson(Collections.singletonMap("apn", allProdNames));
        
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
		
		// request.getRequestDispatcher("WEB-INF/views/qna/qnaMain.jsp").forward(request, response);
	}
}
