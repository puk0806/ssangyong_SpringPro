package di;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class RecordViewImpl implements RecordView{

	private RecordImpl record = null;
	
	public RecordViewImpl() {
	}
	
	public RecordViewImpl(Record record) {
		this.record = (RecordImpl) record;
	}

	public RecordImpl getRecord() {
		return record;
	}

	// p108 자동 주입 어노테이션 (값이 비어있어도 예외를 발생시키지 않고 null값 유지)
	// 생성자, 필드, setter 사용가능
	// 자료형을 기준으로 주입
	@Autowired(required = false)
	// public void setRecord(@Qualifier("r1") RecordImpl record) {			// 이렇게도 사용가능
	@Qualifier("r1")
	public void setRecord(RecordImpl record) {
		this.record = record;
	}


	@Override
	public void input() {
		
		try(Scanner scanner = new Scanner(System.in)){
			System.out.print("국 영 수 점수 입력하세요 : ");
			this.record.setKor(scanner.nextInt());
			this.record.setEng(scanner.nextInt());
			this.record.setMat(scanner.nextInt());
			System.out.println("입력 완료");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void print() {
		System.out.printf("국 : %d 영 : %d 수 : %d 합점 : %d 평균 : %.2f\n "
							,this.record.getKor(),this.record.getEng(),this.record.getMat()
							,this.record.total(),this.record.avg());
	}

}
