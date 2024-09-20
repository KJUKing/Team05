package kr.or.ddit.cart.dao;

import kr.or.ddit.cart.vo.CartVO;

public interface ICartDao {
    public String insertCart(java.lang.String memId);

    public int insertDetailCart(java.lang.String cartId, java.lang.String optionId, java.lang.String prodId, int quantity);

    public double calculateTotalPrice(String cartId);

    public int updateCartTotalPrice(String cartId, double totalPrice);
}
