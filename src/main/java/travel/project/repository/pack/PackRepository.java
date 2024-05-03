package travel.project.repository.pack;

import java.util.List;

import travel.project.domain.Customer;
import travel.project.domain.Destination;
import travel.project.domain.Hotels;
import travel.project.domain.Pack;
import travel.project.domain.Restaurants;

public interface PackRepository {
	
	// 호텔 등록 후 반환
    Hotels saveHotel(Hotels hotels);
    
    // 호텔 이미지 등록
    void saveHotelImg(List<String> imgNames, Long id);
    
    // 호텔 편의시설 등록
    void saveHotelAmenities(List<String> amenities, Long id);

    List<Pack> findAllPacks();

    List<Pack> findPackList(String destination);
    
    // Destination 등록
    long saveDestination(Destination destination);
    
    // Destination 리스트 반환
    List<Destination> findAllDestination();
    
    // Restaurants 등록
    void saveRestaurant(Restaurants restaurants, long destination_Id);
}
