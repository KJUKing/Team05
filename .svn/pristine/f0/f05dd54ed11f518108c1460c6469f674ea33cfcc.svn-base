package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.prod.dao.IOptionDao;
import kr.or.ddit.prod.dao.OptionDaoImpl;
import kr.or.ddit.prod.vo.OptionVO;

public class OptionServiceImpl implements IOptionService {
	
	private static OptionServiceImpl service;
	
	private IOptionDao dao;
	
	private OptionServiceImpl() {
		dao = OptionDaoImpl.getInstance();
	}
	
	public static OptionServiceImpl getInstance() {
		if(service==null) service = new OptionServiceImpl();
		
		return service;
	}

	@Override
	public int insertOption(OptionVO optvo) {
		// TODO Auto-generated method stub
		return dao.insertOption(optvo);
	}

	@Override
	public int deleteOption(String optionId) {
		// TODO Auto-generated method stub
		return dao.deleteOption(optionId);
	}

	@Override
	public int updateOption(OptionVO optvo) {
		// TODO Auto-generated method stub
		return dao.updateOption(optvo);
	}

	@Override
	public List<OptionVO> getAllOption() {
		// TODO Auto-generated method stub
		return dao.getAllOption();
	}

	@Override
	public List<OptionVO> getOneOption(String prodId) {
		// TODO Auto-generated method stub
		return dao.getOneOption(prodId);
	}
	
	
	
}
