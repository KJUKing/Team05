package kr.or.ddit.riturn.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.riturn.vo.RiturnVO;
import kr.or.ddit.util.MybatisUtil;

public class RiturnDaoImpl implements IRiturnDao {
	private static RiturnDaoImpl dao;
	
	private RiturnDaoImpl() {}
	
	public static RiturnDaoImpl getInstance() {
		if(dao==null) dao = new RiturnDaoImpl();
		
		return dao;
	}

	@Override
	public List<RiturnVO> myBuyList() {
		SqlSession session = null;
		List<RiturnVO> riturnList = null;
		
		try {
			session = MybatisUtil.getSqlSession();
			
			riturnList = session.selectList("riturn.myBuyList");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return riturnList;
	}

	@Override
	public List<RiturnVO> riturnList(String cartId) {
		SqlSession session = null;
		List<RiturnVO> riturnList = null;
		
		try {
			session = MybatisUtil.getSqlSession();
			
			riturnList = session.selectList("riturn.riturnList", cartId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return riturnList;
	}

	@Override
	public int riturnInsert(RiturnVO riturnVo) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.insert("riturn.riturnInsert", riturnVo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}

	@Override
	public int paymentUpdate(String payId) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.update("riturn.paymentUpdate", payId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}

	@Override
	public int memMileUpdate(RiturnVO riturnVo) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.update("riturn.memMileUpdate", riturnVo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}

	
}
