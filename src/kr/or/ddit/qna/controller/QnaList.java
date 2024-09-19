package kr.or.ddit.qna.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.qna.service.QnaService;
import kr.or.ddit.qna.vo.QnaVO;

@WebServlet("/qnaList.do")
public class QnaList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String reqData = StreamData.dataChange(request);
		// 역직렬화 - QnaVO - json 형식을 자바객체 형식으로 변환
		Gson gson = new Gson();
		QnaVO vo = gson.fromJson(reqData, QnaVO.class);
		// vo.setPage(1) vo.setStype("") vo.setSword("")
		
		// service 객체 얻기
		QnaService service = QnaService.getInstance();
		
		// list를 가져오기 위한 page 정보 가져오기
		QnaVO pvo = service.pageInfo(vo.getPage(), vo.getStype(), vo.getSword());
		// start, end, startPage, endPage, totalPage
		// 임시
		System.out.println("vo.getPageCount() = " + vo.getPageCount());
		System.out.println("vo.getStype() = " + vo.getStype());
		System.out.println("vo.getSword() = " + vo.getSword());
		System.out.println("vo.getStart() = " + vo.getStart());
		System.out.println("vo.getEnd() = " + vo.getEnd());
		// 임시
		
		// list 가져오기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pvo.getStart());
		map.put("end", pvo.getEnd());
		map.put("stype", vo.getStype());
		map.put("sword", vo.getSword());
		
		// list 가져오기 - 메소드 호출
		List<QnaVO> list = service.selectPostList(map);
		// 임시
		System.out.println("list는 " + list);
		// 임시
		
		// 결과값을 requst에 저장
		request.setAttribute("list", list);
		request.setAttribute("startPage", pvo.getStartPage());
		request.setAttribute("endPage", pvo.getEndPage());
		request.setAttribute("totalPage", pvo.getTotalPage());
		// 임시
		System.out.println("startPage = " + pvo.getStartPage());
		System.out.println("endPage = " + pvo.getEndPage());
		System.out.println("totalPage = " + pvo.getStartPage());
		// 임시
		
		// view 페이지로 이동
        request.getRequestDispatcher("/WEB-INF/views/qna/qnaList.jsp").forward(request, response);
		// 임시
		System.out.println("다 됐는지");
		// 임시
	}
}
