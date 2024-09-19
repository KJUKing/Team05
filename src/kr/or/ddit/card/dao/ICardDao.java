package kr.or.ddit.card.dao;

import java.util.List;

import kr.or.ddit.card.vo.CardVO;

public interface ICardDao {
	public List<CardVO> getAllCard();
	
	public CardVO cardDatail(String cardId);
	
	public int cardInsert(CardVO cardVo);
	
	public int cardDelete(String cardId);
}
