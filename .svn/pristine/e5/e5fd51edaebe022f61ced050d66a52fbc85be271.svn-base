package kr.or.ddit.util;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//MyBatis의 환경 설정 파일(mybatis-config.xml)을 읽어와서 그 내용을 처리한 후
//처리된 결과를 갖는 SqlSessionFactory객체를 생성하고, 
//SqlSession객체를 반환하는 메서드로 구성된 class 작성하기
public class MybatisUtil {
    private static SqlSessionFactory sqlSessionFactory;
    
    // 정적 초기화 블록: 클래스가 로드될 때 한 번만 실행됨
    static {
        try {
            // MyBatis 설정 파일 경로
            String resource = "kr/or/ddit/mybatis/config/mybatis-config.xml";
            // 설정 파일을 읽어오는 InputStream 생성
            InputStream inputStream = Resources.getResourceAsStream(resource);
            // SqlSessionFactory 객체 생성
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            // 초기화 중 예외 발생 시 스택 트레이스 출력
            e.printStackTrace();
        }
    }
    
 // SqlSession 객체를 얻는 메서드
    public static SqlSession getSqlSession() {
    	 if (sqlSessionFactory == null) {
             throw new RuntimeException("SqlSessionFactory is not initialized");
         }
         return sqlSessionFactory.openSession();
     }
 }
