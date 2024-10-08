package kr.or.ddit.member.service;

/**
 * 회원 관리 서비스
 * 
 * 이 클래스는 회원과 관련된 모든 비즈니스 로직을 처리합니다.
 * - 회원 등록, 조회, 수정, 삭제
 * - 로그인 및 인증
 * - 비밀번호 암호화 및 재설정
 * - 이메일 인증
 * - 관리자 로그인
 * - 정석진 2024-09-23- 
 */

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

//import org.apache.log4j.Logger;

import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.util.PasswordEncoder;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.AdminVO;

public class MemberService {
	// 로깅을 위한 Logger 인스턴스
	//private static final Logger logger = Logger.getLogger(MemberService.class);
	// MemberService의 싱글톤 인스턴스
	private static MemberService instance;
	// 데이터베이스 작업을 위한 MemberDao 인스턴스
	private MemberDao memberDao;
	// 비밀번호 암호화를 위한 PasswordEncoder 인스턴스
	private PasswordEncoder passwordEncoder;
	// 이메일 발송을 위한 EmailService 인스턴스
	private EmailService emailService;
	// 애플리케이션의 기본 URL
	private String baseURL;
	// 비밀번호 재설정 토큰을 저장하는 Map (회원 ID를 키로 사용)
	private static Map<String, TokenInfo> resetTokens = new ConcurrentHashMap<>();
	private static Map<String, TokenInfo> verificationTokens = new ConcurrentHashMap<>();
	// 이메일 인증 상태를 저장하는 Map
	private static Map<String, Boolean> emailVerificationStatus = new ConcurrentHashMap<>();

	// 비밀번호 재설정 토큰 정보를 저장하는 내부 클래스
	private static class TokenInfo {
		String token;
		LocalDateTime expiryTime;

		TokenInfo(String token) {
			this.token = token;
			this.expiryTime = LocalDateTime.now().plusMinutes(30); // 30분 유효
		}

		// 토큰의 유효성 검사
		boolean isValid() {
			return LocalDateTime.now().isBefore(expiryTime);
		}
	}

	// 생성자: 필요한 객체들을 초기화
	private MemberService(String baseURL) {
		this.memberDao = new MemberDao();
		this.passwordEncoder = new PasswordEncoder();
		this.emailService = new EmailService();
		this.baseURL = baseURL;
	}

	// 싱글톤 패턴: MemberService의 인스턴스를 반환
	public static synchronized MemberService getInstance(String baseURL) {
		if (instance == null) {
			instance = new MemberService(baseURL);
		}
		return instance;
	}

	// 로그인 기능: 아이디와 비밀번호 확인
	public boolean login(String memId, String memPass) {
		MemberVO member = memberDao.getMemberById(memId);
		if (member != null) {
			return passwordEncoder.matches(memPass, member.getMemPass());
		}
		return false;
	}

	// 회원 정보 조회: 아이디로 회원 정보 가져오기
	public MemberVO getMemberById(String memId) {
		return memberDao.getMemberById(memId);
	}

	// 회원 가입 기능
	public boolean registerMember(MemberVO member) {
		member.setMemPass(passwordEncoder.encode(member.getMemPass()));
		return memberDao.insertMember(member) > 0;
	}

	// 모든 회원 목록 조회
	public List<MemberVO> getAllMembers() {
		return memberDao.getAllMembers();
	}

	// 아이디 중복 확인
	public boolean isIdAvailable(String memId) {
		return memberDao.getMemberById(memId) == null;
	}

	// 이메일 확인
	public boolean isEmailAvailable(String email) {
		return memberDao.getMemberByEmail(email) == null;
	}

	// 이메일 인증 상태 설정
	public void setEmailVerified(String email, boolean verified) {
		emailVerificationStatus.put(email, verified);
	}

	// 이메일 인증 상태 확인
	public boolean isEmailVerified(String email) {
		return emailVerificationStatus.getOrDefault(email, false);
	}

	// 회원 정보 수정
	public boolean updateMember(MemberVO member) {
		MemberVO existingMember = memberDao.getMemberById(member.getMemId());
		if (!member.getMemPass().equals(existingMember.getMemPass())) {
			member.setMemPass(passwordEncoder.encode(member.getMemPass()));
		}
		return memberDao.updateMember(member) > 0;
	}

	// 회원 삭제
	public boolean deleteMember(String memId) {
		return memberDao.deleteMember(memId) > 0;
	}

