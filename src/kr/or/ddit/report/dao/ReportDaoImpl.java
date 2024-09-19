package kr.or.ddit.report.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.express.vo.ExpressVO;
import kr.or.ddit.report.vo.ReportVO;
import kr.or.ddit.util.MybatisUtil;

public class ReportDaoImpl implements IReportDao {
	private static ReportDaoImpl dao;
	
	private  ReportDaoImpl() {}
	
	public static ReportDaoImpl getInstance() {
		if(dao==null) dao = new ReportDaoImpl();
		
		return dao;
	}

	@Override
	public int reportInsert(ReportVO reVo) {
		  int  cnt = 0;
	        SqlSession sql = null;

	        try {
	            sql = MybatisUtil.getSqlSession();

	            cnt = sql.insert("report.reportInsert", reVo);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally {
	            sql.commit();
	            sql.close();
	        }
	        //3 리턴
	        return cnt;
	
	}

	@Override
	public List<ReportVO> adminReportView() {
		SqlSession session = null;
		List<ReportVO> list = null;
		
		try {
			session = MybatisUtil.getSqlSession();
			
			list = session.selectList("report.adminReportView");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			
		}
		return list;
	}

	@Override
	public int adminUpdate(ReportVO reVo) {
		 int  cnt = 0;
	        SqlSession sql = null;

	        try {
	            sql = MybatisUtil.getSqlSession();

	            cnt = sql.update("report.adminUpdate", reVo);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally {
	            sql.commit();
	            sql.close();
	        }
	        //3 리턴
	        return cnt;
	
	}

	
	
}
