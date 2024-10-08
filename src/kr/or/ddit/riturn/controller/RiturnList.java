package kr.or.ddit.riturn.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.cart.vo.CartVO;
import kr.or.ddit.image.service.IImageService;
import kr.or.ddit.image.service.ImageServiceImpl;
import kr.or.ddit.image.vo.ImageVO;
import kr.or.ddit.riturn.service.IRiturnService;
import kr.or.ddit.riturn.service.RiturnServiceImpl;
import kr.or.ddit.riturn.vo.RiturnVO;


@WebServlet("/riturn/riturnList.do")
public class RiturnList extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		IRiturnService service = RiturnServiceImpl.getInstance();
		
		List<RiturnVO> riList = service.riturnList2();
		
		Map<String, ImageVO> imagesMap = new HashMap<>();

        for (RiturnVO riVO : riList) {
            String prodId = riVO.getProd_id();  
            IImageService imageService = ImageServiceImpl.getInstance();
            List<ImageVO> imageList = imageService.getImagesByTargetId(prodId, "PROD");

            // 각 prodId에 대해 첫 번째 이미지를 매핑
            if (!imageList.isEmpty()) {
                imagesMap.put(prodId, imageList.get(0));
            }
        }

        // 이미지 데이터와 장바구니 데이터를 request에 저장
        request.setAttribute("imagesMap", imagesMap);
		
		request.setAttribute("riList", riList);
		
		request.getRequestDispatcher("/main?view=adminReturn").forward(request, response);
	}

}
