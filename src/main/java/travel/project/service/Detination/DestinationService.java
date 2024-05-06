package travel.project.service.Detination;

import travel.project.domain.Attraction;
import travel.project.domain.Destination;

import java.util.List;

public interface DestinationService {

    Destination findDestByName(String destinationName);

    List<Attraction> findAttractionById(long destId);
}
