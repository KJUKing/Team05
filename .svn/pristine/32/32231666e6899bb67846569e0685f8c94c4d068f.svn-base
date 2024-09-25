package kr.or.ddit.payment.service;

import java.util.List;

import kr.or.ddit.payment.vo.PaymentVO;

public interface IPaymentService {

	
	public List<PaymentVO> couponList(String memId);
	
	public List<PaymentVO> cardList();
	
	public List<PaymentVO> paymentList(String memId, String cartId);
	
	public int paymentInsert(PaymentVO payVo);
	
	public int paymentUpdate(String payId);
	
	public int mileageUpdate(PaymentVO payVo);
	
	public PaymentVO couponDiscount(String coupId);
	
	public int removeItem(String prodId);
	
	public int payMileage(String memId,int mileageBonus);
	
	public int updateCouponStatus(String coupId);

	public List<PaymentVO> paymentListForCart(String memId, String cart);
}
