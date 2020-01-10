import org.springframework.context.support.GenericXmlApplicationContext;

import net.madvirus.spring4.chap02.User;

public class Ex03 {

		public static void main(String[] args) {
			// p93 XML 설정과 자바 코드 설정 함께 사용 하기
			// Config.java + record_applicationContext.xml조합
			// 1. record_applicationContext.xml 에     <context:annotation-confin/>    추가
			// 2. xml 파일에 Config.java  improt 설정하기
			
			// record_applicationContext.XML 맨위에 주석 풀어서 실행하기
			GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("record_applicationContext.xml");
			
			User user = ctx.getBean("user1",User.class);
			System.out.println(user.getId());
			
			System.out.println("END");
			
		}
		
}
