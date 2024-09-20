package kr.or.ddit.coupon.dao;

import java.util.List;

import kr.or.ddit.coupon.vo.CouponVO;

public interface ICouponDao {
	
	public int couponInsert(CouponVO coupVo);
	
	public List<CouponVO> couponList();
}
