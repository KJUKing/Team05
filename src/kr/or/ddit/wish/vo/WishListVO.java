package kr.or.ddit.wish.vo;

import java.util.Date;

public class WishListVO {
    private String wish_id; //위시리스트 id 기본키
    private Date wish_date; //위시리스트 등록날짜
    private String prod_id; //상품테이블의 id 외래키
    private String mem_id;  //회원테이블의 id 외래키

    public String getWish_id() {
        return wish_id;
    }

    public void setWish_id(String wish_id) {
        this.wish_id = wish_id;
    }

    public Date getWish_date() {
        return wish_date;
    }

    public void setWish_date(Date wish_date) {
        this.wish_date = wish_date;
    }

    public String getProd_id() {
        return prod_id;
    }

    public void setProd_id(String prod_id) {
        this.prod_id = prod_id;
    }

    public String getMem_id() {
        return mem_id;
    }

    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }
}
