package kr.or.ddit.wish.dao;

import kr.or.ddit.wish.vo.WishListVO;

import java.util.List;

public interface IWishListDao {


    public int addToWishList(WishListVO wishVo);

    public int removeFromWishList(WishListVO wishVo);

    public boolean isProductWishListed(String memId, String prodId);
}
