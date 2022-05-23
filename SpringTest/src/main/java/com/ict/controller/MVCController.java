package com.ict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// @Controller 어노테이션으로 컨테이너에 적재.
@Controller
public class MVCController {
	
	// 기본주소(localhost:8181)뒤에 / goA를 붙이면 goA()메서드 실행
	@RequestMapping(value="/goA")
	public String goA() {
		System.out.println("goA 주소 접속 감지");
		// 결과 페이지는 view 폴더 아래의 A.jsp
		return"A";
	}
	
	@RequestMapping(value="/goB")
	public String goB()	{
		System.out.println("goB주소 접속 감지");
		return"goB";
	}
	
	@RequestMapping(value="/goC")
	// Model을 선언해주면 바인딩 및 포워딩으로 .jsp파일에 데이터를 발송할 수 있습니다.
	public String goC(Model model) {
		// model.addAttribute("전달명,"자료");
		// 바인딩해서 보낸 자료는 .jsp파일에서 EL을 사용해 출력할 수 있습니다.
		model.addAttribute("test", "goC에서 보내온 문자열");
		return"goC";
		
	}
	
	// goD는 파라미터를 입력받을 수 있도록 해보겠습니다.
	@RequestMapping(value="/goD")
	// 주소 뒤 ?dNum값 형태로 들어오는 자료를 로직 내 dNum변수에 대입해줍니다.
	// 들어온 파라미터를 .jsp로 보내주기 위해서는 역시 Model model을 파라미터에 선언해줍니다.
	public String goD(int dNum, Model model) {
		System.out.println("주소로 전달받은 값 : " + dNum);
		model.addAttribute("dNum", dNum);
		return"D";
	}
}
