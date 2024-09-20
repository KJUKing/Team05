package kr.or.ddit.coupon.service;

import java.util.List;

import kr.or.ddit.coupon.vo.CouponVO;

public interface ICouponService {
	
	public int couponInsert(CouponVO coupVo);
	
	public List<CouponVO> couponList();
	
}
