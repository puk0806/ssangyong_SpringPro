import java.lang.reflect.Proxy;

import aop.Calculator;
import aop.CalculatorImpl;
import aop.LogPrintHandler;

public class Ex01 {
	
	public static void main(String[] args) {
		
		// 스프링 필수 요건 : DI + AOP
		// AOP (Aspect Oriented Programming) 
		// 		관점		지향적인	프로그래밍 기법
		// 관점 지향적	- 문제를 바라보는 기준으로 프로그래밍 기법
		// 			- 문제를 해결하기 위한 = 핵심 관심 사항(core concern) + 공통 관심 사항(cross-cutting concern)
		// 참고 ) JSP 필터  -> p204 Spring AOP (인증 권한)
		//			     -> p363 인터셉터 (인증 권한)
		
		// AOP : 보조 업무(공통업무)를 따로 등록 (설정 : 기능구현)
		
		// AOP 기법에서는 로직을 구현한 코드에서 공통 기능을 직접적으로 호출 하지 않는다.핵심 로직을 구현한 코드를 [컴파일]하거나,
		//		[로딩]하거나, [객체 생성]될 떄 AOP가 적용되어 [핵심 로직 구현 코드 안]으로 공통긴으이 [삽입]된다.
		
		// 세가지 Weaving 방식
		// - 컴파일 방식 Weaving 방식 - 컴파일 할 떄 합쳐서 컴파일
		// - 클래스 로딩 Weaving 방식 - 로딩할때 각 각 컴파일된 파일을 합침
		// - 런타임 시 Weaving 방식 	- 프록시 (Proxy==쁘락지) 를 생성 [[보조객체],[주 객체]]를 통해서 로직 구현
		// 						- 프록시 기반 == 런타임 시 위빙 방식 == 메서드 호출 방식 으로 AOP 적용할 수 있다.

		// AOP라이브러리 - 트랜잭션 적용, 보안( 인증+ 권한) 검사 
		
		// AOP용어  p 205 중요!!!
		// ㄱ.  Aspect 	
		//	- 보조 업무( 공통 기능 ) ex) 인증, 권한, 트랜잭션
		// 	- 주업무에 장착해야할 보조 업무
		// ㄴ. Advice
		//	- 언제 공통 관심 기능을 핵심 로직에 적용할 지를 정의한다.
		//	- 보조 업무가 주업 전, 전/후. 전후 등등 장착되는 시점을 Advice
		//	- Advice 종류
		//		- Before Advice
		//		- After Advice
		//		- After Throwing Advice
		//		- After Returning Advice
		//		- Around Advice
		// ㄷ .JoinPoint
		//	- Advice를 적용 가능한 지점
		//	- 메서드 호출, 필드 값 변경 등이 Joinpoint에 해당된다.
		// ㄹ. Pointcut
		//	- Joinpoint의 부분 집합
		//	- 실제로 Advice가 적용되는 Joinpoint를 나타낸다.
		// ㅁ. Weaving
		//	- Advice를 핵심 로직 코드에 적용하는 것
		
		
		// 스프링에서의 AOP
		// ㄱ. 스프링은 자체적으로 프록시 기반의 AOP 지원
		// ㄴ. 메서드 호출 Joinpoint 만을 지원
		// ㄷ. 필드 값 변경 : AOP 도구 사용하면 된다. (AspectJ)
		
		// 스프링 AOP의 또다른 특징은 자바 기반이다.
		// AspectJ : 스프링 AOP PointCut 표현식
		
		
		// 스프링 3가지 방식 AOP 구현 방식
		//	- XML 스키마 기반의 POJO 클래스를 이요한 AOP 구현 (p 209)
		//	- AspectJ에서 정의한 @Aspect 어노테이션 기반의 AOP 구현 (p 226)
		//	- 스프링 API를 이용한 AOP 구현
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// 예제
		// 1. aop 패키지 추가
		// 2. aopalliance 1.0.0 jar 의존 모듈 추가
		// 3. Advice - 메소드 호출하면 이 기능이 처리되는 시간을 로그로 기록하는 보조 업무를 코딩
		//			- AroundAdvice 작성( 전 / 후)
		//			- API org.aopalliance.intercept.MethodInterceptor 인터페이스 구현
		//					import java.lang.reflect.InvocationHandler
		// 4. 산술 연산을 하는 계산기
		//	Calculator 인터페이스 추가
		//	CalculatorImpl 클래스 추가
		//	LogPrintHandler 로그 기록할 보조 업무 클래스
		
		////////////////////////////////////////////////////////
		//Calculator cal = new CalculatorImpl();
		//System.out.println(cal.add(10, 20));
		
		Calculator target = new CalculatorImpl();	// 실제 주 업무를 하는 객체
		
		// 프록시 = 보조(LogPrintHandler) + 주 업무 (target)
		// 파라미터 : (실제 객체(target),메소드,보조 객체)
		Calculator proxy = (Calculator)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new LogPrintHandler(target));
		
		proxy.add(10, 20);
		proxy.sub(10, 20);
		
	}

}
