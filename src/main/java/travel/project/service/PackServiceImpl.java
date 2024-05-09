package travel.project.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import travel.project.domain.Destination;
import travel.project.domain.Hotels;
import travel.project.domain.Pack;
import travel.project.domain.Restaurants;
import travel.project.repository.pack.PackRepository;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PackServiceImpl implements PackService{

	private final PackRepository packRepository;
    private static String UPLOADED_FOLDER = "C://temp//hotel//";
    private int count = 0;


	// 호텔 등록 후 반환
	@Override
	public Hotels saveHotel(Hotels hotels) {
		return packRepository.saveHotel(hotels);
	}

	// 호텔 이미지 등록
	@Override
	public void saveHotelImg(List<String> imgNames, long id) {
		packRepository.saveHotelImg(imgNames, id);
	}


	// 호텔 편의시설 등록
	@Override
	public void saveHotelAmenities(List<String> amenities, long id) {
		packRepository.saveHotelAmenities(amenities, id);
	}

	@Override
	public List<Pack> getPackageListByDestination(String destination) {
		return packRepository.findPackList(destination);
	}




	// 호텔 이미지 업로드
	@Override
	public List<String> uploadHotelImage(MultipartFile[] files, long id) {
		List<String> imgNames = new ArrayList<>();
		count = (int)id;
		String hotelFolderPath = UPLOADED_FOLDER + count + "//";

		// 호텔별 폴더가 없을 경우 생성
	    Path hotelDirectory = Paths.get(hotelFolderPath);
	    if (!Files.exists(hotelDirectory)) {
	        try {
	            Files.createDirectories(hotelDirectory);
	        } catch (IOException e) {
	            log.error("Failed to create directory for hotel: " + hotelFolderPath, e);
	            return null;
	        }
	     }
	    for (MultipartFile file : files) {
	          if (file.isEmpty()) {
	              continue;
	          }

	          try {
	        	  imgNames.add(file.getOriginalFilename());
	              byte[] bytes = file.getBytes();
	              Path path = Paths.get(hotelFolderPath + file.getOriginalFilename());
	              Files.write(path, bytes);

	          } catch (IOException e) {
	              e.printStackTrace();
	          }
	       }

	    return imgNames;
	}

	// Destination 등록
	@Override
	public long saveDestination(Destination destination) {
		return packRepository.saveDestination(destination);
	}

	// Destination 리스트 반환
	@Override
	public List<Destination> findAllDestination() {
		return packRepository.findAllDestination();
	}

	// Restaurants 등록
	@Override
	public void saveRestaurant(Restaurants restaurants, long destination_Id) {
		packRepository.saveRestaurant(restaurants, destination_Id);
	}

	@Override
	public Pack findPackById(long packId) {
		return packRepository.findPackById(packId);
	}

	//패키지 조회
	@Override
	public List<Pack> reservationInquiry(String startDate,String endDate) {
		return packRepository.reservationInquiry(startDate,endDate);
	}
}
