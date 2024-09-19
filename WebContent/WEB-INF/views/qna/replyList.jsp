<%@page import="kr.or.ddit.qna.vo.QnaVO"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	// controller에서 저장한 값 꺼내기
	List<QnaVO> list = (List<QnaVO>)request.getAttribute("list");

	// gson 예쁘게 만들기
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	// gson 사용해서 list를 json으로 만들어주고, 그거를 스트링 객체에 담기
	String result = gson.toJson(list);
	
	// 보내주기
	out.print(result);
	out.flush();
	
%>