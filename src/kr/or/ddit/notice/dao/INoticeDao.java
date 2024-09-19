package kr.or.ddit.notice.dao;

import kr.or.ddit.notice.vo.NoticeVO;
import kr.or.ddit.util.PageVO;

import java.util.List;
import java.util.Map;

public interface INoticeDao {


    public int getTotalCount(Map<String, Object> map);

    public List<NoticeVO> getPaginatedNoticeList(Map<String, Object> map);

    public NoticeVO getNoticeOne(int notiNo);

    public int insertNotice(NoticeVO notice);

    public int updateNotice(NoticeVO notice);

    public int deleteNotice(int notiNo);
}


