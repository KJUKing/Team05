package kr.or.ddit.payment.dao;

import java.util.List;

import kr.or.ddit.payment.vo.PaymentVO;

public interface IPaymentDao {
	
	public List<PaymentVO> paymentList2();
	
	public List<PaymentVO> couponList();
	
	public List<PaymentVO> cardList();
	
	public List<PaymentVO> paymentList(PaymentVO payVo);
	
	public int paymentInsert(PaymentVO payVo);
	
	public int paymentUpdate(String payId);
	
	public int mileageUpdate(PaymentVO payVo);
	
	public PaymentVO couponDiscount(String coupId);
	
}
