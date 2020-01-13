package aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

// Advice == 보조업무
// AroundAdvice : implements MethodInterceptor
@Component("logPrintAroundAdvice")
public class LogPrintAroundAdvice implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		// [AOP 보조 업무]
				// 로그로 처리 시간을 기록하는 보조 업무
				Log log = LogFactory.getLog(this.getClass());
				StopWatch sw = new StopWatch();
				sw.start();
				String methodName =  method.getMethod().getName();
				log.info(methodName+"() start");

				// 주 업무 
				
				// int result = (int)method.invoke(target, args);	// 파라미터 : (주 업무 처리하는실제 객체,메소드 파라미터들)
				int result = (int)method.proceed();

				log.info(methodName+"() stop");
				sw.stop();
				log.info("처리 시간 : " + sw.getTotalTimeMillis());

				return result;
	}
	
}
