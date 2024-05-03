package travel.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import travel.project.domain.Customer;
import travel.project.domain.Destination;
import travel.project.domain.Hotels;
import travel.project.domain.Restaurants;

@Mapper
public interface PackMapper {
	
	// 호텔 등록
    void saveHotel(Hotels hotels);
    
    // 호텔 이름으로 찾기
    Hotels selectOne(String hotelName);
    
    // 호텔 이미지 등록
    void saveHotelImg(@Param("imgNames") List<String> imgNames, @Param("id") Long id);
    
    // 호텔 편의시설 등록
    void saveHotelAmenities(@Param("amenities") List<String> amenities,@Param("id") Long id);
    
    // Destination 등록
    long saveDestination(Destination destination);
    
    // Destination 리스트 반환
    List<Destination> findAllDestination();
    
    // Restaurants 등록
    void saveRestaurant(Restaurants restaurants, long destination_Id);
}
