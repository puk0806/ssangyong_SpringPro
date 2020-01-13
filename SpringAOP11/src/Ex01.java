import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import aop.Calculator;

public class Ex01 {
	
	public static void main(String[] args) {
		
		// 스프링 API 사용해서 AOP 처리
		// appricationConfig.xml 파일 추가 (빈 객체 생성, 조립)
		// AroundAdvice : 처리 시간을 로그 기록 보조 업무 구현
		//	- LobPrintAroundAdvice 클래스 추가
		// [BeforeAdvice 보조 업무 장착 : LogPrintBeforeAdvice 추가]
		// [AfterAdvice 보조 업무 장착 : LogPrintAfterAdvice 추가]
		
				
		GenericApplicationContext ctx = new GenericXmlApplicationContext("appricationConfig.xml");
		 //Calculator target = (Calculator)ctx.getBean("target");
		 //System.out.println(target.add(10, 20));
		Calculator proxy = (Calculator)ctx.getBean("proxy");
		System.out.println(proxy.add(10, 20));
		
		
		
		System.out.println("END");
		
	}

}
