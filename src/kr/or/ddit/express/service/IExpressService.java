package kr.or.ddit.express.service;

import java.util.List;

import kr.or.ddit.express.vo.ExpressVO;

public interface IExpressService {

	public List<ExpressVO> getAllExpress();
	
	public ExpressVO detailExpress(String exId);

	public int insertExpress(ExpressVO exVo);
	
	public int updateExpress(ExpressVO exVo);
	
	public int deleteExpress(String exId);
	
	public List<ExpressVO> trackDelivery(String cartId);
}
