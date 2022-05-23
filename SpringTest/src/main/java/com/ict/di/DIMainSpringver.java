package com.ict.di;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.ict.di.classfile.Broadcast;
import com.ict.di.classfile.Satellite;
import com.ict.di.classfile.Stage;

public class DIMainSpringver {

	public static void main(String[] args) {
		// root-context가 여러 개라면 미리 문자배열로 경로를 만들어 둘 수 있습니ㅏㄷ.
		String[] path = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
				"file:src/main/webapp/WEB-INF/spring/root-context2.xml"};
		
		// 빈 컨테이너에 들어있는 객체를 꺼내기 위해 호출기 생성
		// 목표 빈 컨테이너(root-context.xml) 의 경로를 적어줘서 그쪽 빈컨테이너와 통신하도록 설정
		GenericXmlApplicationContext context = 
			new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/root-*.xml");
		
		// Singer없이 바로 다이렉트로 Stage를 만들어보겠습니다.
		// Stage stage = context.getBean("stage", Stage.class);
		// stage.perform();
		
		//Broadcast broadcast = context.getBean("broadcast", Broadcast.class); 
		//broadcast.broad();
		
		//Satellite satellite = context.getBean("Satellite", Satellite.class);
		//satellite.SatelliteBroad();
		
		//Stage stage = context.getBean("danceStage", Stage.class);
		//stage.perform();
		
		
		//Broadcast hipBroad = context.getBean("hiphopBroad",Broadcast.class);
		//hipBroad.broad();
		
		//Satellite satellite = context.getBean("danceSatell", Satellite.class);
		//satellite.SatelliteBroad();
		
	}

}	
