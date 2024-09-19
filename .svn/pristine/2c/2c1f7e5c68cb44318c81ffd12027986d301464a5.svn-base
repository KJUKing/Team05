package kr.or.ddit.likes.dao;

import kr.or.ddit.likes.vo.LikesVO;
import kr.or.ddit.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

public class LikesDaoImpl implements ILikesDao {

    private LikesDaoImpl() {

    }

    private static ILikesDao dao;

    public static ILikesDao getInstance() {
        if (dao == null) {
            dao = new LikesDaoImpl();
        }
        return dao;
    }


    @Override
    public boolean isProductLiked(String memId, String prodId) {
        SqlSession session = MybatisUtil.getSqlSession();
        Boolean isChecked = false;
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("memId", memId);
            paramMap.put("prodId", prodId);

            isChecked = session.selectOne("likes.isProductLiked", paramMap);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return isChecked != null ? isChecked : false;
    }

    @Override
    public int addToLikes(LikesVO likesVO) {
        int  cnt = 0;
        SqlSession session = MybatisUtil.getSqlSession();
        try {
            cnt = session.insert("likes.addToLikes", likesVO);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.commit();
            session.close();
        }
        return cnt;
    }

    @Override
    public int removeFromLikes(LikesVO likesVO) {
        int  cnt = 0;
        SqlSession session = MybatisUtil.getSqlSession();
        try {
            cnt = session.delete("likes.removeFromLikes", likesVO);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.commit();
            session.close();
        }
        return cnt;
    }
}
