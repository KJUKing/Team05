package kr.or.ddit.card.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.card.service.CardServiceImpl;
import kr.or.ddit.card.service.ICardService;
import kr.or.ddit.express.service.ExpressServiceImpl;
import kr.or.ddit.express.service.IExpressService;


@WebServlet("/card/cardDelete.do")
public class CardDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String cardId = request.getParameter("card_id");
		
		ICardService service = CardServiceImpl.getInstance();
		
		int cnt = service.cardDelete(cardId);
		
		response.sendRedirect(request.getContextPath()+"/card/cardList.do");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
