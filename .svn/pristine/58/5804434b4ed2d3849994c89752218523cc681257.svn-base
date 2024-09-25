package kr.or.ddit.card.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.card.service.CardServiceImpl;
import kr.or.ddit.card.service.ICardService;
import kr.or.ddit.card.vo.CardVO;


@WebServlet("/card/cardList.do")
public class CardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		ICardService service = CardServiceImpl.getInstance();
		List<CardVO> cardList = service.getAllCard();
		
		request.setAttribute("cardList",cardList);
		
		request.getRequestDispatcher("/main?view=adminCard").forward(request, response);
	}

  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
