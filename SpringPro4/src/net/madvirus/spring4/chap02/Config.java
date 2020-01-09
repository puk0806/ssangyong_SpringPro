package net.madvirus.spring4.chap02;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

// config.xml 대신 사용할 java 코드 (설정 파일)
// 나열 할 때  : @ImportResource({"a.xml","b.xml","c.xml"})
@Configuration
@ImportResource("record_applicationContext.xml")
public class Config {
	
	// 빈객체를 생성
	@Bean
	public User user1() {
		return new User("bkchoi", "1234");
	}

	@Bean(name = "user2")
	public User user() {
		return new User("madvirus", "1234");
	}
	
	@Bean
	public AuthFailLogger authFailLogger() {
		AuthFailLogger logger = new AuthFailLogger();
		logger.setThreshold(5);
		return logger;
	}
	
	@Bean
	public UserRepository userRepository() {
		UserRepository userRepository = new UserRepository();
		List<User> users = new ArrayList<User>();
		users.add(user1());
		users.add(user());
		
		userRepository.setUsers(users);
		
		return userRepository;
	}
	
	@Bean
	public PasswordChangeService pwChangeSvc() {
		return new PasswordChangeService(userRepository());
	}
	
	@Bean
	public AuthenticationService authenticationService() {
		AuthenticationService authSvc = new AuthenticationService();
		authSvc.setFailLogger(authFailLogger());
		authSvc.setUserRepository(userRepository());
		return authSvc;
	}
	
	
	
	
}
