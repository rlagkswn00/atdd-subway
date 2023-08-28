package kuit.subway.dto;


import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLineReq {
    private String color;
    private String name;
}
