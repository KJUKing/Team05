package kr.or.ddit.card.service;

import java.util.List;

import kr.or.ddit.card.dao.CardDaoImpl;
import kr.or.ddit.card.vo.CardVO;

public class CardServiceImpl implements ICardService {
	
	private CardDaoImpl dao;
	
	private static CardServiceImpl service;
	
	private CardServiceImpl() {
		dao = CardDaoImpl.getInstance();
		
	}
	
	public static CardServiceImpl getInstance() {
		if(service==null) service = new CardServiceImpl();
		
		return service;
	}

	@Override
	public List<CardVO> getAllCard() {
		// TODO Auto-generated method stub
		return dao.getAllCard();
	}
	@Override
	public CardVO cardDatail(String cardId) {
		// TODO Auto-generated method stub
		return dao.cardDatail(cardId);
	}

	@Override
	public int cardInsert(CardVO cardVo) {
		// TODO Auto-generated method stub
		return dao.cardInsert(cardVo);
	}

	@Override
	public int cardDelete(String cardId) {
		// TODO Auto-generated method stub
		return dao.cardDelete(cardId);
	}

}
