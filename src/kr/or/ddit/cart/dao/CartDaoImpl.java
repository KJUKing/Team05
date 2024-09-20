package kr.or.ddit.cart.dao;

import kr.or.ddit.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

public class CartDaoImpl implements ICartDao {

    private CartDaoImpl() {

    }

    private static ICartDao dao;

    public static ICartDao getInstance() {
        if (dao == null) {
            dao = new CartDaoImpl();
        }
        return dao;
    }


    @Override
    public String insertCart(java.lang.String memId) {

        SqlSession session = MybatisUtil.getSqlSession();
        String cartId = "C" + System.currentTimeMillis(); // 유니크한 cart_id 생성 (시간 기반)
        try {
            // cartId와 memId를 매퍼에 전달하여 insert 실행
            Map<String, String> params = new HashMap<>();
            params.put("cartId", cartId);
            params.put("memId", memId);

            session.insert("cart.insertCart", params);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.commit();
            session.close();
        }
        return cartId;
    }

    @Override
    public int insertDetailCart(java.lang.String cartId, java.lang.String optionId, java.lang.String prodId, int quantity) {
        SqlSession session = MybatisUtil.getSqlSession();
        int result =0;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("cartId", cartId);
            params.put("optionId", optionId);
            params.put("prodId", prodId);
            params.put("quantity", quantity);

            result = session.insert("cart.insertDetailCart", params);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.commit();
            session.close();
        }
        return result;
    }

    @Override
    public double calculateTotalPrice(String cartId) {
        SqlSession session = MybatisUtil.getSqlSession();
        double totalPrice = 0.0;
        try {
            totalPrice = session.selectOne("cart.calculateTotalPrice", cartId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPrice;
    }

    @Override
    public int updateCartTotalPrice(String cartId, double totalPrice) {
        SqlSession session = MybatisUtil.getSqlSession();
        Map<String, Object> params = new HashMap<>();
        params.put("cartId", cartId);
        params.put("totalPrice", totalPrice);

        int result = 0;
        try {
            result = session.update("updateCartTotalPrice", params);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }
}
