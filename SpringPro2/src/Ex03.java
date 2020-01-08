
public class Ex03 {
	
	public static void main(String[] args) {
		// p 47 
		// 1. DI(Dependency Injection) == 의존성 주입
		// 2. 스프링 필수 요건 + AOP

		// p 57 스프링은 객체를 생성하고 연결해주는 DI 컨테이너
		// DI컨테이너== 스프링 컨테이너== IOC 컨테이너
		
		// GenericXmlApplicationContext 클래스
		// 스프링 : .xml 객체 생성, 조립에 대한 설정파일-> 공장 (어플린 케이션 컨텍스트)
		
		// p 58 Spring bean(스프링 빈) 
		//	- 1. xml 설정파일로 부터 컨테이너가 생성할 객체를  <bean>태그 사용
		//	- 2. 스프링 컨테이너가 생성해서 보관하는 객체를 스프링 빈이라고 한다.
		// 	스프링 컨테이너  : 스프링 객체를 생성하고 연결해주는 DI 컨테이너
		
		// p 59 [스프링 컨테이너]의 종류
		// 1. Beanfactory 계열
		// 2. ApplicationContext 계열 (주로 사용)
		//		- GenericXmlApplicationContext : XML파일을 설정 정보로 사용하는 스프링 컨테이너
		//		- AnnotationConfigApplicationContext : 어노테이션을 사용하는 스프링 컨테이너
		//		- GenericGroovyApplicationContext
		//		- XmlWebApplicationContext - 웹 어플리케이션 개발 + XML설정파일
		//		- AnnotationConfigWebApplicationContext
		
		// p 61 스프링 DI 설정
		//	1. 스프링 DI 설정하는 3가지 방법
		//		ㄱ XML 설정파일 (p66~85)
		//		ㄴ 자바 코드 ( 어노테이션 ) (p85~)
		//		ㄷ 그루비 코드
		
		//	ㄱ. xml 설정파일
		//	1. <bean> 태그 이용
		//	2. <property> 태그나 <constructor-arg> 태그 이용
		//	3. ???.xml 파일이용
		//		ex)
		//		<beans> 최상위 루트 노드
		//			<bean>
		//				<property />
		//			</bean>
		//		</beans>
		//	4. p 67	<bean> 태그
		//		- 생성할 객체 지정하는 태그
		//		- id 속성 : 고유 이름 지정
		//		- class 속성 : 패키지 이름을 포함한 완전한 클래스 이름
		
		
		
		
		
		
		
	}

}
