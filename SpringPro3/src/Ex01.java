import java.util.Properties;

public class Ex01 {
	
	public static void main(String[] args) {
		
		// p77 List, Map, Set 타입 컬렉션 사용
		// <map> 태그, <entry> 태그를 사용한다
		/*
		<bean id = "monitor" class="net.Monitor">
			<property name="sensorMap">
				<map>
					<entry>
						<key>admin</key>
						<value>20</value>
					</entry>
					<entry key="ad" value = "30"></entry>
					// 만약 빈객체라면
					<entry key="ad" value-ref = "빈이름"></entry>
				</map>
			</property>
		</bean>
		*/

		
		// <set> 태그,
		/*
		<property name ="agentCodes">
			<set>
				<value>1</value>	|		<ref bean=".."/>
				<value>1</value>	|		<ref bean=".."/>
				<value>1</value>	|		<ref bean=".."/>
			</set>
		</property>
		*/
		
		
		// sensor_config.xml 에서 spring 객체로 생성하기
		Properties prop = new Properties();
		prop.setProperty("theshold", "9");
		prop.setProperty("retry", "3");
		
		Sensor s = new Sensor();
		s.setAddtionalInfo(prop);
		
		
	}

}
