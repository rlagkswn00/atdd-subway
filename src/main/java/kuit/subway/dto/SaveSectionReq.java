package kuit.subway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveSectionReq {
    private Long upStationId;
    private Long downStationId;
    private Long lineId;
}
