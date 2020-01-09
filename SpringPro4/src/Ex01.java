import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import net.madvirus.spring4.chap02.Config;
import net.madvirus.spring4.chap02.User;
import net.madvirus.spring4.chap02.UserRepository;

public class Ex01 {
	
	public static void main(String[] args) {
		
		// p 86 자바코드를 이요한 DI설정
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		User user1 = ctx.getBean("user1",User.class);		
		System.out.println(user1.getId());
		
		User user2 = ctx.getBean("user2",User.class);		
		System.out.println(user2.getId());
		
		UserRepository userRepository = ctx.getBean("userRepository",UserRepository.class);
		System.out.println(userRepository.findUserById("madvirus"));
		
		
		
		System.out.println("END");
		
		
	}

}
