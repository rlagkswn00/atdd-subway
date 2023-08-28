package kuit.subway.dto;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SaveSectionReq {
    private Long upStationId;
    private Long downStationId;
    private Long lineId;
}
