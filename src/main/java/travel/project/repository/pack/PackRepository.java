package travel.project.repository.pack;

import java.util.List;

import travel.project.domain.Customer;
import travel.project.domain.Hotels;

public interface PackRepository {
	
	// 호텔 등록 후 반환
    Hotels saveHotel(Hotels hotels);
    
    // 호텔 이미지 등록
    void saveHotelImg(List<String> imgNames, Long id);
    
    // 호텔 편의시설 등록
    void saveHotelAmenities(List<String> amenities, Long id);
    
}
