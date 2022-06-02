package com.ict.persistence;

import java.sql.Date;

import lombok.Data;

@Data
public class BoardVO {

	private long bno;
	private String titie;
	private String content;
	private String writer;
	private Date regDate;
	private Date updateDate;
	
}
