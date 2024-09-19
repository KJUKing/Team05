package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.prod.vo.MainCateVO;
import kr.or.ddit.util.MybatisUtil;

public class MainCateDaoImpl implements IMainCateDao {
	
	// 1번
	private static MainCateDaoImpl dao;
	
	// 2번 - 생성자
	private MainCateDaoImpl() { }
	
	// 3번
	public static MainCateDaoImpl getInstance() {
		if(dao==null) dao = new MainCateDaoImpl();
		
		return dao;
	}

	@Override
	public int insertMainCate(String cateName) {
		SqlSession session = MybatisUtil.getSqlSession();
		int cnt = 0;
		
		try {
			cnt = session.insert("maincate.insertMainCate", cateName);
			if(cnt > 0 ) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return cnt;
	}

	@Override
	public int deleteMainCate(String cateId) {
		SqlSession session = MybatisUtil.getSqlSession();
		int cnt = 0;
		
		try {
			cnt = session.delete("maincate.deleteMainCate", cateId);
			if(cnt > 0 ) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return cnt;
	}

	@Override
	public int updateMainCate(String cateName) {
		SqlSession session = MybatisUtil.getSqlSession();
		int cnt = 0;
		
		try {
			cnt = session.update("maincate.updateMainCate", cateName);
			if(cnt > 0 ) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return cnt;
	}

	@Override
	public List<MainCateVO> getAllMainCate() {
		SqlSession session = MybatisUtil.getSqlSession();
		List<MainCateVO> mcList = null;
		
		try {
			mcList = session.selectList("maincate.getAllMainCate");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return mcList;
	}

	


}
