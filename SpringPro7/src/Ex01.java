import org.springframework.context.support.GenericXmlApplicationContext;

import di.RecordViewImpl;
import net.madvirus.spring4.chap02.User;

public class Ex01 {
	
	public static void main(String[] args) {
		// 문제 점) 프로젝트 -> 스프링 빈 객체 수백개 생성된다.
		// 스프링 빈 : 핸들러, 서비스, DAO, DTO
		// 스프링 빈 관리 (생성+조립)를 XML 설정 파일 or 자바 코드
		
		// 문제 해결)
		// 어노테이션 + 빈 객체 생성 자동으로 된다면 편리
		//자동으로 의존성 주입을 해주는 어노테이션 : @Autowired @Resource @inject ...
		
		// @Autowired @resource 을 사용하려면
		// <context:annotation-config /> 태그를 추가해야한다.
		
	
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("record_applicationContext.xml");
		
		// 2. Record_Config.java 
		RecordViewImpl view =  ctx.getBean("recordView",RecordViewImpl.class);
		view.input();
		view.print();
		
		System.out.println("END");
		
	}

}
