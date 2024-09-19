package kr.or.ddit.member.service;

import java.util.Properties;
import org.apache.log4j.Logger;
import javax.mail.*;
import javax.mail.internet.*;

// 이메일 발송 서비스 클래스
public class EmailService {
    // 로깅을 위한 Logger 인스턴스
    private static final Logger logger = Logger.getLogger(EmailService.class);

    /**
     * 이메일을 발송한다.
     * @param emailProvider 이메일 제공자 (예: gmail, naver)
     * @param toEmail 수신자 이메일 주소
     * @param subject 이메일 제목
     * @param body 이메일 본문
     */
    public void sendEmail(String emailProvider, String toEmail, String subject, String body) {
        // 이메일 제공자에 따른 메일 서버 속성 설정
    	Properties props = getMailServerProperties(emailProvider);

        // 환경 변수에서 이메일 계정 정보 가져오기
        final String username = System.getenv("EMAIL_USERNAME");
        final String password = System.getenv("EMAIL_PASSWORD");

        // 이메일 계정 정보가 없으면 예외 발생
        if (username == null || password == null) {
            logger.error("이메일 인증 정보가 설정되지 않았습니다. 환경 변수를 확인해주세요.");
            throw new RuntimeException("이메일 설정 오류");
        }

        // 이메일 세션 생성
        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        
// 디버그 모드 설정
  session.setDebug(true);

        try {
            // 메시지 생성 및 설정
        	 Message message = new MimeMessage(session);
             message.setFrom(new InternetAddress(username));
             message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
             message.setSubject(subject);
             
             // HTML 형식의 이메일 본문 설정
             message.setContent(body, "text/html; charset=utf-8");

            // 이메일 발송
            Transport.send(message);

            logger.info("이메일이 성공적으로 발송되었습니다: " + toEmail);
        } catch (MessagingException e) {
            logger.error("이메일 전송 중 오류 발생: " + e.getMessage(), e);
            throw new RuntimeException("이메일 전송 실패", e);
        }
    }

    /**
     * 이메일 제공자에 따른 메일 서버 속성을 반환한다.
     * @param emailProvider 이메일 제공자 (예: gmail, naver)
     * @return 메일 서버 속성
     */
    private Properties getMailServerProperties(String emailProvider) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // 이메일 제공자에 따른 SMTP 서버 설정
        switch (emailProvider.toLowerCase()) {
            case "gmail":
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");
                break;
            case "naver":
                props.put("mail.smtp.host", "smtp.naver.com");
                props.put("mail.smtp.port", "587");
                break;
            case "yahoo":
                props.put("mail.smtp.host", "smtp.mail.yahoo.com");
                props.put("mail.smtp.port", "587");
                break;
            default:
                logger.error("지원하지 않는 이메일 서비스 제공자입니다: " + emailProvider);
                throw new IllegalArgumentException("지원하지 않는 이메일 서비스 제공자입니다: " + emailProvider);
        }

        return props;
    }
}