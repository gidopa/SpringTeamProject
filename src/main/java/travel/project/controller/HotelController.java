package travel.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HotelController {
	String main = "main/main";
	
	@GetMapping("/hotel")
	public String hotel(Model model) {
		log.info("hotel controller");
		model.addAttribute("center", "../hotel/hotelRegister.jsp");
		return main;
	}
}
