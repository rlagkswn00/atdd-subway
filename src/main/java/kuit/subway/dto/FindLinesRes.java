package kuit.subway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
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
