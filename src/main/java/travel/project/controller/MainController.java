package travel.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
//aaa
@Slf4j
@Controller
public class MainController {
	String main = "main/main";


	@RequestMapping("/")
	public String home() {
		log.info("home controller");
		return main;
	}

	@GetMapping("/reservation")
	public String res(Model model){
		model.addAttribute("center", "../reservation.jsp");
		return "main/main";
	}

	@GetMapping("/aboutus")
	public String aboutus(Model model){
		model.addAttribute("center", "aboutus.jsp");
		return main;
	}

}
