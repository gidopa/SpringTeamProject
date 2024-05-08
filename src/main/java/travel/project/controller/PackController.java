package travel.project.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.tags.shaded.org.apache.xpath.SourceTree;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import travel.project.domain.Attraction;
import travel.project.domain.Destination;
import travel.project.domain.HotelView;
import travel.project.domain.Hotels;
import travel.project.domain.Pack;
import travel.project.domain.RestaurantView;
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
		
		// 두 날짜 차이 계산
		long daysDifference = packService.dayDifference(pack.getStartDate(), pack.getEndDate());
		
		// pack 등록
		long savedPack= packService.savePack(pack);
		
		// 호텔 모든 열 지역으로 검색
		List<HotelView> hotelViews = packService.findByDestinationHotels(pack.getDestinationName());
		
		// 목적지 명소 등 조회
		// 레스토랑 모든 열 지역으로 검색
		List<RestaurantView> restaurantViews = packService.findByDestinationRestaurant(pack.getDestinationName());
		
		// 관광지 모든 열 지역으로 검색
		List<AttractionView> attractionViews = packService.findByDestinationAttraction(pack.getDestinationName(), "tourist");
		
		// 액티비티 모든 열 지역으로 검색
		List<AttractionView> activityViews = packService.findByDestinationAttraction(pack.getDestinationName(), "Activity");
		
		model.addAttribute("daysDifference", daysDifference+1);
		model.addAttribute("hotelView", hotelViews);
		model.addAttribute("restaurantView", restaurantViews);
		model.addAttribute("attractionView", attractionViews);
		model.addAttribute("activityView", activityViews);
		model.addAttribute("packId", savedPack);
		model.addAttribute("center", "../pack/packagesDetail.jsp");
		return main;
	}
	
	@Transactional
	@PostMapping("/packages/{packId}")
	public String packages(@PathVariable long packId,
						@RequestParam long days,
						@RequestParam Map<String, String> params
//						@RequestParam(required = false, defaultValue = "{}") String[] hotel,
//	                       @RequestParam(required = false, defaultValue = "{}") String[] restaurant,
//	                       @RequestParam(required = false, defaultValue = "{}") String[] tourist,
//	                       @RequestParam(required = false, defaultValue = "{}") String[] activity
//                        @RequestParam String[] restaurant,
//                        @RequestParam String[] tourist,
//                        @RequestParam String[] activity
						) {
		
//		 printParams(params);
		
		Map<String, String> filteredParams = params.entrySet().stream()
		        .filter(entry -> entry.getKey().matches("hotel\\[\\d+\\]|restaurant\\[\\d+\\]|tourist\\[\\d+\\]"))
		        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

		    // 필터링된 파라미터 사용
		    filteredParams.forEach((key, value) -> System.out.println(key + " : " + value));
		 
//		 for (Map.Entry<String, String> entry : params.entrySet()) {
//			 String key = (String)entry.getKey();
//			 String value = (String)entry.getValue();
//			 System.out.println("key : " + key);
//			 System.out.println("value : " + value);
//		        // key에서 일차 정보 추출 및 처리
//		    }
//		int[] hotelIds = convertToIntArray(hotel);
//		List<int[]> restaurantIdsList = new ArrayList<>();
		
//	    for (String restaurantEntry : restaurant) {
//	        int[] restaurantIds = convertSpaceSeparatedStringToIntArray(restaurantEntry);
//	        restaurantIdsList.add(restaurantIds);
//	        System.out.println("변환된 레스토랑 ID 배열: ");
//	        for (int id : restaurantIds) {
//	            System.out.print(id + " ");
//	        }
//	        System.out.println();
//	    }
	    
//	    // 모든 배열의 원소를 하나의 배열로 합치기
//	    int totalSize = 0;
//	    for (int[] array : restaurantIdsList) {
//	        totalSize += array.length;
//	    }
//
//	    // 새 배열 생성
//	    int[] combinedRestaurantIds = new int[totalSize];
//	    int currentIndex = 0;
//	    for (int[] array : restaurantIdsList) {
//	        System.arraycopy(array, 0, combinedRestaurantIds, currentIndex, array.length);
//	        currentIndex += array.length;
//	    }
//
//	    // 새 배열 출력
//	    System.out.print("합쳐진 레스토랑 ID 배열: ");
//	    for (int id : combinedRestaurantIds) {
//	        System.out.print(id + " ");
//	    }
//	    System.out.println();
	    
		
//	    System.out.println("모든 레스토랑 ID 배열:");
//	    for (int[] ids : restaurantIdsList) {
//	        for (int id : ids) {
//	            System.out.print(id + " ");
//	        }
//	        System.out.println();  // 각 배열의 끝에서 줄바꿈
//	    }
		
		
//		for(int i=0; i<days; i++) {
//			packService.saveScheduleHotel(hotelIds[i], i+1, packId);
//			packService.saveEachHotel(hotelIds[i], i+1, packId);
//			
//			packService.saveScheduleRestaurant(restaurantIdsList, i+1, packId);
//		}
		
		return null;
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


/*	@GetMapping("/package/{tripId}")
	public String packDetail(@PathVariable long tripId, Model model){

	}*/
	

	@GetMapping("/package/{tripId}")
	public String packDetail(@PathVariable long tripId, Model model, @RequestParam String destinationName){
		Destination destination = destinationService.findDestByName(destinationName);
		long destId = destination.getDestinationId();
		// 여행 목적지에 관한 attraction(관광지)에 대한 정보 가져옴
		List<Attraction> attractionList = destinationService.findAttractionById(destId);
		List<Restaurants> restaurantsList = destinationService.findRestaurantsById(destId);
		List<Schedule> scheduleList = scheduleService.findScheduleById(tripId);
		for(Attraction a : attractionList){
			log.info(a.getAttractionName());
		}
		log.info("restaurantsList.size() = {}" , restaurantsList.size());
		log.info("attractionList.size() = {}" , attractionList.size());
		log.info("scheduleList.size() = {}", scheduleList.size());
		return null;
	}
	
	// 문자배열 > 숫자배열 변경
//	private int[] convertToIntArray(String[] stringArray) {
//	    ArrayList<Integer> tempList = new ArrayList<>();
//	    for (String item : stringArray) {
//	        if (!item.isEmpty()) {
//	            try {
//	                tempList.add(Integer.parseInt(item));
//	            } catch (NumberFormatException e) {
//	                // 로그 출력, 오류 처리, 또는 기본값 설정
//	                System.out.println("Invalid integer format for input string: '" + item + "'");
//	                tempList.add(0);  // 기본값으로 0을 사용하거나 이 부분을 적절히 조정
//	            }
//	        }
//	    }
//	    // ArrayList를 기본 int 배열로 변환
//	    int[] intArray = new int[tempList.size()];
//	    for (int i = 0; i < tempList.size(); i++) {
//	        intArray[i] = tempList.get(i);
//	    }
//	    return intArray;
//	}
	
	private void printParams(Map<String, Object> params) {
	    for (Map.Entry<String, Object> entry : params.entrySet()) {
	        String key = entry.getKey();
	        Object value = entry.getValue();
	        
	        // 값이 String 배열인지 확인
	        if (value instanceof String[]) {
	            String[] values = (String[]) value;
	            System.out.println(key + " (String array): " + Arrays.toString(values));
	        } else if (value instanceof String) {
	            // 값이 String 인지 확인
	            String singleValue = (String) value;
	            System.out.println(key + " (String): " + singleValue);
	        } else {
	            // 그 외 타입에 대한 처리
	            System.out.println(key + " (Unknown type): " + value.toString());
	        }
	    }
	}
	
	private int[] convertSpaceSeparatedStringToIntArray(String spaceSeparatedNumbers) {
	    String[] numbers = spaceSeparatedNumbers.trim().split("\\s+");
	    int[] result = new int[numbers.length];
	    for (int i = 0; i < numbers.length; i++) {
	        result[i] = Integer.parseInt(numbers[i]);
	    }
	    return result;
	}

	private int[] convertToIntArray(String[] stringArray) {
	    List<Integer> temp = new ArrayList<>();
	    for (String str : stringArray) {
	        if (!str.isEmpty()) {
	            String[] parts = str.split("\\s+");
	            for (String part : parts) {
	                try {
	                    temp.add(Integer.parseInt(part));
	                } catch (NumberFormatException e) {
	                    System.out.println("Invalid input for conversion: " + part);
	                }
	            }
	        }
	    }
	    return temp.stream().mapToInt(i -> i).toArray();
	}
}
