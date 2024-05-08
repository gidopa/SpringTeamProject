package travel.project.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import travel.project.domain.Attraction;
import travel.project.domain.AttractionView;
import travel.project.domain.Destination;
import travel.project.domain.HotelView;
import travel.project.domain.Hotels;
import travel.project.domain.Pack;
import travel.project.domain.RestaurantView;
import travel.project.domain.Restaurants;

public interface PackService {
	
	// 호텔 등록
	public Hotels saveHotel(Hotels hotels);
	
	// 호텔 이미지 등록
	public void saveHotelImg(List<String> imgNames, long id);
	
	// 호텔 편의시설 등록
	public void saveHotelAmenities(List<String> amenities, long id);

	List<Pack> getPackageListByDestination(String destination);

	
	// 이미지 업로드
	public List<String> uploadImage(MultipartFile[] files, long id, String category);
	
	// Destination 등록
	public long saveDestination(Destination destination);
	
	// Destination 리스트 반환
	public List<Destination> findAllDestination();
	
	// Restaurants 등록
	public void saveRestaurant(Restaurants restaurants, long destination_Id);
	
	// 식당, 명소, 관광지 이미지 등록
	public void saveImg(List<String> imgNames, String type, long id);
	
	// Attraction 등록
	public void saveAttraction(Attraction attraction, long id);
	
	// sql Date 타입 변경
	public LocalDate replaceSqlDate(String date);
	
	// 두 날짜 차이 계산
	public long dayDifference(Date startDate, Date endDate);
	
	// Pack 등록
	public long savePack(Pack pack);

	// 호텔 모든 열 지역으로 검색
	public List<HotelView> findByDestinationHotels(String destinationName);
	
	// 레스토랑 모든 열 지역으로 검색
	public List<RestaurantView> findByDestinationRestaurant(String destinationName);

	Pack findPackById(long tripId);
	
	// 관광지 모든 열 지역으로 검색
	public List<AttractionView> findByDestinationAttraction(String destinationName, String type);
	
	// 호텔 상세일정 등록
	public void saveScheduleHotel(int hotelIds, int dayNum, long packId);
	
	// 호텔 each 테이블 등록
	public void saveEachHotel(int hotelIds, int dayNum, long packId);
	
	// 레스토랑 상세일정 등록
	public void saveScheduleRestaurant(List<int[]> restaurantIds, int dayNum, long packId);
	
}
