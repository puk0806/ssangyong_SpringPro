package net.madvirus.spring4.chap01;

import java.util.List;

public class Project {

	private List<String> srcDirs;	// 소스파일 경로
	private String binDir;			// 클래스 파일 경로
	
	// Project 클래스가 BuildRunner 인터페이스를 필드
	// private BuildRunner buildRunner = new MavenBuildRunner();		// 결합력 높은 코딩 ( 좋은 코딩 X)
	private BuildRunner buildRunner ; 	// 생성자에서 생성 or setter를 통해 생성 (의존성 주입 dependency injecttion)
	
	public Project() {
	}

	// 생성자 매개변수로 의존 객체를 주입받아서 결합(조립) : 생성자 DI
	public Project(BuildRunner buildRunner) {
		super();
		this.buildRunner = buildRunner;
	}

	public void setSrcDirs(List<String> srcDirs) {
		this.srcDirs = srcDirs;
	}

	public void setBinDir(String binDir) {
		this.binDir = binDir;
	}
	
	
	// setter를 통해서 읜존 객체를 주입 받아서 결합 (조립) : setter DI
	public void setBuildRunner(BuildRunner buildRunner) {
		this.buildRunner = buildRunner;
	}
	
	public void build() {
		this.buildRunner.build(srcDirs, binDir);
	}
	
	
}
