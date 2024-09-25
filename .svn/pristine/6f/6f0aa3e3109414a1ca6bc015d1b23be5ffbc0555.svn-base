package kr.or.ddit.cart.vo;

import java.io.Serializable;
import java.util.Date;

public class CartVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cartId;    // CART_ID (VARCHAR2)
    private String memId;     // MEM_ID (VARCHAR2)
    private double cartPrice; // CART_PRICE (NUMBER)
    private String status;    // CART 상태 (VARCHAR2)
    private Date cartDate;  // Date 필드 추가

    // DETAIL_CART 필드 추가
    private String optionId;   // OPTION_ID (VARCHAR2)
    private String prodId;     // PROD_ID (VARCHAR2)
    private int dcartQty;      // DCART_QTY (NUMBER)

    private String prodName;   // 추가
    private String optionName; // 추가

    public double getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(double prodPrice) {
        this.prodPrice = prodPrice;
    }

    private double prodPrice;  // 추가

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public int getDcartQty() {
        return dcartQty;
    }

    public void setDcartQty(int dcartQty) {
        this.dcartQty = dcartQty;
    }

    public CartVO() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCartDate() {
        return cartDate;
    }

    public void setCartDate(Date cartDate) {
        this.cartDate = cartDate;
    }
}

