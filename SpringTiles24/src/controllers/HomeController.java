package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

	// index.jsp
	@RequestMapping("index.htm")
	public String home() {
		// return "index.jsp";
		System.out.println("컨트롤러 들어오");
		return "home.index";
	}
	
}
