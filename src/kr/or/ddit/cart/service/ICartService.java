package kr.or.ddit.cart.service;

public interface ICartService {
    public String insertCart(String memId);

    public void insertDetailCart(String cartId, String optionId, String prodId, int quantity);

    public double calculateTotalPrice(String cartId);

    public int updateCartTotalPrice(String cartId, double totalPrice);
}
