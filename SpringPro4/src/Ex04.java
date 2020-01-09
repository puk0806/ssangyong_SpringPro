import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import di.RecordImpl;
import net.madvirus.spring4.chap02.Config;

public class Ex04 {
	
	public static void main(String[] args) {
		// p 95 자바코드 설정에 XML 파일 설정 조합하기
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		
		RecordImpl record =  ctx.getBean("record",RecordImpl.class);
		System.out.println(record);
		
		System.out.println("END");
		
		
	}

}
