package travel.project.repository.DestinationRepository;

import travel.project.domain.Attraction;
import travel.project.domain.Destination;

import java.util.List;

public interface DestinationRepository {
    Destination findDestByName(String destinationName);

    List<Attraction> findAttractionById(long destId);
}
