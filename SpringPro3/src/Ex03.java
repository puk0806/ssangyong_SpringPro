import org.springframework.context.support.GenericXmlApplicationContext;

import net.madvirus.spring4.chap02.User;

public class Ex03 {
	
	public static void main(String[] args) {
		// p 83 <import> 태그를 이요한 설정 파일 : 설정 파일이 여러개 흩어져 있는 경우 사용하는 태그 이다. 
		
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("sensor_config.xml");
		User user1 = ctx.getBean("user1",User.class);		// sensor_config에서 sensor를 임포트해서 사용 가능
		System.out.println(user1.getId());
		
		System.out.println("END");
		
		
	}

}
