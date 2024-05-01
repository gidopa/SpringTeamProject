package travel.project.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurants {
    private long restaurantId;
    private long destinationId;
    private String name;
    private String cuisine;
    private String description;

}
