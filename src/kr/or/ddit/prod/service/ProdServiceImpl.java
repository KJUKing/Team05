package kr.or.ddit.prod.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.prod.dao.IProdDao;
import kr.or.ddit.prod.dao.ProdDaoImpl;
import kr.or.ddit.prod.vo.ProdVO;
import kr.or.ddit.util.PageVO;
import kr.or.ddit.util.PaginationUtil;

public class ProdServiceImpl implements IProdService {

	// 1번
	private static ProdServiceImpl service;
	
	// Dao객체 변수 선언
	private IProdDao dao;
	
	// 2번 생성자
	private ProdServiceImpl() {
		dao = ProdDaoImpl.getInstance();
	}
	
	// 3번
	public static ProdServiceImpl getInstance() {
		if(service==null) service = new ProdServiceImpl();
		
		return service;
	}
	
	
	@Override
	public int insertProd(ProdVO pvo) {
		// TODO Auto-generated method stub
		return dao.insertProd(pvo);
	}

	@Override
	public int deleteProd(String prodId) {
		// TODO Auto-generated method stub
		return dao.deleteProd(prodId);
	}

	@Override
	public int updateProd(ProdVO pvo) {
		// TODO Auto-generated method stub
		return dao.updateProd(pvo);
	}

	@Override
	public List<ProdVO> getAllProd() {
		// TODO Auto-generated method stub
		return dao.getAllProd();
	}
	
	@Override
	public List<ProdVO> getOneProd(String prodId) {
		// TODO Auto-generated method stub
		return dao.getOneProd(prodId);
	}

	@Override
	public int getProdCount(String prodId) {
		// TODO Auto-generated method stub
		return dao.getProdCount(prodId);
	}

	@Override
	public ProdVO getProd(String prodId) {
		// TODO Auto-generated method stub
		return dao.getProd(prodId);
	}

	@Override
	public List<ProdVO> getWishListByMemberId(String memId) {
		return dao.getWishListByMemberId(memId);
	}

	@Override
	public PageVO getPagination(int page, String stype, String sword) {
		// Map 생성 방식 수정
		Map<String, Object> map = new HashMap<>();
		map.put("stype", stype);
		map.put("sword", sword);

		// 전체 레코드 수 가져오기
		int totalCount = dao.getTotalCount(map);

		// perList와 perPage는 PageVO 기본 생성자에서 설정된 값 사용
		int perList = 12;  // 페이지당 게시물 수 (기본값)
		int perPage = 5;   // 페이지당 페이지 수 (기본값)

		// PaginationUtil을 사용해 PageVO 생성
		return PaginationUtil.getPageInfo(page, totalCount, perList, perPage);
	}

	@Override
	public List<ProdVO> getPaginatedProdList(Map<String, Object> map) {
		return dao.getPaginatedProdList(map);
	}

}
