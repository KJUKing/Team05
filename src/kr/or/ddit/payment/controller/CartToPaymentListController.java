package kr.or.ddit.payment.controller;

import kr.or.ddit.image.service.IImageService;
import kr.or.ddit.image.service.ImageServiceImpl;
import kr.or.ddit.image.vo.ImageVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.payment.service.IPaymentService;
import kr.or.ddit.payment.service.PaymentServiceImpl;
import kr.or.ddit.payment.vo.PaymentVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/cart/CartToPaymentList.do")
public class CartToPaymentListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //memId갖고오는거임
        HttpSession session = request.getSession();
        String memId = (String) session.getAttribute("memId");
        if (memId == null) {
            MemberVO loggedInMember = (MemberVO) session.getAttribute("loggedInMember");
            if (loggedInMember != null) {
                memId = loggedInMember.getMemId(); // loggedInMember에서 memId 가져오기
            }
        }

        IPaymentService service = PaymentServiceImpl.getInstance();
        // action 값 확인
        String action = request.getParameter("action");

        List<PaymentVO> payList;

        if ("buyAll".equals(action)) {
            // memId와 CART 상태인 장바구니 항목 전부 불러오기
            payList = service.paymentListForCart(memId, "CART"); // CART 상태의 모든 항목 불러오기

        } else {
            String cartId = request.getParameter("cartId");
            System.out.println("cartId = " + cartId);

            payList = service.paymentList(memId, cartId);
        }

        IImageService imageService = ImageServiceImpl.getInstance();

        Map<String, ImageVO> imagesMap = new HashMap<>();

        for (PaymentVO payment : payList) {
            String prodId = payment.getProd_id();
            List<ImageVO> imageList = imageService.getImagesByTargetId(prodId, "PROD");

            if (!imageList.isEmpty()) {
                imagesMap.put(prodId, imageList.get(0)); // 첫번째 이미지 매핑
            }
        }

        //이미지데이터 결제데이터 request에 저장
        request.setAttribute("payList", payList);
        request.setAttribute("imagesMap", imagesMap);

        // 공통항목
        List<PaymentVO> couponList = service.couponList(memId);
        request.setAttribute("couponList", couponList);

        List<PaymentVO> cardList = service.cardList();
        request.setAttribute("cardList", cardList);

        request.getRequestDispatcher("/main?view=paymentList").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
