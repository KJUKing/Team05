package kr.or.ddit.payment.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.payment.vo.PaymentVO;
import kr.or.ddit.util.MybatisUtil;

public class PaymentDaoImpl implements IPaymentDao {
	
	private static PaymentDaoImpl dao;
	
	private PaymentDaoImpl() {}
	
	public static PaymentDaoImpl getInstance() {
		if(dao==null) dao = new PaymentDaoImpl();
		
		return dao;
	}

	@Override
	public List<PaymentVO> paymentList(PaymentVO payVo) {
		return null;
	}

	@Override
	public int paymentInsert(PaymentVO payVo) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.insert("payment.paymentInsert", payVo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}

	@Override
	public int paymentUpdate(String payId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PaymentVO> paymentList2() {
		SqlSession session = null;
		List<PaymentVO> payList = null;
		
		try {
			session = MybatisUtil.getSqlSession();
			
			payList = session.selectList("payment.paymentList2");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return payList;
	}

	@Override
	public List<PaymentVO> couponList(String memId) {
		SqlSession session = null;
		List<PaymentVO> payList = null;
		
		try {
			session = MybatisUtil.getSqlSession();
			
			payList = session.selectList("payment.couponList",memId);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return payList;
	}

	@Override
	public List<PaymentVO> cardList() {
		SqlSession session = null;
		List<PaymentVO> payList = null;
		
		try {
			session = MybatisUtil.getSqlSession();
			
			payList = session.selectList("payment.cardList");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return payList;
	}

	@Override
	public int mileageUpdate(PaymentVO payVo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.update("payment.mileageUpdate", payVo);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		
		return cnt;
	}

	@Override
	public PaymentVO couponDiscount(String coupId) {
		SqlSession session = null;
		PaymentVO payVo = null;
		
		try {
			session = MybatisUtil.getSqlSession();
			
			payVo = session.selectOne("payment.couponDiscount", coupId);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return payVo;
	}

	@Override
	public int removeItem(String prodId) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.update("payment.removeItem", prodId);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		
		return cnt;
	}

	@Override
	public int payMileage(String memId,int mileageBonus) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MybatisUtil.getSqlSession();
			
			 Map<String, Object> param = new HashMap<>();
		     param.put("memId", memId);
		     param.put("mileageBonus", mileageBonus);
			
			cnt = session.update("payment.payMileage", param);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		
		return cnt;
	}

	
}
