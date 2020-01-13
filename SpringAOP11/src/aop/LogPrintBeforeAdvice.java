package aop;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

// before
@Component
public class LogPrintBeforeAdvice implements MethodBeforeAdvice{

	@Override
	// 파라미터 (target, method파라미터, 대상 객체)
	public void before(Method method, Object[] args, Object target) throws Throwable {
		String methodName = method.getName();
		Log log = LogFactory.getLog(this.getClass());
		log.info(">>>"+methodName+"() : BeforeAdvice 호출 " );
		
	}

}
