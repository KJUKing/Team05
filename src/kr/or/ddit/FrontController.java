package kr.or.ddit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main")
public class FrontController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String viewName = request.getParameter("view");
        String page = "/index.jsp"; // 기본 페이지

        if (viewName != null) {
            switch (viewName) {
                case "index":
                    page = "/index.jsp";
                    break;
                case "myPage":
                    page = "/WEB-INF/views/member/myPage.jsp";
                    break;
                case "prodList":
                    page = "/WEB-INF/views/prod/prodList.jsp";
                    break;
                case "wishList":
                    page = "/WEB-INF/views/wish/wishList.jsp";
                    break;
                case "login":
                    page = "/WEB-INF/views/member/Login.jsp";
                    break;
                case "noticeList":
                    page = "/WEB-INF/views/notice/noticeList.jsp";
                    break;
                case "notiDetail":
                    page = "/WEB-INF/views/notice/noticeDetail.jsp";
                    break;
                case "insertNotice":
                    page = "/WEB-INF/views/notice/insertNotice.jsp";
                    break;
                case "updateNotice":
                    page = "/WEB-INF/views/notice/updateNotice.jsp";
                    break;
                case "review":
                    page = "/WEB-INF/views/review/reviewMain.jsp";
                    break;
                case "report":
                    page = "/WEB-INF/views/report/adminReportView.jsp";
                    break;
                case "prodListView":
                    page = "/WEB-INF/views/prod/prodListView.jsp";
                    break;


                // 필요한 페이지를 더 추가하면 됩니다.
            }
        }

        // 메인 레이아웃에 페이지 전달
        request.setAttribute("contentPage", page);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/mainLayout.jsp");
        dispatcher.forward(request, response);
    }
}
