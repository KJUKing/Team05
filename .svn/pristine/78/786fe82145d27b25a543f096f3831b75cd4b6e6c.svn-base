package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.prod.dao.IMainCateDao;
import kr.or.ddit.prod.dao.MainCateDaoImpl;
import kr.or.ddit.prod.vo.MainCateVO;

public class MainCateServiceImpl implements IMainCateService{
	
	// 1번
	private static MainCateServiceImpl service;
	
	// Dao객체 변수 선언
	private IMainCateDao dao;
	
	// 2번 생성자
	private MainCateServiceImpl() {
		dao = MainCateDaoImpl.getInstance();
	}
	
	// 3번
	public static MainCateServiceImpl getInstance() {
		if(service==null) service = new MainCateServiceImpl();
		
		return service;
	}
	
	@Override
	public int insertMainCate(String cateName) {
		// TODO Auto-generated method stub
		return dao.insertMainCate(cateName);
	}

	@Override
	public int deleteMainCate(String cateId) {
		// TODO Auto-generated method stub
		return dao.deleteMainCate(cateId);
	}

	@Override
	public int updateMainCate(String cateName) {
		// TODO Auto-generated method stub
		return dao.updateMainCate(cateName);
	}

	@Override
	public List<MainCateVO> getAllMainCate() {
		// TODO Auto-generated method stub
		return dao.getAllMainCate();
	}

}
