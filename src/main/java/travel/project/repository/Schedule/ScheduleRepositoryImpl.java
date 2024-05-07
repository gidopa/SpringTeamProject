package travel.project.repository.Schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import travel.project.domain.Schedule;
import travel.project.mapper.ScheduleMapper;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final ScheduleMapper scheduleMapper;

    @Override
    public List<Schedule> findScheduleById(long tripId) {
        return scheduleMapper.findScheduleById(tripId);
    }
}
