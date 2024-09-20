package kr.or.ddit.review.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.review.dao.IReviewDao;
import kr.or.ddit.review.dao.ReviewDaoImpl;
import kr.or.ddit.review.vo.ReviewVO;

public class ReviewServiceImpl implements IReviewService {

    private static ReviewServiceImpl service;
    
    private IReviewDao dao;
    
    private ReviewServiceImpl() {
        dao = ReviewDaoImpl.getInstance();
    }
    
    public static ReviewServiceImpl getInstance() {
        if (service == null) service = new ReviewServiceImpl();
        return service;
    }
    
    @Override
    public List<ReviewVO> getAllReview() {
        // 모든 리뷰 가져오기
        return dao.getAllReview();
    }
    
    @Override
    public int deleteReview(String reviewId) {
        // 리뷰 삭제하기
        return dao.deleteReview(reviewId);
    }
    
    @Override
    public List<ReviewVO> getMyReview(String memId) {
        // 나의 리뷰 가져오기
        return dao.getMyReview(memId);
    }
    
    @Override
    public int insertReview(ReviewVO reviewVo) {
        // 리뷰 insert
        return dao.insertReview(reviewVo);
    }
    
    @Override
    public ReviewVO reviewDetail(String reviewId) {
        // 리뷰 상세보기
        return dao.reviewDetail(reviewId);
    }
    
    @Override
    public String getProdId(String memId) {
        // 구매하지 않은 사람은 리뷰 못 쓰게 하기
        return dao.getProdId(memId);
    }
    
    @Override
    public int updateReview(ReviewVO reviewVo) {
        // 리뷰 수정하기
        return dao.updateReview(reviewVo);
    }
    
    @Override
    public int updateReport(String reviewId) {
        // 신고 횟수 업데이트
        return dao.updateReport(reviewId);
    }
    
    @Override
    public boolean isReviewExistForCart(String cartId, String memId) {
        // DAO에서 중복 체크 메서드를 호출
        return dao.isReviewExistForCart(cartId, memId) > 0;
    }

	@Override
	public List<String> getPaidCartIdsForMember(String memId) {
		// TODO Auto-generated method stub
		return dao.getPaidCartIdsForMember(memId);
	}

	@Override
	public List<ReviewVO> getProductInfoForCart(String memId) {
		// TODO Auto-generated method stub
		return dao.getProductInfoForCart(memId);
	}
}
