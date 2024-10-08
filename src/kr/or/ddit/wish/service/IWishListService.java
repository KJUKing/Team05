package kr.or.ddit.wish.service;

import kr.or.ddit.wish.vo.WishListVO;

import java.util.List;

public interface IWishListService {

    public int addToWishList(WishListVO wishVo);

    public int removeFromWishList(WishListVO wishVo);

    public boolean isProductWishListed(String memId, String prodId);
    
    public List<WishListVO> imageWish(String memId);
}
