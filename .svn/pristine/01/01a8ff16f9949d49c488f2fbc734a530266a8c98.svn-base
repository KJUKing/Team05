package kr.or.ddit.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {
    // 비밀번호를 암호화하는 메서드
    public String encode(String rawPassword) {
        try {
            // SHA-256 해시 알고리즘을 사용하는 MessageDigest 객체 생성
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // 입력받은 비밀번호를 바이트 배열로 변환하여 해시 처리
            byte[] hash = md.digest(rawPassword.getBytes());
            // 해시된 바이트 배열을 16진수 문자열로 변환
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // 알고리즘이 존재하지 않을 경우 런타임 예외 발생
            throw new RuntimeException(e);
        }
    }

    // 입력된 비밀번호와 저장된 암호화된 비밀번호를 비교하는 메서드
    public boolean matches(String rawPassword, String encodedPassword) {
        // 입력된 비밀번호를 암호화하여 저장된 암호화 비밀번호와 비교
        return encode(rawPassword).equals(encodedPassword);
    }
}