package kr.or.ddit.cart.dao;

import kr.or.ddit.cart.vo.CartVO;

import java.util.List;

public interface ICartDao {
    public String insertCart(String memId, String action);

    public void insertDetailCart(java.lang.String cartId, java.lang.String optionId, java.lang.String prodId, int quantity);

    public double calculateTotalPrice(String cartId);

    public int updateCartTotalPrice(String cartId, double totalPrice);

    public int updateCartStatus(String cartId, String status);

    public String getRecentPendingCart(String memId);

    public String getPendingCartByMemId(String memId);

    public int deletePendingCart(String memId);

    public String getCartIdByMemId(String memId, String cart);

    public List<CartVO> getCartListByMemId(String memId);
    
    public List<String> getProdId(String memId);
    
    public int removeCartItem(String cartId);
}
