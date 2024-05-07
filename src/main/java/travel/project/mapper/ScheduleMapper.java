package travel.project.mapper;

import org.apache.ibatis.annotations.Mapper;
import travel.project.domain.Schedule;

import java.util.List;

@Mapper
public interface ScheduleMapper {
    List<Schedule> findScheduleById(long tripId);
}
