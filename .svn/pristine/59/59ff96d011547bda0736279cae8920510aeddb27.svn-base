package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

@WebServlet("/prod/prodUpdate.do")
@MultipartConfig // 파일 업로드 처리용
public class ProdUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IProdService prodService = ProdServiceImpl.getInstance();
        String prodId = request.getParameter("prod_id");

        // 기존 제품 정보 조회
        ProdVO pvo = prodService.getProd(prodId);
        request.setAttribute("prod", pvo);

        IMainCateService mcService = MainCateServiceImpl.getInstance();
        List<MainCateVO> mcList = mcService.getAllMainCate();
        request.setAttribute("mcList", mcList);
        request.getRequestDispatcher("/WEB-INF/views/prod/prodForm.jsp").forward(request, response);
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
        String uploadPath = "d:/d_other/uploadFiles";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 업데이트할 제품 정보 수집
        String prodId = request.getParameter("prod_id");
        String prodName = request.getParameter("prod_name");
        String prodCont = request.getParameter("prod_content");
        int prodPrice = Integer.parseInt(request.getParameter("prod_price"));
        String prodYn = request.getParameter("prod_yn");
        int prodSale = Integer.parseInt(request.getParameter("prod_sale"));
        String deprod = request.getParameter("mainId");

        // ProdVO 객체 생성 및 업데이트
        ProdVO pvo = new ProdVO();
        pvo.setProd_id(prodId);
        pvo.setProd_name(prodName);
        pvo.setProd_content(prodCont);
        pvo.setProd_price(prodPrice);
        pvo.setProd_yn(prodYn);
        pvo.setProd_sale(prodSale);
        pvo.setDcate_id(deprod);
        
        // 제품 업데이트
        int updateResult = prodService.updateProd(pvo);
        System.out.println("업데이트 결과=" + updateResult);

        // 기존 이미지 목록 조회
        List<ImageVO> existingImages = imageService.getImagesByTargetId(prodId, "PROD");
        List<String> newImagePaths = new ArrayList<>();

        // 파일 업로드 처리 (새로운 이미지를 추가)
        for (Part part : request.getParts()) {
            String originFileName = extractFilename(part);
            if (!"".equals(originFileName)) {
                String saveFileName = prodId + "_" + originFileName;
                part.write(uploadPath + File.separator + saveFileName);
                newImagePaths.add(saveFileName);

                // 새로운 이미지 인서트
                ImageVO newImageVo = new ImageVO();
                newImageVo.setTargetId(prodId);
                newImageVo.setTargetType("PROD");
                newImageVo.setImagePath(saveFileName);
                imageService.insertImage(newImageVo);
            }
        }

        // 기존 이미지와 비교하여 삭제할 이미지 처리
        for (ImageVO existingImage : existingImages) {
            if (!newImagePaths.contains(existingImage.getImagePath())) {
                // 새로운 이미지 목록에 없으므로 삭제
                imageService.deleteImage(existingImage.getImageId());
            }
        }

        // 옵션 처리
        String[] optionNames = request.getParameterValues("optionName[]");
        String[] optionValues = request.getParameterValues("optionValue[]");
        String[] oprodQtys = request.getParameterValues("oprodQty[]");

        for (int i = 0; i < optionNames.length; i++) {
            OptionVO optvo = new OptionVO();
            optvo.setOption_name(optionNames[i]);
            optvo.setOption_value(optionValues[i]);
            optionService.updateOption(optvo); // 옵션 업데이트 메서드 호출

            OprodVO oprvo = new OprodVO();
            oprvo.setOprod_qty(Integer.parseInt(oprodQtys[i]));
            oprvo.setProd_id(prodId);
            oprodService.updateOprod(oprvo); // Oprod 업데이트 메서드 호출
        }

        // 리스트 페이지로 리다이렉트
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
