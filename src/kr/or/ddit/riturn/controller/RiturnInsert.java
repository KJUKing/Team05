package kr.or.ddit.riturn.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.riturn.service.IRiturnService;
import kr.or.ddit.riturn.service.RiturnServiceImpl;
import kr.or.ddit.riturn.vo.RiturnVO;


@WebServlet("/riturn/riturnInsert.do")
public class RiturnInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int reQty = Integer.parseInt(request.getParameter("re_qty"));  
        String reReason = request.getParameter("re_reason");
        String payId = request.getParameter("pay_id"); 
		String memId = request.getParameter("mem_id");
		int mileUsed = Integer.parseInt(request.getParameter("mile_used"));
		
		
		RiturnVO riturnVo = new RiturnVO();
		riturnVo.setRe_qty(reQty);
		riturnVo.setRe_reason(reReason);
		riturnVo.setPay_id(payId);
		
		IRiturnService service = RiturnServiceImpl.getInstance();
		int cnt = service.riturnInsert(riturnVo);

		int cnt2 = service.paymentUpdate(payId);
		
		RiturnVO riturnVo1 = new RiturnVO();
		riturnVo1.setMile_used(mileUsed);
		riturnVo1.setMem_id(memId);
		
		int cnt3 = service.memMileUpdate(riturnVo1);
		
		
	}

}
