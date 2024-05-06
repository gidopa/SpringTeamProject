package travel.project.repository.pack;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import travel.project.domain.Attraction;
import travel.project.domain.Destination;
import travel.project.domain.HotelView;
import travel.project.domain.Hotels;
import travel.project.domain.Pack;
import travel.project.domain.Restaurants;
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
	public Pack savePack(Pack pack) {
		long packId = packMapper.savePack(pack);
		return packMapper.findByIdPack(packId);
	}
	
	// 호텔 모든 열 지역으로 검색
	@Override
	public List<HotelView> findByDestinationHotels(String destinationName) {
		return packMapper.findByDestinationHotels(destinationName);
	}

}
