package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.prod.vo.ProdVO;
import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.util.PageVO;

public class ProdDaoImpl implements IProdDao {
	
	// 1번
	private static ProdDaoImpl dao;
	
	// 2번 - 생성자
	private ProdDaoImpl() { }
	
	// 3번
	public static ProdDaoImpl getInstance() {
		if(dao==null) dao = new ProdDaoImpl();
		
		return dao;
	}
	
	
	@Override
	public int insertProd(ProdVO pvo) {
		SqlSession session = MybatisUtil.getSqlSession();
		int cnt = 0;
		
		try {
			cnt = session.insert("prod.insertProd", pvo);
			if(cnt > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}

	@Override
	public int deleteProd(String prodId) {
		SqlSession session = MybatisUtil.getSqlSession();
		int cnt = 0;
		
		try {
			cnt = session.delete("prod.deleteProd", prodId);
			if(cnt > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}

	@Override
	public int updateProd(ProdVO pvo) {
		SqlSession session = MybatisUtil.getSqlSession();
		int cnt = 0;
		
		try {
			cnt = session.update("prod.updateProd", pvo);
			if(cnt > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}

	@Override
	public List<ProdVO> getAllProd() {
		SqlSession session = MybatisUtil.getSqlSession();
		List<ProdVO> prodList = null;
		
		try {
			prodList = session.selectList("prod.getAllProd");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return prodList;
	}

	@Override
	public ProdVO getOneProd(String prodId) {
		SqlSession session = MybatisUtil.getSqlSession();
		ProdVO prodVo = null;
		
		try {
			prodVo = session.selectOne("prod.getOneProd", prodId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return prodVo;
	}
	
	@Override
	public int getProdCount(String prodId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ProdVO getProd(String prodId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<ProdVO> selectForNewArr() {
		SqlSession session = MybatisUtil.getSqlSession();
		List<ProdVO> prodList = null;
		
		try {
			prodList = session.selectList("prod.selectForNewArr");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return prodList;
	}

	@Override
	public List<ProdVO> selectForBest() {
		SqlSession session = MybatisUtil.getSqlSession();
		List<ProdVO> prodList = null;
		
		try {
			prodList = session.selectList("prod.selectForBest");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return prodList;
	}

	@Override
	public List<ProdVO> selectForOuter() {
		SqlSession session = MybatisUtil.getSqlSession();
		List<ProdVO> prodList = null;
		
		try {
			prodList = session.selectList("prod.selectForOuter");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return prodList;
	}

	@Override
	public List<ProdVO> selectForTop() {
		SqlSession session = MybatisUtil.getSqlSession();
		List<ProdVO> prodList = null;
		
		try {
			prodList = session.selectList("prod.selectForTop");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return prodList;
	}

	@Override
	public List<ProdVO> selectForBottom() {
		SqlSession session = MybatisUtil.getSqlSession();
		List<ProdVO> prodList = null;
		
		try {
			prodList = session.selectList("prod.selectForBottom");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return prodList;
	}

	@Override
	public List<ProdVO> selectForAcc() {
		SqlSession session = MybatisUtil.getSqlSession();
		List<ProdVO> prodList = null;
		
		try {
			prodList = session.selectList("prod.selectForAcc");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return prodList;
	}
	
	
	

	@Override
	public List<ProdVO> getWishListByMemberId(String memId) {
		List<ProdVO> list = null;
		SqlSession session = null;
		try {
			session = MybatisUtil.getSqlSession();
			//mapper수행
			list = session.selectList("prod.getWishListByMemberId", memId);
		}catch(Exception  e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		//결과값을 service로 리턴
		return list;
	}
	
	

	@Override
	public int getTotalCount(Map<String, Object> map) {
		SqlSession session = MybatisUtil.getSqlSession();
		int count = 0;
		try {
			count = session.selectOne("prod.getTotalCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return count;
	}

	@Override
	public List<ProdVO> getPaginatedProdList(Map<String, Object> map) {
		SqlSession session = MybatisUtil.getSqlSession();
		List<ProdVO> list = null;
		try {
			list = session.selectList("prod.getPaginatedProdList", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<ProdVO> getPaginatedNewArrivals(Map<String, Object> map) {
		SqlSession session = MybatisUtil.getSqlSession();
		List<ProdVO> list = null;
		try {
			list = session.selectList("prod.getPaginatedNewArrivals", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<ProdVO> getPaginatedBestList(Map<String, Object> map) {
		SqlSession session = MybatisUtil.getSqlSession();
		List<ProdVO> list = null;
		try {
			list = session.selectList("prod.getPaginatedBestList", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<ProdVO> getPaginatedOuterList(Map<String, Object> map) {
		SqlSession session = MybatisUtil.getSqlSession();
		List<ProdVO> list = null;
		try {
			list = session.selectList("prod.getPaginatedOuterList", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<ProdVO> getPaginatedTopList(Map<String, Object> map) {
		SqlSession session = MybatisUtil.getSqlSession();
		List<ProdVO> list = null;
		try {
			list = session.selectList("prod.getPaginatedTopList", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<ProdVO> getPaginatedBottomList(Map<String, Object> map) {
		SqlSession session = MybatisUtil.getSqlSession();
		List<ProdVO> list = null;
		try {
			list = session.selectList("prod.getPaginatedBottomList", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<ProdVO> getPaginatedAccList(Map<String, Object> map) {
		SqlSession session = MybatisUtil.getSqlSession();
		List<ProdVO> list = null;
		try {
			list = session.selectList("prod.getPaginatedAccList", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public int getNewArrCount(Map<String, Object> map) {
		SqlSession session = MybatisUtil.getSqlSession();
		int count = 0;
		try {
			count = session.selectOne("prod.getNewArrCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return count;
	}
	
	@Override
	public int getBestCount(Map<String, Object> map) {
		SqlSession session = MybatisUtil.getSqlSession();
		int count = 0;
		try {
			count = session.selectOne("prod.getBestCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return count;
	}

	@Override
	public int getOuterCount(Map<String, Object> map) {
		SqlSession session = MybatisUtil.getSqlSession();
		int count = 0;
		try {
			count = session.selectOne("prod.getOuterCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return count;
	}

	@Override
	public int getTopCount(Map<String, Object> map) {
		SqlSession session = MybatisUtil.getSqlSession();
		int count = 0;
		try {
			count = session.selectOne("prod.getTopCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return count;
	}

	@Override
	public int getBottomCount(Map<String, Object> map) {
		SqlSession session = MybatisUtil.getSqlSession();
		int count = 0;
		try {
			count = session.selectOne("prod.getBottomCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return count;
	}

	@Override
	public int getAccCount(Map<String, Object> map) {
		SqlSession session = MybatisUtil.getSqlSession();
		int count = 0;
		try {
			count = session.selectOne("prod.getAccCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return count;
	}





}
