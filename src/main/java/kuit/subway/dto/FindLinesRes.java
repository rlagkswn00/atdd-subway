package kuit.subway.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindLinesRes {
    private Long id;
    private String name;
    private String color;
    private List<FindStationsRes> stations;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

}
