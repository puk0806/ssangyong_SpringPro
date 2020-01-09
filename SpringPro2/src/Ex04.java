import org.springframework.context.support.GenericXmlApplicationContext;

import net.madvirus.spring4.chap02.AuthFailLogger;
import net.madvirus.spring4.chap02.AuthenticationService;

public class Ex04 {

	public static void main(String[] args) {
		// p62 스프링 DI 설명 예제
		// net.madvirus.spring4.chap02 패키지
		// 1. User						유저 정보 클래스
		// 2. UserRepository			사용자 저장소 클래스
		// 3. AuthFailLogger			인증 실패 로그 클래스
		// 4. AuthenticationService		인증 처리 서비스 클래스
		// 5. PasswordChangeService		비밀 번호 변경 클래스
		
		
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("config.xml");
		AuthFailLogger logger = ctx.getBean("authFailLogger",AuthFailLogger.class);
		
		for(int i =0 ;i<=6; i++) {
			logger.insertBadPw("bkchoi", "1111");
		}
		
		AuthenticationService service = ctx.getBean("AuthenticationService",AuthenticationService.class);
		service.authenticate("admin", "1234");	// 인증 받는 메소드
		
		System.out.println(" END ");
		
	}
	
}
