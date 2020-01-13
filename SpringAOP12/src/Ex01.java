import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import aop.Calculator;

public class Ex01 {
	
	public static void main(String[] args) {
		
		// P 209 XML 스키마 기반 AOP 퀵 스타트
		// 1. 의존 모듈 추가 - pom.xml = (spring-aop / org.aspectj)추가
		// 2. 공통기능(보조업무) 제공할 클래스 구현 (LogPrintProfiler 클래스)
		// 3. xml 설정 파일에 <aop:config /> 태그 AOP 설정
		
		GenericApplicationContext ctx = new GenericXmlApplicationContext("config.xml");
		
		Calculator proxy = (Calculator) ctx.getBean("target");
		System.out.println(proxy.add(10, 20));
		
		System.out.println("END");
				
	}

}
