package com.ict.di.classfile;

import org.springframework.stereotype.Component;

@Component
public class DanceSinger extends Singer {

	@Override
	public void sing() {
		System.out.println("댄스가수가 노래를 합니다.");
	}
}
