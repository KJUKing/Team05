package kr.or.ddit.notice.controller;

import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.notice.service.NoticeServiceImpl;
import kr.or.ddit.notice.vo.NoticeVO;
import kr.or.ddit.util.PageVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/notice/noticeList.do")
public class NoticeListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageParam = request.getParameter("page");
        String stype = request.getParameter("stype");
        String sword = request.getParameter("sword");

        // page 파라미터가 없으면 기본값을 1로 설정
        int page = (pageParam == null || pageParam.trim().isEmpty()) ? 1 : Integer.parseInt(pageParam.trim());

        // 필터링에 사용될 stype, sword 값도 공백을 제거하여 처리
        stype = (stype == null || stype.trim().isEmpty()) ? null : stype.trim();
        sword = (sword == null || sword.trim().isEmpty()) ? null : sword.trim();

        INoticeService service = NoticeServiceImpl.getInstance();
        PageVO pageInfo = service.getPagination(page, stype, sword);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("start", pageInfo.getStart());
        paramMap.put("end", pageInfo.getEnd());
        paramMap.put("stype", stype);
        paramMap.put("sword", sword);

        // 공지사항 리스트 조회
        List<NoticeVO> noticeList = service.getNoticeList(paramMap);

        // JSP로 데이터 전달
        request.setAttribute("noticeList", noticeList);
        request.setAttribute("pageInfo", pageInfo);

        request.getRequestDispatcher("/main?view=noticeList").forward(request, response);
    }

}

