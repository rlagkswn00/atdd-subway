package kuit.subway.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindStationsRes {
    private Long id;
    private String name;
}
