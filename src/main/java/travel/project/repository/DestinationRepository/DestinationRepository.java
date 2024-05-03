package travel.project.repository.DestinationRepository;

import travel.project.domain.Destination;

public interface DestinationRepository {
    Destination findDestByName(String destinationName);
}
