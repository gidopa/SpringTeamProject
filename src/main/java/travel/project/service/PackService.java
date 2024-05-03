package travel.project.service;

import java.util.List;

import travel.project.domain.Hotels;
import travel.project.domain.Pack;

public interface PackService {
	
	// 호텔 등록
	public Hotels saveHotel(Hotels hotels);
	
	// 호텔 이미지 등록
	public void saveHotelImg(List<String> imgNames, Long id);
	
	// 호텔 편의시설 등록
	public void saveHotelAmenities(List<String> amenities, Long id);

	List<Pack> getPackageListByDestination(String destination);
}
