package travel.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminController {
	
	@GetMapping
	public String adminHome() {
		return "admin/index";
	}
	
	@GetMapping("/membership")
	public String membership() {
		return "admin/membership";
	}
	
	@GetMapping("/reservation")
	public String reservation() {
		return "admin/reservation";
	}
	
	@GetMapping("/travelpackage")
	public String travelPackage() {
		return "admin/travelPackage";
	}
}
