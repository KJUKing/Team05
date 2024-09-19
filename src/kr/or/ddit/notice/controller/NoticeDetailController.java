package kr.or.ddit.notice.controller;

import kr.or.ddit.image.service.IImageService;
import kr.or.ddit.image.service.ImageServiceImpl;
import kr.or.ddit.image.vo.ImageVO;
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.notice.service.NoticeServiceImpl;
import kr.or.ddit.notice.vo.NoticeVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/notice/noticeDetail.do")
public class NoticeDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");


        String notiNoParameter = request.getParameter("notiNo").trim();
        int notiNo = Integer.parseInt(notiNoParameter);

        INoticeService service = NoticeServiceImpl.getInstance();
        NoticeVO noticeOne = service.getNoticeOne(notiNo);

        // 이미지 서비스 호출하여 공지사항과 연결된 이미지 리스트 가져오기
        IImageService imageService = ImageServiceImpl.getInstance();
        List<ImageVO> imageList = imageService.getImagesByTargetId(notiNoParameter.trim(), "NOTICE");

        // 공지사항과 이미지 리스트를 request에 저장
        request.setAttribute("notice", noticeOne);
        request.setAttribute("imageList", imageList);

        request.getRequestDispatcher("/main?view=notiDetail").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
