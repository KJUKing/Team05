package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.prod.vo.OprodVO;

public interface IOprodDao {
	
	public int insertOprod(OprodVO oprvo);
	
	public int deleteOprod(String prodId);
	
	public int updateOprod(OprodVO oprvo);
	
	public List<OprodVO> getAllOprod();
	
	public List<OprodVO> getOneOprod(String prodId);
	
}
