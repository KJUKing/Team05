package kr.or.ddit.cart.vo;

import java.io.Serializable;

public class CartVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cartId;    // CART_ID (VARCHAR2)
    private String memId;     // MEM_ID (VARCHAR2)
    private double cartPrice; // CART_PRICE (NUMBER)

    public CartVO() {
    }

    public CartVO(String cartId, String memId, double cartPrice) {
        this.cartId = cartId;
        this.memId = memId;
        this.cartPrice = cartPrice;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public double getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(double cartPrice) {
        this.cartPrice = cartPrice;
    }

}
