package kr.or.ddit.qna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/qnaProdTemp.do")
public class QnaProdTemp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    // 메인 레이아웃에 페이지 전달
	    request.setAttribute("qnaProd", "/WEB-INF/views/qna/qnaProd.jsp");
	    // request.getRequestDispatcher("/WEB-INF/views/qna/qnaProdTemp.jsp").forward(request, response);
	    
	    
		request.getRequestDispatcher("/FrontControllerTemp?view=qnaProdTemp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
