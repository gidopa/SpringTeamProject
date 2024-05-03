package travel.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import travel.project.domain.Hotels;
import travel.project.domain.Pack;
import travel.project.mapper.PackMapper;
import travel.project.repository.pack.PackRepository;
import travel.project.repository.pack.PackRepositoryImpl;

@Service
@Slf4j
@RequiredArgsConstructor
public class PackServiceImpl implements PackService{
	
	private final PackRepository packRepository;
	
	// 호텔 등록 후 반환
	@Override
	public Hotels saveHotel(Hotels hotels) {
		return packRepository.saveHotel(hotels);
	}
	
	// 호텔 이미지 등록
	@Override
	public void saveHotelImg(List<String> imgNames, Long id) {
		packRepository.saveHotelImg(imgNames, id);
	}
	
	// 호텔 편의시설 등록
	@Override
	public void saveHotelAmenities(List<String> amenities, Long id) {
		packRepository.saveHotelAmenities(amenities, id);
	}

	@Override
	public List<Pack> getPackageListByDestination(String destination) {
		return packRepository.findPackList(destination);
	}



}
