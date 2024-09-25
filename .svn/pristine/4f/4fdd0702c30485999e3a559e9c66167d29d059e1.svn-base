package kr.or.ddit.likes.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.likes.service.ILikesService;
import kr.or.ddit.likes.service.LikesServiceImpl;
import kr.or.ddit.likes.vo.LikesVO;

@WebServlet("/likes/likesCount.do")
public class LikesCount extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String prodId = request.getParameter("prodId");
		String currentStatus = request.getParameter("currentStatus");

		ILikesService service = LikesServiceImpl.getInstance();
		
		// VO에 값 담기
        LikesVO likesVO = new LikesVO();
        likesVO.setProdId(prodId);
		
	    boolean isLiked = false;
	    int likeCount = 0;

	    // 현재 상태가 unliked라면 좋아요 추가, liked라면 좋아요 제거
	    if ("unliked".equals(currentStatus)) {
	    	service.addToLikes(likesVO); // 좋아요 추가 로직
	        isLiked = true;
	    } else {
	    	service.removeFromLikes(likesVO); // 좋아요 제거 로직
	        isLiked = false;
	    }

	    // 업데이트된 좋아요 수 조회
	    likeCount = service.likesCount(prodId);

	    // JSON 형태로 응답
	    response.setContentType("application/json");
	    PrintWriter out = response.getWriter();
	    out.print("{\"isLiked\": " + isLiked + ", \"likeCount\": " + likeCount + "}");
	    out.flush();
	}

}
