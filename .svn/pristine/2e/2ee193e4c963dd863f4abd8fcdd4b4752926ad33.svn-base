package kr.or.ddit.prod.controller;

import kr.or.ddit.image.service.IImageService;
import kr.or.ddit.image.service.ImageServiceImpl;
import kr.or.ddit.image.vo.ImageVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.prod.vo.ProdVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/prod/wishList.do")
public class ProdToWish extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 세션에서 로그인한 회원 정보 가져오기
        HttpSession session = request.getSession();
        MemberVO loggedInMember = (MemberVO) session.getAttribute("loggedInMember");

        if (loggedInMember != null) {
            // 세션에서 memId 추출
            String memId = loggedInMember.getMemId();
            
            // 서비스 객체 생성 호출
            IProdService prodService = ProdServiceImpl.getInstance();
            IImageService imageService = ImageServiceImpl.getInstance(); // 이미지 서비스 추가

            // 위시리스트 정보 불러오기
            List<ProdVO> wishList = prodService.getWishListByMemberId(memId);
            
            // 각 상품에 대해 첫 번째 이미지를 설정하여 ImageVO 리스트로 저장
            List<ImageVO> firstImageList = new ArrayList<>(); // 첫 번째 이미지를 저장할 리스트

            for (ProdVO prod : wishList) {
                // 이미지 리스트 가져오기
                List<ImageVO> imageList = imageService.getImagesByTargetId(prod.getProd_id(), "PROD");

                // 첫 번째 이미지를 리스트에 추가 (존재할 경우)
                if (imageList != null && !imageList.isEmpty()) {
                    firstImageList.add(imageList.get(0)); // 첫 번째 이미지만 추가
                } else {
                    // 이미지가 없을 경우 기본 이미지를 추가 (예: No Image Available 이미지)
                    ImageVO noImage = new ImageVO();
                    noImage.setImagePath("/images/no_image_available.png");
                    firstImageList.add(noImage);
                }
            }

            // request에 결과값 저장
            request.setAttribute("wishList", wishList);
            request.setAttribute("firstImageList", firstImageList); // 첫 번째 이미지만 저장된 리스트 추가
            request.getRequestDispatcher("/main?view=wishList").forward(request, response);
        } else {
            // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
            response.sendRedirect(request.getContextPath() + "/member/login.do");
        }
    }
}
