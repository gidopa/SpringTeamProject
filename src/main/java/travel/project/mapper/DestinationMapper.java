package travel.project.mapper;

import org.apache.ibatis.annotations.Mapper;
import travel.project.domain.Destination;

@Mapper
public interface DestinationMapper {

    Destination findDestByName(String destinationName);
}
