package net.madvirus.spring4.chap01;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		
		// Project 객체 + MavenBuildRunner 객체
		// 위의 두 객체는 의존관계가 있다.
		// 의존성 주입(DI)

		// 객체 생성+DI(조립) 하는 아래 코딩 모두 향후 FOM.XML에서 처리
		
		MavenBuildRunner buildRunner = new MavenBuildRunner();
		buildRunner.setMavenPath("c:\\apache-maven-3.1.1");

		// 1. 생성자 DI		
		// Project samplProject = new Project(buildRunner);
		
		// 2. setter DI
		Project samplProject = new Project();
		samplProject.setBuildRunner(buildRunner);
		samplProject.setBinDir("bin");
		List<String> srcDirs = new ArrayList<String>();
		srcDirs.add("src");
		srcDirs.add("resources");
		samplProject.setSrcDirs(srcDirs);
		
		
		samplProject.build();
		System.out.println(" END ");
		
	}

}

