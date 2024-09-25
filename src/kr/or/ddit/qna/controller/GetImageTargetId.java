package kr.or.ddit.qna.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.qna.service.QnaService;

@WebServlet("/getImageTargetId.do")
public class GetImageTargetId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/plain; charset=utf-8"); // 응답의 타입과 인코딩 설정
		response.setCharacterEncoding("utf-8"); // 인코딩 설정
		
		String target = request.getParameter("target");
		System.out.println(target+" 값은 들어왔겠지?");
		
		QnaService service = QnaService.getInstance();
		String imageId = service.getImageId(target);
		System.out.println("imageId 출력하기 : " + imageId);
		request.setAttribute("imageId", imageId);
		
		// 클라이언트로 문자열 반환
		response.getWriter().write(imageId); // imageId를 클라이언트로 전송
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
