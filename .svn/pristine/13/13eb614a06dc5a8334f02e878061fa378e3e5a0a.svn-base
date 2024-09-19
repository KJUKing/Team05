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

        //세션
        HttpSession session = request.getSession();
        MemberVO loggedInMember = (MemberVO) session.getAttribute("loggedInMember");

        boolean isLikesChecked = false; // 기본값은 false (좋아요 안누른상태)

        if (loggedInMember != null) {
            //세션 ID추출
            String memId = loggedInMember.getMemId();

            //클라이언트에서 전달된 prodId 파라미터 가져오기
            String prodId = request.getParameter("prodId");

            ILikesService service = LikesServiceImpl.getInstance();

            isLikesChecked = service.isProductLiked(memId, prodId);

            //결과 JSON으로 응답
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("{\"isLikesChecked\": " + isLikesChecked + "}");
            out.flush();
        }
    }
}
