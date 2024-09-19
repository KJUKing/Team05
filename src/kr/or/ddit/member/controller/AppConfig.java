package kr.or.ddit.member.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppConfig implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.setInitParameter("baseURL", "http://localhost/Team05");
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 필요한 경우 정리 작업 애플리케이션 요구사항 추가 자리
    }
}