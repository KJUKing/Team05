package kr.or.ddit.cart.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.cart.service.CartServiceImpl;
import kr.or.ddit.cart.service.ICartService;
import kr.or.ddit.cart.vo.CartVO;
import kr.or.ddit.image.service.IImageService;
import kr.or.ddit.image.service.ImageServiceImpl;
import kr.or.ddit.image.vo.ImageVO;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/cart/cartList.do")
public class CartListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // memId를 세션에서 가져옴
        HttpSession session = request.getSession();
        String memId = (String) session.getAttribute("memId");
        if (memId == null) {
            MemberVO loggedInMember = (MemberVO) session.getAttribute("loggedInMember");
            if (loggedInMember != null) {
                memId = loggedInMember.getMemId(); // loggedInMember에서 memId 가져오기
            }
        }

        if (memId != null) {
            ICartService service = CartServiceImpl.getInstance();
            List<CartVO> cartList = service.getCartListByMemId(memId);
            
            // 상품 ID에 따른 첫 번째 이미지를 저장할 Map
            Map<String, ImageVO> imagesMap = new HashMap<>();

            for (CartVO cartVO : cartList) {
                String prodId = cartVO.getProdId();  // CartVO에서 prodId를 가져옴
                IImageService imageService = ImageServiceImpl.getInstance();
                List<ImageVO> imageList = imageService.getImagesByTargetId(prodId, "PROD");

                // 각 prodId에 대해 첫 번째 이미지를 매핑
                if (!imageList.isEmpty()) {
                    imagesMap.put(prodId, imageList.get(0));
                }
            }

            // 이미지 데이터와 장바구니 데이터를 request에 저장
            request.setAttribute("imagesMap", imagesMap);
            request.setAttribute("cartList", cartList);

            // JSP로 포워딩
            request.getRequestDispatcher("/main?view=cartList").forward(request, response);
        } else {
            // memId가 없을 때 다른 페이지로 포워딩
            request.getRequestDispatcher("/main?view=").forward(request, response);
        }
    }
}
