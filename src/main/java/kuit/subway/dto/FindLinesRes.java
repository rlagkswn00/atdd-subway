package kuit.subway.dto;

import kuit.subway.domain.Line;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class FindLinesRes {
    private Long id;
    private String name;
    private String color;
    private List<FindStationsRes> stations;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static FindLinesRes of(Line line, List<FindStationsRes> stations){
        FindLinesRes findLinesRes = new FindLinesRes();
        findLinesRes.id = line.getId();
        findLinesRes.stations = stations;
        findLinesRes.color = line.getColor();
        findLinesRes.createdDate = line.getCreatedDate();
        findLinesRes.modifiedDate = line.getModifiedDate();

        return findLinesRes;
    }
}
