package travel.project.repository.pack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Repository;

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
import travel.project.domain.Schedule;
import travel.project.mapper.CustomerMapper;
import travel.project.mapper.PackMapper;
import travel.project.repository.customer.CustomerRepositoryImpl;

@Slf4j
@RequiredArgsConstructor
@Repository
public class PackRepositoryImpl implements PackRepository{
	
	private final PackMapper packMapper;
	
	// 호텔 등록 후 반환
	@Override
	public Hotels saveHotel(Hotels hotels) {
		packMapper.saveHotel(hotels);
		
		return packMapper.selectOne(hotels.getHotelName());
	}
	
	// 호텔 이미지 등록
	@Override
	public void saveHotelImg(List<String> imgNames, long id) {
		packMapper.saveHotelImg(imgNames, id);
	}
	
	// 호텔 편의시설 등록
	@Override
	public void saveHotelAmenities(List<String> amenities, long id) {
		packMapper.saveHotelAmenities(amenities, id);
	}
	
	// Destination 등록
	@Override
	public long saveDestination(Destination destination) {
		return packMapper.saveDestination(destination);
	}
	
	// Destination 리스트 반환
	@Override
	public List<Destination> findAllDestination() {
		return packMapper.findAllDestination();
	}
	
	// Restaurants 등록
	@Override
	public void saveRestaurant(Restaurants restaurants, long destination_Id) {
		packMapper.saveRestaurant(restaurants, destination_Id);
	}
	
	// 식당, 명소, 관광지 이미지 등록
	@Override
	public void saveImg(List<String> imgNames, String type, long id) {
		packMapper.saveImg(imgNames, type, id);
	}


	@Override
	public List<Pack> findAllPacks() {
		return packMapper.findAllPacks();
	}

	@Override
	public List<Pack> findPackList(String destination) {
		List<Pack> packList = new ArrayList<>();
		// PathVariable 이 All 이면 모든 리스트 보여줌
		if(destination.equals("all")){
			packList = packMapper.findAllPacks();
		}else{
			// PathVariable 이 특정 목적지면 해당 목적지의 리스트 보여줌
			packList = packMapper.findPacksByDestination(destination);
		}
		return packList;
	}
	
	// Attraction 등록
	@Override
	public void saveAttraction(Attraction attraction, long id) {
		packMapper.saveAttraction(attraction, id);
	}
	
	// Pack 등록 후 리턴
	@Override
	public long savePack(Pack pack) {
		packMapper.savePack(pack);
		return packMapper.selectLastPack();
	}
	
	// 호텔 모든 열 지역으로 검색
	@Override
	public List<HotelView> findByDestinationHotels(String destinationName) {
		return packMapper.findByDestinationHotels(destinationName);
	}
	
	// 레스토랑 모든 열 지역으로 검색
	@Override
	public List<RestaurantView> findByDestinationRestaurant(String destinationName) {
		return packMapper.findByDestinationRestaurant(destinationName);
	}

	@Override
	public Pack findPackById(long tripId) {
		return packMapper.findPackById(tripId);
	}
	
	// 관광지 모든 열 지역으로 검색
	@Override
	public List<AttractionView> findByDestinationAttraction(String destinationName, String type) {
		return packMapper.findByDestinationAttraction(destinationName, type);
	}
	
	// Schedule 등록
	@Override
	public void saveSchedule(long packId, long days, Map<String, String> params) {
		// 필터링된 파라미터 사용
	    Map<String, List<Integer>> filteredParams = params.entrySet().stream()
	        .filter(entry -> entry.getKey().matches("hotel\\[\\d+\\]|restaurant\\[\\d+\\]|tourist\\[\\d+\\]|activity\\[\\d+\\]"))
	        .collect(Collectors.toMap(
	            Map.Entry::getKey,
	            entry -> Arrays.stream(entry.getValue().split("\\s+"))
	                           .filter(s -> !s.isEmpty())
	                           .map(Integer::parseInt)
	                           .collect(Collectors.toList())
	     ));
	    
	    // dayCounts 맵을 받은 'days' 매개변수에 기반하여 동적으로 초기화
	    Map<Integer, Integer> dayCounts = IntStream.rangeClosed(1, (int)days)
	        .boxed()
	        .collect(Collectors.toMap(day -> day, day -> 0));

	    for (Map.Entry<String, List<Integer>> entry : filteredParams.entrySet()) {
	        String eventType = entry.getKey().split("\\[")[0];
	        int dayIndex = Integer.parseInt(entry.getKey().split("\\[")[1].replaceAll("\\D", ""));
	        List<Integer> eventIds = entry.getValue();

	        // 각 이벤트 ID에 대해 처리
	        for (Integer eventId : eventIds) {
	            dayCounts.put(dayIndex, dayCounts.get(dayIndex) + 1);
	            Schedule schedule = new Schedule();
	            schedule.setPackId(packId);
	            schedule.setDayNumber(dayIndex);
	            schedule.setScheduleType(eventType);
	            schedule.setEventId(eventId);
	            
	            packMapper.insertSchedule(schedule);
	        }
	    }
	}
}
