package net.madvirus.spring4.chap02;

public class PasswordChangeService {
	
	private UserRepository userrepository;
	
	public PasswordChangeService() {
	
	}

	public PasswordChangeService(UserRepository userrepository) {
		this.userrepository = userrepository;
	}
	
	public void changePassword(String userId, String oldPw, String newPw) {
		User user = userrepository.findUserById(userId);
		if(user== null) {
			throw new UserNotFoundException();
		}
		user.changePassword(oldPw, newPw);
	}

}
