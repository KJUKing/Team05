package kr.or.ddit.notice.controller;

import kr.or.ddit.image.service.IImageService;
import kr.or.ddit.image.service.ImageServiceImpl;
import kr.or.ddit.image.vo.ImageVO;
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.notice.service.NoticeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/notice/deleteNotice.do")
public class DeleteNoticeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 공지사항 번호 받기
        int notiNo = Integer.parseInt(request.getParameter("notiNo"));

        // 공지사항 삭제
        INoticeService noticeService = NoticeServiceImpl.getInstance();
        int deleteResult = noticeService.deleteNotice(notiNo);

        if (deleteResult > 0) {
            // 이미지 삭제
            IImageService imageService = ImageServiceImpl.getInstance();
            List<ImageVO> imageList = imageService.getImagesByTargetId(String.valueOf(notiNo), "NOTICE");

            for (ImageVO image : imageList) {
                // 1. 이미지 DB에서 삭제
                imageService.deleteImage(image.getImageId());

                // 2. 실제 파일 삭제 처리
                String uploadPath = getServletContext().getRealPath(image.getImagePath()); // 파일 경로 얻기
                if (uploadPath != null) {
                    File file = new File(uploadPath);
                    if (file.exists()) {
                        boolean isDeleted = file.delete(); // 파일 삭제 시도
                        if (!isDeleted) {
                            // 파일 삭제 실패 시 로그 출력
                            System.out.println("Failed to delete file: " + file.getPath());
                        }
                    } else {
                        // 파일이 존재하지 않을 경우 로그 출력
                        System.out.println("File not found: " + file.getPath());
                    }
                } else {
                    // 경로가 null일 경우 로그 출력
                    System.out.println("Upload path is null for file: " + image.getImagePath());
                }
            }
        }

        // 삭제 완료 후 공지사항 리스트로 리다이렉트
        response.sendRedirect(request.getContextPath() + "/notice/noticeList.do");
    }
}

