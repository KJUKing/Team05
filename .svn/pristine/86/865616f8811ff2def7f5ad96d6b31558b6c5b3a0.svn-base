package kr.or.ddit.image.service;

import kr.or.ddit.image.dao.IImageDao;
import kr.or.ddit.image.dao.ImageDaoImpl;
import kr.or.ddit.image.vo.ImageVO;

import java.util.Collections;
import java.util.List;


public class ImageServiceImpl implements IImageService {

    private IImageDao dao;  // DAO 객체를 사용

    // 싱글톤 패턴을 위한 private 생성자
    private ImageServiceImpl() {
        dao = ImageDaoImpl.getInstance();  // 싱글톤 DAO 호출
    }

    private static IImageService service;  // 싱글톤 인스턴스 보관

    // 싱글톤 인스턴스를 반환하는 메서드
    public static IImageService getInstance() {
        if (service == null) {
            service = new ImageServiceImpl();
        }
        return service;
    }

    // 이미지 데이터를 DB에 삽입하는 메서드
    @Override
    public int insertImage(ImageVO image) {
        return dao.insertImage(image);
    }

    @Override
    public List<ImageVO> getImagesByTargetId(String targetId, String targetType) {
        return dao.getImagesByTargetId(targetId, targetType);
    }

    @Override
    public void deleteImage(int imageId) {
        dao.deleteImage(imageId);
    }

	@Override
	public ImageVO getImagesByImageId(int imageId) {
		return dao.getImagesByImageId(imageId);
	}
}
