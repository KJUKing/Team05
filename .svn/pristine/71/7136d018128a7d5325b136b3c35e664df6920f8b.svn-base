package kr.or.ddit.notice.service;

import kr.or.ddit.notice.dao.INoticeDao;
import kr.or.ddit.notice.dao.NoticeDaoImpl;
import kr.or.ddit.notice.vo.NoticeVO;
import kr.or.ddit.util.PageVO;
import kr.or.ddit.util.PaginationUtil;

import java.util.Collections;
import kr.or.ddit.util.PageVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoticeServiceImpl implements INoticeService {

    private INoticeDao dao;

    private NoticeServiceImpl() {
        dao = NoticeDaoImpl.getInstance();
    }

    private static INoticeService service;

    public static INoticeService getInstance() {
        if (service == null) {
            service = new NoticeServiceImpl();
        }
        return service;
    }



    @Override
    public PageVO getPagination(int page, String stype, String sword) {
        // Map 생성 방식 수정
        Map<String, Object> map = new HashMap<>();
        map.put("stype", stype);
        map.put("sword", sword);

        // 전체 레코드 수 가져오기
        int totalCount = dao.getTotalCount(map);

        // perList와 perPage는 PageVO 기본 생성자에서 설정된 값 사용
        int perList = 10;  // 페이지당 게시물 수 (기본값)
        int perPage = 5;   // 페이지당 페이지 수 (기본값)

        // PaginationUtil을 사용해 PageVO 생성
        return PaginationUtil.getPageInfo(page, totalCount, perList, perPage);
    }


    @Override
    public List<NoticeVO> getNoticeList(Map<String, Object> map) {
        return dao.getPaginatedNoticeList(map);
    }

    @Override
    public NoticeVO getNoticeOne(int notiNo) {
        return dao.getNoticeOne(notiNo);
    }

    @Override
    public int insertNotice(NoticeVO notice) {
        return dao.insertNotice(notice);
    }

    @Override
    public int updateNotice(NoticeVO notice) {
        return dao.updateNotice(notice);
    }

    @Override
    public int deleteNotice(int notiNo) {
        return dao.deleteNotice(notiNo);
    }


}
