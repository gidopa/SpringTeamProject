package travel.project.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Trips {
    private long tripId;
    private String destination;
    private String tripType;
    private Date startDate; // startDate와 endDate는 트리거로 insert, update시 검증
    private Date endDate;
    private int price;
}
