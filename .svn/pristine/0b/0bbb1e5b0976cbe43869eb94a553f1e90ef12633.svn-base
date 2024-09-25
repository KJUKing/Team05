package kr.or.ddit.payment.service;

import java.util.Collections;
import java.util.List;

import kr.or.ddit.payment.dao.PaymentDaoImpl;
import kr.or.ddit.payment.vo.PaymentVO;

public class PaymentServiceImpl implements IPaymentService {
	
	private PaymentDaoImpl dao;
	
	private static PaymentServiceImpl service;
	
	private PaymentServiceImpl() {
		dao = PaymentDaoImpl.getInstance();
	}
	
	public static PaymentServiceImpl getInstance() {
		if(service==null) service = new PaymentServiceImpl();
		
		return service;
	}

	@Override
	public List<PaymentVO> paymentList(String memId, String cartId) {
		// TODO Auto-generated method stub
		return dao.paymentList(memId, cartId);
	}

	@Override
	public int paymentInsert(PaymentVO payVo) {
		// TODO Auto-generated method stub
		return dao.paymentInsert(payVo);
	}

	@Override
	public int paymentUpdate(String payId) {
		// TODO Auto-generated method stub
		return dao.paymentUpdate(payId);
	}



	@Override
	public List<PaymentVO> couponList(String memId) {
		// TODO Auto-generated method stub
		return dao.couponList(memId);
	}

	@Override
	public List<PaymentVO> cardList() {
		// TODO Auto-generated method stub
		return dao.cardList();
	}

	@Override
	public int mileageUpdate(PaymentVO payVo) {
		// TODO Auto-generated method stub
		return dao.mileageUpdate(payVo);
	}

	@Override
	public PaymentVO couponDiscount(String coupId) {
		// TODO Auto-generated method stub
		return dao.couponDiscount(coupId);
	}

	@Override
	public int removeItem(String prodId) {
		// TODO Auto-generated method stub
		return dao.removeItem(prodId);
	}

	@Override
	public int payMileage(String memId,int mileageBonus) {
		// TODO Auto-generated method stub
		return dao.payMileage(memId, mileageBonus);
	}

	@Override
	public int updateCouponStatus(String coupId) {
		// TODO Auto-generated method stub
		return dao.updateCouponStatus(coupId);
	}

	@Override
	public List<PaymentVO> paymentListForCart(String memId, String cart) {
		return dao.paymentListForCart(memId, cart);
	}
}
