package kuit.subway.dto;

import kuit.subway.domain.Station;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FindStationsRes {
    private Long id;
    private String name;

    public static FindStationsRes from(Station station){
        FindStationsRes findStationsRes = new FindStationsRes();
        findStationsRes.id = station.getId();
        findStationsRes.name = station.getName();

        return findStationsRes;
    }
}
