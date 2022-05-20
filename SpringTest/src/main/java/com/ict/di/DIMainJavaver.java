package com.ict.di;

import com.ict.di.classfile.Singer;
import com.ict.di.classfile.Stage;
import com.ict.di.classfile.Broadcast;
import com.ict.di.classfile.DanceSinger;
import com.ict.di.classfile.HiphopSinger;
import com.ict.di.classfile.Satellite;

public class DIMainJavaver {

	public static void main(String[] args) {
		// Singer를 생성해서 노래하게 만들어보세요.
		//Singer singer = new Singer();
		
		// Stage도 만들어서 공연을 시켜보세요.
		//
		//DanceSinger singer = new DanceSinger();
		HiphopSinger singer = new HiphopSinger();
		Stage stage = new Stage(singer);
		stage.perform();
		singer.sing();
		// Broadcast를 생성해서 방송무대를 송출해보겠습니다.
		Broadcast broadcast = new Broadcast(stage);
		broadcast.broad();
		
		Satellite satelle = new Satellite(broadcast);
		satelle.SatelliteBroad();
	}

}
