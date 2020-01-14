package aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// 보조 업무( 공통 기능 ) 클래스 - Advice 구현 클래스
@Component
// <aop:aspect id="traceAspect" ref="logPrintProfiler" >
@Aspect
public class LogPrintProfiler {
	
	// <aop:pointcut expression="execution(public * aop..*(*,*))" id="publicMethod"/>
	@Pointcut("execution(public * aop..*(*,*))")
	public void profilTarget() {
	}
		
	
	/* <aop:around method="trace" pointcut-ref="publicMethod" /> */
	// 리턴 자료형은 void 이어야 한다.
	@Around("profilTarget()")
	public Object trace(ProceedingJoinPoint joinPoint)throws Throwable{
		String signatureString = joinPoint.getSignature().toShortString();
		// target : joinPoint.getTarget();
		// args : joinPoint.getArgs();
		
		System.out.println(signatureString+"시작");
		long start = System.currentTimeMillis();
		try {
			Object result = joinPoint.proceed();
			return result;
		}finally {
			long finish = System.currentTimeMillis();
			System.out.println(signatureString+"종료");
			System.out.println(signatureString+"실행시간"+(finish-start)+"ms");
		}
		
	}
	
	public void before (JoinPoint joinPoint) {
		
		// 1. 호출 대상 객체(target) : joinPoint.getTarget()
		// 2. 호출되는 메서드 정보 (method) : joinPoint.getSignature()
		//										.getName() (메서드 이름 얻기)	.toLognString()(메서드이름, 리턴타입, 파라미터),toShortString()(축약) ...등
		// 3. 파라미터값 ( args ) : joinPoint.getArgs() 
		
		String name = joinPoint.getSignature().getName();
		System.out.println("before advice : "+name);
		
	}
	
	public void afterFinally(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		System.out.println("after advice : "+name);
	}
	
}
