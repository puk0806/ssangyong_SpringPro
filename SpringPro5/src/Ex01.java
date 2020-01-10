import org.springframework.context.support.GenericXmlApplicationContext;

import net.madvirus.spring4.chap02.erp.ErpClient;
import net.madvirus.spring4.chap02.erp.ErpClientFactory;

public class Ex01 {
	
	public static void main(String[] args) {
		/*
		p96 팩토리(Factory) 방식의 스프링 빈 설정
		자바 클래스 중에 Factory(공장) 이름 있는 클래스
		
		추상클래스 Calendar.getInstance() static 메서드
		스프링 빈 객체 생성하는 방법
		아주 복잡하게 객체가 초기화 되어져서 생성된다면 스프링 빈 어떻게 처리?
		ex)
		public abstract class ErpClientFactory{
		public static ErpClientFactory instance(){}
		-- Spring객체 생성
		방법 1 : 
		<bean id = "erp" class="ErpClientFactory"
			factory-method="instace"			// static 메서드 사용
		>	
			<constructor-arg>파라미터값</constructor-arg>		// 생성자의 파라미터 X static 메서드의 파라미터 :O
		</bean>
		
		*/
		
		GenericXmlApplicationContext ctx = 
				new GenericXmlApplicationContext("classpath:config-erp.xml");		//classpath: (bin폴더 안에 있는 폴더를 찾음),(없으면 src안에 있는 폴더를 찾음)
		ErpClientFactory factory = ctx.getBean("factory", ErpClientFactory.class);
		ErpClient client = factory.create();
		client.connect();
		client.close();
		ctx.close();
		
	}

}
