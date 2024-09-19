package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.prod.vo.OprodVO;
import kr.or.ddit.util.MybatisUtil;

public class OprodDaoImpl implements IOprodDao {
	
	// 1번
	private static OprodDaoImpl dao;
	
	// 2번 - 생성자
	private OprodDaoImpl() { }
	
	// 3번
	public static OprodDaoImpl getInstance() {
		if(dao==null) dao = new OprodDaoImpl();
		
		return dao;
	}

	@Override
	public int insertOprod(OprodVO oprvo) {
		SqlSession session = MybatisUtil.getSqlSession();
		int cnt = 0;
		
		try {
			cnt = session.insert("oprod.insertOprod", oprvo);
			if(cnt > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}

	@Override
	public int deleteOprod(String prodId) {
		SqlSession session = MybatisUtil.getSqlSession();
		int cnt = 0;
		
		try {
			cnt = session.delete("oprod.deleteOprod", prodId);
			if(cnt > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}

	@Override
	public int updateOprod(OprodVO oprvo) {
		SqlSession session = MybatisUtil.getSqlSession();
		int cnt = 0;
		
		try {
			cnt = session.update("oprod.updateOprod", oprvo);
			if(cnt > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}

	@Override
	public List<OprodVO> getAllOprod() {
		SqlSession session = MybatisUtil.getSqlSession();
		List<OprodVO> OprodList = null;
		
		try {
			OprodList = session.selectList("oprod.getAllOprod");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return OprodList;
	}

}
