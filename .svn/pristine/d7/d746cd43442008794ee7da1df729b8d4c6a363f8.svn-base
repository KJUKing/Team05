package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.prod.vo.DeCateVO;
import kr.or.ddit.util.MybatisUtil;

public class DeCateDaoImpl implements IDeCateDao {

	private static DeCateDaoImpl dao;
	
	private DeCateDaoImpl() { }
	
	public static DeCateDaoImpl getInstance() {
		if(dao==null) dao = new DeCateDaoImpl();
		
		return dao;
	}
	
	
	@Override
	public int insertDeCate(DeCateVO dcvo) {
		SqlSession session = MybatisUtil.getSqlSession();
		int cnt = 0;
		
		try {
			cnt = session.insert("dacate.insertDeCate", dcvo);
			if(cnt > 0 ) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return cnt;
	}

	@Override
	public int deleteDeCate(String dcateId) {
		SqlSession session = MybatisUtil.getSqlSession();
		int cnt = 0;
		
		try {
			cnt = session.delete("dacate.deleteDeCate", dcateId);
			if(cnt > 0 ) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return cnt;
	}

	@Override
	public int updateDeCate(DeCateVO dcvo) {
		SqlSession session = MybatisUtil.getSqlSession();
		int cnt = 0;
		
		try {
			cnt = session.update("dacate.updateDeCate", dcvo);
			if(cnt > 0 ) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return cnt;
	}

	@Override
	public List<DeCateVO> getAllDeCate() {
		SqlSession session = MybatisUtil.getSqlSession();
		List<DeCateVO> dcList = null;
		
		try {
			dcList = session.selectList("dacate.getAllDeCate");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return dcList;
	}

	@Override
	public List<DeCateVO> getIdDeCate(String cateId) {
		SqlSession session = MybatisUtil.getSqlSession();
		List<DeCateVO> dcList = null;
		
		try {
			dcList = session.selectList("dacate.getIdDeCate", cateId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return dcList;
	}

}
