import javax.annotation.Resource;

import org.springframework.context.support.GenericXmlApplicationContext;

import di.RecordViewImpl;
import net.madvirus.spring4.chap02.User;

public class Ex01 {
	
	public static void main(String[] args) {

		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("record_applicationContext.xml");
		
		// p 111
		// @Autowired : (생성자, 필드, setter) 사용가능, 자료형을 기준으로 주입, requered=false 입력하면 빈객체 안넣어주면 null로 들어감
		// @Resource : (필드, setter) 사용가능, 이름을 기준으로 주입
		// @Inject + @Name :(필드 메서드 생성자) 사용가능, 스프링 4.0.4 버전 사용가능, 반드시 주입 할 빈 객체 존재해야함
		
		
		
		RecordViewImpl view =  ctx.getBean("recordView",RecordViewImpl.class);
		view.input();
		view.print();
		
		System.out.println("END");
		
	}

}
