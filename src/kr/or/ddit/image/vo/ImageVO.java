package kr.or.ddit.image.vo;

import java.util.Date;

public class ImageVO {
    private int imageId;          // 이미지 고유 ID
    private String targetId;      // 공지사항, QnA, 리뷰, 상품 등의 고유 ID
    private String targetType;    // 테이블 종류 (NOTICE, QNA, REVIEW, PRODUCT 등)
    private String imagePath;     // 이미지 파일 경로
    private Date uploadDate;      // 업로드 날짜

    // Getters and Setters
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
}
