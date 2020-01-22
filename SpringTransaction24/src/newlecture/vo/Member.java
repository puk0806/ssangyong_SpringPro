package newlecture.vo;

import java.util.Date;

public class Member {
	// 테이블의 컬러명 == 필드명 선언.
	private String id;       // uid 수정
	private String pwd;
	private String name;
	private String gender;
	private String birth;
	private String is_lunar; // isLunar 수정
	private String cphone;   // cPhone 수정
	private String email;
	private String habit;
	private Date   regdate;   // regDate 수정
	
	private int point;  // 트랜잭션 테스트용 필드 추가
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
	// getter, setter 삭제 후 새로 생성..
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getIs_lunar() {
		return is_lunar;
	}
	public void setIs_lunar(String is_lunar) {
		this.is_lunar = is_lunar;
	}
	public String getCphone() {
		return cphone;
	}
	public void setCphone(String cphone) {
		this.cphone = cphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHabit() {
		return habit;
	}
	public void setHabit(String habit) {
		this.habit = habit;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	} 
}
