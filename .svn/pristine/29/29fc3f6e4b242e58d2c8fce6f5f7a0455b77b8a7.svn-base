package kr.or.ddit.qna.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.qna.service.QnaService;
import kr.or.ddit.qna.vo.QnaVO;

@WebServlet("/qnaUpdatePost.do")
public class QnaUpdatePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 전송데이터 받기
		String reqData = StreamData.dataChange(request);
		
		// 자바 객체로 역직렬화
		Gson gson = new Gson();
		QnaVO vo = gson.fromJson(reqData, QnaVO.class);
		
		// service객체 얻기
		QnaService service = QnaService.getInstance();
		System.out.println("prodid"+vo.getProd_id());
		System.out.println("qnano"+vo.getQna_no());
		System.out.println("memid"+vo.getMem_id());
		System.out.println("title"+vo.getQna_title());
		System.out.println("pass"+vo.getQna_pass());
		System.out.println("content"+vo.getQna_content());
		
		// service메소드 호출하기 - 결과값 받기
		int cnt = service.qnaUpdatePost(vo);
		
		// request에 저장
		request.setAttribute("result", cnt);
		
		// view페이지 이동
		request.getRequestDispatcher("WEB-INF/views/qna/result.jsp").forward(request, response);
	}
}
