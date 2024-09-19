package kr.or.ddit.card.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.card.vo.CardVO;
import kr.or.ddit.util.MybatisUtil;

public class CardDaoImpl implements ICardDao {
	private static CardDaoImpl dao;
	
	private CardDaoImpl(){}
	
	public static CardDaoImpl getInstance() {
		if(dao==null) dao = new CardDaoImpl();
		
		return dao;
	}

	@Override
	public List<CardVO> getAllCard() {
		SqlSession session = null;
		List<CardVO> list = null;
		try {
			session = MybatisUtil.getSqlSession();
			
			list = session.selectList("card.getAllCard");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public CardVO cardDatail(String cardId) {
		SqlSession session = null;
		CardVO cardVo = null;
		try {
			session = MybatisUtil.getSqlSession();
			
			cardVo = session.selectOne("card.detailCard", cardId);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cardVo;
	}
	
	@Override
	public int cardInsert(CardVO cardVo) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.insert("card.cardInsert", cardVo);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}

	@Override
	public int cardDelete(String cardId) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.delete("card.cardDelete", cardId);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}

}
