package kr.or.ddit.review.controller;

import kr.or.ddit.image.service.IImageService;
import kr.or.ddit.image.service.ImageServiceImpl;
import kr.or.ddit.review.service.IReviewService;
import kr.or.ddit.review.service.ReviewServiceImpl;
import kr.or.ddit.review.vo.ReviewVO;
import kr.or.ddit.image.vo.ImageVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@WebServlet("/reviewUpdate.do")
@MultipartConfig
public class ReviewUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // GET 요청 처리: 리뷰 수정 폼 로딩
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        // 리뷰 ID 받아오기
        String reviewId = request.getParameter("review_id");

        // 서비스 호출하여 해당 리뷰 정보 조회
        IReviewService reviewService = ReviewServiceImpl.getInstance();
        ReviewVO rvo = reviewService.reviewDetail(reviewId);

        // 이미지 서비스 호출하여 리뷰에 관련된 이미지 목록 가져오기
        IImageService imageService = ImageServiceImpl.getInstance();
        List<ImageVO> imageList = imageService.getImagesByTargetId(reviewId, "REVIEW");

        // 리뷰와 이미지 데이터를 request에 저장
        request.setAttribute("reviewVo", rvo);
        request.setAttribute("imageList", imageList);

        // JSP로 포워딩 (리뷰 수정 폼 표시)
        request.getRequestDispatcher("/main?view=reviewUpdate").forward(request, response);
    }

    // POST 요청 처리: 리뷰 업데이트
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        // 폼 데이터 받아오기
        String reviewId = request.getParameter("review_id");
        String reviewStarStr = request.getParameter("review_star");
        String reviewContent = request.getParameter("review_content");

        // 별점 변환
        int reviewStar = 0;
        try {
            reviewStar = Integer.parseInt(reviewStarStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        // 리뷰 정보 설정
        ReviewVO rvo = new ReviewVO();
        rvo.setReview_id(reviewId);
        rvo.setReview_star(reviewStar);
        rvo.setReview_content(reviewContent);

        IReviewService reviewService = ReviewServiceImpl.getInstance();
        int cnt = reviewService.updateReview(rvo);

       

        // 1. 기존 리뷰 이미지 삭제
        String[] deleteImages = request.getParameterValues("deleteImage");
        IImageService imageService = ImageServiceImpl.getInstance();
        if (deleteImages != null) {
            for (String imageIdStr : deleteImages) {
                int imageId = Integer.parseInt(imageIdStr);
                imageService.deleteImage(imageId);
            }
        }

        // 2. 새로 추가할 이미지 처리
        String uploadPath = "//192.168.146.20/공유폴더/중프자료제출/5연근보유조/reviewimage";  // 실제 이미지 저장 경로
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();  // 폴더가 없으면 생성
        }

        List<ImageVO> imageList = new ArrayList<>();
        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            if (!fileName.isEmpty()) {
                String saveFileName = UUID.randomUUID().toString() + "_" + fileName;
                part.write(uploadPath + File.separator + saveFileName);

                // 이미지 정보 저장
                ImageVO imageVO = new ImageVO();
                imageVO.setTargetId(reviewId);  // 수정된 리뷰 ID 사용
                imageVO.setTargetType("REVIEW");
                imageVO.setImagePath(saveFileName);  // 상대 경로로 저장

                imageList.add(imageVO);
            }
        }

        // 새 이미지 삽입
        for (ImageVO image : imageList) {
            imageService.insertImage(image);
        }

        // 업데이트 완료 후 리뷰 리스트 페이지로 리다이렉트
        if (cnt > 0) {
            response.sendRedirect(request.getContextPath() + "/reviewList.do");
        } else {
            response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
        }
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
