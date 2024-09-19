package kr.or.ddit.payment.service;

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
	public List<PaymentVO> paymentList(PaymentVO payVo) {
		// TODO Auto-generated method stub
		return dao.paymentList(payVo);
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
	public List<PaymentVO> paymentList2() {
		// TODO Auto-generated method stub
		return dao.paymentList2();
	}

	@Override
	public List<PaymentVO> couponList() {
		// TODO Auto-generated method stub
		return dao.couponList();
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
}
