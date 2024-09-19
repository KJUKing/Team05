package kr.or.ddit.express.dao;

import java.util.List;

import kr.or.ddit.express.vo.ExpressVO;

public interface IExpressDao {
	
	public List<ExpressVO> getAllExpress();
	
	public ExpressVO detailExpress(String exId);

	public int insertExpress(ExpressVO exVo);
	
	public int updateExpress(ExpressVO exVo);
	
	public int deleteExpress(String exId);

	public List<ExpressVO> trackDelivery(String cartId);
}
