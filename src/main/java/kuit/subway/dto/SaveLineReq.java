package kuit.subway.dto;


import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SaveLineReq {
    private String color;
    private Long distance;
    private String name;
    private Long upStationId;
    private Long downStationId;





}
