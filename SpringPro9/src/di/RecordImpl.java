package di;

import org.springframework.stereotype.Component;

@Component
public class RecordImpl implements Record{

	private int kor;
	private int eng;
	private int mat;
	
	public RecordImpl() {
	}
	
	public RecordImpl(int kor, int eng, int mat) {
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
	}


	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMat() {
		return mat;
	}

	public void setMat(int mat) {
		this.mat = mat;
	}

	@Override
	public int total() {
		return this.kor+this.eng+this.mat;
	}

	@Override
	public double avg() {
		return (double)this.total()/3;
	}

}
