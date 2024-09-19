package kr.or.ddit.prod.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.prod.service.DeCateServiceImpl;
import kr.or.ddit.prod.service.IDeCateService;
import kr.or.ddit.prod.vo.DeCateVO;

@WebServlet("/prod/deCateInsert.do")
public class DeCateInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        
        IDeCateService dcService = DeCateServiceImpl.getInstance();
        String deCateName = request.getParameter("deCateName");
        String cateId = request.getParameter("mainCateId");
        
        DeCateVO dcvo = new DeCateVO();
        dcvo.setDcate_name(deCateName);
        dcvo.setCate_id(cateId);
        
        int cnt = dcService.insertDeCate(dcvo);
        
        if (cnt == 1) {
            response.getWriter().write("{\"세부 카테고리 추가\":\"성공\"}");
        } else {
            response.getWriter().write("{\"세부 카테고리 추가\":\"실패\"}");
        }
        
	}

}
