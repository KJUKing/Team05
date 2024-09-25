package kr.or.ddit.qna.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.prod.vo.ProdVO;
import kr.or.ddit.qna.vo.QnaVO;
import kr.or.ddit.util.MybatisUtil;

public class QnaDao {
	private static QnaDao dao;
	
	// 빈 기본생성자 생성하여 싱글톤으로 변경
	private QnaDao() {}
	
	// 자기 자신 getInstance 생성
	public static QnaDao getInstance() {
		if(dao == null) dao = new QnaDao();
		return dao;
	}
	
	public List<QnaVO> qnaGetList() {
		SqlSession session = null;
		List<QnaVO> qnaList = null;
		
		try {
			session = MybatisUtil.getSqlSession();
			qnaList = session.selectList("qna.qnaGetList");
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null) session.close();
		}
		return qnaList;
	}
	
	public List<QnaVO> selectPostList(Map<String, Object> map) {
		
		// 1. 선언
		List<QnaVO> list = null;
		SqlSession sql = null;
		
		// 2. 실행
		try {
			sql = MybatisUtil.getSqlSession();
			list = sql.selectList("qna.selectPostList", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sql.commit();
			sql.close();
		}
		
		// 3. 리턴
		return list;
	}
	
	
	public int countPost(Map<String, Object> map) {
		// 1. 선언
		int list = 0;
		SqlSession sql = null;
		
		// 2. 실행
		try {
			sql = MybatisUtil.getSqlSession();
			list = sql.selectOne("qna.countPost", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sql.commit();
			sql.close();
		}
		
		// 3. 리턴
		return list;
	}
	
	
	public int insertPost(QnaVO vo) {
		// 1. 선언
		int cnt = 0;
		SqlSession sql = null;
		
		// 2. 실행
		try {
			sql = MybatisUtil.getSqlSession();
			cnt = sql.insert("qna.insertPost", vo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sql.commit();
			sql.close();
		}
		
		// 3. 리턴
		return cnt;
	}

	public int qnaUpdatePost(QnaVO vo) {
		// 1. 선언
		int cnt = 0;
		SqlSession sql = null;
		
		// 2. 실행
		try {
			sql = MybatisUtil.getSqlSession();
			cnt = sql.update("qna.updatePost", vo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sql.commit();
			sql.close();
		}
		
		// 3. 리턴
		return cnt;
	}
	
	
	public int qnaDeletePost(int num) {
		// 1. 선언
		int cnt = 0;
		SqlSession sql = null;
		
		// 2. 실행
		try {
			sql = MybatisUtil.getSqlSession();
			cnt = sql.update("qna.qnaDeletePost", num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sql.commit();
			sql.close();
		}
		
		// 3. 리턴
		return cnt;
	}

	public int qnaInsertReply(QnaVO vo) {
		// 1. 선언
		int list = 0;
		SqlSession sql = null;
		
		// 2. 실행
		try {
			sql = MybatisUtil.getSqlSession();
			list = sql.update("qna.insertReply", vo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sql.commit();
			sql.close();
		}
		
		// 3. 리턴
		return list;
	}

	public List<QnaVO> qnaReplyList(int bonum) {
		// 1. 선언
		List<QnaVO> list = null;
		SqlSession sql = null;
		
		// 2. 실행
		try {
			sql = MybatisUtil.getSqlSession();
			list = sql.selectList("qna.replyList", bonum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sql.commit();
			sql.close();
		}
		
		// 3. 리턴
		return list;
	}

	public List<ProdVO> getAllProdName(ProdVO vo) {
		// 1. 선언
		List<ProdVO> list = null;
		SqlSession sql = null;
		
		// 2. 실행
		try {
			sql = MybatisUtil.getSqlSession();
			list = sql.selectList("qna.getAllProdName", vo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sql.commit();
			sql.close();
		}
		
		// 3. 리턴
		return list;
	}

	public String getImageId(String target) {
		// 1. 선언
		String list = "";
		SqlSession sql = null;
		
		// 2. 실행
		try {
			sql = MybatisUtil.getSqlSession();
			list = sql.selectOne("qna.getImageId", target);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sql.commit();
			sql.close();
		}
		
		// 3. 리턴
		return list;
	}
	
}
