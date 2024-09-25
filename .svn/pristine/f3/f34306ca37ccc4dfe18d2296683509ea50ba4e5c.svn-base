package kr.or.ddit.card.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.card.service.CardServiceImpl;
import kr.or.ddit.card.service.ICardService;
import kr.or.ddit.card.vo.CardVO;


@WebServlet("/card/cardInsert.do")
public class CardInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/main?view=adminCardInsert").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String cardName= request.getParameter("card_name");
		
		CardVO cardVo = new CardVO();
		cardVo.setCard_name(cardName);
		
		ICardService service = CardServiceImpl.getInstance();
		
		int cnt = service.cardInsert(cardVo);
		
		response.sendRedirect(request.getContextPath()+"/card/cardList.do");
		
		
	}

}
