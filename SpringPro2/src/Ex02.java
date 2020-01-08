import di.RecordImpl;
import di.RecordViewImpl;

public class Ex02 {
	
	public static void main(String[] args) {
		
		// di 패키지
		// 1. Record 인터페이스 선언
		//		총점 반환하는 추상 메서드
		//		평균 반환하는 추상 메서드
		// 2. RecordImpl 클래스 선언 
		//		총점, 평균 반환 메서드 오버라이딩
		// 3. RecordView 인터페이스 선언
		// 		레코드(성적) 정보 입력하는 추상메서드
		// 		레코드(성적) 정보 출력하는 추상메서드
		// 4. RecordViewImple 클래스 선언
		//		성정 입력, 출력 메서드 오버라이딩
		// Ex02 main() 			- spirng없이 객체 생성조립 사용
		// Ex02_02 main()		- spring을 통한 스프링 객체 생성 조립
		
		// 1) 생성자 DI
		RecordImpl record = new RecordImpl();
		RecordViewImpl view = new RecordViewImpl(record);
		
		view.input();
		view.print();
		
	}

}
