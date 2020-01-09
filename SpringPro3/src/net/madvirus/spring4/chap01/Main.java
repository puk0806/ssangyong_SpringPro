package net.madvirus.spring4.chap01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		
		// applicationContext.xml 설정파일
		
		// 스프링 컨테이너 == 공장 == 어플리케이션 컨텍스트 (공장) 생성해서 빈 객체를 얻어와서 사용
		// xml 파일 -> 어플리케이션 컨텍스트 생성 : GenericXmlApplicationContext(공장) 클래스
		
		String resourceLocations= "applicationContext.xml";
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		// 스프링 빈 객체 생성/ 조립 완료
		
		
		// Project sampleProject = (Project) ctx.getBean("sampleProject");
		Project sampleProject = ctx.getBean("sampleProject",Project.class);		// 
		
		
		sampleProject.build();
		
		System.out.println(" END ");
		
	}

}

