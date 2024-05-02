package travel.project.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import travel.project.domain.Hotels;

public interface PackService {
	
	// 호텔 등록
	public Hotels saveHotel(Hotels hotels);
	
	// 호텔 이미지 등록
	public void saveHotelImg(List<String> imgNames, long id);
	
	// 호텔 편의시설 등록
	public void saveHotelAmenities(List<String> amenities, long id);
	
	// 호텔 이미지 업로드
	public List<String> uploadHotelImage(MultipartFile[] files, long id);

}
