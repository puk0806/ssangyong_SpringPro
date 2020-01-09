package di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import net.madvirus.spring4.chap02.Config;

@Configuration
@Import(Config.class)
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
