package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.prod.vo.OptionVO;
import kr.or.ddit.util.MybatisUtil;

public class OptionDaoImpl implements IOptionDao {
	
	private static OptionDaoImpl dao;
	
	private OptionDaoImpl() { }
	
	public static OptionDaoImpl getInstance() {
		if(dao==null) dao = new OptionDaoImpl();
		
		return dao;
	}

	@Override
	public int insertOption(OptionVO optvo) {
		SqlSession session = MybatisUtil.getSqlSession();
		int cnt = 0;
		
		try {
			cnt = session.insert("option.insertOption", optvo);
			if(cnt > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}

	@Override
	public int deleteOption(String optionId) {
		SqlSession session = MybatisUtil.getSqlSession();
		int cnt = 0;
		
		try {
			cnt = session.delete("option.deleteOption", optionId);
			if(cnt > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}

	@Override
	public int updateOption(OptionVO optvo) {
		SqlSession session = MybatisUtil.getSqlSession();
		int cnt = 0;
		
		try {
			cnt = session.update("option.updateOption", optvo);
			if(cnt > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}

	@Override
	public List<OptionVO> getAllOption() {
		SqlSession session = MybatisUtil.getSqlSession();
		List<OptionVO> OptionList = null;
		
		try {
			OptionList = session.selectList("option.getAllOption");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return OptionList;
	}

	@Override
	public List<OptionVO> getOneOption(String prodId) {
		SqlSession session = MybatisUtil.getSqlSession();
		List<OptionVO> OptionList = null;
		
		try {
			OptionList = session.selectList("option.getOneOption", prodId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return OptionList;
	}

}
