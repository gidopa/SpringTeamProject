package travel.project.service.ScheduleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import travel.project.domain.Schedule;
import travel.project.repository.Schedule.ScheduleRepository;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> findScheduleById(long tripId) {
        return scheduleRepository.findScheduleById(tripId);
    }
}
