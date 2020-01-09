import javax.naming.AuthenticationException;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import di.RecordImpl;
import di.RecordViewImpl;
import net.madvirus.spring4.chap01.Project;

public class Ex02_02 {
	
	public static void main(String[] args) {
		
		// Ex02_02 main()		- spring을 통한 스프링 객체 생성 조립
		
		String resourceLocations= "record_applicationContext.xml";
				
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);		
		RecordViewImpl recordView = ctx.getBean("recordView",RecordViewImpl.class);
		
		recordView.input();
		recordView.print();
		
		System.out.println(" END ");
		
	}

}
