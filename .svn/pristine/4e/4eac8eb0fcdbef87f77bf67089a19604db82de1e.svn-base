package kr.or.ddit.cart.dao;

import kr.or.ddit.cart.vo.CartVO;
import kr.or.ddit.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
    public String insertCart(String memId, String action) {
        SqlSession session = MybatisUtil.getSqlSession();
        String cartId = "C" + System.currentTimeMillis(); // 유니크한 cart_id 생성 (시간 기반)
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("cartId", cartId);
            params.put("memId", memId);
            params.put("action", action);
            // 디버그용 출력
            System.out.println("Parameters: " + params);

            session.insert("cart.insertCart", params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.commit();
            session.close();
        }
        return cartId;
    }

    @Override
    public int updateCartStatus(String cartId, String status) {
        SqlSession session = MybatisUtil.getSqlSession();
        Map<String, Object> params = new HashMap<>();
        params.put("cartId", cartId);
        params.put("cartStatus", status);

        int result = 0;
        try {
            result = session.update("cart.updateCartStatus", params);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public void insertDetailCart(java.lang.String cartId, java.lang.String optionId, java.lang.String prodId, int quantity) {
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


    @Override
    public String getRecentPendingCart(String memId) {
        SqlSession session = MybatisUtil.getSqlSession();
        String cartId = null;
        try {
            cartId = session.selectOne("cart.getRecentPendingCart", memId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return cartId;
    }

    @Override
    public String getPendingCartByMemId(String memId) {
        SqlSession session = MybatisUtil.getSqlSession();
        String pendingCartId = null;
        try {
            System.out.println("Checking for pending cart for memId: " + memId); // 로그 추가
            pendingCartId = session.selectOne("cart.getPendingCartByMemId", memId);
            System.out.println("Pending cart found: " + pendingCartId); // 로그 추가

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return pendingCartId;
    }

    @Override
    public int deletePendingCart(String memId) {
        SqlSession session = MybatisUtil.getSqlSession();
        int result = 0;
        try {
            result = session.delete("cart.deletePendingCart", memId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.commit();
            session.close();
        }
        return result;
    }

    @Override
    public String getCartIdByMemId(String memId, String cart) {
        SqlSession session = MybatisUtil.getSqlSession();
        String existingCartId = null;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("memId", memId);
            params.put("cart", cart);
            existingCartId = session.selectOne("cart.getCartIdByMemId", params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return existingCartId;
    }

    @Override
    public List<CartVO> getCartListByMemId(String memId) {
        SqlSession session = MybatisUtil.getSqlSession();
        List<CartVO> cartList = null;
        try {
            cartList = session.selectList("cart.getCartListByMemId", memId);
            if (cartList != null) {
                for (CartVO cart : cartList) {
                    System.out.println("CartVO 내용: " + cart.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return cartList;
    }


	@Override
	public List<String> getProdId(String memId) {
		SqlSession session = MybatisUtil.getSqlSession();
        List<String> cartList = null;
        try {
            cartList = session.selectList("cart.getProdId", memId);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return cartList;
	}


	@Override
	public int removeCartItem(String cartId) {
		SqlSession session = MybatisUtil.getSqlSession();
        int result = 0;
        try {
            result = session.delete("cart.removeCartItem", cartId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.commit();
            session.close();
        }
        return result;
	}
}
