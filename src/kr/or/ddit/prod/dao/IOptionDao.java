package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.prod.vo.OptionVO;

public interface IOptionDao {
	
	public int insertOption(OptionVO optvo);
	
	public int deleteOption(String optionId);
	
	public int updateOption(OptionVO optvo);
	
	public List<OptionVO> getAllOption();
	
	public List<OptionVO> getOneOption(String prodId);
}
