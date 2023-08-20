package kuit.subway.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveLineReq {
    private String color;
    private Long distance;
    private String name;
    private Long downStationId;
    private Long upStationId;




}
