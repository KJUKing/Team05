package kr.or.ddit.image.dao;

import kr.or.ddit.image.vo.ImageVO;

import java.util.List;

public interface IImageDao {

    public int insertImage(ImageVO image);

    public List<ImageVO> getImagesByTargetId(String targetId, String targetType);

    public void deleteImage(int imageId);
    
    public ImageVO getImagesByImageId(int imageId);
    
    public void deleteImageForTargetId(String targetId);
}
