package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.prod.vo.MainCateVO;

public interface IMainCateDao {

	/**
	 * 분류 카테고리 입력
	 * @param mcvo
	 * @return
	 */
	public int insertMainCate(String cateName);
	
	/**
	 * 분류 카테고리 삭제
	 * @param cateId
	 * @return
	 */
	public int deleteMainCate(String cateId);
	
	/**
	 * 분류 카테고리 수정
	 * @param mcvo
	 * @return
	 */
	public int updateMainCate(String cateName);
	
	/**
	 * 분류 카테고리 전체 조회
	 * @return
	 */
	public List<MainCateVO> getAllMainCate();
}
