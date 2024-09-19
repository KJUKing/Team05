package kr.or.ddit.prod.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.prod.service.IMainCateService;
import kr.or.ddit.prod.service.MainCateServiceImpl;
import kr.or.ddit.prod.vo.MainCateVO;

@WebServlet("/prod/mainCateInsert.do")
public class MainCateInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        
        IMainCateService mcService = MainCateServiceImpl.getInstance();
        String cateName = request.getParameter("mainCateName");
        
        // 카테고리 추가 로직
        int cnt = mcService.insertMainCate(cateName); // 서비스 메서드를 통해 DB에 추가

        // 응답 처리
        if (cnt == 1) {
            response.getWriter().write("{\"카테고리 추가\":\"성공\"}");
        } else {
            response.getWriter().write("{\"카테고리 추가\":\"실패\"}");
        }
        
        
	}

}
