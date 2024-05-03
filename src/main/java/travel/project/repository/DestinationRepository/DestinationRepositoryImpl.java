package travel.project.repository.DestinationRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import travel.project.domain.Destination;
import travel.project.mapper.DestinationMapper;

@RequiredArgsConstructor
@Repository
public class DestinationRepositoryImpl implements DestinationRepository{

    private final DestinationMapper destinationMapper;


    @Override
    public Destination findDestByName(String destinationName) {
        return destinationMapper.findDestByName(destinationName);
    }
}
