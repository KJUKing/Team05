package kr.or.ddit.report.dao;

import java.util.List;

import kr.or.ddit.report.vo.ReportVO;

public interface IReportDao {
	 public int reportInsert(ReportVO reVo);
	 
	 public List<ReportVO>adminReportView();
	 
	 public int adminUpdate(ReportVO reVo);
}
