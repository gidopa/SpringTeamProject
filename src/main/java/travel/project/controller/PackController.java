package travel.project.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import travel.project.domain.Hotels;
import travel.project.service.PackService;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Slf4j
@Controller
@RequiredArgsConstructor
public class PackController {
	
    private final PackService packService;
    private static String UPLOADED_FOLDER = "C://temp//hotel//";
//    private static final String UPLOADED_FOLDER = "C:/upload/";
    private int count = 0;
	String main = "main/main";
	
	// 호텔 등록 페이지
	@GetMapping("/hotels")
	public String hotels(Model model){
		log.info("packController");
		model.addAttribute("center", "../pack/hotelRegister.jsp");
		return main;
	}
	
	
	// 호텔, 호텔 편의시설, 호텔 이미지 등록
	@Transactional
	@PostMapping(value = "/hotels")
	public String upload(@ModelAttribute Hotels hotels,
						 @RequestParam("amenities") List<String> amenities,
						 @RequestParam("hotelImage") MultipartFile[] files) {
		
		// 호텔 등록
		Hotels savedHotels = packService.saveHotel(hotels);
		
		// 호텔 편의시설 등록
		packService.saveHotelAmenities(amenities, savedHotels.getHotelId());
		
		List<String> imgNames = new ArrayList<>();
		count = (int)savedHotels.getHotelId();
		String hotelFolderPath = UPLOADED_FOLDER + count + "//";
		
		// 호텔별 폴더가 없을 경우 생성
	    Path hotelDirectory = Paths.get(hotelFolderPath);
	    if (!Files.exists(hotelDirectory)) {
	        try {
	            Files.createDirectories(hotelDirectory);
	        } catch (IOException e) {
	            log.error("Failed to create directory for hotel: " + hotelFolderPath, e);
	            return main; // 폴더 생성 실패시 메인 페이지로 리다이렉트 할 수도 있습니다.
	        }
	     }
        
        for (MultipartFile file : files) {
          if (file.isEmpty()) {
              continue;
          }

          try {
        	  imgNames.add(file.getOriginalFilename());
              byte[] bytes = file.getBytes();
              Path path = Paths.get(hotelFolderPath + file.getOriginalFilename());
              Files.write(path, bytes);

          } catch (IOException e) {
              e.printStackTrace();
          }
       }
        
        for(String a : imgNames) {
			System.out.println("제발 :" + a);
		}
        
        // 호텔 이미지 등록
        packService.saveHotelImg(imgNames, savedHotels.getHotelId());
        
		return null;
	}
	
	@GetMapping("/package/list")
	public String getAllPackageList(Model model){
//		packService.getAllPackageList();
		model.addAttribute("center","../pack/AllPackageList.jsp");
		return "main/main";
	}

	
}
