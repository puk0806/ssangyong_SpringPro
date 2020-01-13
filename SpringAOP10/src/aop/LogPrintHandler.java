package aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;

// 로그 기록하는 보조 업무 클래스
public class LogPrintHandler implements InvocationHandler {
	
	private Object target; 	// 실제 주 업무 담당하는 객체
	
	public LogPrintHandler() {
	}
	
	public LogPrintHandler(Object target) {
		this.target = target;
	}



	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable { // 파라미터 : (프록시,메소드,메소드 파라미터들)
		// [AOP 보조 업무]
		// 로그로 처리 시간을 기록하는 보조 업무
		Log log = LogFactory.getLog(this.getClass());
		StopWatch sw = new StopWatch();
		sw.start();
		String methodName =  method.getName();
		log.info(methodName+"() start");

		// 주 업무 
		
		int result = (int)method.invoke(target, args);	// 파라미터 : (주 업무 처리하는실제 객체,메소드 파라미터들)

		log.info(methodName+"() stop");
		sw.stop();
		log.info("처리 시간 : " + sw.getTotalTimeMillis());

		return result;
	}

}
