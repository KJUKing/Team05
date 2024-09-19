package kr.or.ddit.express.service;

import java.util.List;

import javax.smartcardio.CardException;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.express.dao.ExpressDaoImpl;
import kr.or.ddit.express.vo.ExpressVO;
import kr.or.ddit.util.MybatisUtil;

public class ExpressServiceImpl implements IExpressService {
	
	private ExpressDaoImpl dao;
	
	private static ExpressServiceImpl service;
	
	private ExpressServiceImpl() {
		dao = ExpressDaoImpl.getInstance();
		
	}
	
	public static ExpressServiceImpl getInstance() {
		if(service==null) service = new ExpressServiceImpl();
		
		return service;
	}

	@Override
	public List<ExpressVO> getAllExpress() {
		// TODO Auto-generated method stub
		return dao.getAllExpress();
	}
	
	@Override
	public ExpressVO detailExpress(String exId) {
		// TODO Auto-generated method stub
		return dao.detailExpress(exId);
	}

	@Override
	public int insertExpress(ExpressVO exVo) {
		// TODO Auto-generated method stub
		return dao.insertExpress(exVo);
	}

	@Override
	public int updateExpress(ExpressVO exVo) {
		// TODO Auto-generated method stub
		return dao.updateExpress(exVo);
	}

	@Override
	public int deleteExpress(String exId) {
		// TODO Auto-generated method stub
		return dao.deleteExpress(exId);
	}

	@Override
	public List<ExpressVO> trackDelivery(String cartId) {
		// TODO Auto-generated method stub
		return dao.trackDelivery(cartId);
	}

	
	
}
