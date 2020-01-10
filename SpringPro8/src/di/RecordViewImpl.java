package di;

import java.util.Scanner;

import javax.annotation.Resource;

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

	// bean객체의 이름을 가지고 자동 주입 가능
	// Resource 필드,setter사용가능
	// 이름을 기준으로 주입
	@Resource(name = "record")
	//@Inject
	/* public void setRecord(@Name("r1") RecordImpl record) { */
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
