import org.springframework.context.support.GenericXmlApplicationContext;

import net.madvirus.spring4.chap02.AuthFailLogger;
import net.madvirus.spring4.chap02.AuthenticationService;

public class Ex02 {

	
	public static void main(String[] args) {
		// 컴포넌트 스캔 예제 + XML(config.java)
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("config.xml");
		AuthFailLogger logger = ctx.getBean("authFailLogger",AuthFailLogger.class);
		logger.setThreshold(5);
		
			logger.insertBadPw("bkchoi", "1111");
		
		AuthenticationService service = ctx.getBean("authenticationService",AuthenticationService.class);
		service.authenticate("admin", "1234");	// 인증 받는 메소드
		
		// userRepository 빈 객체 얻어와서 user1, user2 Map 추가
		
		System.out.println(" END ");
		
	}
}
