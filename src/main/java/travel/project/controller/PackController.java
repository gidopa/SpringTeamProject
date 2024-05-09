package travel.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import travel.project.domain.Attraction;
import travel.project.domain.Destination;
import travel.project.domain.Hotels;
import travel.project.domain.Pack;
import travel.project.domain.Restaurants;
import travel.project.service.PackService;
import travel.project.service.Detination.DestinationService;




@Slf4j
@Controller
@RequiredArgsConstructor
public class PackController {

    private final PackService packService;
	private final DestinationService destinationService;
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
		List<String> imgNames = packService.uploadHotelImage(files, savedHotels.getHotelId());

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

	// 목적지 리스트 페이지 요청
	@GetMapping("/destinationsList")
	public String destiNames(Model model){
		List<Destination> destinations = packService.findAllDestination();
		model.addAttribute("destinations", destinations);
		model.addAttribute("center", "../pack/destinationsList1.jsp");
		return main;
	}


	// restaurants 페이지 요청
	@GetMapping("/restaurants/{id}")
	public String restaurants(@PathVariable("id") long id, Model model){
		log.info("packController");

		model.addAttribute("destination_Id", id);
		model.addAttribute("center", "../pack/restaurants.jsp");

		return main;
	}

	@PostMapping("/restaurants")
	public String restaurants(@ModelAttribute Restaurants restaurants, @RequestParam("destinationId") long destination_Id){
		packService.saveRestaurant(restaurants, destination_Id);

		return main;
	}
	// 지역별 패키지의 리스트
	@GetMapping("/package/list/{destination}")
	public String getAllPackageList(Model model,@PathVariable("destination") String destination){
		// PathVariable로 destination을 받아 해당 destination에 따른 list 보여줌
		List<Pack> packs = packService.getPackageListByDestination(destination);
		model.addAttribute("center","../pack/PackageList.jsp");
		model.addAttribute("list", packs);
		return "main/main";
	}

	@GetMapping("/package/{packId}")
	public String packDetail(@PathVariable("packId") long packId, Model model, @RequestParam String destinationName){
		Destination destination = destinationService.findDestByName(destinationName);
		long destId = destination.getDestinationId();
		List<Attraction> attractionList = destinationService.findAttractionById(destId);
		System.out.println("attractionList.size() = " + attractionList.size());
		return null;
	}

	//달력 날짜 클릭해서 그 날짜 동안의 패키지 조회
	@GetMapping("/reservationInquiry")
	public String getMethodName(@RequestParam("startDate") String startDate,
								@RequestParam("endDate") String endDate,
								@RequestParam("numberOfPeople") Integer numberOfPeople ,Model model) {
		//받아온 날짜 데이터의 형식이 2024.05.15수 에서 2024.05.15 요일을 뺀 나머지 값을 받음 + 2024-05-15로 바꿈
		 startDate = startDate.substring(0, startDate.length() - 1);
		 startDate = startDate.replace(".", "-");
		 endDate = endDate.substring(0, endDate.length() - 1);
		 endDate = endDate.replace(".", "-");
		List<Pack> list = packService.reservationInquiry(startDate,endDate);

		model.addAttribute("center","../pack/PackageList.jsp" );
		model.addAttribute("numberOfPeople",numberOfPeople);
		model.addAttribute("list", list);
		return main;
	}

}
