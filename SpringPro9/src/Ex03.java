import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import di.RecordViewImpl;
import di.Record_Config;
import net.madvirus.spring4.chap02.User;

public class Ex03 {
	
	public static void main(String[] args) {
		
		// 컴포넌트 스캔 예제 + 자바코드(Record_Config.java)(spring버전 4.0.4 부터 가능) 

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Record_Config.class);
		

		RecordViewImpl view =  ctx.getBean("recordViewImpl",RecordViewImpl.class);
		view.input();
		view.print();
		
		System.out.println("END");		
		
		// p 118 @Component 어노테이션
		// 종류 - 컴포넌트 스캔을 하면
		// @Component		그외
		// @service			Service
		// @repository		DAO
		// controller		MV[C]
		
	}

}
