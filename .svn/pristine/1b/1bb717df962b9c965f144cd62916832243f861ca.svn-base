package kr.or.ddit.likes.controller;

import kr.or.ddit.likes.service.ILikesService;
import kr.or.ddit.likes.service.LikesServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/likes/checkLikes.do")
public class CheckLikesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 세션에서 로그인된 사용자 정보 가져오기
        HttpSession session = request.getSession();
        MemberVO loggedInMember = (MemberVO) session.getAttribute("loggedInMember");

        boolean isLikesChecked = false; // 기본값: 좋아요가 눌리지 않음
        int likeCount = 0;  // 기본값: 좋아요 수 0

        // 로그인한 상태라면
        if (loggedInMember != null) {
            String memId = loggedInMember.getMemId(); // 세션에서 사용자 ID 가져오기

            // 클라이언트에서 전달된 prodId 파라미터 가져오기
            String prodId = request.getParameter("prodId");

            ILikesService service = LikesServiceImpl.getInstance();

            // 해당 상품에 대해 사용자가 좋아요를 눌렀는지 확인
            isLikesChecked = service.isProductLiked(memId, prodId);
        }

        // 상품의 총 좋아요 수 가져오기 (로그인 여부와 관계없이)
        String prodId = request.getParameter("prodId");
        ILikesService service = LikesServiceImpl.getInstance();
        likeCount = service.likesCount(prodId);

        // 결과 JSON으로 응답
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("{\"isLikesChecked\": " + isLikesChecked + ", \"likeCount\": " + likeCount + "}");
        out.flush();
    }
}
