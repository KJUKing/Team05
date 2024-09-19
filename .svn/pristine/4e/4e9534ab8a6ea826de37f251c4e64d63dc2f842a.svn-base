package kr.or.ddit.review.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.review.vo.ReviewVO;
import kr.or.ddit.util.MybatisUtil;

public class ReviewDaoImpl implements IReviewDao {
	
	private static ReviewDaoImpl dao;
	
	private  ReviewDaoImpl() {}
	
	public static ReviewDaoImpl getInstance() {
		if(dao == null) dao = new ReviewDaoImpl();
		return dao;
	}
		
	@Override
	public List<ReviewVO> getAllReview() {
		SqlSession session = MybatisUtil.getSqlSession();
		List<ReviewVO> rList = null;
		
		try {
			rList = session.selectList("review.getAllReview");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return rList;
	}

	@Override
	public int deleteReview(String reviewId) {
		SqlSession session = MybatisUtil.getSqlSession();
		int cnt = 0;
		
		try {
			cnt = session.delete("review.deleteReview", reviewId);
			if(cnt > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return cnt;
	}

	@Override
	public List<ReviewVO> getMyReview(String memId) {
		SqlSession session = MybatisUtil.getSqlSession();
		List<ReviewVO> rList = null;
		
		try {
			rList = session.selectList("review.getMyReview", memId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return rList;
	}

	@Override
	public int insertReview(ReviewVO reviewVo) {
		SqlSession session = MybatisUtil.getSqlSession();
		int cnt = 0;
		
		try {
			cnt = session.insert("review.insertReview", reviewVo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(cnt > 0) session.commit();
			if(session != null) session.close();
		}
		
		return cnt;
	}

	@Override
	public ReviewVO reviewDetail(String reviewId) {
		SqlSession session = MybatisUtil.getSqlSession();
		ReviewVO review = null;
		
		try {
			review = session.selectOne("review.reviewDetail", reviewId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return review;
	}

	@Override
	public String getProdId(String cartId) {  // 수정: cartId 기반으로 상품 ID 조회
		SqlSession session = MybatisUtil.getSqlSession();
		String prodId = null;
		
		try {
			prodId = session.selectOne("review.getProdId", cartId);  // cartId를 기준으로 조회
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return prodId;
	}

	@Override
	public int updateReview(ReviewVO reviewVo) {
		SqlSession session = MybatisUtil.getSqlSession();
		int cnt = 0;
		
		try {
			cnt = session.update("review.updateReview", reviewVo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(cnt > 0) session.commit();
			if(session != null) session.close();
		}
		
		return cnt;
	}

	@Override
	public int updateReport(String reviewId) {
		SqlSession session = MybatisUtil.getSqlSession();
		int cnt = 0;
		
		try {
			cnt = session.update("review.updateReport", reviewId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(cnt > 0) session.commit();
			if(session != null) session.close();
		}
		
		return cnt;
	}
	
	@Override
	public int isReviewExistForCart(String cartId, String memId) {
		SqlSession session = MybatisUtil.getSqlSession();
		int count = 0;

		try {
			Map<String, String> params = new HashMap<>();
			params.put("cartId", cartId);
			params.put("memId", memId);
			
			count = session.selectOne("review.isReviewExist", params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}

		return count;
	}

	@Override
	public List<String> getPaidCartIdsForMember(String memId) {  // 추가: 장바구니 목록 조회
		SqlSession session = MybatisUtil.getSqlSession();
		List<String> cartList = null;
		
		try {
			cartList = session.selectList("review.getPaidCartIdsForMember", memId);  // memId를 기준으로 장바구니 목록 조회
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return cartList;
	}
}
