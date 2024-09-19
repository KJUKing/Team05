package kr.or.ddit.notice.dao;

import kr.or.ddit.notice.vo.NoticeVO;
import kr.or.ddit.prod.vo.ProdVO;
import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.util.PageVO;
import kr.or.ddit.wish.dao.IWishListDao;
import kr.or.ddit.wish.dao.WishListDaoImpl;
import org.apache.ibatis.session.SqlSession;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class NoticeDaoImpl implements INoticeDao {

    private NoticeDaoImpl() {

    }

    private static INoticeDao dao;

    public static INoticeDao getInstance() {
        if (dao == null) {
            dao = new NoticeDaoImpl();
        }
        return dao;
    }

    @Override
    public int getTotalCount(Map<String, Object> map) {
        SqlSession session = MybatisUtil.getSqlSession();
        int count = 0;
        try {
            count = session.selectOne("notice.getTotalCount", map);
        } finally {
            session.close();
        }
        return count;
    }

    @Override
    public List<NoticeVO> getPaginatedNoticeList(Map<String, Object> map) {
        SqlSession session = MybatisUtil.getSqlSession();
        List<NoticeVO> list = null;
        try {
            list = session.selectList("notice.getPaginatedNoticeList", map);
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public NoticeVO getNoticeOne(int notiNo) {
        SqlSession session = MybatisUtil.getSqlSession();
        NoticeVO notiVo = null;
        try {
            notiVo = session.selectOne("notice.getNoticeOne", notiNo);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return notiVo;
    }

    @Override
    public int insertNotice(NoticeVO notice) {
        SqlSession session = MybatisUtil.getSqlSession();
        int count = 0;
        try {
            // 시퀀스 값을 먼저 가져옵니다.
            int noticeId = session.selectOne("notice.getNextNoticeId");
            notice.setNotiNo(noticeId); // 시퀀스 값을 VO에 설정

            // 공지사항 데이터를 삽입
            count = session.insert("notice.insertNotice", notice);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.commit();
            session.close();
        }
        return count;
    }

    @Override
    public int updateNotice(NoticeVO notice) {
        SqlSession session = MybatisUtil.getSqlSession();
        int count = 0;
        try {
            count = session.update("notice.updateNotice", notice);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.commit();
            session.close();
        }
        return count;
    }

    @Override
    public int deleteNotice(int notiNo) {
        SqlSession session = MybatisUtil.getSqlSession();
        int count = 0;
        try {
            count = session.delete("notice.deleteNotice", notiNo);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.commit();
            session.close();
        }
        return count;
    }


}
