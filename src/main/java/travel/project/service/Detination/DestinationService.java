package travel.project.service.Detination;

import travel.project.domain.Destination;

public interface DestinationService {

    Destination findDestByName(String destinationName);
}
