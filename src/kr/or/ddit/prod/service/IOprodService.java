package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.prod.vo.OprodVO;
import kr.or.ddit.prod.vo.ProdVO;

public interface IOprodService {
	
	public int insertOprod(OprodVO oprvo);
	
	public int deleteOprod(String prodId);
	
	public int updateOprod(OprodVO oprvo);
	
	public List<OprodVO> getAllOprod();
	
	public List<OprodVO> getOneOprod(String prodId);
	
}
