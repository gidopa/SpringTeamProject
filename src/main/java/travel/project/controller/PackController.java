package travel.project.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.tags.shaded.org.apache.xpath.SourceTree;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Attr;
import travel.project.domain.Attraction;
import travel.project.domain.Destination;
import travel.project.domain.HotelView;
import travel.project.domain.Hotels;
import travel.project.domain.Pack;
import travel.project.domain.Restaurants;
import travel.project.domain.*;
import travel.project.service.Detination.DestinationService;
import travel.project.service.PackService;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import travel.project.service.ScheduleService.ScheduleService;


@Slf4j
@Controller
@RequiredArgsConstructor
public class PackController {
	
    private final PackService packService;
	private final DestinationService destinationService;
	private final ScheduleService scheduleService;
	String main = "main/main";
	
	// 호텔 등록 페이지
	@GetMapping("/hotels")
	public String hotels(Model model){
		log.info("packController");
		model.addAttribute("center", "../pack/hotelRegister.jsp");
		return main;
	}
	
	// 호텔, 호텔 편의시설, 호텔 이미지 등록
	@PostMapping("/hotels")
	public String upload(@ModelAttribute Hotels hotels,
						 @RequestParam(value = "amenities", required = true)  List<String> amenities,
						 @RequestParam(value = "hotelImage", required = true)  MultipartFile[] files) {
		
		// 호텔 등록
		Hotels savedHotels = packService.saveHotel(hotels);
		
		// 호텔 편의시설 등록
		packService.saveHotelAmenities(amenities, savedHotels.getHotelId());
		
		// 호텔 이미지 업로드
		List<String> imgNames = packService.uploadImage(files, savedHotels.getHotelId(), "hotel");
		
		// 호텔 이미지 등록
        packService.saveHotelImg(imgNames, savedHotels.getHotelId());
        
		return main;
	}
	
	// Destination 페이지 요청
	@GetMapping("/destinations")
	public String destinations(Model model){
		log.info("packController");
		model.addAttribute("center", "../pack/destination.jsp");
		return main;
	}
	
	// Destination 등록
	@PostMapping("/destinations")
	public String destinations(@ModelAttribute Destination destination, Model model) {
		packService.saveDestination(destination);
		return main;
	}
	
	// restaurants 등록을 위한 Destination 리스트 페이지
	@GetMapping("/restaurants")
	public String destiNames(Model model){
		List<Destination> destinations = packService.findAllDestination();
		model.addAttribute("destinations", destinations);
		model.addAttribute("center", "../pack/destinationsList1.jsp");
		return main;
	}
	
	// restaurants 페이지 요청
	@GetMapping("/restaurants/{id}")
	public String restaurants(@PathVariable("id") long id, Model model){
		
		model.addAttribute("destination_Id", id);
		model.addAttribute("center", "../pack/restaurants.jsp");
		return main;
	}
	
	// restaurants 등록
	@PostMapping("/restaurants/{destination_Id}")
	public String restaurants(@ModelAttribute Restaurants restaurants, @PathVariable("destination_Id") long destination_Id,
							  @RequestParam(value = "restaurantsImage", required = true)  MultipartFile[] files){
		
		// 이미지 업로드
		List<String> imgNames = packService.uploadImage(files, destination_Id, "restaurants");
		
		// 이미지 등록
		packService.saveImg(imgNames, "restaurants", destination_Id);
		
		// 레스토랑 등록
		packService.saveRestaurant(restaurants, destination_Id);
		
		return main;
	}
	
	// attractions 등록을 위한 Destination 리스트 페이지
	@GetMapping("/attractions")
	public String attractions(Model model) {
		List<Destination> destinations = packService.findAllDestination();
		model.addAttribute("destinations", destinations);
		model.addAttribute("center", "../pack/destinationList2.jsp");
		
		return main;
	}
	
	// attractions 페이지 요청
	@GetMapping("/attractions/{id}")
	public String attractions(@PathVariable("id") long id, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("center", "../pack/attractions.jsp");
		return main;
	}
	
	// attractions 등록
	@PostMapping("/attractions/{id}")
	public String attractions(@ModelAttribute Attraction attraction, @PathVariable("id") long id,
							  @RequestParam(value = "attractionsImage", required = true) MultipartFile[] files) {
		
		// 이미지 업로드
		List<String> imgNames = packService.uploadImage(files, id, "attractions");
		
		// 이미지 등록
		packService.saveImg(imgNames, attraction.getType(), id);
		
		// attractions 등록
		packService.saveAttraction(attraction, id);
		return main;
	}
	
