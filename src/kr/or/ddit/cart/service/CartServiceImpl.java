package kr.or.ddit.cart.service;

import kr.or.ddit.cart.dao.CartDaoImpl;
import kr.or.ddit.cart.dao.ICartDao;

public class CartServiceImpl implements ICartService {

    private ICartDao dao;

    private CartServiceImpl() {
        dao = CartDaoImpl.getInstance();
    }

    private static ICartService service;

    public static ICartService getInstance() {
        if (service == null) {
            service = new CartServiceImpl();
        }
        return service;
    }

    @Override
    public String insertCart(String memId) {
        return dao.insertCart(memId);
    }

    @Override
    public int insertDetailCart(String cartId, String optionId, String prodId, int quantity) {

        dao.insertDetailCart(cartId, optionId, prodId, quantity);

        //바로 가격총합구해야함
        double totalPrice = dao.calculateTotalPrice(cartId);

        //총합 구한걸 업데이트하고 성공 실패여부 int값 반환
        return dao.updateCartTotalPrice(cartId, totalPrice);
    }
}
