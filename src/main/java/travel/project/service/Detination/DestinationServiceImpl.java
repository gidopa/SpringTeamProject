package travel.project.service.Detination;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import travel.project.domain.Destination;
import travel.project.repository.DestinationRepository.DestinationRepository;

@RequiredArgsConstructor
@Service
public class DestinationServiceImpl implements DestinationService{

    private final DestinationRepository destinationRepository;


    @Override
    public Destination findDestByName(String destinationName) {
        return destinationRepository.findDestByName(destinationName);
    }
}
