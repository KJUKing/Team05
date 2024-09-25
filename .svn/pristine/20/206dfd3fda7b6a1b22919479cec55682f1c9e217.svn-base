package kr.or.ddit.review.controller;

import kr.or.ddit.image.service.ImageServiceImpl;
import kr.or.ddit.review.service.IReviewService;
import kr.or.ddit.review.service.ReviewServiceImpl;
import kr.or.ddit.review.vo.ReviewVO;
import kr.or.ddit.image.vo.ImageVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.image.service.IImageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
        maxFileSize = 1024 * 1024 * 10,       // 10MB
        maxRequestSize = 1024 * 1024 * 50     // 50MB
)
@WebServlet("/review/insertReview.do")
public class ReviewInsert extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 폼 데이터 받아오기
        String content = request.getParameter("reviewContent");
        int star = Integer.parseInt(request.getParameter("reviewStar"));
        String cartId = request.getParameter("cartId");  // 선택된 장바구니 ID
        String prodId = request.getParameter("prodId");  // 상품 ID
        String optionId = request.getParameter("optionId");  // 옵션 ID

        System.out.println("Received cartId: " + cartId);  // cartId 값이 null인지 확인
        System.out.println("Received prodId: " + prodId);  // prodId 값 확인
        System.out.println("Received optionId: " + optionId);  // optionId 값 확인

        // 세션에서 로그인된 회원 정보 가져오기
        HttpSession session = request.getSession();
        MemberVO loggedInMember = (MemberVO) session.getAttribute("loggedInMember");
        if (loggedInMember == null) {
            response.sendRedirect(request.getContextPath() + "/login.do");
            return;
        }

        String memId = loggedInMember.getMemId(); // 회원 ID 가져오기

        // ReviewVO 객체 생성
        ReviewVO review = new ReviewVO();
        review.setReview_content(content);
        review.setReview_star(star);
        review.setCart_id(cartId);  // 사용자가 선택한 cart_id 설정
        review.setProd_id(prodId);  // prod_id 설정
        review.setOption_id(optionId);  // 옵션 ID 설정
        review.setMem_id(memId);    // 세션에서 가져온 회원 ID 설정

        // 중복 체크
        IReviewService reviewService = ReviewServiceImpl.getInstance();
        boolean isDuplicate = reviewService.isReviewExistForCart(prodId, memId);
        if (isDuplicate) {
            response.setContentType("text/html; charset=UTF-8");
            
            // PrintWriter를 선언합니다.
            PrintWriter out = response.getWriter(); 
            
            // 자바스크립트 alert 메시지 출력
            out.println("<script>alert('이미 해당 카트에 대한 리뷰가 존재합니다.'); history.back();</script>");
            out.flush();
            return;
        }

        // 리뷰 삽입
        int result = reviewService.insertReview(review);

        if (result == 1) {
            // 리뷰 저장 후 이미지 처리
            String reviewId = review.getReview_id();
            String uploadPath = "//192.168.146.20/공유폴더/중프자료제출/5연근보유조/reviewimage";
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

                    ImageVO imageVO = new ImageVO();
                    imageVO.setTargetId(reviewId);  // 리뷰 ID 설정
                    imageVO.setTargetType("REVIEW");
                    imageVO.setImagePath(saveFileName);

                    imageList.add(imageVO);
                }
            }

            // 이미지 정보 DB 저장
            IImageService imageService = ImageServiceImpl.getInstance();
            for (ImageVO image : imageList) {
                imageService.insertImage(image);
            }

            response.sendRedirect(request.getContextPath() + "/reviewList.do");
        } else {
            System.out.println("리뷰 저장 실패");
        }
    }

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MemberVO loggedInMember = (MemberVO) session.getAttribute("loggedInMember");
        if (loggedInMember == null) {
            response.sendRedirect(request.getContextPath() + "/login.do");
            return;
        }

        String memId = loggedInMember.getMemId();  // 회원 ID

        IReviewService reviewService = ReviewServiceImpl.getInstance();
        List<ReviewVO> productInfoList = reviewService.getProductInfoForCart(memId);  // 상품 정보 및 옵션 정보 조회

        if (productInfoList == null || productInfoList.isEmpty()) {
            response.setContentType("text/html; charset=UTF-8");
            
            // PrintWriter를 선언합니다.
            PrintWriter out = response.getWriter(); 
            
            // 자바스크립트 alert 메시지 출력 후 뒤로가기
            out.println("<script>alert('결제된 장바구니가 없습니다.'); history.back();</script>");
            out.flush();
        } else {
            request.setAttribute("productInfoList", productInfoList);
            request.getRequestDispatcher("/WEB-INF/views/review/reviewInsertForm.jsp").forward(request, response);
        }

        request.getRequestDispatcher("/WEB-INF/views/review/reviewInsertForm.jsp").forward(request, response);
    }
}
