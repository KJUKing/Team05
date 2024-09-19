package kr.or.ddit.report.service;

import java.util.List;

import kr.or.ddit.report.vo.ReportVO;

public interface IReportService {
	
	/**
	 * 신고 값 저장
	 * @param reVo
	 * @return
	 */
	 public int reportInsert(ReportVO reVo);
	 
	 /**
	  * 관리자의 신고 리뷰 조회
	  * @return
	  */
	 public List<ReportVO>adminReportView();
	 
	 /** 
	  * 관리자의 리뷰 신고 상태 변경 
	  * @param reVo
	  * @return
	  */
	 public int adminUpdate(ReportVO reVo);
}
