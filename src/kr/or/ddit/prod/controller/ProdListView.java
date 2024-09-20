package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.image.service.IImageService;
import kr.or.ddit.image.service.ImageServiceImpl;
import kr.or.ddit.image.vo.ImageVO;
import kr.or.ddit.prod.service.IOptionService;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.OptionServiceImpl;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.prod.vo.OptionVO;
import kr.or.ddit.prod.vo.ProdVO;

@WebServlet("/prod/prodListView.do")
public class ProdListView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		String prodId = request.getParameter("prodId");
		
		IProdService service = ProdServiceImpl.getInstance();
		IOptionService opService = OptionServiceImpl.getInstance();
		IImageService imgService = ImageServiceImpl.getInstance();
		
		ProdVO prodVo = service.getOneProd(prodId);
		List<OptionVO> optionList = opService.getOneOption(prodId);
		prodVo.setOptionList(optionList);
		
		List<ImageVO> imgList = imgService.getImagesByTargetId(prodId, "PROD");
		prodVo.setImageList(imgList);
		
		System.out.println("prodID @@@@@= " + prodId);
		System.out.println("ProdListView.java@@@@@@" + prodVo);
//		System.out.println("optionListView.java@@@@@" + optionList);
		
		request.setAttribute("prodVo", prodVo);
//		request.setAttribute("optionList", optionList);
		
		request.getRequestDispatcher("/main?view=prodListView").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
