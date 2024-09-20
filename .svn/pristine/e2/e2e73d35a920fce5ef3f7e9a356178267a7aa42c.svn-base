package kr.or.ddit.notice.controller;

import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.notice.service.NoticeServiceImpl;
import kr.or.ddit.image.service.IImageService;
import kr.or.ddit.image.service.ImageServiceImpl;
import kr.or.ddit.notice.vo.NoticeVO;
import kr.or.ddit.image.vo.ImageVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet("/notice/updateNotice.do")
@MultipartConfig
public class UpdateNoticeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 공지사항 번호를 파라미터로 받음
        int notiNo = Integer.parseInt(request.getParameter("notiNo"));

        // 서비스에서 공지사항 데이터를 가져옴
        NoticeVO notice = NoticeServiceImpl.getInstance().getNoticeOne(notiNo);

        // 이미지 서비스에서 해당 공지사항의 이미지 리스트 갖고오기
        List<ImageVO> imageList = ImageServiceImpl.getInstance().getImagesByTargetId(String.valueOf(notiNo), "NOTICE");

        // notice 객체와 이미지를 request에 저장
        request.setAttribute("notice", notice);
        request.setAttribute("imageList", imageList);

        request.getRequestDispatcher("/main?view=updateNotice").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 폼 데이터 받아오기
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        int notiNo = Integer.parseInt(request.getParameter("notiNo"));
        String title = request.getParameter("notiTitle");
        String content = request.getParameter("notiContent");

        // NoticeVO 생성 및 데이터 설정
        NoticeVO notice = new NoticeVO();
        notice.setNotiNo(notiNo);
        notice.setNotiTitle(title);
        notice.setNotiContent(content);

        INoticeService noticeService = NoticeServiceImpl.getInstance();
        int updateResult = noticeService.updateNotice(notice); // 공지사항 수정

        // 삭제할 이미지 처리
        String[] deleteImages = request.getParameterValues("deleteImage");
        IImageService imageService = ImageServiceImpl.getInstance();
        if (deleteImages != null) {
            for (String imageIdStr : deleteImages) {
                int imageId = Integer.parseInt(imageIdStr);
                imageService.deleteImage(imageId);  // 이미지 삭제
            }
        }

        // 새로 추가할 이미지 처리
        String uploadPath = "//192.168.146.20/공유폴더/중프자료제출/5연근보유조/noticeimage";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        List<ImageVO> imageList = new ArrayList<>();
        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            if (!fileName.isEmpty()) {
                String saveFileName = UUID.randomUUID().toString() + "_" + fileName;
                part.write(uploadPath + File.separator + saveFileName);

                ImageVO imageVO = new ImageVO();
                imageVO.setTargetId(String.valueOf(notiNo));  // 리뷰 ID 설정
                imageVO.setTargetType("NOTICE");
                imageVO.setImagePath(saveFileName);

                imageList.add(imageVO);
            }
        }

        for (ImageVO image : imageList) {
            imageService.insertImage(image);  // 새 이미지 삽입
        }

        response.sendRedirect(request.getContextPath() + "/notice/noticeList.do");
    }

    // 파일 이름 추출 메소드
    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return "";
    }
}

