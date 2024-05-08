package travel.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import travel.project.domain.*;


@Mapper
public interface PackMapper {
	
	// 호텔 등록
    void saveHotel(Hotels hotels);
    
    // 호텔 이름으로 찾기
    Hotels selectOne(String hotelName);
    
    // 호텔 이미지 등록
    void saveHotelImg(@Param("imgNames") List<String> imgNames, @Param("id") long id);
    
    // 호텔 편의시설 등록
    void saveHotelAmenities(@Param("amenities") List<String> amenities,@Param("id") long id);
    
    List<Pack> findAllPacks();

    List<Pack> findPacksByDestination(String destination);

    
    // Destination 등록
    long saveDestination(Destination destination); 
    
    // Destination 리스트 반환
    List<Destination> findAllDestination();
    
    // restaurant 등록
    void saveRestaurant(@Param("restaurants") Restaurants restaurants, @Param("destination_Id") long destination_Id);
    
    // 식당, 명소, 관광지 이미지 등록
    void saveImg(@Param("imgNames") List<String> imgNames, @Param("type")String type, @Param("id")long id);
    
    // Attraction 등록
    void saveAttraction(@Param("attraction") Attraction attraction, @Param("id") long id);
    
    // Pack 등록
    void savePack(Pack pack);
    
    // Pack 인덱스 반환
    long selectLastPack();
    
    // Pack id 조회
    Pack findByIdPack(long packId);
    
	// 호텔 모든 열 지역으로 검색
    List<HotelView> findByDestinationHotels(String destinationName);
    
	// 레스토랑 모든 열 지역으로 검색
    List<RestaurantView> findByDestinationRestaurant(String destinationName);

    Pack findPackById(long tripId);

    // 관광지 모든 열 지역으로 검색
    List<AttractionView> findByDestinationAttraction(@Param("destinationName")String destinationName, @Param("type")String type);
    
    // Schedule 등록 
    void insertSchedule(Schedule schedule);

}