	// 아이디 찾기: 이름, 생년월일, 전화번호로 아이디 찾기
	public String findIdByNameBirthdatePhone(String name, String birthdate, String phone) {
		return memberDao.findIdByNameBirthdatePhone(name, birthdate, phone);
	}

	// 비밀번호 재설정을 위한 사용자 확인
	public boolean isValidUserForPasswordReset(String id, String email) {
		return memberDao.isValidUserForPasswordReset(id, email);
	}

	// 비밀번호 재설정 이메일 발송
	public void sendPasswordResetEmail(String email, String emailProvider, String memId) {
		try {
			String resetToken = UUID.randomUUID().toString();
			resetTokens.put(memId, new TokenInfo(resetToken));
			//logger.debug("Generated token for " + memId + ": " + resetToken);

			String resetLink = baseURL + "/member/resetPassword.do?memId=" + memId + "&token=" + resetToken;
			String emailBody = "비밀번호 재설정을 위해 다음 링크에 접속하세요: " + resetLink;

			emailService.sendEmail(emailProvider, email, "비밀번호 재설정", emailBody);
			//logger.info("비밀번호 재설정 이메일이 발송되었습니다: " + email);
		} catch (RuntimeException e) {
			//logger.error("비밀번호 재설정 이메일 발송 실패: " + e.getMessage(), e);
			throw e;
		}
	}

	// 비밀번호 재설정 토큰 검증
	public boolean verifyResetToken(String memId, String token) {
		//logger.debug("Verifying token for " + memId + ". Received token: " + token);
		TokenInfo tokenInfo = resetTokens.get(memId);
		boolean isValid = tokenInfo != null && tokenInfo.token.equals(token) && tokenInfo.isValid();
		//logger.debug("Token verification result: " + isValid);
		return isValid;
	}

	// 비밀번호 재설정 실행
	public boolean resetPassword(String memId, String newPassword) {
		MemberVO member = memberDao.getMemberById(memId);
		if (member != null) {
			member.setMemPass(passwordEncoder.encode(newPassword));
			boolean result = memberDao.updateMember(member) > 0;
			if (result) {
				resetTokens.remove(memId); // 비밀번호 재설정 후 토큰 제거
				//logger.debug("Password reset successful for " + memId + ". Token removed.");
			}
			return result;
		}
		return false;
	}

	// 주어진 이메일에 대한 이메일 인증 토큰을 생성하고, 토큰 정보를 저장
	public String generateEmailVerificationToken(String email) {
		String token = UUID.randomUUID().toString();
		verificationTokens.put(email, new TokenInfo(token));
		return token;
	}

	// 인증 이메일을 생성된 인증 링크와 함께 전송
	public void sendVerificationEmail(String email, String token) {
		String verificationLink = baseURL + "/member/verifyEmail.do?email=" + email + "&token=" + token;
		String emailBody = "회원가입을 완료하려면 다음 링크를 클릭하세요: " + verificationLink;

		emailService.sendEmail(extractEmailProvider(email), email, "이메일 인증", emailBody);
	}

	// 주어진 이메일과 토큰을 확인하여 유효한지 검증, 유효하면 토큰 삭제
	public boolean verifyEmail(String email, String token) {
		TokenInfo tokenInfo = verificationTokens.get(email);
		if (tokenInfo != null && tokenInfo.token.equals(token) && tokenInfo.isValid()) {
			verificationTokens.remove(email);
			setEmailVerified(email, true); // 이메일 인증 상태를 true로 설정
			return true;
		}
		return false;
	}

	// 이메일 주소에서 이메일 제공자를 추출하는 메소드
	// 이메일에서 도메인 추출 (예: example@gmail.com -> gmail)
	private String extractEmailProvider(String email) {
		String domain = email.substring(email.indexOf("@") + 1);
		return domain.substring(0, domain.indexOf("."));
	}

	// 관리자 로그인을 처리하는 메소드
	public AdminVO loginAdmin(String adminId, String adminPass) {
		AdminVO admin = memberDao.getAdminById(adminId);
		System.out.println("Admin object: " + admin); // 디버깅용 로그

		if (admin != null) {
			System.out.println("Stored password: " + admin.getAdminPass());
			System.out.println("Input password: " + adminPass);

			if (admin.getAdminPass().equals(adminPass)) {
				System.out.println("Password match successful");
				return admin;
			} else {
				System.out.println("Password match failed");
			}
		} else {
			System.out.println("Admin not found for ID: " + adminId);
		}
		return null; // @return 로그인 성공 시 AdminVO 객체, 실패 시 null
	}
}