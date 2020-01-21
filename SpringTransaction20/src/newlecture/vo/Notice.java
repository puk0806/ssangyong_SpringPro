package newlecture.vo;

import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;



public class Notice {
	
	private int seq;    // 글번호   String seq
	private String title;
	private String writer;  // name    X id
	private Date regdate;
	private String filesrc; // fileSrc 수정 
    private int hit;
	private String content;
	
	// p 445 첨부된 파일 저장 필드 
	// private MultipartFile
	// noticeReg.jsp 의  input name="file"
	private CommonsMultipartFile file;	
	
	public CommonsMultipartFile getFile() {
		return file;
	}
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	
	// getter, setter 생성
	public String getFilesrc() {
		return filesrc;
	}
	public void setFilesrc(String filesrc) {
		this.filesrc = filesrc;
	}
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
