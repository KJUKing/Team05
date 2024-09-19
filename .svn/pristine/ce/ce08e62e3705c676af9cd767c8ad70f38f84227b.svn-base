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
import kr.or.ddit.express.service.ExpressServiceImpl;
import kr.or.ddit.express.service.IExpressService;
import kr.or.ddit.express.vo.ExpressVO;


@WebServlet("/card/cardView.do")
public class CardView extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String cardId= request.getParameter("card_id");
		
		ICardService service = CardServiceImpl.getInstance();
		CardVO cardVo = service.cardDatail(cardId);
		
		request.setAttribute("cardVo", cardVo);
		
		request.getRequestDispatcher("/WEB-INF/views/card/cardView.jsp")
		.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
