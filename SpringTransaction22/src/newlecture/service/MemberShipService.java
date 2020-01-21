package newlecture.service;

import java.sql.SQLException;

import newlecture.vo.Notice;

public interface MemberShipService {

	// 트랜잭션 테스트 용도의 메서드 
		public void insertAndPointUpOfMember(Notice notice, String id) throws ClassNotFoundException, SQLException;
	
}
