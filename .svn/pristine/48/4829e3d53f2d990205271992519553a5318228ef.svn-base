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
        String cartId = request.getParameter("cartId");  // 사용자가 선택한 장바구니 ID

        // 세션에서 로그인된 회원 정보 가져오기
        HttpSession session = request.getSession();
        MemberVO loggedInMember = (MemberVO) session.getAttribute("loggedInMember");
        if (loggedInMember == null) {
            response.sendRedirect(request.getContextPath() + "/login.do");
            return;
        }

        String memId = loggedInMember.getMemId(); // 회원 ID 가져오기

        // 상품 ID 조회 (DAO에서 가져옴)
        IReviewService reviewService = ReviewServiceImpl.getInstance();
        String prodId = reviewService.getProdId(cartId);  // cartId를 기반으로 상품 ID 조회
        if (prodId == null) {
            // 해당 장바구니에 결제 내역이 없는 경우 처리
            request.setAttribute("errorMessage", "해당 상품에 대한 구매 내역이 없습니다.");
            request.getRequestDispatcher("/WEB-INF/views/review/reviewInsertForm.jsp").forward(request, response);
            return;
        }

        // ReviewVO 객체 생성
        ReviewVO review = new ReviewVO();
        review.setReview_content(content);
        review.setReview_star(star);
        review.setCart_id(cartId);  // 사용자가 선택한 cart_id 설정
        review.setOption_id("");  // MyBatis 쿼리에서 자동으로 조회
        review.setProd_id(prodId.toString());  // prod_id 설정
        review.setMem_id(memId);  // 세션에서 가져온 회원 ID 설정

        // 중복 체크: CART_ID와 MEM_ID를 기반으로 이미 리뷰가 존재하는지 확인
        boolean isDuplicate = reviewService.isReviewExistForCart(cartId, memId);

        if (isDuplicate) {
            // 중복된 리뷰가 있을 경우 오류 메시지와 함께 페이지로 다시 이동
            request.setAttribute("errorMessage", "이미 해당 카트에 대한 리뷰가 존재합니다.");
            request.getRequestDispatcher("/WEB-INF/views/review/reviewInsertForm.jsp").forward(request, response);
        } else {
            // 리뷰 삽입
            int result = reviewService.insertReview(review);

            // 리뷰 저장 후 리뷰 ID 가져오기 (String 타입)
            String reviewId = review.getReview_id();

            // 이미지 파일 처리
            if (result == 1) {
                String uploadPath = getServletContext().getRealPath("/images/review");
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
                        imageVO.setTargetId(reviewId);  // String 타입의 리뷰 ID 설정
                        imageVO.setTargetType("REVIEW");
                        imageVO.setImagePath("/images/review/" + saveFileName);

                        imageList.add(imageVO);
                    }
                }

                // 이미지 정보 DB 저장
                IImageService imageService = ImageServiceImpl.getInstance();
                for (ImageVO image : imageList) {
                    imageService.insertImage(image);
                }

                response.sendRedirect(request.getContextPath() + "/review/reviewList.do");
            } else {
                System.out.println("리뷰 저장 실패");
            }
        }
    }

    // Part 객체에서 파일 이름 추출
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
        // 세션에서 로그인된 회원 정보 가져오기
        HttpSession session = request.getSession();
        MemberVO loggedInMember = (MemberVO) session.getAttribute("loggedInMember");
        if (loggedInMember == null) {
            response.sendRedirect(request.getContextPath() + "/login.do");
            return;
        }

        String memId = loggedInMember.getMemId(); // 회원 ID 가져오기

        // 결제 완료된 장바구니 목록을 조회 (DAO에서 조회)
        IReviewService reviewService = ReviewServiceImpl.getInstance();
        List<String> cartList = reviewService.getPaidCartIdsForMember(memId);  // 결제 완료된 장바구니 목록 가져옴

        if (cartList == null || cartList.isEmpty()) {
            request.setAttribute("errorMessage", "결제된 장바구니가 없습니다.");
        } else {
            request.setAttribute("cartList", cartList);  // JSP로 cartList 전달
        }

        // 리뷰 작성 폼을 보여주기 위한 GET 요청 처리
        request.getRequestDispatcher("/WEB-INF/views/review/reviewInsertForm.jsp").forward(request, response);
    }
}
