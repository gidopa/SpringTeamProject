package travel.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import travel.project.domain.Attraction;
import travel.project.domain.Customer;
import travel.project.domain.Destination;
import travel.project.service.PackService;
import travel.project.service.admin.AdminService;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {
	
	private final AdminService adminService;
	private final PackService packService;
	
	@GetMapping
	public String adminHome() {
		return "admin/index";
	}
	
	// 모든 회원정보 보기
	@GetMapping("/membership")
	public String membership(Model model) {
		
		List<Customer> customers = adminService.allCustomers();
		model.addAttribute("customers", customers);
		
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