	// package 등록 화면
	@GetMapping("/packages")
	public String packages(Model model) {
		model.addAttribute("center", "../pack/packages.jsp");
		return main;
	}
	
	@PostMapping("/packages")
	public String packages(@ModelAttribute Pack pack,
							@RequestParam("sDate") String sDate,
							@RequestParam("eDate") String eDate,
							Model model) {
		// sql Date 타입 변경
		LocalDate startDate = packService.replaceSqlDate(sDate);
		LocalDate endDate = packService.replaceSqlDate(eDate);
		pack.setStartDate(Date.valueOf(startDate));
		pack.setEndDate(Date.valueOf(endDate));
		System.out.println(pack.getPackName());
		
		// 두 날짜 차이 계산
		long daysDifference = packService.dayDifference(pack.getStartDate(), pack.getEndDate());
		
		// pack 등록
		Pack savedPack= packService.savePack(pack);
		
		// 호텔 모든 열 지역으로 검색
		List<HotelView> hotelViews = packService.findByDestinationHotels(pack.getDestinationName());
		
		// 목적지 명소 등 조회
		// 레스토랑 모든 열 조회
		
		model.addAttribute("hotelView", hotelViews);
		model.addAttribute("center", "../pack/packagesDetail.jsp");
		return main;
	}
	
	// 지역별 패키지의 리스트
	@GetMapping("/package/list/{destination}")
	public String getAllPackageList(Model model,@PathVariable String destination){
		// PathVariable로 destination을 받아 해당 destination에 따른 list 보여줌
		List<Pack> packs = packService.getPackageListByDestination(destination);
		model.addAttribute("center","../pack/PackageList.jsp");
		model.addAttribute("list", packs);
		return "main/main";
	}


/*	@GetMapping("/package/{packId}")
	public String packDetail(@PathVariable long packId, Model model){

	}*/
	

	@GetMapping("/package/{packId}")
	public String packDetail(@PathVariable long packId, Model model, @RequestParam String destinationName){
		Destination destination = destinationService.findDestByName(destinationName);
		long destId = destination.getDestinationId();
		// 여행 목적지에 관한 attraction(관광지)에 대한 정보 가져옴
		List<Schedule> scheduleList = scheduleService.findScheduleById(packId);
		List<Attraction> attractionList = destinationService.findAttractionById(destId);
		List<Restaurants> restaurantsList = destinationService.findRestaurantsById(destId);

		// 여기부터 시도
		MultiValueMap<Integer, Object> map = new LinkedMultiValueMap<>();
		int maxDayNum = scheduleService.getMaxDayNum(packId);
		log.info("maxDayNum = {}", maxDayNum);
		 // packId에 대한 것도 같이 매개변수로 넘겨야 할 것 같음 .
		for (int i = 1; i <= maxDayNum; i++) {
			List attractionDayNum = scheduleService.findAttractionByDayNum(i,packId);
			Hotels hotelDayNum = scheduleService.findHotelByDayNum(i,packId);
			List restaurantsDayNum = scheduleService.findRestaurantByDayNum(i,packId);
			System.out.println("restaurantsDayNum.size() = " + restaurantsDayNum.size());
			// 호텔과 관광 명소 리스트를 하나의 리스트로 합치기
			List<Object> items = new ArrayList<>();
			items.add(new ItemWrapper(hotelDayNum, "hotel"));
			for (Attraction attraction : (List<Attraction>)attractionDayNum) {
				items.add(new ItemWrapper(attraction, "attraction"));
			}
			for (Restaurants restaurants : (List<Restaurants>)restaurantsDayNum){
				items.add(new ItemWrapper(restaurants, "restaurant"));
			}

			// 모든 항목을 map에 저장
			map.put(i,items);

		}

		for(Attraction a : attractionList){
			log.info(a.getAttractionName());
		}
		List<HotelView> hotelsList = packService.findHotelsByDestinationName(destinationName);
		log.info("hotelList.size() = {}", hotelsList.size());
		log.info("restaurantsList.size() = {}" , restaurantsList.size());
		log.info("attractionList.size() = {}" , attractionList.size());
		log.info("scheduleList.size() = {}", scheduleList.size());
		model.addAttribute("map",map);
		return "test";
	}



	
}
