package di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponetScan(basePackage="di")	// 스프링 4.0.4 버전 부터 가능함
public class Record_Config {
	
	@Bean
	public RecordImpl record() {
		return new RecordImpl();
	}
	
	@Bean
	public RecordViewImpl recordView() {
		return new RecordViewImpl(record());
	}
	
	
}
