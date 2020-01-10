package net.madvirus.spring4.chap02;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthFailLogger failLogger;
	
	//@Resource(name = "userRepository")
	//@Resource(name = "authFailLogger")
	public AuthInfo authenticate(String id, String password) {
		User user = userRepository.findUserById(id);
		if(user == null) {
			throw new UserNotFoundException();
		}
		if(!user.matchPassword(password)) {
			failLogger.insertBadPw(id, password);
			throw new AuthException();
		}
		
		return new AuthInfo(user.getId());
	}
	
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void setFailLogger(AuthFailLogger failLogger) {
		this.failLogger = failLogger;
	}
}
