package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.image.service.IImageService;
import kr.or.ddit.image.service.ImageServiceImpl;
import kr.or.ddit.image.vo.ImageVO;
import kr.or.ddit.prod.service.IOprodService;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.OprodServiceImpl;
import kr.or.ddit.prod.service.ProdServiceImpl;

@WebServlet("/prod/prodDelete.do")
public class ProdDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		IProdService prodService = ProdServiceImpl.getInstance();
		IOprodService oprodService = OprodServiceImpl.getInstance();
		IImageService imageService = ImageServiceImpl.getInstance();

		String prodId = request.getParameter("prod_id");
		
		int deleteOprod = oprodService.deleteOprod(prodId);
		int deleteProd = prodService.deleteProd(prodId);
		boolean success = false;
		
		if(deleteOprod > 0) {
			System.out.println(" 삭제완 ");
		}
		System.out.println("deleteProd : " + deleteProd);
		System.out.println("deleteOProd : " + deleteOprod);
		
		if(deleteProd > 0) {
			// 이미지 삭제
			List<ImageVO> imageList = imageService.getImagesByTargetId(prodId, "PROD");
			for (ImageVO image : imageList) {
				// 1. 이미지 DB에서 삭제
				imageService.deleteImageForTargetId(prodId);
				
				// 2. 실제 파일 삭제 처리
//                String uploadPath = getServletContext().getRealPath(image.getImagePath()); // 파일 경로 얻기
                //String uploadPath = getServletContext().getRealPath(image.getImagePath()); // 파일 경로 얻기
                String uploadPath = "//192.168.146.20/공유폴더/중프자료제출/5연근보유조/prodimage";
                File file = new File(uploadPath, image.getImagePath());
                if (file.exists()) {
                    boolean isDeleted = file.delete(); // 파일 삭제 시도
                    if (!isDeleted) {
                        // 파일 삭제 실패 시 로그 출력
                        System.out.println("Failed to delete file: " + file.getPath());
                    }else {
                    	success = true;
                    }
                } else {
                    // 파일이 존재하지 않을 경우 로그 출력
                    System.out.println("File not found: " + file.getPath());
                }
			}
			
			
		}
		
		
		System.out.println("삭제할 아이디 : " + prodId);
		String jsonStr = "{\"success\": " + success + "}";
		System.out.println("==> " + jsonStr);
		response.getWriter().println(jsonStr);
		//response.sendRedirect(request.getContextPath() + "/prod/prodList.do");
	}

}
