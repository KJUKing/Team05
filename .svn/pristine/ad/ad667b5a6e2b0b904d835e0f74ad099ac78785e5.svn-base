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

@WebServlet("/qnaInsertPost.do")
public class QnaInsertPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		System.out.println("뭐여 update인데 왜 insert 들어와?");
		
		String reqData = StreamData.dataChange(request);
		// {"writer" : "김은대" , "subject" : "sdf"} // 이런 방식으로 오는거 // board.jsp에다가 써놨지? {키: '벨류', 키: '벨류, ..}
		System.out.println("reqData="+reqData);
		
		// 자바객체로 역직렬화 - BoardVO
		Gson gson = new Gson();
		QnaVO vo = gson.fromJson(reqData, QnaVO.class);
		
		// vo.setWip(request.getRemoteAddr());
		// 이거 뭐임? ip인데..
		
		// service객체 얻기
		QnaService service = QnaService.getInstance();
		
		// 값 테스트
		System.out.println("vo는"+ vo);
		System.out.println("prodid"+vo.getProd_id());
		System.out.println("sword"+vo.getSword());
		// 값 테스트
		
		// service메소드 호출. 지금 하는게 게시글작성이니까 insertBoard // 오옹 insertBoard라는거 service랑 dao에 이미 만들어뒀네? 언제 만들었지! ㅋㅋ
		int cnt = service.insertPost(vo);
		
		// 결과값 - result
		request.setAttribute("result", cnt);
		
		// view 페이지로 이동
		request.getRequestDispatcher("WEB-INF/views/qna/result.jsp").forward(request, response);
	}
}
