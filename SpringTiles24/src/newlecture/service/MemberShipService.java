package newlecture.service;

import java.sql.SQLException;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import newlecture.vo.Notice;

public interface MemberShipService {

	// 트랜잭션 테스트 용도의 메서드
	@Transactional(propagation = Propagation.REQUIRED)		// 실제 구현체에 트랜잭션이 걸린다.
	public void insertAndPointUpOfMember(Notice notice, String id) throws ClassNotFoundException, SQLException;
	
}
