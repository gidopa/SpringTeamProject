package travel.project.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    private long scheduleId;
    private long tripId;
    private int dayNumber; // ex) 2일차 스케쥴, 3일차 스케쥴
    private String description;

}
