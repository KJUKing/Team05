     package kr.or.ddit.riturn.service;

import java.util.List;

import kr.or.ddit.riturn.dao.RiturnDaoImpl;
import kr.or.ddit.riturn.vo.RiturnVO;

public class RiturnServiceImpl implements IRiturnService {
	private RiturnDaoImpl dao;
	
	private static RiturnServiceImpl service;
	
	private RiturnServiceImpl() {
		dao = RiturnDaoImpl.getInstance();
	}
	
	public static RiturnServiceImpl getInstance() {
		if(service==null) service = new RiturnServiceImpl();
		
		return service;
	}

	@Override
	public List<RiturnVO> myBuyList() {
		// TODO Auto-generated method stub
		return dao.myBuyList();
	}

	@Override
	public List<RiturnVO> riturnList(String cartId) {
		// TODO Auto-generated method stub
		return dao.riturnList(cartId);
	}

	@Override
	public int riturnInsert(RiturnVO riturnVo) {
		// TODO Auto-generated method stub
		return dao.riturnInsert(riturnVo);
	}

	@Override
	public int paymentUpdate(String payId) {
		// TODO Auto-generated method stub
		return dao.paymentUpdate(payId);
	}

	@Override
	public int memMileUpdate(RiturnVO riturnVo) {
		// TODO Auto-generated method stub
		return dao.memMileUpdate(riturnVo);
	}

	
}
