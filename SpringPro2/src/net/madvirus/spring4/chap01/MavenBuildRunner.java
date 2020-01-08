package net.madvirus.spring4.chap01;

import java.util.List;

public class MavenBuildRunner implements BuildRunner{

	private String mavenPath;
	
	public void setMavenPath(String mavenPath) {
		this.mavenPath = mavenPath;
	}


	@Override
	public void build(List<String> srcDirs, String binDir) {
		String info = "메이븐 경로 : "+mavenPath +" \n";
		for(String srcDiS : srcDirs) {
			info +="소스 경로 : "+srcDiS +"\n";
		}
		info += "클래스 파일 경로 : "+binDir +"\n";
		System.out.printf("MavenBuildRunner.build() 실행 \n %s",info);
		
	}

}
