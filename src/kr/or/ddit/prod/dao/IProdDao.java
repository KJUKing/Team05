package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.prod.vo.ProdVO;

public interface IProdDao {
	/**
	 * 상품 입력
	 * @param pvo
	 * @return
	 */
	public int insertProd(ProdVO pvo);
	
	/**
	 * 상품 삭제
	 * @param prodId
	 * @return
	 */
	public int deleteProd(String prodId);
	
	/**
	 * 상품 수정
	 * @param pvo
	 * @return
	 */
	public int updateProd(ProdVO pvo);
	
	/**
	 * 상품 전체 조회
	 * @return
	 */
	public List<ProdVO> getAllProd();
	
	/**
	 * 한개의 상품 전체 조회
	 * @return
	 */
	public List<ProdVO> getOneProd(String prodId);
	
	/**
	 * 상품 아이디 검색
	 * @param prodId
	 * @return
	 */
	public int getProdCount(String prodId);
	
	/**
	 * 상품 개별 조회
	 * @param prodId
	 * @return
	 */
	public ProdVO getProd(String prodId);


	//민경주 - wishList를통해서 prod를 가져오려면 ProdVO를 사용해야하기때문에 여기다 작성했습니다
	List<ProdVO> getWishListByMemberId(String memId);

	public int getTotalCount(Map<String, Object> map);

	public List<ProdVO> getPaginatedProdList(Map<String, Object> map);
}
