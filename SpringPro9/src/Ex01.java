import org.springframework.context.support.GenericXmlApplicationContext;

import di.RecordViewImpl;

public class Ex01 {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("record_applicationContext.xml");

		RecordViewImpl view = ctx.getBean("recordViewImpl", RecordViewImpl.class);
		view.input();
		view.print();

		System.out.println("END");

	}

}
