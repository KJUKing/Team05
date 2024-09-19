package kr.or.ddit.notice.service;

import kr.or.ddit.notice.vo.NoticeVO;
import kr.or.ddit.util.PageVO;

import java.util.List;
import java.util.Map;

public interface INoticeService {

    public PageVO getPagination(int page, String stype, String sword);

    public List<NoticeVO> getNoticeList(Map<String, Object> map);

    public NoticeVO getNoticeOne(int notiNo);

    public int insertNotice(NoticeVO notice);

    public int updateNotice(NoticeVO notice);

    public int deleteNotice(int notiNo);
}
