package kr.or.ddit.likes.service;

import kr.or.ddit.likes.dao.ILikesDao;
import kr.or.ddit.likes.dao.LikesDaoImpl;
import kr.or.ddit.likes.vo.LikesVO;

public class LikesServiceImpl implements ILikesService{

    private ILikesDao dao;

    private LikesServiceImpl() {
        dao = LikesDaoImpl.getInstance();
    }
    private static LikesServiceImpl service;

    public static LikesServiceImpl getInstance() {
        if (service == null) {
            service = new LikesServiceImpl();
        }
        return service;
    }


    @Override
    public boolean isProductLiked(String memId, String prodId) {
        return dao.isProductLiked(memId, prodId);
    }

    @Override
    public int addToLikes(LikesVO likesVO) {
        return dao.addToLikes(likesVO);
    }

    @Override
    public int removeFromLikes(LikesVO likesVO) {
        return dao.removeFromLikes(likesVO);
    }
}
