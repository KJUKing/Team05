package kr.or.ddit.report.service;

import java.util.List;

import kr.or.ddit.report.dao.IReportDao;
import kr.or.ddit.report.dao.ReportDaoImpl;
import kr.or.ddit.report.vo.ReportVO;

public class ReportServiceImpl implements IReportService {
	private static ReportServiceImpl service;
	
	private IReportDao dao;
	
	private ReportServiceImpl() {
		dao = ReportDaoImpl.getInstance();
	}
	public static ReportServiceImpl getInstance() {
		if (service ==null) service = new ReportServiceImpl();
		
		return service;
	}
	@Override
	public int reportInsert(ReportVO reVo) {
		return dao.reportInsert(reVo);
	}
	@Override
	public List<ReportVO> adminReportView() {
		return dao.adminReportView();
	}
	@Override
	public int adminUpdate(ReportVO reVo) {
		return dao.adminUpdate(reVo);
	}
	
	
}
