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
import javax.servlet.http.HttpSession;

import kr.or.ddit.prod.service.DeCateServiceImpl;
import kr.or.ddit.prod.service.IDeCateService;
import kr.or.ddit.prod.service.IMainCateService;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.MainCateServiceImpl;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.prod.vo.DeCateVO;
import kr.or.ddit.prod.vo.MainCateVO;
import kr.or.ddit.prod.vo.ProdVO;
import kr.or.ddit.util.PageVO;

@WebServlet("/prod/prodList.do")
public class ProdList extends HttpServlet {
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
		IMainCateService mcService = MainCateServiceImpl.getInstance();
		IDeCateService dcService = DeCateServiceImpl.getInstance();
		//페이지네이션 서비스 호출
		PageVO pageInfo = prodService.getPagination(page, stype, sword);

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("start", pageInfo.getStart());
		paramMap.put("end", pageInfo.getEnd());
		paramMap.put("stype", stype);
		paramMap.put("sword", sword);

		//HttpSession session = request.getSession();

//		이제 이걸 쓰면안되고
//		List<ProdVO> prodList = prodService.getAllProd();

		// 상품리스트 페이지네이션 조회 이걸써야함
		List<ProdVO> prodList = prodService.getPaginatedProdList(paramMap);

		List<MainCateVO> mcList = mcService.getAllMainCate();
		List<DeCateVO> dcList = dcService.getAllDeCate();
		System.out.println("dc===" + dcList);
		
		//세션처리해서 여러번 쓰게 해야함
		//dclist는 여기서 for문 돌려서 
		//if(mcList == val)(prodForm에서 가져온 change.value)
		//list에 저장해서 넣어줘야함
		//그리고 session에서 추가해서 보내야함
		
		/*
		 * session.setAttribute("prodList", prodList); session.setAttribute("mcList",
		 * mcList); session.setAttribute("dcList", dcList);
		 */
		
		request.setAttribute("prodList", prodList);
		request.setAttribute("mcList", mcList);
		request.setAttribute("dcList", dcList);
		request.setAttribute("pageInfo", pageInfo);
		
		request.getRequestDispatcher("/main?view=prodList").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
