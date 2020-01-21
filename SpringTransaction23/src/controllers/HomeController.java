package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

	// index.jsp
	@RequestMapping("index.htm")
	public String home() {
		return "index.jsp";		
	}
	
}












/*
	2020. 1. 20.
1	김건영/박지수/이경섭/천재희/장동환/김예주(11/04  탈락)
2	김수빈/이영재/김지민/정의호/김혜빈
3	정경원/박성훈/여원경/박성재/정영진/고승우
4	박찬호/김호연/박상만/김재우/권미지
*/