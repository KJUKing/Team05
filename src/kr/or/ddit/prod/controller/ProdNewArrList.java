package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.image.service.IImageService;
import kr.or.ddit.image.service.ImageServiceImpl;
import kr.or.ddit.image.vo.ImageVO;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.prod.vo.ProdVO;
import kr.or.ddit.util.PageVO;

@WebServlet("/prod/prodNewArrList.do")
public class ProdNewArrList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pageParam = request.getParameter("page");
		String stype = request.getParameter("stype");
		String sword = request.getParameter("sword");

		// page 파라미터가 없으면 기본값을 1로 설정
		int page = (pageParam == null || pageParam.trim().isEmpty()) ? 1 : Integer.parseInt(pageParam.trim());
		
		// 필터링에 사용될 stype, sword 값도 공백을 제거하여 처리
		stype = (stype == null || stype.trim().isEmpty()) ? null : stype.trim();
		sword = (sword == null || sword.trim().isEmpty()) ? null : sword.trim();
		
		//상품리스트 서비스 호출
		IProdService prodService = ProdServiceImpl.getInstance();
		IImageService imgService = ImageServiceImpl.getInstance();
		
		//페이지네이션 서비스 호출
		PageVO pageInfo = prodService.getPagination(page, stype, sword);
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("start", pageInfo.getStart());
		paramMap.put("end", pageInfo.getEnd());
		paramMap.put("stype", stype);
		paramMap.put("sword", sword);
		
		//이제 이걸 쓰면안되고
		List<ProdVO> prodList = prodService.selectForNewArr();
		
		// 상품리스트 페이지네이션 조회 이걸써야함
		//List<ProdVO> prodList = prodService.getPaginatedProdList(paramMap);

		for(ProdVO pvo : prodList) {
			List<ImageVO> imgList = imgService.getImagesByTargetId(pvo.getProd_id(), "PROD");
			pvo.setImageList(imgList);
		}
		
		request.setAttribute("prodList", prodList);
		
		request.getRequestDispatcher("/main?view=prodList").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
