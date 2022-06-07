package com.ict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ict.persistence.BoardVO;
import com.ict.service.BoardService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@Log4j
public class BoardController {

	
	@Autowired
	private BoardService service;
	
	@RequestMapping(value = "/list",
			method = {RequestMethod.GET, RequestMethod.POST})
	public String getBoardList(Model model)	{
		List<BoardVO> boardList = service.getList();
		log.info(boardList);
		model.addAttribute("boardList", boardList);
		return "/board/getBoardList";
	}
	
}
