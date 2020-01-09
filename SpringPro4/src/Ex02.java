import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import di.RecordViewImpl;
import di.Record_Config;
import net.madvirus.spring4.chap02.User;

public class Ex02 {

	public static void main(String[] args) {
		
		// p92 import 어노테이션 이용한 조합
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Record_Config.class);
		
		// 1. Config.java
		User user1 = ctx.getBean("user1",User.class);
		System.out.println(user1.getId());
		
		
		// 2. Record_Config.java 
		RecordViewImpl view =  ctx.getBean("recordView",RecordViewImpl.class);
		view.input();
		view.print();
		
		System.out.println("END");
		
	}
	
}
