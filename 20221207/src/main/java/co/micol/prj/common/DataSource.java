package co.micol.prj.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;



public class DataSource {
	//싱글톤 클래스
	//private로만들어서 겟인스턴스를 통해서 사용
	//클래스명과 변수명은 동일하게 만드는것이 좋음 
	private static SqlSessionFactory sqlSessionFactory;
	
	//외부에서 나를 생성하지못하도록 생성자를 private로 만들어줌
	//생성자 : 내 클래스와 동일한 메서드 명! 생성자를 만들지않으면 자바가 자동으로만들어줌
	//but 자바가 자동으로 만들지 못하게하기위해 private로 생성자를 만들어줌
	private DataSource() {};
	
	public static SqlSessionFactory getInstance() {
		String resource = "config/mybatis-config.xml";
		
		//인풋 스트림이 들어가니까 트라이 캐치문
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return sqlSessionFactory;
	}
}
