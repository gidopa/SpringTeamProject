package travel.project.service.ScheduleService;

import travel.project.domain.Schedule;

import java.util.List;

public interface ScheduleService {

    List<Schedule> findScheduleById(long tripId);

}
