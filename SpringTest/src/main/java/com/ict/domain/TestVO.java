package com.ict.domain;

import lombok.Data;

// lombok을 이용한 자동 VO생성
@Data
public class TestVO {

	private String name;
	private int age;
	private int level;
	private String addr;
}
