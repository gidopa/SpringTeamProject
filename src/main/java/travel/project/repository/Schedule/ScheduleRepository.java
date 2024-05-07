package travel.project.repository.Schedule;

import travel.project.domain.Schedule;

import java.util.List;

public interface ScheduleRepository {


    List<Schedule> findScheduleById(long tripId);
}
