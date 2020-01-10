import org.springframework.context.support.GenericXmlApplicationContext;

import net.madvirus.spring4.chap02.search.SearchClientFactory;

public class Ex01 {
	
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = 
				new GenericXmlApplicationContext("classpath:config-search.xml");
		SearchClientFactory factory = ctx.getBean("searchClientFactory", SearchClientFactory.class);
		System.out.println(factory);
		SearchClientFactory factory2 = ctx.getBean("searchClientFactory", SearchClientFactory.class);
		System.out.println("same instance = " + (factory == factory2));
		ctx.close();
	}

}
