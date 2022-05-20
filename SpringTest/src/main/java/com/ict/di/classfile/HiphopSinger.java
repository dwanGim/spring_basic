package com.ict.di.classfile;

import org.springframework.stereotype.Component;

@Component
public class HiphopSinger extends Singer{

	@Override
	public void sing() {
		System.out.println("힙합가수가 랩을 합니다.");
	}
}

