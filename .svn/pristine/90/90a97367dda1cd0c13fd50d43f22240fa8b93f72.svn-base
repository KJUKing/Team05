package kr.or.ddit.coupon.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.coupon.vo.CouponVO;
import kr.or.ddit.util.MybatisUtil;

public class CouponDaoImpl implements ICouponDao {
	
private static CouponDaoImpl dao;
	
	private CouponDaoImpl(){}
	
	public static CouponDaoImpl getInstance() {
		if(dao==null) dao = new CouponDaoImpl();
		
		return dao;
	}

	@Override
	public int couponInsert(CouponVO coupVo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.insert("coupon.couponInsert", coupVo);
		} catch (Exception e) {
			e.printStackTrace();
		}	finally {
			session.commit();
			session.close();
		}
		
		return cnt;
	}

	@Override
	public List<CouponVO> couponList() {
		SqlSession session = null;
		List<CouponVO> couponList = null;
		
		try {
			session = MybatisUtil.getSqlSession();
			
			couponList = session.selectList("coupon.couponList");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return couponList;
	}

}
