package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.image.service.IImageService;
import kr.or.ddit.image.service.ImageServiceImpl;
import kr.or.ddit.image.vo.ImageVO;
import kr.or.ddit.prod.service.IMainCateService;
import kr.or.ddit.prod.service.IOprodService;
import kr.or.ddit.prod.service.IOptionService;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.MainCateServiceImpl;
import kr.or.ddit.prod.service.OprodServiceImpl;
import kr.or.ddit.prod.service.OptionServiceImpl;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.prod.vo.MainCateVO;
import kr.or.ddit.prod.vo.OprodVO;
import kr.or.ddit.prod.vo.OptionVO;
import kr.or.ddit.prod.vo.ProdVO;

@WebServlet("/prod/prodInsert.do")
@MultipartConfig // 파일 업로드 처리용 (상품 사진 업로드)
public class ProdInsert extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IMainCateService mcService = MainCateServiceImpl.getInstance();
        List<MainCateVO> mcList = mcService.getAllMainCate();
        request.setAttribute("mcList", mcList);
        request.getRequestDispatcher("/main?view=adminProdInsert").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        
        // 서비스 호출
        IProdService prodService = ProdServiceImpl.getInstance();
        IOptionService optionService = OptionServiceImpl.getInstance();
        IOprodService oprodService = OprodServiceImpl.getInstance();
        IImageService imageService = ImageServiceImpl.getInstance();

        // 파일 업로드 경로 설정
        //String uploadPath = getServletContext().getRealPath("/images/prod");
        String uploadPath = "//192.168.146.20/공유폴더/중프자료제출/5연근보유조/prodimage";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        List<ImageVO> imageList = new ArrayList<ImageVO>();

        // 상품 입력 정보 수집
        String prodName = request.getParameter("prod_name");
        String prodCont = request.getParameter("prod_content");
        int prodPrice = Integer.parseInt(request.getParameter("prod_price"));
        String prodYn = request.getParameter("prod_yn");
        int prodSale = Integer.parseInt(request.getParameter("prod_sale"));
        String deprod = request.getParameter("mainId");

        // ProdVO 객체 생성
        ProdVO pvo = new ProdVO();
        pvo.setProd_name(prodName);
        pvo.setProd_content(prodCont);
        pvo.setProd_price(prodPrice);
        pvo.setProd_yn(prodYn);
        pvo.setProd_sale(prodSale);
        pvo.setDcate_id(deprod);
        
        // Prod 삽입
        int aa = prodService.insertProd(pvo);
        System.out.println("aa=" + aa);
        // prod_id 여기있음
        String prodId = pvo.getProd_id(); // 삽입 후 prod_id 가져오기
        System.out.println("prodId=" + prodId);
        // 파일 업로드 처리
        for(Part part : request.getParts()) {
        	String originFileName = extractFilename(part);
        	if(!"".equals(originFileName)) {
        		
        		ImageVO imageVo = new ImageVO();
        		
        		imageVo.setTargetId(prodId);
        		imageVo.setTargetType("PROD");
        		
        		//String saveFileName = UUID.randomUUID().toString() + "_" + originFileName;
        		String saveFileName = prodId + "_" + originFileName;
        		imageVo.setImagePath(saveFileName);
        		
				try {
					part.write(uploadPath + File.separator + saveFileName);
				} catch (IOException e) {
					e.printStackTrace();
				}
        		
//				imageList.add(imageVo);
				imageService.insertImage(imageVo);
        	}
        	
        }
//        Part part = request.getPart("prod_image");
//        String fileName = extractFilename(part);
//        if (!"".equals(fileName)) {
//            try {
//                part.write(uploadPath + File.separator + fileName);
//                pvo.setProd_image(fileName);  // DB에 저장할 파일명을 VO 객체에 저장
//            } catch (Exception e) {
//                pvo.setProd_image(null);  // 오류가 발생하면 null로 설정
//            }
//        } else {
//            pvo.setProd_image(null);  // 파일명이 없으면 null로 설정
//        }

      
        
        
        String optionName = null;
        String optionValue = null;
        String oprodQty = null;

        // 옵션 정보 수집
        String[] optionNames = request.getParameterValues("optionName[]");
        String[] optionValues = request.getParameterValues("optionValue[]");
        String[] oprodQtys = request.getParameterValues("oprodQty[]");

        for (int i = 0; i < optionNames.length; i++) {
            optionName = optionNames[i];
            optionValue = optionValues[i];
            oprodQty = oprodQtys[i];
            // 데이터베이스에 저장하는 로직 추가
            // 예: optionService.insertOption(optionName, optionValue, optionQuantity);
            
            
            OptionVO optvo = new OptionVO();
            optvo.setOption_name(optionName);
            optvo.setOption_value(optionValue);
            // Option 삽입
            optionService.insertOption(optvo);
            String optionId = optvo.getOption_id(); // 삽입 후 option_id 가져오기
            System.out.println("optionId=" + optionId);
            
            // OptionVO 및 OprodVO 객체 생성
            OprodVO oprvo = new OprodVO();
            oprvo.setOprod_qty(Integer.parseInt(oprodQty));
            
            // Oprod 삽입
            oprvo.setProd_id(prodId);
            oprvo.setOption_id(optionId);
            oprodService.insertOprod(oprvo);
            
        }
        
        // 작업이 완료되면 List페이지로 이동
        response.sendRedirect(request.getContextPath() + "/prod/prodList.do");
    }

    private String extractFilename(Part part) {
        String fileName = "";
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                fileName = item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return fileName;
    }
}
