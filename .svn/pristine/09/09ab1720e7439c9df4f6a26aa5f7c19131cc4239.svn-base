package kr.or.ddit.review.dao;

import java.util.List;


import kr.or.ddit.review.vo.ReviewVO;

public interface IReviewDao {
	
	public List<ReviewVO> getAllReview();
	
	
	
	public List<ReviewVO> getMyReview(String memId);
	
	public int insertReview(ReviewVO reviewVo);
	
	String getProdId(String memId);
	
	public ReviewVO reviewDetail(String reviewId);
	
	public int updateReview(ReviewVO reviewVo);
	
	public int deleteReview(String reviewId);
	
	public int updateReport(String reviewId);
	
	 // 리뷰 중복 확인 메서드
    int isReviewExistForCart(String cartId, String memId);
	
    //결제완료인 cart_id가져오기
    public List<String> getPaidCartIdsForMember(String memId);
	
}	
