package kr.or.ddit.coupon.service;

import java.util.List;

import kr.or.ddit.coupon.dao.CouponDaoImpl;
import kr.or.ddit.coupon.vo.CouponVO;

public class CouponServiceImpl implements ICouponService {
	
private CouponDaoImpl dao;
	
	private static CouponServiceImpl service;
	
	private CouponServiceImpl() {
		dao = CouponDaoImpl.getInstance();
		
	}
	
	public static CouponServiceImpl getInstance() {
		if(service==null) service = new CouponServiceImpl();
		
		return service;
	}

	@Override
	public int couponInsert(CouponVO coupVo) {
		// TODO Auto-generated method stub
		return dao.couponInsert(coupVo);
	}

	@Override
	public List<CouponVO> couponList() {
		// TODO Auto-generated method stub
		return dao.couponList();
	}

}
