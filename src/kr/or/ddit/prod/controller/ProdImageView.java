package kr.or.ddit.prod.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.image.service.IImageService;
import kr.or.ddit.image.service.ImageServiceImpl;
import kr.or.ddit.image.vo.ImageVO;

@WebServlet("/images/prodImageView.do")
public class ProdImageView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 파일 번호 받기
		int img_id = Integer.parseInt(request.getParameter("imgId"));
		
		// DB에서 파일 정보 가져오기
		IImageService service = ImageServiceImpl.getInstance();
		ImageVO imgVo = service.getImagesByImageId(img_id);
		
		// 이미지 파일이 저장된 폴더 설정
		String uploadPath = getServletContext().getRealPath("/images/prod");
		
		File file = new File(uploadPath);
		
		// 저장될 폴더가 없으면 새로 생성한다.
		if(!file.exists()) {
			file.mkdirs();
		}
		
		File imageFile = new File(file, imgVo.getImagePath());
		
		if(imageFile.exists()) {
			// 서버에 저장된 파일을 읽어서 클라이언트로 전송하기
			BufferedInputStream bin = null;
			BufferedOutputStream bout = null;

			try {
				// 출력용 스트림 객체 생성 ==> Response 객체 이용
				bout = new BufferedOutputStream(response.getOutputStream());

				// 파일 입력용 스트림 객체 생성
				bin = new BufferedInputStream(new FileInputStream(imageFile));

				byte[] temp = new byte[1024];
				int len = 0;

				while ((len = bin.read(temp)) > 0) {
					bout.write(temp, 0, len);
				}

				bout.flush();

			} catch (Exception e) {
				System.out.println("입출력 오류 : " + e.getMessage());
			} finally {
				if (bin != null)
					try {
						bin.close();
					} catch (IOException e) {
					}
				if (bout != null)
					try {
						bout.close();
					} catch (IOException e) {
					}
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
