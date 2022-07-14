package com.ict.persistence;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class BoardVO {
	
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	private Date updateDate;
	private Long replyCount;
	
	// 첨부파일이 여러 개 일 수 있기 때문에
	private List<BoardAttachVO> attachList;
}
