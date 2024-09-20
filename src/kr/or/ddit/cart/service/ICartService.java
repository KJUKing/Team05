package kr.or.ddit.cart.service;

public interface ICartService {
    public String insertCart(String memId);

    public int insertDetailCart(String cartId, String optionId, String prodId, int quantity);
}
