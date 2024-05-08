package travel.project.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import travel.project.domain.Attraction;
import travel.project.domain.AttractionView;
import travel.project.domain.Destination;
import travel.project.domain.HotelView;
import travel.project.domain.Hotels;
import travel.project.domain.Pack;
import travel.project.domain.RestaurantView;
import travel.project.domain.Restaurants;
import travel.project.mapper.PackMapper;
import travel.project.repository.pack.PackRepository;
import travel.project.repository.pack.PackRepositoryImpl;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PackServiceImpl implements PackService{
	
	private final PackRepository packRepository;
    private static String UPLOADED_FOLDER = "C://temp//";
    private int count = 0;

	
	// 호텔 등록 후 반환
	@Override
	public Hotels saveHotel(Hotels hotels) {
		return packRepository.saveHotel(hotels);
	}
	
	// 호텔 이미지 등록
	@Override
	public void saveHotelImg(List<String> imgNames, long id) {
		packRepository.saveHotelImg(imgNames, id);
	}


	// 호텔 편의시설 등록
	@Override
	public void saveHotelAmenities(List<String> amenities, long id) {
		packRepository.saveHotelAmenities(amenities, id);
	}

	@Override
	public List<Pack> getPackageListByDestination(String destination) {
		return packRepository.findPackList(destination);
	}
	
	// 이미지 업로드
	@Override
	public List<String> uploadImage(MultipartFile[] files, long id, String category) {
		List<String> imgNames = new ArrayList<>();
		count = (int)id;
		String FolderPath = UPLOADED_FOLDER + category + "//" + count + "//";
		
		// 호텔별 폴더가 없을 경우 생성
	    Path Directory = Paths.get(FolderPath);
	    if (!Files.exists(Directory)) {
	        try {
	            Files.createDirectories(Directory);
	        } catch (IOException e) {
	            log.error("Failed to create directory for hotel: " + FolderPath, e);
	        }
	     }
	    for (MultipartFile file : files) {
	          if (file.isEmpty()) {
	              continue;
	          }

	          try {
	        	  imgNames.add(file.getOriginalFilename());
	              byte[] bytes = file.getBytes();
	              Path path = Paths.get(FolderPath + file.getOriginalFilename());
	              Files.write(path, bytes);

	          } catch (IOException e) {
	              e.printStackTrace();
	          }
	       }
	    
	    return imgNames;
	}
	
	// Destination 등록
	@Override
	public long saveDestination(Destination destination) {
		return packRepository.saveDestination(destination);
	}
	
	// Destination 리스트 반환
	@Override
	public List<Destination> findAllDestination() {
		return packRepository.findAllDestination();
	}
	
	// Restaurants 등록
	@Override
	public void saveRestaurant(Restaurants restaurants, long destination_Id) {
		packRepository.saveRestaurant(restaurants, destination_Id);
	}

	
	// 식당, 명소, 관광지 이미지 등록
	@Override
	public void saveImg(List<String> imgNames, String type, long id) {
		packRepository.saveImg(imgNames, type, id);
	}
	
	// Attraction 등록
	@Override
	public void saveAttraction(Attraction attraction, long id) {
		packRepository.saveAttraction(attraction, id);
	}
	
	// sql Date 타입 변경
	@Override
	public LocalDate replaceSqlDate(String date) {
		date = date.substring(0, date.length() -1);
		date = date.replace(".", "-");
		
		LocalDate localDate = LocalDate.parse(date);
		return localDate;
	}
	
	// 두 날짜 차이 계산
	@Override
	public long dayDifference(Date startDate, Date endDate) {
		return ChronoUnit.DAYS.between(startDate.toLocalDate(), endDate.toLocalDate());
	}
	
	// Pack 등록
	@Override
	public long savePack(Pack pack) {
		return packRepository.savePack(pack);
	}
	
	// 호텔 모든 열 지역으로 검색
	@Override
	public List<HotelView> findByDestinationHotels(String destinationName) {
		return packRepository.findByDestinationHotels(destinationName);
	}
	
	// 레스토랑 모든 열 지역으로 검색
	@Override
	public List<RestaurantView> findByDestinationRestaurant(String destinationName) {
		return packRepository.findByDestinationRestaurant(destinationName);
	}
	@Override
	public Pack findPackById(long tripId) {
		return packRepository.findPackById(tripId);
	}
	
	// 관광지 모든 열 지역으로 검색
	@Override
	public List<AttractionView> findByDestinationAttraction(String destinationName, String type) {
		return packRepository.findByDestinationAttraction(destinationName, type);
	}
	
	// 호텔 상세일정 등록
	@Override
	public void saveScheduleHotel(int hotelIds, int dayNum, long packId) {
		packRepository.saveScheduleHotel(hotelIds, dayNum, packId);
	}
	
	// 호텔 each 테이블 등록
	@Override
	public void saveEachHotel(int hotelIds, int dayNum, long packId) {
		packRepository.saveEachHotel(hotelIds, dayNum, packId);
	}
	
	// 레스토랑 상세일정 등록
	@Override
	public void saveScheduleRestaurant(List<int[]> restaurantIds, int dayNum, long packId) {
		packRepository.saveScheduleRestaurant(restaurantIds, dayNum, packId);
	}
}
