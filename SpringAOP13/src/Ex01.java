import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import aop.Calculator;

public class Ex01 {

	public static void main(String[] args) {

		// P 209 @Aspect 어노테이션 기반 AOP 사용

		GenericApplicationContext ctx = new GenericXmlApplicationContext("config.xml");

		Calculator proxy = (Calculator) ctx.getBean("target");
		System.out.println(proxy.add(10, 20));

		System.out.println("END");

	}

}
