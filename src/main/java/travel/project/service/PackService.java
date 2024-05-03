package travel.project.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import travel.project.domain.Destination;
import travel.project.domain.Hotels;
import travel.project.domain.Pack;
import travel.project.domain.Restaurants;

public interface PackService {
	
	// 호텔 등록
	public Hotels saveHotel(Hotels hotels);
	
	// 호텔 이미지 등록
	public void saveHotelImg(List<String> imgNames, long id);
	
	// 호텔 편의시설 등록
	public void saveHotelAmenities(List<String> amenities, Long id);

	List<Pack> getPackageListByDestination(String destination);
	public void saveHotelAmenities(List<String> amenities, long id);
	
	// 호텔 이미지 업로드
	public List<String> uploadHotelImage(MultipartFile[] files, long id);
	
	// Destination 등록
	public long saveDestination(Destination destination);
	
	// Destination 리스트 반환
	public List<Destination> findAllDestination();
	
	// Restaurants 등록
	public void saveRestaurant(Restaurants restaurants, long destination_Id);

}
